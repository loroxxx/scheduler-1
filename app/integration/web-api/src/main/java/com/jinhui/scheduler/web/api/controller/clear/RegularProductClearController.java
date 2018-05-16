package com.jinhui.scheduler.web.api.controller.clear;

import com.alibaba.fastjson.JSON;
import com.jinhui.scheduler.biz.imiqian.common.Result;
import com.jinhui.scheduler.biz.imiqian.service.ImiqianJobService;
import com.jinhui.scheduler.biz.imiqian.service.RegularProductServcie;
import com.jinhui.scheduler.data.core.mapper.imiqian.BatchStateMapper;
import com.jinhui.scheduler.data.core.mapper.imiqian.ClearResultRegularProductMapper;
import com.jinhui.scheduler.domain.common.ChannelCode;
import com.jinhui.scheduler.domain.imiqian.domain.BatchState;
import com.jinhui.scheduler.domain.imiqian.domain.ClearResultRegularProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *
 * 定期产品清算API
 * create by wsc 2017-06-07 14:30
 *
 **/
@Controller
@RequestMapping("regularClear")
public class RegularProductClearController {
    private static Logger logger = LoggerFactory.getLogger(RegularProductClearController.class);

    @Autowired
    RegularProductServcie regularProductServcie;
    @Autowired
    private ClearResultRegularProductMapper clearResultRegularProductMapper;
    @Autowired
    private BatchStateMapper batchStateMapper;

    /**
     * 爱蜜钱 --- 清算
     * @return
     */
    @RequestMapping(value="/imiqianRegularClear",method = RequestMethod.POST)
    @ResponseBody
    public Result imiqianRegularClear() throws Exception{
        //定期产品成立处理
        regularProductServcie.toSetupRegularProduct();
        //定期产品付息处理，到期兑付处理
        regularProductServcie.toPayInterest();

        return Result.success("清算成功！");
    }

    /**
     * 爱蜜钱 --- 清算结果
     * @return
     */
    @RequestMapping(value="/imiqianRegularClearResult",method = RequestMethod.GET)
    @ResponseBody
    public Result imiqianRegularClearResult(@RequestParam("batchDate") String batchDate) throws Exception{

        ClearResultRegularProduct crrpChoice = new ClearResultRegularProduct();
        crrpChoice.setBatchDate(batchDate);
        crrpChoice.setChnCode(ChannelCode.Imiqian.getCode());
        List<ClearResultRegularProduct> list = clearResultRegularProductMapper.selectListByChoice(crrpChoice);
        return Result.successData(list);
    }



}
