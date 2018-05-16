package com.jinhui.scheduler.biz.core.jinhui;

import com.alibaba.fastjson.JSON;
import com.jinhui.scheduler.biz.imiqian.common.AmqClearStepConst;
import com.jinhui.scheduler.biz.imiqian.service.ImiqianJobService;
import com.jinhui.scheduler.biz.imiqian.service.RollBackService;
import com.jinhui.scheduler.biz.imiqian.utils.DateUtils;
import com.jinhui.scheduler.data.core.mapper.imiqian.BatchStateMapper;
import com.jinhui.scheduler.data.core.mapper.imiqian.ClearErrorLogMapper;
import com.jinhui.scheduler.domain.common.ChannelCode;
import com.jinhui.scheduler.domain.imiqian.domain.BatchState;
import com.jinhui.scheduler.domain.imiqian.domain.ClearErrorLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml", "classpath*:META-INF/spring/*.xml" })
public class AimiqianJobTests {

    @Autowired
    RollBackService rollBackService;
    @Autowired
    ImiqianJobService imiqianJobService;
    @Autowired
    ClearErrorLogMapper clearErrorLogMapper;
    @Autowired
    BatchStateMapper batchStateMapper;



    /**
     * 申请文件入库
     * 1. 账户申请文件入库
     * 2. 交易申请文件入库
     * @throws Exception
     */
    @Test
    public void testApplyJob() throws Exception {
        System.out.println(JSON.toJSONString(imiqianJobService.applyImport()));

    }

    /**
     * 清算
     * 1. 将T日交易数据备份至临时表
     * 2. 备份T-1日的持仓数据
     * 3. 生成T日客户当前持有金额的收益
     * 4. 统计持仓
     * 5. 业务数据汇总
     * @throws Exception
     */
    @Test
    public void testStaticsServiceJob() throws Exception {
       //System.out.println(JSON.toJSONString(imiqianJobService.clear())) ;
    }

    /**
     * 生成确认文件
     * 1. 生成02账户确认文件
     * 2. 生成04交易确认文件
     * 3. 生成05对账数据文件
     * 4. 生成06基金分红文件
     * 5. 生成12业务数据汇总文件
     * @throws Exception
     */
    @Test
    public void testConfirmJob() throws Exception {
        System.out.println(JSON.toJSONString(imiqianJobService.confirmExport()));
    }



    /**
     * 回滚
     * @throws Exception
     */
    @Test
    public void testRollBackJob() throws Exception {
        imiqianJobService.rollback();
    }

    /**
     * 查询清算任务
     * @throws Exception
     */
    @Test
    public void testFindClearTask() throws Exception {
        BatchState batchState = batchStateMapper.findNewest();
        System.out.println(imiqianJobService.findClearTask(batchState.getDate()).getData().toString());
    }

    /**
     * 查询清算任务
     * @throws Exception
     */
    @Test
    public void testErrorLogTask() throws Exception {
        ClearErrorLog clearErrorLog = new ClearErrorLog();

        clearErrorLog.setBatchDate("20170614");
        clearErrorLog.setBatchCode(117);
        clearErrorLog.setChnCode("1018");
        clearErrorLog.setStepCode(AmqClearStepConst.ClearSteps.STEP_1.getClearCode());
        clearErrorLog.setStepDesc(AmqClearStepConst.ClearSteps.STEP_1.getClearDesc());
        clearErrorLog.setTargetCode(this.getClass().getSimpleName());
        clearErrorLog.setCreateTime(DateUtils.getCurrentDatetime());
        clearErrorLog.setErrorInfo("测试");
        clearErrorLogMapper.save(clearErrorLog);
    }


}
