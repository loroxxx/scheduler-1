package com.jinhui.scheduler.biz.imiqian.service.impl;

import com.jinhui.scheduler.biz.core.common.HolidayService;
import com.jinhui.scheduler.biz.core.common.IIdService;
import com.jinhui.scheduler.biz.imiqian.service.RegularProductServcie;
import com.jinhui.scheduler.biz.imiqian.utils.DateUtils;
import com.jinhui.scheduler.data.core.mapper.imiqian.*;
import com.jinhui.scheduler.domain.common.ChannelCode;
import com.jinhui.scheduler.domain.imiqian.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 定期产品服务层实现
 *
 * @autor wsc
 * @create 2017-12-18 16:11
 **/
@Service("regularProductServcie")
public class RegularProductServiceImpl implements RegularProductServcie{
    private static Logger logger = LoggerFactory.getLogger(RegularProductServiceImpl.class);
    @Autowired
    private InvestorPositionRegularProductMapper investorPositionRegularProductMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private InvestorTransMapper investorTransMapper;
    @Autowired
    private TermPaymentScheduleMapperDAO termPaymentScheduleMapper;
    @Autowired
    private TermPaymentDetailMapper termPaymentDetailMapper;
    @Autowired
    private BatchStateMapper batchStateMapper;
    @Autowired
    private IIdService iIdService;
    @Autowired
    private HolidayService holidayService;
    @Autowired
    private ClearResultRegularProductMapper clearResultRegularProductMapper;


    /**
     * 定期产品成立: 1.保存客户持仓记录   2.生成客户回款计划
     * @return
     */
    public void toSetupRegularProduct(){
        logger.info("........................................定期产品成立开始.....................................");
        //前一天是否为工作日
        Boolean IS_WORKDAY = holidayService.isWorkDay(DateUtils.getPreDate(DateUtils.getCurrentDate()));
        logger.info("当前日期："+DateUtils.getCurrentDate()+  "   前一天是否为工作日：" + IS_WORKDAY);
        List<Product> proList = null;
        //查询到了产品起息日待成立的定期产品: 成立日 = 产品起息日(定期产品) - 1
        if(IS_WORKDAY){
            //前一天为工作日
            proList = productMapper.selectToSetupProductList();
        }else{
            //前一天为非工作日
            proList = productMapper.selectToSetupProductListWithChoice();
        }

        logger.info("当天成立的定期产品数量： "+ proList.size());
        for(Product product : proList){
                InvestorTrans investorTrans = new InvestorTrans();
                investorTrans.setProductNo(product.getProductNo());
                //根据成立的产品查询该产品的投资记录
                List<InvestorTrans> transList = investorTransMapper.findRegularProductTransList(investorTrans);
                logger.info("产品交易记录数： "+ transList.size());
                for(InvestorTrans trans : transList){
                    InvestorPositionRegularProduct investorPositionSearch = new InvestorPositionRegularProduct();
                    investorPositionSearch.setChnId(trans.getChnId());
                    investorPositionSearch.setInvestorId(trans.getInvestorId());
                    investorPositionSearch.setProductNo(trans.getProductNo());
                    //查询当前客户是否有持仓记录
                    InvestorPositionRegularProduct investorPosition = investorPositionRegularProductMapper.selectIsExistPosition(investorPositionSearch);
                    //保存客户持仓记录
                    if(investorPosition == null){
                        BatchState batchState = batchStateMapper.findNewest();
                        InvestorPositionRegularProduct investorPositionAdd = new InvestorPositionRegularProduct();
                        investorPositionAdd.setGatherDate(batchState.getDate());
                        investorPositionAdd.setBatchCode(trans.getBatchCode());
                        investorPositionAdd.setId(trans.getId());
                        investorPositionRegularProductMapper.addRegularProductPosition(investorPositionAdd);
                    }else{
                        investorPositionRegularProductMapper.updateRegularProductPosition(trans);
                    }

                    //查询当前产品的回款计划
                    List<TermPaymentSchedule> planList = termPaymentScheduleMapper.selectPlanByProductNo(product.getProductNo());
                    //生成客户投资的回款计划
                    for(TermPaymentSchedule plan : planList){
                        TermPaymentDetail termPaymentDetail = new TermPaymentDetail();
                        termPaymentDetail.setChnCode(trans.getChnCode());
                        termPaymentDetail.setProductName(product.getProductName());
                        termPaymentDetail.setProductNo(product.getProductNo());
                        termPaymentDetail.setInvestorId(trans.getInvestorId());
                        termPaymentDetail.setInvestorName(trans.getName());
                        termPaymentDetail.setSubsTotalAmount(new BigDecimal(trans.getTransAmount()));
                        termPaymentDetail.setInterestPeriod(plan.getInterestPeriod());
                        termPaymentDetail.setInterestDate(plan.getInterestDate());
                        termPaymentDetail.setInterestAmount(new BigDecimal(trans.getTransVol()).multiply(plan.getPerInterestAmount()));
                        termPaymentDetailMapper.insertDetail(termPaymentDetail);
                    }
                }
                //定期产品成立时，更新产品状态为02-存续期
                productMapper.updateProductStateToLast(product.getProductNo());

                //保存产品成立记录
                saveClearResult(product.getProductNo(),"1");

        }
        logger.info("..........................................定期产品成立结束..............................................");
    }


