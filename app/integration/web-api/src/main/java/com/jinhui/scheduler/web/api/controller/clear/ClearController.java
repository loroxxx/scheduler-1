package com.jinhui.scheduler.web.api.controller.clear;

import com.alibaba.fastjson.JSON;
import com.jinhui.scheduler.biz.imiqian.common.Result;
import com.jinhui.scheduler.biz.imiqian.service.ImiqianJobService;
import com.jinhui.scheduler.data.core.mapper.imiqian.BatchStateMapper;
import com.jinhui.scheduler.data.core.mapper.imiqian.ClearResultMapper;
import com.jinhui.scheduler.domain.common.ChannelCode;
import com.jinhui.scheduler.domain.common.InstitutionType;
import com.jinhui.scheduler.domain.imiqian.domain.BatchState;
import com.jinhui.scheduler.domain.imiqian.domain.ClearResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * 清算API
 * create by wsc 2017-06-07 14:30
 *
 **/
@Controller
@RequestMapping("clear")
public class ClearController {
    private static Logger logger = LoggerFactory.getLogger(ClearController.class);

    @Autowired
    ImiqianJobService imiqianJobService;

    /**
     * 爱蜜钱 --- 清算
     * @return
     */
    @RequestMapping(value="/imiqianClear",method = RequestMethod.POST)
    @ResponseBody
    public Result imiqianClear(@RequestParam("batchDate") String batchDate) throws Exception{
        logger.info("imiqian......开始清算............");
        logger.info("输入参数： batchDate=" +batchDate);
        Result result = imiqianJobService.clear(batchDate);
        logger.info("清算结果 = "+ JSON.toJSONString(result));
        logger.info("imiqian......清算完成............");
        return result;
    }

    /**
     * 爱蜜钱 --- 查询清算任务
     * @return
     */
    @RequestMapping(value="/findClearTask",method = RequestMethod.GET)
    @ResponseBody
    public Result findClearTask(@RequestParam("batchDate") String batchDate) throws Exception{
        logger.info("查询清算任务： batchDate=" +batchDate);
        Result result = imiqianJobService.findClearTask(batchDate);
        logger.info("输出 = "+ JSON.toJSONString(result));
        return result;
    }

    /**
     * 查询清算结果
     * @param batchDate
     * @return
     */
    @RequestMapping(value="/findClearResult",method = RequestMethod.GET)
    @ResponseBody
    public Result findClearResult(@RequestParam("batchDate") String batchDate){
        logger.info("查询清算是否成功： batchDate=" +batchDate);
        Result result = imiqianJobService.findClearResult(batchDate);
        logger.info("输出 = "+ JSON.toJSONString(result));
        return result;
    }



}
