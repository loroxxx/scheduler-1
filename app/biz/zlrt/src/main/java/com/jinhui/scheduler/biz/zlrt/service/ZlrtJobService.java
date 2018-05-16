package com.jinhui.scheduler.biz.zlrt.service;

import com.github.pagehelper.PageInfo;
import com.jinhui.scheduler.biz.zlrt.common.Result;
import com.jinhui.scheduler.domain.zlrt.ZlBalanceAccBill;
import com.jinhui.scheduler.domain.zlrt.ZlBalanceAccBillResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 证联融通文件对账服务层
 *
 * @autor wsc
 * @create 2017-11-24 9:57
 **/
public interface ZlrtJobService {
    /**
     * 证联对账单导入
     * @return
     */
    public Result importBalanceAcctFile() throws Exception;

    /**
     * 查询导入的对账单列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<ZlBalanceAccBill> queryImportAcctList(Integer pageNum, Integer pageSize);

    /**
     * 对账
     * @return
     */
    public Result balanceAcct();

    /**
     * 查询对账结果列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<ZlBalanceAccBillResult> queryBalanceResultList(Integer pageNum, Integer pageSize);

    /**
     * 调账处理
     */
    public Result chargeToCorrect();

    /**
     * 调账后检查
     * @return
     */
    public Result checkUpAfterCharge();


    /**
     * 赎回文件报送
     * @return
     */
    public Result sendRedeemFile() throws Exception;

    /**
     * 监管银行文件报送
     * @return
     */
    public Result sendBankFile() throws Exception;



    /**
     * 报送成功之后的结果
     * @return
     */
    public Result afterSendList();

    /**
     * 报送赎回文件，监管文件之后通知证联
     * @return
     */
    public Result announce();

}
