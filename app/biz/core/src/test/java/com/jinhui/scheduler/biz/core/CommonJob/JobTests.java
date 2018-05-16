package com.jinhui.scheduler.biz.core.CommonJob;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Entropy on 2016/11/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml",
                                    "classpath*:META-INF/spring/*.xml" })
public class JobTests {

    @Autowired private SchedulerFactoryBean schedulerFactoryBean;
    @Autowired JobLauncher jobLauncher;
    @Autowired JobRegistry jobRegistry;
    @Test
    public void productPayInfoConfirmJob() {
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        schedulerFactoryBean.start();
//        JobParameters param = new JobParametersBuilder()
//                .addDate("start_time", new Date())
//                .toJobParameters();
//        try {
//            jobLauncher.run(jobRegistry.getJob("productPayStatusConfirm"),param);
//        } catch (JobExecutionAlreadyRunningException e) {
//            e.printStackTrace();
//        } catch (JobRestartException e) {
//            e.printStackTrace();
//        } catch (JobInstanceAlreadyCompleteException e) {
//            e.printStackTrace();
//        } catch (JobParametersInvalidException e) {
//            e.printStackTrace();
//        } catch (NoSuchJobException e) {
//            e.printStackTrace();
//        }
    }
}
