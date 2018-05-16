package com.jinhui.scheduler.biz.core.zlrt;

import com.jinhui.scheduler.biz.core.JobLauncherTestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml", "classpath*:META-INF/spring/*.xml" })
public class ZlrtJobTests {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JobRegistry jobRegistry;

    @Test
    public void testStatementJob() throws Exception {
        Job job = jobRegistry.getJob("zlrtStatementJob");
        JobParameters parameters = new JobParametersBuilder()
                .addString("timestamp", System.currentTimeMillis()+"")
                                                             .toJobParameters();
        JobExecution jobExecution = jobLauncherTestUtils.getJobLauncher().run(job, parameters);
        System.out.println(jobExecution.toString());
    }

    @Test
    public void testRedeemJob() throws Exception {
        Job job = jobRegistry.getJob("zlrtRedeemJob");
        JobParameters parameters = new JobParametersBuilder()
                .addString("timestamp", System.currentTimeMillis()+"")
                .addString("batchCode","3")
                .addString("chnCode","1018")
                .addString("applyDate","20171120151210")
                .toJobParameters();
        JobExecution jobExecution = jobLauncherTestUtils.getJobLauncher().run(job, parameters);
        System.out.println(jobExecution.toString());
    }

    @Test
    public void testPurchaseJob() throws Exception {
        Job job = jobRegistry.getJob("zlrtBankJob");
        JobParameters parameters = new JobParametersBuilder()
                .addString("timestamp", System.currentTimeMillis()+"")
                .addString("batchCode","7")
                .addString("chnCode","1018")
                .addString("applyDate","20171120")
                .toJobParameters();
        JobExecution jobExecution = jobLauncherTestUtils.getJobLauncher().run(job, parameters);
        System.out.println(jobExecution.toString());
    }

}
