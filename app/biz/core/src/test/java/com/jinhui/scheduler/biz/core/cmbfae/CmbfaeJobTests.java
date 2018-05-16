package com.jinhui.scheduler.biz.core.cmbfae;

import java.util.Calendar;
import java.util.Date;

import com.jinhui.scheduler.biz.core.JobLauncherTestUtils;
import com.jinhui.scheduler.biz.core.config.FileBatchJobMapper;
import com.jinhui.scheduler.data.core.repository.core.FileQueryManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml", "classpath:META-INF/job/*.xml",
        "classpath*:META-INF/spring/*.xml" })
public class CmbfaeJobTests {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private FileBatchJobMapper mapper;

    @Autowired
    private JobRegistry jobRegistry;

    @Autowired
    FileQueryManager fileQueryManager;

    @Test
    public void testJob() throws Exception {
        Job job = jobRegistry.getJob("testUploadJob");
        JobParameters parameters = new JobParametersBuilder().addString("productNum", "20170317re00300000000080")
                .addDate("time", Calendar.getInstance().getTime())
                .addDate("batchDate", new Date()).toJobParameters();
        JobExecution jobExecution = jobLauncherTestUtils.getJobLauncher().run(job, parameters);
        Assert.assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());

        /*Job job = jobRegistry.getJob("testDownloadJob");
        JobParameters parameters = new JobParametersBuilder()
                .addDate("time", Calendar.getInstance().getTime()).toJobParameters();
        JobExecution jobExecution = jobLauncherTestUtils.getJobLauncher().run(job, parameters);
        //addString("file.path", "D:/scheduler/nas/download/cmbfae/test_20170524_005.req.txt")
        Assert.assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());*/

       // System.out.println(TemplateFileReader.getAccountConfirmHeader("20170506"));
    }

    @Test
    public void testJob2() throws Exception {
        Job job = jobRegistry.getJob("receivedFileTestJob");
        JobParameters parameters = new JobParametersBuilder().
                addDate("time", Calendar.getInstance().getTime()).toJobParameters();
        JobExecution jobExecution = jobLauncherTestUtils.getJobLauncher().run(job, parameters);
        Assert.assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
    }

}