    //定期产品付息日，兑付日处理
    public void toPayInterest(){
        logger.info("..........................................定期产品付息处理开始................................................");
        //前一天是否为工作日
        Boolean IS_WORKDAY = holidayService.isWorkDay(DateUtils.getPreDate(DateUtils.getCurrentDate()));
        logger.info("当前日期："+DateUtils.getCurrentDate() +"   前一天是否为工作日：" + IS_WORKDAY);
        List<TermPaymentDetail> termPaymentDetails = null;
        //当前需要付息的记录
        if(IS_WORKDAY){
            //前一天为工作日
            termPaymentDetails = termPaymentDetailMapper.selectToPayInterest();
        }else{
            //前一天为非工作日
            //获取上一个工作日
            String preWorkDay = holidayService.getPreWorkDay(DateUtils.getCurrentDate());
            logger.info("上一个工作日： "+preWorkDay);
            termPaymentDetails = termPaymentDetailMapper.selectToPayInterestWithChoice(preWorkDay);
        }
        logger.info("需付息的记录数： "+termPaymentDetails.size());
        if(termPaymentDetails.size() != 0){
            for(TermPaymentDetail termPaymentDetail : termPaymentDetails){
                //更新客户回款计划状态为"已付息"
                termPaymentDetailMapper.updateToPayInterest(termPaymentDetail.getId());
                //更新产品的回款计划状态为“已付息”
                termPaymentScheduleMapper.updateToPayInterest(termPaymentDetail.getProductNo(),termPaymentDetail.getInterestPeriod());


                Product product = productMapper.selectByProductNo(termPaymentDetail.getProductNo());
                //如果是定期产品最后一期
                if(product.getProductTotalPeriods() == termPaymentDetail.getInterestPeriod()){
                    //更新定期产品的总收益同时清空持仓份额和金额为0 (定期产品最后一期)
                    investorPositionRegularProductMapper.upgrateTotalIncomeAndClearPosition(termPaymentDetail);

                    //最后一期生成定期产品全额赎回(包含本金+最后一期利息)交易记录
                    saveRedeemRecord(product,termPaymentDetail);

                    //定期产品结束时，更新产品状态为03-产品终止
                    productMapper.updateProductStateToOver(product.getProductNo());

                    //保存到期兑付记录
                    saveClearResult(termPaymentDetail.getProductNo(),"2");
                }else{
                    //更新定期产品的总收益
                    investorPositionRegularProductMapper.upgrateTotalIncome(termPaymentDetail);
                    //保存付息处理记录
                    saveClearResult(termPaymentDetail.getProductNo(),"3");
                }
            }
        }
        logger.info("..............................................定期产品付息处理结束.....................................");
    }

    private void saveRedeemRecord(Product product,TermPaymentDetail termPaymentDetail){
        InvestorTrans investorTrans = new InvestorTrans();
        investorTrans.setSerialNumber(iIdService.generateSerialNumber(ChannelCode.Imiqian));
        //investorTrans.setBatchCode();
        investorTrans.setInvestorId(termPaymentDetail.getInvestorId());
        investorTrans.setChnCode(termPaymentDetail.getChnCode());
        investorTrans.setName(termPaymentDetail.getInvestorName());
        investorTrans.setTransTime(DateUtils.getCurrentDatetime());
        investorTrans.setIsExcess("0");
        investorTrans.setProductNo(termPaymentDetail.getProductNo());
        investorTrans.setProductName(product.getProductName());
        investorTrans.setProductVol(product.getProductVol().doubleValue());
        //交易类型，022-申购，024-赎回 099-定期产品全额赎回(清算时由系统生成)
        investorTrans.setTransType("099");


        //以下为非空项
        investorTrans.setChnId("70100980000000000");
        investorTrans.setAppSheetSerialNo("701009800000000000000000");
        investorTrans.setChildProductNo("000000");
        investorTrans.setTransVol(termPaymentDetail.getSubsTotalAmount().add(termPaymentDetail.getInterestAmount()).divide(product.getProductVol()).doubleValue());
        investorTrans.setTransFix(0.00);
        investorTrans.setCreateTime(DateUtils.getCurrentDatetime());
        investorTrans.setUpdateTime(DateUtils.getCurrentDatetime());


        investorTrans.setTransAmount(termPaymentDetail.getSubsTotalAmount().add(termPaymentDetail.getInterestAmount()).doubleValue());
        investorTransMapper.save(investorTrans);
    }


    private void saveClearResult(String productNo,String stepCode){
        BatchState batchState = batchStateMapper.findNewest();
        ClearResultRegularProduct crrpChoice = new ClearResultRegularProduct();
        crrpChoice.setBatchCode(batchState.getBatchCode());
        crrpChoice.setChnCode(ChannelCode.Imiqian.getCode());
        crrpChoice.setProductNo(productNo);
        crrpChoice.setStepCode(stepCode);
        ClearResultRegularProduct crrp = clearResultRegularProductMapper.selectByChoice(crrpChoice);
        if(crrp == null){
            ClearResultRegularProduct clearResultRegularProduct = new ClearResultRegularProduct();
            clearResultRegularProduct.setBatchCode(batchState.getBatchCode());
            clearResultRegularProduct.setBatchDate(batchState.getDate());
            clearResultRegularProduct.setChnCode(ChannelCode.Imiqian.getCode());
            clearResultRegularProduct.setProductNo(productNo);
            clearResultRegularProduct.setStepCode(stepCode);
            clearResultRegularProduct.setStatus("1");
            clearResultRegularProductMapper.insertSelective(clearResultRegularProduct);
        }

    }


}
