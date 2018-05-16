package com.jinhui.scheduler.web.api.controller.rollback;

import com.jinhui.scheduler.biz.imiqian.common.Result;
import com.jinhui.scheduler.biz.imiqian.service.RollBackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 回滚
 *
 * create by wsc 2017-06-12 18:03
 **/
@RestController
@RequestMapping("rollback")
public class RollBackController {
    private static Logger logger = LoggerFactory.getLogger(RollBackController.class);

    @Autowired
    RollBackService rollBackService;

    /**
     * 爱蜜钱 --- 回滚
     * @return
     */
    @RequestMapping(value="/imiqianRollback",method = RequestMethod.POST)
    @ResponseBody
    public Result imiqianRollback() throws Exception{
        logger.info("imiqian......开始回滚............");

        Result result = rollBackService.rollBack();

        logger.info("imiqian......回滚完成............");
        return result;
    }

}
