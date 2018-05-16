package com.jinhui.scheduler.biz.core.zlrt;

import com.jinhui.scheduler.biz.core.JobLauncherTestUtils;
import com.jinhui.scheduler.biz.imiqian.service.StatisticsPosIncomeService;
import com.jinhui.scheduler.data.core.mapper.imiqian.ChnOpenMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml", "classpath*:META-INF/spring/*.xml" })
public class AimiqianJobTests {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JobRegistry jobRegistry;

    @Autowired
    ChnOpenMapper chnOpenMapper;

    @Autowired
    StatisticsPosIncomeService statisticsPosIncomeService;

    @Test
    public void testAccountApplyJob() throws Exception {
        Job job = jobRegistry.getJob("amqAccountApplyJob");
        JobParameters parameters = new JobParametersBuilder().addString("timestamp", System.currentTimeMillis()+"").toJobParameters();
        JobExecution jobExecution = jobLauncherTestUtils.getJobLauncher().run(job, parameters);
        System.out.println(jobExecution.toString());
    }

    @Test
    public void testAccountConfirmJob() throws Exception {
        Job job = jobRegistry.getJob("amqAccountConfirmJob");
        JobParameters parameters = new JobParametersBuilder()
                .addString("date","20170522")
                .addString("applyDate","20170522")
                .addString("timestamp", System.currentTimeMillis()+"")
                                                             .toJobParameters();
        JobExecution jobExecution = jobLauncherTestUtils.getJobLauncher().run(job, parameters);
        System.out.println(jobExecution.toString());

    }


    @Test
    public void testTradeApplyJob() throws Exception {
        Job job = jobRegistry.getJob("amqTradeApplyJob");
        JobParameters parameters = new JobParametersBuilder().addString("timestamp", System.currentTimeMillis()+"").toJobParameters();
        JobExecution jobExecution = jobLauncherTestUtils.getJobLauncher().run(job, parameters);
        System.out.println(jobExecution.toString());
    }

    @Test
    public void testTradeConfirmJob() throws Exception {
        Job job = jobRegistry.getJob("amqTradeConfirmJob");
        JobParameters parameters = new JobParametersBuilder()
                .addString("date","20170522")
                .addString("applyDate","20170522")
                .addString("timestamp", System.currentTimeMillis()+"")
                .toJobParameters();
        JobExecution jobExecution = jobLauncherTestUtils.getJobLauncher().run(job, parameters);
        System.out.println(jobExecution.toString());

    }


    @Test
    public void testStaticsServiceJob() throws Exception {
        //statisticsPosIncomeService.statisticsPosition();
    }



}
