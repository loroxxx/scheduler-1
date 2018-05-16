package service;

import com.jinhui.scheduler.biz.cmbfae.service.export.CmbfaeExportService;
import com.jinhui.scheduler.biz.cmbfae.service.upload.UploadFileService;
import com.jinhui.scheduler.domain.common.ExchangeType;
import org.junit.Test;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.beans.factory.annotation.Autowired;


public class JobTest extends TestConfig {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JobRegistry jobRegistry;

    @Autowired
    private CmbfaeExportService cmbfaeExportService;

    @Autowired
    private UploadFileService uploadFileService;

    //交易记录导出
    @Test
    public void test1() throws Exception {


        Job job = jobRegistry.getJob("cmbfaeTransFileJob");
        JobParameters parameters = new JobParametersBuilder()
                .addString("generatePath", "C:\\Users\\Administrator\\Desktop\\trans.txt")
                .addString("timestamp", System.currentTimeMillis() + "")
                .addString("exchangeNo", ExchangeType.Cmbfae.getExchangeNo())
                .addString("batchCode", "8")
                .toJobParameters();

        JobExecution jobExecution = jobLauncherTestUtils.getJobLauncher().run(job, parameters);
        System.out.println(jobExecution.toString());


    }

    //持仓记录导出
    @Test
    public void test2() throws Exception {


        Job job = jobRegistry.getJob("cmbfaePositionFileJob");
        JobParameters parameters = new JobParametersBuilder()
                .addString("generatePath", "C:\\Users\\Administrator\\Desktop\\position.txt")
                .addString("timestamp", System.currentTimeMillis() + "")
                .addString("exchangeNo", ExchangeType.Cmbfae.getExchangeNo())
                .toJobParameters();

        JobExecution jobExecution = jobLauncherTestUtils.getJobLauncher().run(job, parameters);
        System.out.println(jobExecution.toString());


    }


    @Test
    public void test3() throws Exception {
        cmbfaeExportService.export("");

    }

    @Test
    public void test4() {
        uploadFileService.uploadFile();
    }

}
