import com.jinhui.scheduler.biz.core.common.IFileDirectoryService;
import com.jinhui.scheduler.biz.gzefe.service.export.ExportService;
import com.jinhui.scheduler.biz.gzefe.service.gather.BizGatherService;
import com.jinhui.scheduler.domain.common.InstitutionType;
import org.junit.Test;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.JobRegistry;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Callable;

/**
 * Created by Administrator on 2017/11/14 0014.
 */
public class JobTest extends TestConfig {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JobRegistry jobRegistry;

    @Autowired
    private BizGatherService bizGatherService;

    @Autowired
    private ExportService exportService;

    @Autowired
    private IFileDirectoryService iFileDirectoryService;

    //生成索引文件,账户申请文件,交易申请，分红，汇总文件
    @Test
    public void test1() throws Exception {


        String generatePath = iFileDirectoryService.getWorkspaceUploadFileDir(InstitutionType.Gzefe);

        Job job = jobRegistry.getJob("gzefeApplyJob");
        JobParameters parameters = new JobParametersBuilder()
                .addString("generatePath", generatePath)
                .addString("timestamp", System.currentTimeMillis() + "")
                .addString("applyDate", "20170711")
                .addString("exchangeCode", InstitutionType.Gzefe.getAbbr())
                .addString("batchCode", "8")
                .addString("chnCode", "1018")
                .toJobParameters();

        JobExecution jobExecution = jobLauncherTestUtils.getJobLauncher().run(job, parameters);
        System.out.println(jobExecution.toString());


    }


    //统计子产品的汇总信息
    @Test
    public void test5() {


        bizGatherService.statisticsChildProduct();
    }


}
