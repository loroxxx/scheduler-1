package com.jinhui.scheduler.web.api.controller.zlrtBalanceAcct;

import com.github.pagehelper.PageInfo;
import com.jinhui.scheduler.biz.zlrt.common.Result;
import com.jinhui.scheduler.biz.zlrt.service.ZlrtJobService;
import com.jinhui.scheduler.biz.zlrt.utils.zlrt.ZlrtHttpUtils;
import com.jinhui.scheduler.domain.zlrt.ZlBalanceAccBill;
import com.jinhui.scheduler.domain.zlrt.ZlBalanceAccBillResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * 申请文件导入API
 * create by wsc 2017-06-07 14:30
 *
 **/
@RestController
@RequestMapping("/zlrt")
public class ZlrtBalanceAccController {
    private static Logger logger = LoggerFactory.getLogger(ZlrtBalanceAccController.class);

    @Autowired
    private ZlrtJobService zlrtJobService;



    /**
     * 证联 --- 对账文件导入
     */
    @RequestMapping(value="/importBalanceAcctFile",method = RequestMethod.POST)
    @ResponseBody
    public Result importBalanceAcctFile() throws Exception{
        Result result = zlrtJobService.importBalanceAcctFile();
        return result;
    }

    /**
     * 证联 --- 导入之后的对账单列表
     */
    @RequestMapping(value="/queryImportAcctList",method = RequestMethod.GET)
    @ResponseBody
    public Result queryImportAcctList(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) throws Exception{
        PageInfo<ZlBalanceAccBill> list = zlrtJobService.queryImportAcctList(pageNum,pageSize);
        return Result.successData(list);
    }

    /**
     * 证联 --- 对账之后的对账结果列表
     */
    @RequestMapping(value="/queryBalanceResultList",method = RequestMethod.GET)
    @ResponseBody
    public Result queryBalanceResultList(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) throws Exception{
        PageInfo<ZlBalanceAccBillResult> list = zlrtJobService.queryBalanceResultList(pageNum,pageSize);
        return Result.successData(list);
    }

    /**
     * 证联 --- 对账
     */
    @RequestMapping(value="/balanceAcct",method = RequestMethod.POST)
    @ResponseBody
    public Result balanceAcct() throws Exception{
        Result result = zlrtJobService.balanceAcct();
        return result;
    }


    /**
     * 证联 --- 调账处理
     */
    @RequestMapping(value="/chargeToCorrect",method = RequestMethod.POST)
    @ResponseBody
    public Result chargeToCorrect() throws Exception{
        Result result = zlrtJobService.chargeToCorrect();
       return result;
    }

    /**
     * 证联 --- 调账后检查
     */
    @RequestMapping(value="/checkUpAfterCharge",method = RequestMethod.POST)
    @ResponseBody
    public Result checkUpAfterCharge() throws Exception{
        Result result = zlrtJobService.checkUpAfterCharge();
        return result;
    }



    /**
     * 证联 --- 给证联报送赎回文件
     */
    @RequestMapping(value="/sendRedeemFile",method = RequestMethod.POST)
    @ResponseBody
    public Result sendRedeemFile() throws Exception{
        Result result = zlrtJobService.sendRedeemFile();
        return result;
    }

    /**
     * 证联 --- 监管文件报送
     */
    @RequestMapping(value="/sendBankFile",method = RequestMethod.POST)
    @ResponseBody
    public Result sendBankFile() throws Exception{
        Result result = zlrtJobService.sendBankFile();
        return result;
    }

    @RequestMapping(value="/afterSendList",method = RequestMethod.GET)
    @ResponseBody
    public Result afterSendList() throws Exception{
        Result result = zlrtJobService.afterSendList();
        return result;
    }

    @RequestMapping(value="/announce",method = RequestMethod.GET)
    @ResponseBody
    public Result announct() throws Exception{
        Result result = zlrtJobService.announce();
        return result;
    }




}
