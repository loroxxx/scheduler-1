package com.jinhui.scheduler.biz.imiqian.service.impl;

import com.jinhui.scheduler.biz.core.common.HolidayService;
import com.jinhui.scheduler.biz.core.common.IIdService;
import com.jinhui.scheduler.biz.imiqian.common.AmqClearStepConst;
import com.jinhui.scheduler.biz.imiqian.common.AmqConst;
import com.jinhui.scheduler.biz.imiqian.service.BenifitService;
import com.jinhui.scheduler.biz.imiqian.service.ChnProductService;
import com.jinhui.scheduler.biz.imiqian.service.StatisticsPosIncomeService;
import com.jinhui.scheduler.biz.imiqian.utils.DateUtils;
import com.jinhui.scheduler.data.core.mapper.imiqian.*;
import com.jinhui.scheduler.domain.common.ChannelCode;
import com.jinhui.scheduler.domain.imiqian.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统计持仓服务层实现
 *    T日日终持仓金额=T日日初持仓金额+T日日初持仓收益+T日交易金额
 *    T日日初持仓收益=万元收益*T日日初持仓金额/10000
 *    万元收益= ((1+T日收益率）^(1/365)-1)*10000
 *   （注：T日日初持仓金额是指在T日日终清算前的客户持仓金额）
 * create by wsc  2017-05-24 15:55
 *
 **/
@Service("statisticsPosIncomeService")
public class StatisticsPosIncomeServiceImpl implements StatisticsPosIncomeService {
    private final static Logger logger = LoggerFactory.getLogger(StatisticsPosIncomeServiceImpl.class);
    @Autowired
    private InvestorTransMapper investorTransMapper;
    @Autowired
    private InvestorPositionMapper investorPositionMapper;
    @Autowired
    private PositionHistoryMapper positionHistoryMapper;
    @Autowired
    private InvestorIncomeMapper investorIncomeMapper;
    @Autowired
    private IIdService iIdService;
    @Autowired
    private ProductHistoryRateMapper productHistoryRateMapper;
    @Autowired
    private BusinessGatherMapper businessGatherMapper;
    @Autowired
    private ClearResultMapper clearResultMapper;
    @Autowired
    private BatchStateMapper batchStateMapper;
    @Autowired
    private ChnProductMapper chnProductMapper;
    @Autowired
    private HolidayService holidayService;
    @Autowired
    private HolidayMapper holidayMapper;
    @Autowired
    private BatchStateCurrentMapper batchStateCurrentMapper;
    @Autowired
    private ChnProductService chnProductService;
    @Autowired
    private ChnOpenMapper chnOpenMapper;
    @Autowired
    private BenifitService benifitService;


    Map<String, ChnProduct> productMaps = new HashMap<>();
    Map<String, ClearResult> clearResultMap = new HashMap<>();

    //批次号
    int batchCode;
    //汇总日期
    String gatherDate = "";

    //产品收益率 需要查询
    BigDecimal incomeRate = null;
    //万份收益 需要查询
    BigDecimal tenThounsand = null;
    //名义上的万份收益 需要查询
    BigDecimal nameTenThounsand = null;
    //客户当日申购上限
    BigDecimal subsToplimit = BigDecimal.ZERO;
    //客户当日赎回上限
    BigDecimal redeemToplimit = BigDecimal.ZERO;

    //当前批次
    BatchStateCurrent batchStateCurrent = null;

    private void initData(int param1, String param2) {
        //初始化汇总日期和批次号
        batchCode = param1;
        gatherDate = param2;

        //初始化当前批次
        batchStateCurrent = batchStateCurrentMapper.findCurrentBatch();

        //初始化产品信息
        List<ChnProduct> chnProductList = chnProductMapper.findAllProduct();
        for (ChnProduct chnPro : chnProductList) {
            productMaps.put(chnPro.getProductNo(), chnPro);
        }

        //初始化已清算的步骤
        ClearResult clearResult = new ClearResult();
        clearResult.setBatchCode(batchCode);
        List<ClearResult> clearResultList = clearResultMapper.findList(clearResult);
        for (ClearResult clearResultIn : clearResultList) {
            clearResultMap.put(clearResultIn.getStepCode(), clearResultIn);
        }

    }

    /**
     * 将T日交易数据备份至临时表 --> 备份T-1日持仓 --> 生成T日收益(包含生成历史收益率)  --> 统计持仓
     *
     * @param batchCode 批次代码
     * @param batchDate 批次日期
     */
    public void statisticsPosition(int batchCode, String batchDate) {
        //下一天是否为工作日
        Boolean IS_WORKDAY = holidayService.isWorkDay(DateUtils.getNextDate(batchDate));
        //下一天为工作日
        if (IS_WORKDAY) {
            clear(batchCode, batchDate, true);
        } else {  //下一天为非工作日
            //获取下一个工作日
            String nextWorkDay = holidayService.getNextWorkDay(batchDate);
            List<Holiday> holidayList = holidayMapper.findHolidayList(batchDate, nextWorkDay);
            for (Holiday holiday : holidayList) {
                BatchState batchState = batchStateMapper.findBatchStateByDate(holiday.getDate());
                if (batchState != null && batchState.getBatchCode() != null && batchState.getBatchCode() != 0) {
                    clear(batchState.getBatchCode(), holiday.getDate(), false);
                }

            }
        }
    }

    /*
     * 清算
     * @param batchCode  批次代码
     * @param batchDate  批次日期
     *
     */
    private void clear(int batchCode, String batchDate, Boolean isWorkDay) {
        //初始化数据
        initData(batchCode, batchDate);

        //备份产品历史利率
        backupChnProductHistory();

        //将T日交易数据备份至临时表
        transferDataToTemp();


        //备份T-1日的持仓数据
        backupToPositionHistory();

        //生成T日客户当前持有金额的收益
        batchGenerateIncome(isWorkDay);

        //统计持仓
        caculateInvestorPosition(isWorkDay);

        //业务数据汇总
        gatherBusiness();

        //更新批次日期和批次
        upgrateBatchCode();
    }

    private void backupChnProductHistory() {
        chnProductService.backupChnProductHistory();
    }

    //将T日交易数据移到临时表
    private void transferDataToTemp() {
        if (clearResultMap.get(AmqClearStepConst.ClearSteps.STEP_3.getClearCode()) == null) {
            //清空交易临时表
            investorTransMapper.deleteTransTemp();

            //备份T日交易数据至临时表
            InvestorTrans investorTrans = new InvestorTrans();
            investorTrans.setBatchCode(batchCode);
            investorTransMapper.transferDataToTemp(investorTrans);
            //throw new NullPointerException();

            //记录完成状态
            clearResultMapper.save(generateClearResult(AmqClearStepConst.STEPS.STEP_3.toString(), AmqClearStepConst.ClearSteps.STEP_3.getClearCode(), AmqClearStepConst.ClearSteps.STEP_3.getClearDesc()));
        } else {
            logger.info("[批次号=" + batchCode + "]的数据已执行过流程[将T日交易数据移到临时表]");
        }
    }


    //备份T-1日的持仓数据
    private void backupToPositionHistory() {
        if (clearResultMap.get(AmqClearStepConst.ClearSteps.STEP_4.getClearCode()) == null) {
            InvestorPosition inPos = new InvestorPosition();
            inPos.setBatchCode(batchCode - 1);
            List<InvestorPosition> posList = investorPositionMapper.findList(inPos);
            List<PositionHistory> posHisList = new ArrayList<PositionHistory>();
            for (InvestorPosition investorPosition : posList) {
                PositionHistory posHis = new PositionHistory();
                BeanUtils.copyProperties(investorPosition, posHis);
                if(!"0000".equals(investorPosition.getChnCode())){
                    posHis.setSerialNumber(iIdService.generateSerialNumber(ChannelCode.codeOf(investorPosition.getChnCode())));
                }else{
                    posHis.setSerialNumber(iIdService.generateSerialNumber(ChannelCode.codeOf(ChannelCode.XWBank.getCode())));
                }
                posHis.setCreateTime(DateUtils.getCurrentDatetime());
                posHis.setUpdateTime(DateUtils.getCurrentDatetime());
                posHisList.add(posHis);
            }
            //批量备份T-1日的历史持仓数据
            if (!posHisList.isEmpty() && posHisList.size() != 0) {
                positionHistoryMapper.bakcupToPosHis(posHisList);
            }

            //记录完成状态
            clearResultMapper.save(generateClearResult(AmqClearStepConst.STEPS.STEP_4.toString(), AmqClearStepConst.ClearSteps.STEP_4.getClearCode(), AmqClearStepConst.ClearSteps.STEP_4.getClearDesc()));
        } else {
            logger.info("[批次号=" + batchCode + "]的数据已执行过流程[备份T-1日的持仓数据]");
        }
    }

    //生成T日客户当前持有金额的收益
    private void batchGenerateIncome(Boolean isWorkDay) {
        if (clearResultMap.get(AmqClearStepConst.ClearSteps.STEP_5.getClearCode()) == null) {
            InvestorPosition inPos = new InvestorPosition();
            inPos.setBatchCode(batchCode - 1);
            List<InvestorPosition> posList = investorPositionMapper.findNormalListByChoice(inPos);
            List<InvestorIncome> incomeList = new ArrayList<InvestorIncome>();
            for (InvestorPosition investorPosition : posList) {
                //获取产品收益率和万份收益
                incomeRate = new BigDecimal(productMaps.get(investorPosition.getProductNo()).getIncomeRate());
                tenThounsand = new BigDecimal(productMaps.get(investorPosition.getProductNo()).getPercentIncomeRate());   //实际万份收益

                InvestorIncome investIncome = new InvestorIncome();
                BeanUtils.copyProperties(investorPosition, investIncome);
                investIncome.setBatchCode(batchCode);
                investIncome.setApplyDate(batchStateCurrent.getDate());
                investIncome.setGatherDate(gatherDate);
                investIncome.setSerialNumber(iIdService.generateSerialNumber(ChannelCode.codeOf(investorPosition.getChnCode())));
                if (isWorkDay) {
                    investIncome.setIncomeAmount(generateIncome(new BigDecimal(investorPosition.getTotalPostionAmount()), tenThounsand));   //收益金额
                } else {
                    //查询T-1日持仓
                    PositionHistory posHis = new PositionHistory();
                    posHis.setChnCode(investorPosition.getChnCode());
                    posHis.setGatherDate(holidayService.getPreWorkDay(batchStateCurrent.getDate()));
                    posHis.setProductNo(investorPosition.getProductNo());
                    posHis.setInvestorId(investorPosition.getInvestorId());
                    PositionHistory positionHistory = positionHistoryMapper.findPosHistory(posHis);
                    //根据T-1日持仓算收益
                    if (positionHistory != null) {
                        investIncome.setTotalPostionAmount(positionHistory.getTotalPostionAmount());
                        investIncome.setIncomeAmount(generateIncome(new BigDecimal(positionHistory.getTotalPostionAmount()), tenThounsand));   //收益金额
                    } else {
                        investIncome.setIncomeAmount(new Double("0.00"));   //收益金额
                    }


                }
                investIncome.setIncomeRate(incomeRate.doubleValue());   //收益率
                investIncome.setPercentIncome(tenThounsand.doubleValue());  //万份收益
                investIncome.setCreateTime(DateUtils.getCurrentDatetime());
                investIncome.setUpdateTime(DateUtils.getCurrentDatetime());
                investIncome.setIncomeType("0");
                incomeList.add(investIncome);
            }

            //记录差额收益
            incomeList = recordDiffIncome(posList, incomeList, isWorkDay);

            if (!incomeList.isEmpty() && incomeList.size() != 0) {
                investorIncomeMapper.batchGenerateIncome(incomeList);
            }

            //记录完成状态
            clearResultMapper.save(generateClearResult(AmqClearStepConst.STEPS.STEP_5.toString(), AmqClearStepConst.ClearSteps.STEP_5.getClearCode(), AmqClearStepConst.ClearSteps.STEP_5.getClearDesc()));
        } else {
            logger.info("[批次号=" + batchCode + "]的数据已执行过流程[生成T日客户当前持有金额的收益]");
        }

    }

    //统计持仓
    private void caculateInvestorPosition(Boolean isWorkDay) {
        if (clearResultMap.get(AmqClearStepConst.ClearSteps.STEP_6.getClearCode()) == null) {
            List<InvestorPosition> addPositionList = new ArrayList<>();
            List<InvestorPosition> updatePositionList = new ArrayList<>();

            InvestorTrans investorTrans = new InvestorTrans();
            investorTrans.setBatchCode(batchCode);
            //统计T日的交易
            List<InvestorTrans> transList = investorTransMapper.findConfrimedList(investorTrans);
            Map<String, Object> transMap = new HashMap<String, Object>();
            for (InvestorTrans trans : transList) {
                transMap.put(trans.getChnCode() + trans.getProductNo() + trans.getInvestorId(), trans);
            }

            //查询客户的日初持仓
            List<InvestorPosition> posList = investorPositionMapper.findNormalPositionList();

            //记录差额持仓: 持仓金额为日初持仓总额，总收益为名义万份收益和实际万份收益计算的收益差额累计值
            recordDiffPosition(posList, isWorkDay);

            //已有持仓记录的客户更新持仓金额，总收益，总申购金额，总赎回金额等
            for (InvestorPosition pos : posList) {
                //万份收益
                tenThounsand = new BigDecimal(productMaps.get(pos.getProductNo()).getPercentIncomeRate());
                //客户当日申购上限
                subsToplimit = new BigDecimal(productMaps.get(pos.getProductNo()).getSubsToplimit());
                //客户当日赎回上限
                redeemToplimit = new BigDecimal(productMaps.get(pos.getProductNo()).getRedeemToplimit());

                Double income = new Double("0.00");
                //下一天为工作日
                if (isWorkDay) {
                    income = generateIncome(new BigDecimal(pos.getTotalPostionAmount()), tenThounsand);  // 计算收益
                } else {  //下一天为非工作日
                    //查询T-1日持仓
                    PositionHistory posHis = new PositionHistory();
                    posHis.setChnCode(pos.getChnCode());
                    posHis.setGatherDate(holidayService.getPreWorkDay(batchStateCurrent.getDate()));
                    posHis.setProductNo(pos.getProductNo());
                    posHis.setInvestorId(pos.getInvestorId());
                    PositionHistory positionHistory = positionHistoryMapper.findPosHistory(posHis);
                    //根据T-1日持仓算收益
                    if (positionHistory != null) {
                        income = generateIncome(new BigDecimal(positionHistory.getTotalPostionAmount()), tenThounsand);  // 计算收益
                    }
                }
                pos.setTotalIncome(pos.getTotalIncome() + income);
                InvestorTrans transIn = new InvestorTrans();
                transIn.setBatchCode(batchStateCurrent.getBatchCode());
                transIn.setProductNo(pos.getProductNo());
                transIn.setInvestorId(pos.getInvestorId());
                List<InvestorTrans> transTempList = investorTransMapper.findTransTempList(transIn);

                BigDecimal totalSubsAmount = BigDecimal.ZERO;
                BigDecimal totalRedeemAmount = BigDecimal.ZERO;
                BigDecimal leftPos = BigDecimal.ZERO;
                if (transMap.containsKey(pos.getChnCode() + pos.getProductNo() + pos.getInvestorId())) {
                    for (InvestorTrans transTemp : transTempList) {
                        InvestorTrans totalTrans = (InvestorTrans) transMap.get(pos.getChnCode() + pos.getProductNo() + pos.getInvestorId());
                        if (transTemp.getTransType().equals(AmqConst.BUSINESS_CODE_PURCHASE_APPLY)) {
                            totalSubsAmount = totalSubsAmount.add(new BigDecimal(transTemp.getTransAmount()));
                            if (new BigDecimal(totalTrans.getTotalSubsAmount()).compareTo(subsToplimit) <= 0) {
                                pos.setTotalSubsAmount(pos.getTotalSubsAmount() + transTemp.getTransAmount());
                                pos.setTotalPostionAmount(pos.getTotalPostionAmount() + transTemp.getTransAmount());
                            } else {
                                if (totalSubsAmount.compareTo(subsToplimit) <= 0) {
                                    pos.setTotalSubsAmount(pos.getTotalSubsAmount() + transTemp.getTransAmount());
                                    pos.setTotalPostionAmount(pos.getTotalPostionAmount() + transTemp.getTransAmount());
                                } else {
                                    totalSubsAmount = totalSubsAmount.subtract(new BigDecimal(transTemp.getTransAmount()));
                                    InvestorTrans transExcess = new InvestorTrans();
                                    transExcess.setId(transTemp.getId());
                                    transExcess.setIsExcess(String.valueOf(AmqConst.IsExcess.ONE.ordinal()));
                                    transExcess.setTransState("0010");   //失败
                                    investorTransMapper.updateTrans(transExcess);
                                    investorTransMapper.updateTransTemp(transExcess);
                                }
                            }
                        } else if (transTemp.getTransType().equals(AmqConst.BUSINESS_CODE_REDEEM_APPLY)) {
                            String isExcess = "";
                            BigDecimal nowTransAmount = new BigDecimal(transTemp.getTransAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);
                            totalRedeemAmount = totalRedeemAmount.add(nowTransAmount);
                            leftPos = new BigDecimal(pos.getTotalPostionAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);
                            logger.info(transTemp.getInvestorId() + ":   交易金额=["+ nowTransAmount + "]   剩余持仓=["+ leftPos +"]");
                            logger.info("对比结果： "+ nowTransAmount.compareTo(leftPos) + "  " + nowTransAmount.compareTo(redeemToplimit) + "  " +totalRedeemAmount.compareTo(redeemToplimit));
                            if ((nowTransAmount.compareTo(leftPos) <= 0) && (nowTransAmount.compareTo(redeemToplimit) <= 0) && (totalRedeemAmount.compareTo(redeemToplimit) <= 0)) {
                                pos.setTotalRedemedAmount(pos.getTotalRedemedAmount() + nowTransAmount.doubleValue());
                                pos.setTotalPostionAmount(leftPos.doubleValue() - nowTransAmount.doubleValue());
                            }else if(nowTransAmount.compareTo(leftPos) > 0 && nowTransAmount.compareTo(redeemToplimit) <= 0){
                                totalRedeemAmount = totalRedeemAmount.subtract(nowTransAmount);
                                isExcess = String.valueOf(AmqConst.IsExcess.THREE.ordinal());   //3客户当日赎回金额超过持仓金额
                                InvestorTrans transExcess = new InvestorTrans();
                                transExcess.setId(transTemp.getId());
                                transExcess.setIsExcess(isExcess);
                                transExcess.setTransState("0010");   //失败
                                investorTransMapper.updateTransTemp(transExcess);
                                investorTransMapper.updateTrans(transExcess);
                            }else if(nowTransAmount.compareTo(leftPos) <= 0 && (nowTransAmount.compareTo(redeemToplimit) > 0 || totalRedeemAmount.compareTo(redeemToplimit) > 0)){
                                totalRedeemAmount = totalRedeemAmount.subtract(nowTransAmount);
                                isExcess = String.valueOf(AmqConst.IsExcess.TWO.ordinal());   //2客户当日赎回金额超过最大赎回金额
                                InvestorTrans transExcess = new InvestorTrans();
                                transExcess.setId(transTemp.getId());
                                transExcess.setIsExcess(isExcess);
                                transExcess.setTransState("0010");   //失败
                                investorTransMapper.updateTrans(transExcess);
                                investorTransMapper.updateTransTemp(transExcess);
                            }else {
                                    totalRedeemAmount = totalRedeemAmount.subtract(nowTransAmount);
                                    isExcess = String.valueOf(AmqConst.IsExcess.THREE.ordinal());   //3客户当日赎回金额超过持仓金额
                                    InvestorTrans transExcess = new InvestorTrans();
                                    transExcess.setId(transTemp.getId());
                                    transExcess.setIsExcess(isExcess);
                                    transExcess.setTransState("0010");   //失败
                                    investorTransMapper.updateTrans(transExcess);
                                    investorTransMapper.updateTransTemp(transExcess);
                            }
                        }
                    }
                    transMap.remove(pos.getChnCode() + pos.getProductNo() + pos.getInvestorId());
                }

                //全额赎回处理  在交易表中新增一条赎回记录，赎回金额为最后一天的收益
                pos = handingRedeemAll(pos,income);
                pos.setBatchCode(batchCode);
                pos.setGatherDate(gatherDate);
                updatePositionList.add(pos);
            }

            //没有持仓记录的首次申购的用户新增持仓记录
            for (Map.Entry<String, Object> tranEntry : transMap.entrySet()) {
                InvestorPosition addPos = new InvestorPosition();
                InvestorTrans newTrans = (InvestorTrans) tranEntry.getValue();
                //客户当日申购上限
                subsToplimit = new BigDecimal(productMaps.get(newTrans.getProductNo()).getSubsToplimit());
                BeanUtils.copyProperties(newTrans, addPos);
                addPos.setTotalPostionAmount(BigDecimal.ZERO.doubleValue());
                InvestorTrans transIn = new InvestorTrans();
                transIn.setBatchCode(batchStateCurrent.getBatchCode());
                transIn.setProductNo(newTrans.getProductNo());
                transIn.setInvestorId(newTrans.getInvestorId());
                List<InvestorTrans> transTempList = investorTransMapper.findTransTempList(transIn);
                BigDecimal totalSubsAmount = BigDecimal.ZERO;
                for (InvestorTrans transTemp : transTempList) {
                    if (transTemp.getTransType().equals(AmqConst.BUSINESS_CODE_PURCHASE_APPLY)) {
                        totalSubsAmount = totalSubsAmount.add(new BigDecimal(transTemp.getTransAmount()));
                        if (new BigDecimal(newTrans.getTotalSubsAmount()).compareTo(subsToplimit) <= 0) {
                            addPos.setTotalSubsAmount(totalSubsAmount.doubleValue());
                            addPos.setTotalPostionAmount(totalSubsAmount.doubleValue());
                        } else {
                            if (totalSubsAmount.compareTo(subsToplimit) <= 0) {
                                addPos.setTotalSubsAmount(totalSubsAmount.doubleValue());
                                addPos.setTotalPostionAmount(totalSubsAmount.doubleValue());
                            } else {
                                totalSubsAmount = totalSubsAmount.subtract(new BigDecimal(transTemp.getTransAmount()));
                                InvestorTrans transExcess = new InvestorTrans();
                                transExcess.setId(transTemp.getId());
                                transExcess.setIsExcess(String.valueOf(AmqConst.IsExcess.ONE.ordinal()));
                                transExcess.setTransState("0010");   //失败
                                investorTransMapper.updateTransTemp(transExcess);
                                investorTransMapper.updateTrans(transExcess);
                            }
                        }
                    } else if (transTemp.getTransType().equals(AmqConst.BUSINESS_CODE_REDEEM_APPLY)) {
                        String isExcess = String.valueOf(AmqConst.IsExcess.THREE.ordinal());
                        InvestorTrans transExcess = new InvestorTrans();
                        transExcess.setId(transTemp.getId());
                        transExcess.setIsExcess(isExcess);
                        transExcess.setTransState("0010");   //失败
                        investorTransMapper.updateTrans(transExcess);
                        investorTransMapper.updateTransTemp(transExcess);
                    }

                }
                addPos.setBatchCode(batchCode);
                addPos.setGatherDate(gatherDate);
                addPos.setTotalIncome(BigDecimal.ZERO.doubleValue());
                addPos.setPositionType("0");
                if (addPos.getProductVol() == 1) {
                    addPos.setTotalPostionVol(addPos.getTotalPostionAmount());
                } else {
                    addPos.setTotalPostionVol(new BigDecimal(String.valueOf(addPos.getTotalPostionAmount()))
                            .divide(new BigDecimal(String.valueOf(addPos.getProductVol())), 2, RoundingMode.DOWN).doubleValue());
                }
                addPositionList.add(addPos);
            }

            if (!addPositionList.isEmpty() && addPositionList.size() != 0) {
                investorPositionMapper.batchSave(addPositionList);
            }
            if (!updatePositionList.isEmpty() && updatePositionList.size() != 0) {
                investorPositionMapper.batchUpdate(updatePositionList);
            }


            //记录完成状态
            clearResultMapper.save(generateClearResult(AmqClearStepConst.STEPS.STEP_6.toString(), AmqClearStepConst.ClearSteps.STEP_6.getClearCode(), AmqClearStepConst.ClearSteps.STEP_6.getClearDesc()));
        } else {
            logger.info("[批次号=" + batchCode + "]的数据已执行过流程[统计持仓]");
        }
    }

    //全额赎回处理
    private InvestorPosition handingRedeemAll(InvestorPosition pos,Double income){
        //如果最后计算的持仓为0，表示是全额赎回
        if(pos.getTotalPostionAmount() == 0){
            pos.setTotalPostionAmount(pos.getTotalPostionAmount());
            if (pos.getProductVol() == 1) {
                pos.setTotalPostionVol(pos.getTotalPostionAmount());
            } else {
                pos.setTotalPostionVol(new BigDecimal(String.valueOf(pos.getTotalPostionAmount()))
                        .divide(new BigDecimal(String.valueOf(pos.getProductVol())), 2, RoundingMode.DOWN).doubleValue());

            }

            InvestorTrans investorTrans = new InvestorTrans();
            investorTrans.setSerialNumber(iIdService.generateSerialNumber(ChannelCode.codeOf(pos.getChnCode())));
            investorTrans.setZlFundSeqId(benifitService.generateBenifitNo());
            investorTrans.setBatchCode(batchStateCurrent.getBatchCode());
            investorTrans.setInvestorId(pos.getInvestorId());
            investorTrans.setChnId(pos.getChnId());
            investorTrans.setChnCode(pos.getChnCode());
            investorTrans.setName(pos.getName());
            investorTrans.setTransTime(DateUtils.getCurrentDatetime());
            investorTrans.setIsExcess("0");
            investorTrans.setAppSheetSerialNo(investorTrans.getZlFundSeqId());
            investorTrans.setProductNo(pos.getProductNo());
            investorTrans.setProductName(pos.getProductName());
            investorTrans.setTransType(AmqConst.BUSINESS_CODE_REDEEM_ALL_APPLY);
            investorTrans.setTransAmount(income);
            Double transVol = new BigDecimal(String.valueOf(income))
                    .divide(new BigDecimal(String.valueOf(pos.getProductVol())), 2, RoundingMode.DOWN).doubleValue();
            investorTrans.setTransVol(transVol);
            investorTrans.setProductVol(pos.getProductVol());
            investorTrans.setTransFix(0.00);
            InvestorTrans searchTrans = new InvestorTrans();
            searchTrans.setChnId(pos.getChnId());
            searchTrans.setChnCode(pos.getChnCode());
            searchTrans.setProductNo(pos.getProductNo());
            searchTrans.setTransType(AmqConst.BUSINESS_CODE_REDEEM_APPLY);
            InvestorTrans resultTrans = investorTransMapper.selectTransByChoice(searchTrans);
            if(resultTrans != null){
                investorTrans.setTransBankCode(resultTrans.getTransBankCode());
                investorTrans.setTransBankAct(resultTrans.getTransBankAct());
                investorTrans.setChildProductNo(resultTrans.getChildProductNo());
            }else{
                logger.info("插入全额赎回分红记录时未找到 [银行账号] [银行代码]！");
            }
            investorTrans.setChargeRate(0.00);
            investorTrans.setSpecifyFee(0.00);
            investorTrans.setRedeemFlag("0");
            investorTrans.setCreateTime(DateUtils.getCurrentDatetime());
            investorTrans.setUpdateTime(DateUtils.getCurrentDatetime());
            if(income != 0){
                investorTransMapper.save(investorTrans);
                investorTransMapper.saveTransTemp(investorTrans);
            }


        }else{
            pos.setTotalPostionAmount(pos.getTotalPostionAmount() + income);
            if (pos.getProductVol() == 1) {
                pos.setTotalPostionVol(pos.getTotalPostionAmount());
            } else {
                pos.setTotalPostionVol(new BigDecimal(String.valueOf(pos.getTotalPostionAmount()))
                        .divide(new BigDecimal(String.valueOf(pos.getProductVol())), 2, RoundingMode.DOWN).doubleValue());

            }
        }


        return pos;
    }

    //业务汇总
    private void gatherBusiness() {
        if (clearResultMap.get(AmqClearStepConst.ClearSteps.STEP_7.getClearCode()) == null) {
            BusinessGather purchaseBusGather = new BusinessGather();
            BusinessGather redeemBusGather = new BusinessGather();

            InvestorTrans investorTrans = new InvestorTrans();
            investorTrans.setBatchCode(batchCode);
            List<InvestorTrans> transList = investorTransMapper.gatherBusiness(investorTrans);


            for (Map.Entry<String, ChnProduct> entry : productMaps.entrySet()) {
                int purchaseTransNum = 0; //交易笔数
                int purchaseSuccessNum = 0; //成功笔数
                int purchaseFailNum = 0; //失败笔数
                Double purchaseTotalTransVol = new Double("0");   //交易份额
                Double purchaseTotalTransAmount = new Double("0");  //交易金额
                Double purchaseSuccessTransVol = new Double("0");   //成功份额
                Double purchaseSuccessTransAmount = new Double("0");  //成功金额
                Double purchaseFailTransVol = new Double("0");   //失败份额
                Double purchaseFailTransAmount = new Double("0");  //失败金额
                int redeemTransNum = 0; //交易笔数
                int redeemSuccessNum = 0; //成功笔数
                int redeemFailNum = 0; //失败笔数
                Double redeemTotalTransVol = new Double("0");   //交易份额
                Double redeemTotalTransAmount = new Double("0");  //交易金额
                Double redeemSuccessTransVol = new Double("0");   //成功份额
                Double redeemSuccessTransAmount = new Double("0");  //成功金额
                Double redeemFailTransVol = new Double("0");   //失败份额
                Double redeemFailTransAmount = new Double("0");  //失败金额
                if (!transList.isEmpty() && transList.size() != 0) {
                    purchaseBusGather.setBatchCode(batchCode);
                    purchaseBusGather.setGatherDate(gatherDate);
                    redeemBusGather.setBatchCode(batchCode);
                    redeemBusGather.setGatherDate(gatherDate);
                }
                for (InvestorTrans tran : transList) {
                    if (entry.getKey().equals(tran.getProductNo().trim())) {
                        purchaseBusGather.setProductNo(tran.getProductNo().trim());
                        purchaseBusGather.setProductName(tran.getProductName().trim());
                        purchaseBusGather.setChnCode(tran.getChnCode());

                        redeemBusGather.setProductNo(tran.getProductNo().trim());
                        redeemBusGather.setProductName(tran.getProductName().trim());
                        redeemBusGather.setChnCode(tran.getChnCode());

                        //申购
                        if (AmqConst.BUSINESS_CODE_PURCHASE_APPLY.equals(tran.getTransType())) {
                            purchaseTransNum += Integer.parseInt(tran.getNum());
                            purchaseTotalTransVol += tran.getTransVol();
                            purchaseTotalTransAmount += tran.getTransAmount();
                            if (AmqConst.SHB_SUCC_CODE.equals(tran.getIsExcess())) {
                                purchaseSuccessNum += Integer.parseInt(tran.getNum());
                                purchaseSuccessTransVol += tran.getTransVol();
                                purchaseSuccessTransAmount += tran.getTransAmount();
                            } else if (AmqConst.SHB_FAIL_CODE.equals(tran.getIsExcess())) {
                                purchaseFailNum += Integer.parseInt(tran.getNum());
                                purchaseFailTransVol += tran.getTransVol();
                                purchaseFailTransAmount += tran.getTransAmount();
                            }
                            //赎回
                        } else if (AmqConst.BUSINESS_CODE_REDEEM_APPLY.equals(tran.getTransType())) {
                            redeemTransNum += Integer.parseInt(tran.getNum());
                            redeemTotalTransVol += tran.getTransVol();
                            redeemTotalTransAmount += tran.getTransAmount();
                            if (AmqConst.SHB_SUCC_CODE.equals(tran.getIsExcess())) {
                                redeemSuccessNum += Integer.parseInt(tran.getNum());
                                redeemSuccessTransVol += tran.getTransVol();
                                redeemSuccessTransAmount += tran.getTransAmount();
                            } else if (AmqConst.SHB_FAIL_CODE.equals(tran.getIsExcess())) {
                                redeemFailNum += Integer.parseInt(tran.getNum());
                                redeemFailTransAmount += tran.getTransAmount();
                                redeemFailTransVol += tran.getTransVol();
                            }
                        }
                    }
                }
                purchaseBusGather.setTransType(AmqConst.BUSINESS_CODE_PURCHASE_APPLY);
                purchaseBusGather.setTransNum(purchaseTransNum);
                purchaseBusGather.setTransVol(purchaseTotalTransVol);
                purchaseBusGather.setTransAmount(purchaseTotalTransAmount);
                purchaseBusGather.setSuccessNum(purchaseSuccessNum);
                purchaseBusGather.setSuccessVol(purchaseSuccessTransVol);
                purchaseBusGather.setSuccessAmount(purchaseSuccessTransAmount);
                purchaseBusGather.setFailNum(purchaseFailNum);
                purchaseBusGather.setFailVol(purchaseFailTransVol);
                purchaseBusGather.setFailAmount(purchaseFailTransAmount);

                redeemBusGather.setTransType(AmqConst.BUSINESS_CODE_REDEEM_APPLY);
                redeemBusGather.setTransNum(redeemTransNum);
                redeemBusGather.setTransVol(redeemTotalTransVol);
                redeemBusGather.setTransAmount(redeemTotalTransAmount);
                redeemBusGather.setSuccessNum(redeemSuccessNum);
                redeemBusGather.setSuccessVol(redeemSuccessTransVol);
                redeemBusGather.setSuccessAmount(redeemSuccessTransAmount);
                redeemBusGather.setFailNum(redeemFailNum);
                redeemBusGather.setFailVol(redeemFailTransVol);
                redeemBusGather.setFailAmount(redeemFailTransAmount);

                if (!"".equals(purchaseBusGather.getProductNo()) && purchaseBusGather.getTransAmount() != BigDecimal.ZERO.doubleValue()) {
                    businessGatherMapper.save(purchaseBusGather);
                }

                if (!"".equals(redeemBusGather.getProductNo()) && redeemBusGather.getTransAmount() != BigDecimal.ZERO.doubleValue()) {
                    businessGatherMapper.save(redeemBusGather);
                }
            }
            //记录完成状态
            clearResultMapper.save(generateClearResult(AmqClearStepConst.STEPS.STEP_7.toString(), AmqClearStepConst.ClearSteps.STEP_7.getClearCode(), AmqClearStepConst.ClearSteps.STEP_7.getClearDesc()));
        } else {
            logger.info("[批次号=" + batchCode + "]的数据已执行过流程[业务汇总]");
        }
    }

    /**
     * 更新批次
     */
    private void upgrateBatchCode() {
        if (clearResultMap.get(AmqClearStepConst.ClearSteps.STEP_8.getClearCode()) == null) {

            BatchState batchState = batchStateMapper.findNextBatch();
            batchStateMapper.upgrateBatchCode(batchState);

            //记录完成状态
            clearResultMapper.save(generateClearResult(AmqClearStepConst.STEPS.STEP_8.toString(), AmqClearStepConst.ClearSteps.STEP_8.getClearCode(), AmqClearStepConst.ClearSteps.STEP_8.getClearDesc()));
        } else {
            logger.info("[批次号=" + batchCode + "]的数据已执行过流程[更新批次]");
        }

    }


    /**
     * 计算收益
     * T日日初持仓收益=万元收益*T日日初持仓金额/10000
     */
    private static Double generateIncome(BigDecimal totalPostionAmount, BigDecimal tenThounsand) {
        return totalPostionAmount.multiply(tenThounsand).divide(AmqConst.TEN_THOUSAND, 2, RoundingMode.DOWN).doubleValue();
    }

    /**
     * 生成清算步骤
     *
     * @param stepNo   //步骤
     * @param stepCode //步骤代码
     * @param stepDesc //步骤描述
     * @return
     */
    private ClearResult generateClearResult(String stepNo, String stepCode, String stepDesc) {
        ClearResult clearResultOut = new ClearResult();
        clearResultOut.setBatchCode(batchCode);
        clearResultOut.setChnCode("0000");
        clearResultOut.setBatchDate(gatherDate);
        clearResultOut.setStepNo(stepNo);
        clearResultOut.setStepCode(stepCode);
        clearResultOut.setStepDesc(stepDesc);
        clearResultOut.setStatus("0");  //状态： 成功
        clearResultOut.setType("2");    //类型  1-文件   2-数据
        clearResultOut.setCreateTime(DateUtils.getCurrentDatetime());
        return clearResultOut;
    }

    //记录差额持仓
    private void recordDiffPosition(List<InvestorPosition> normalPosList, Boolean isWorkDay) {
        ChnOpen chnOpen = chnOpenMapper.findSysChnOpen();
        InvestorPosition addPosition = new InvestorPosition();
        addPosition.setInvestorId(chnOpen.getInvestorId());
        addPosition.setChnId(chnOpen.getChnId());
        addPosition.setChnCode(chnOpen.getChnCode());
        addPosition.setName(chnOpen.getName());
        addPosition.setBatchCode(batchCode);
        addPosition.setGatherDate(gatherDate);

        //将客户日初持仓列表放入map中
        if (normalPosList.size() != 0) {
            Map<String, Object> normalPosMap = new HashMap<String, Object>();
            for (InvestorPosition normalPos : normalPosList) {
                normalPosMap.put(normalPos.getChnCode() + normalPos.getProductNo() + normalPos.getInvestorId(), normalPos);
            }

            //查询差额持仓列表
            List<InvestorPosition> diffPosList = investorPositionMapper.findDiffPositionList();
            Map<String, String> haveBuyProductMap = new HashMap<>();

            for (Map.Entry<String, ChnProduct> entry : productMaps.entrySet()) {
                if (diffPosList.size() == 0) {
                    Double actualIncome = BigDecimal.ZERO.doubleValue();  //当前产品实际总收益
                    Double nameIncome = BigDecimal.ZERO.doubleValue();    //当前产品名义上的总收益
                    BigDecimal totalAmount = new BigDecimal("0");     //记录当前产品的日初持仓
                    for (InvestorPosition normalPos : normalPosList) {
                        if (entry.getKey().equals(normalPos.getProductNo().trim())) {
                            if (!haveBuyProductMap.containsKey(normalPos.getProductNo().trim())) {
                                haveBuyProductMap.put(normalPos.getProductNo().trim(), normalPos.getProductNo().trim());
                            }
                            tenThounsand = new BigDecimal(productMaps.get(entry.getKey()).getPercentIncomeRate());
                            nameTenThounsand = new BigDecimal(productMaps.get(entry.getKey()).getNormalPercentIncomeRate());
                            actualIncome = toIncome(isWorkDay, actualIncome, normalPos, tenThounsand);
                            nameIncome = toIncome(isWorkDay, nameIncome, normalPos, nameTenThounsand);
                            totalAmount = totalAmount.add(new BigDecimal(normalPos.getTotalPostionAmount()));
                        }
                    }
                    if (haveBuyProductMap.containsKey(entry.getKey())) {
                        addPosition.setTotalPostionAmount(totalAmount.doubleValue());
                        addPosition(addPosition, entry, nameIncome, actualIncome);
                    }

                } else {
                    for (InvestorPosition diffPos : diffPosList) {
                        Double actualIncome = BigDecimal.ZERO.doubleValue();  //当前产品实际总收益
                        Double nameIncome = BigDecimal.ZERO.doubleValue();    //当前产品名义上的总收益
                        BigDecimal totalAmount = new BigDecimal("0");     //记录当前产品的日初持仓
                        for (InvestorPosition normalPos : normalPosList) {
                            if (diffPos.getProductNo().equals(normalPos.getProductNo())) {
                                if (!haveBuyProductMap.containsKey(normalPos.getProductNo().trim())) {
                                    haveBuyProductMap.put(normalPos.getProductNo().trim(), normalPos.getProductNo().trim());
                                }
                                normalPosMap.remove(normalPos.getChnCode() + normalPos.getProductNo() + normalPos.getInvestorId());
                                tenThounsand = new BigDecimal(productMaps.get(entry.getKey()).getPercentIncomeRate());
                                nameTenThounsand = new BigDecimal(productMaps.get(entry.getKey()).getNormalPercentIncomeRate());
                                actualIncome = toIncome(isWorkDay, actualIncome, normalPos, tenThounsand);
                                nameIncome = toIncome(isWorkDay, nameIncome, normalPos, nameTenThounsand);
                                totalAmount = totalAmount.add(new BigDecimal(normalPos.getTotalPostionAmount()));
                            }
                        }
                        if (haveBuyProductMap.containsKey(entry.getKey())) {
                            if(entry.getKey().equals(diffPos.getProductNo().trim())){
                                InvestorPosition updatePosition = new InvestorPosition();
                                BeanUtils.copyProperties(diffPos, updatePosition);
                                updatePosition.setBatchCode(batchCode);
                                updatePosition.setGatherDate(gatherDate);
                                logger.info("    --更新差额持仓记录： " + "  " + entry.getKey() + "_" + entry.getValue().getProductName() + diffPos.getTotalIncome()  + "  差额收益 = " + nameIncome + "-" + actualIncome + " = " + (nameIncome - actualIncome));
                                updatePosition.setTotalIncome(diffPos.getTotalIncome() + nameIncome - actualIncome);
                                updatePosition.setTotalPostionAmount(totalAmount.doubleValue());
                                if (entry.getValue().getProductVol() == 1) {
                                    updatePosition.setTotalPostionVol(totalAmount.doubleValue());
                                } else {
                                    updatePosition.setTotalPostionVol(new BigDecimal(String.valueOf(totalAmount.doubleValue()))
                                            .divide(new BigDecimal(String.valueOf(entry.getValue().getProductVol())), 2, RoundingMode.DOWN).doubleValue());
                                }
                                List<InvestorPosition> updatePositionList = new ArrayList<>();
                                updatePositionList.add(updatePosition);
                                if (!updatePositionList.isEmpty() && updatePositionList.size() != 0) {
                                    investorPositionMapper.batchUpdate(updatePositionList);
                                }
                            }
                        }
                    }
                    if (haveBuyProductMap.containsKey(entry.getKey())) {
                        Double actualIncome = BigDecimal.ZERO.doubleValue();  //当前产品实际总收益
                        Double nameIncome = BigDecimal.ZERO.doubleValue();    //当前产品名义上的总收益
                        BigDecimal totalAmount = new BigDecimal("0");     //记录当前产品的日初持仓
                        Boolean flag = false;
                        for (Map.Entry<String, Object> posEntry : normalPosMap.entrySet()) {
                            InvestorPosition addPos = (InvestorPosition) posEntry.getValue();
                            logger.info("    --剩下的： "+ addPos.getId());
                            if (entry.getKey().equals(addPos.getProductNo().trim())) {
                                nameTenThounsand = new BigDecimal(productMaps.get(entry.getKey()).getNormalPercentIncomeRate());
                                nameIncome = toIncome(isWorkDay, nameIncome, addPos, nameTenThounsand);
                                tenThounsand = new BigDecimal(productMaps.get(entry.getKey()).getPercentIncomeRate());
                                actualIncome = toIncome(isWorkDay, actualIncome, addPos, tenThounsand);
                                totalAmount = totalAmount.add(new BigDecimal(addPos.getTotalPostionAmount()));
                                flag = true;
                            }
                        }
                        if(flag){
                            addPosition.setTotalPostionAmount(totalAmount.doubleValue());
                            addPosition(addPosition, entry, nameIncome, actualIncome);
                        }

                    }
                }
            }
        }


    }

    private Double toIncome(Boolean isWorkDay, Double income, InvestorPosition pos, BigDecimal tenThounsand) {
        //下一天为工作日
        if (isWorkDay) {
            income = income + generateIncome(new BigDecimal(pos.getTotalPostionAmount()), tenThounsand);  // 计算实际收益
        } else {  //下一天为非工作日
            //查询T-1日持仓
            PositionHistory posHis = new PositionHistory();
            posHis.setChnCode(pos.getChnCode());
            posHis.setProductNo(pos.getProductNo());
            posHis.setInvestorId(pos.getInvestorId());
            posHis.setGatherDate(holidayService.getPreWorkDay(batchStateCurrent.getDate()));
            PositionHistory positionHistory = positionHistoryMapper.findPosHistory(posHis);
            //根据T-1日持仓算收益
            if (positionHistory != null) {
                income = income + generateIncome(new BigDecimal(positionHistory.getTotalPostionAmount()), tenThounsand);  // 计算实际收益
            }
        }
        return income;
    }

    private void addPosition(InvestorPosition addPosition, Map.Entry<String, ChnProduct> entry, Double nameIncome, Double actualIncome) {
        addPosition.setProductNo(entry.getKey());
        addPosition.setProductName(entry.getValue().getProductName());
        addPosition.setProductVol(entry.getValue().getProductVol());
        addPosition.setTotalPostionAmount(addPosition.getTotalPostionAmount());
        if (entry.getValue().getProductVol() == 1) {
            addPosition.setTotalPostionVol(addPosition.getTotalPostionAmount());
        } else {
            addPosition.setTotalPostionVol(new BigDecimal(String.valueOf(addPosition.getTotalPostionAmount()))
                    .divide(new BigDecimal(String.valueOf(entry.getValue().getProductVol())), 2, RoundingMode.DOWN).doubleValue());
        }
        addPosition.setTotalRedemedAmount(BigDecimal.ZERO.doubleValue());
        addPosition.setTotalSubsAmount(BigDecimal.ZERO.doubleValue());
        addPosition.setTotalIncome(nameIncome - actualIncome);
        addPosition.setPositionType("1");
        logger.info("    --新增差额持仓记录： "  + "  " + entry.getKey() + "_" + entry.getValue().getProductName()+ "  差额收益 = " + nameIncome + "-" + actualIncome + " = " + (nameIncome - actualIncome));
        List<InvestorPosition> addPositionList = new ArrayList<>();
        addPositionList.add(addPosition);
        if (!addPositionList.isEmpty() && addPositionList.size() != 0) {
            investorPositionMapper.batchSave(addPositionList);
        }
    }

    private List<InvestorIncome> recordDiffIncome(List<InvestorPosition> posList, List<InvestorIncome> incomeList, Boolean isWorkDay) {
        Map<String, String> haveBuyProductMap = new HashMap<>();
        for (Map.Entry<String, ChnProduct> entry : productMaps.entrySet()) {
            Double actualIncome = BigDecimal.ZERO.doubleValue();  //当前产品实际总收益
            Double nameIncome = BigDecimal.ZERO.doubleValue();    //当前产品名义上的总收益
            tenThounsand = new BigDecimal(productMaps.get(entry.getKey()).getPercentIncomeRate());
            nameTenThounsand = new BigDecimal(productMaps.get(entry.getKey()).getNormalPercentIncomeRate());
            BigDecimal totalAmount = new BigDecimal("0");     //记录当前产品的日初持仓
            for (InvestorPosition pos : posList) {
                if (entry.getKey().equals(pos.getProductNo().trim())) {
                    if (!haveBuyProductMap.containsKey(pos.getProductNo().trim())) {
                        haveBuyProductMap.put(pos.getProductNo().trim(), pos.getProductNo().trim());
                    }
                    if (isWorkDay) {
                        actualIncome = actualIncome + generateIncome(new BigDecimal(pos.getTotalPostionAmount()), tenThounsand);
                        nameIncome = nameIncome + generateIncome(new BigDecimal(pos.getTotalPostionAmount()), nameTenThounsand);
                    } else {
                        //查询T-1日持仓
                        PositionHistory posHis = new PositionHistory();
                        posHis.setChnCode(pos.getChnCode());
                        posHis.setGatherDate(holidayService.getPreWorkDay(batchStateCurrent.getDate()));
                        posHis.setProductNo(pos.getProductNo());
                        posHis.setInvestorId(pos.getInvestorId());
                        PositionHistory positionHistory = positionHistoryMapper.findPosHistory(posHis);
                        //根据T-1日持仓算收益
                        if (positionHistory != null) {
                            actualIncome = actualIncome + generateIncome(new BigDecimal(positionHistory.getTotalPostionAmount()), tenThounsand);
                            nameIncome = nameIncome + generateIncome(new BigDecimal(positionHistory.getTotalPostionAmount()), nameTenThounsand);
                        }
                    }
                    totalAmount = totalAmount.add(new BigDecimal(pos.getTotalPostionAmount()));
                }
            }
            if (haveBuyProductMap.containsKey(entry.getKey())) {
                ChnOpen chnOpen = chnOpenMapper.findSysChnOpen();
                InvestorIncome investorIncome = new InvestorIncome();
                investorIncome.setInvestorId(chnOpen.getInvestorId());
                investorIncome.setChnId(chnOpen.getChnId());
                investorIncome.setChnCode(chnOpen.getChnCode());
                investorIncome.setName(chnOpen.getName());
                investorIncome.setBatchCode(batchStateCurrent.getBatchCode());
                investorIncome.setGatherDate(gatherDate);
                investorIncome.setApplyDate(batchStateCurrent.getDate());
                investorIncome.setSerialNumber(iIdService.generateSerialNumber(ChannelCode.codeOf(ChannelCode.XWBank.getCode())));
                investorIncome.setProductNo(entry.getKey());
                investorIncome.setProductName(entry.getValue().getProductName());
                investorIncome.setTotalPostionAmount(totalAmount.doubleValue());
                investorIncome.setIncomeRate(entry.getValue().getIncomeRate());
                investorIncome.setPercentIncome(entry.getValue().getPercentIncomeRate());
                investorIncome.setIncomeType("1");
                investorIncome.setIncomeAmount(nameIncome - actualIncome);
                investorIncome.setCreateTime(DateUtils.getCurrentDatetime());
                investorIncome.setUpdateTime(DateUtils.getCurrentDatetime());
                logger.info("收益表： 产品：" + entry.getKey() + "_" + entry.getValue().getProductName() + "  差额收益 = " + nameIncome + "-" + actualIncome + " = " + (nameIncome - actualIncome));

                incomeList.add(investorIncome);
            }

        }
        return incomeList;
    }
}
