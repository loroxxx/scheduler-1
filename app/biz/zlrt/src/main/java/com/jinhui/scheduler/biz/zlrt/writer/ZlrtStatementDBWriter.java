package com.jinhui.scheduler.biz.zlrt.writer;


import com.jinhui.scheduler.data.core.mapper.zlrt.BatchStateMapperZlrt;
import com.jinhui.scheduler.data.core.mapper.zlrt.ZlBalanceAccBillMapper;
import com.jinhui.scheduler.domain.zlrt.BatchState;
import com.jinhui.scheduler.domain.zlrt.ZlBalanceAccBill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * created by wsc
 *  2017-11-15 19:16
 **/
public class ZlrtStatementDBWriter implements ItemWriter<ZlBalanceAccBill> {
    private final static Logger logger = LoggerFactory.getLogger(ZlrtStatementDBWriter.class);

    @Autowired
    ZlBalanceAccBillMapper zlBalanceAccBillMapper;
    @Autowired
    BatchStateMapperZlrt batchStateMapper;

    @Override
    public void write(List<? extends ZlBalanceAccBill> items) throws Exception {
        //初始化汇总日期和批次号
        BatchState batchState = batchStateMapper.findNewest();

          for(ZlBalanceAccBill zbab : items){
              zbab.setBatchCode(String.valueOf(batchState.getBatchCode()));
              zlBalanceAccBillMapper.insertSelective(zbab);
          }
    }




    /*@Override
    public void write(List<? extends ZlrtStatementLineItem> items) throws Exception {
        logger.info("***************  对账单记录数： *********** " + items.size());

        //查询最新的批次信息
        BatchState batchState = batchStateMapper.findNewest();

        for(ZlrtStatementLineItem zsli : items){
            logger.info(zsli.toString());

            ZlBalanceAccBill zbar = new ZlBalanceAccBill();
            BeanUtils.copyProperties(zsli,zbar);
            zbar.setTransAmt(ConvertorUtils.convertToStrDivideOneHundred(zsli.getTransAmt()));
            zbar.setChnCode(ChannelCode.Imiqian.getCode());
            zbar.setBatchCode(String.valueOf(batchState.getBatchCode()));

            //对账逻辑：根据证联对账单中的商户系统流水号去核对每笔流水信息，与系统划转表所存储的交易信息（交易日期、交易金额、产品代码）、用户信息（用户姓名、身份证号）核对
            InvestorTrans trans = new InvestorTrans();
            trans.setZlFundSeqId(zsli.getFundSeqId());
            //先查询对账单数据是否存在T日交易流水中
            List<InvestorTrans> transTempList = investorTransMapper.findTransTempList(trans);
            if(transTempList.size() == 1){

                justify(transTempList,zbar,zsli);
            }else{
                //如果不存在，则交易流水表全表查询是否存在
                List<InvestorTrans> transList = investorTransMapper.findTransList(trans);
                if(transList.size() == 1){

                    justify(transList,zbar,zsli);
                }else{
                    //都不存在，则记录“商户系统流水号不存在”
                    zbar.setBalanceResult(ZlBalanceResultConst.NOT_EXIST_FUND_SEQID);
                    zlBalanceAccResultMapper.insertSelective(zbar);
                }
            }

        }
    }

    private void justify(List<InvestorTrans> list,ZlBalanceAccBill zbar,ZlrtStatementLineItem zsli){
        InvestorTrans transPo = list.get(0);
        if(!zsli.getFundCode().equals(transPo.getProductNo())){
            //产品代码不一致
            zbar.setBalanceResult(ZlBalanceResultConst.NOT_EQUAL_FUND_CODE);
            zlBalanceAccResultMapper.insertSelective(zbar);
        }
        if(ConvertorUtils.convertToStrMulOneHundred(zsli.getTransAmt()).doubleValue() != transPo.getTransAmount()){
            //交易金额不一致
            zbar.setBalanceResult(ZlBalanceResultConst.NOT_EQUAL_TRANS_AMT);
            zlBalanceAccResultMapper.insertSelective(zbar);
        }
        if(!zsli.getFundDate().equals(transPo.getTransTime().substring(0,8))){
            //交易日期不一致
            zbar.setBalanceResult(ZlBalanceResultConst.NOT_EQUAL_TRANS_DATE);
            zlBalanceAccResultMapper.insertSelective(zbar);
        }
        if(!zsli.getUserNameText().equals(transPo.getName())){
            //客户的姓名不一致
            zbar.setBalanceResult(ZlBalanceResultConst.NOT_EQUAL_USERNAME);
            zlBalanceAccResultMapper.insertSelective(zbar);
        }
        if(!zsli.getCertId().equals(transPo.getIdNo())){
            //客户证件号码不一致
            zbar.setBalanceResult(ZlBalanceResultConst.NOT_EQUAL_CERT_NO);
            zlBalanceAccResultMapper.insertSelective(zbar);
        }
    }*/

}
