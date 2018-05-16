package service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jinhui.scheduler.biz.cmbfae.constans.CmbfaeExportSteps;
import com.jinhui.scheduler.biz.cmbfae.service.log.LogService;
import com.jinhui.scheduler.biz.core.batch.BatchStateService;
import com.jinhui.scheduler.biz.core.common.IFileService;
import com.jinhui.scheduler.domain.cmbfae.model.BatchState;
import com.jinhui.scheduler.domain.common.ExchangeType;
import com.jinhui.scheduler.domain.common.InstitutionFile;
import com.jinhui.scheduler.domain.common.InstitutionType;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/19 0019.
 */

public class CmbfaeFileMock extends TestConfig {


    @Autowired
    private IFileService iFileService;


    @Autowired
    private BatchStateService batchStateService;


    @Autowired
    private LogService logService;

    /**
     * 模拟招银给我们返回的文件，
     * 返回的文件中，给每一条json记录加上"status":"1"
     */


    @Test
    public void test1() throws InterruptedException, IOException {
            schedule();
    }


    public void schedule() throws IOException {




        //先把文件下载下来
        BatchState current = batchStateService.getBatchStateCurrent();


        CmbfaeExportSteps step = logService.queryStep(current.getDate(), ExchangeType.Cmbfae.getExchangeNo());

        if(step.equals(CmbfaeExportSteps.STEP_9)){
            return;
        }

        List<InstitutionFile> files = downFiles(current);


        for (InstitutionFile file : files) {

            ArrayList list = new ArrayList();
            String filePath = file.getFileWorkspaceLocation() + File.separator + file.getFileName();

            String fileName = file.getFileName();
            String[] split = fileName.split("\\.");
            String respFilePath = file.getFileWorkspaceLocation() + File.separator + split[0] + ".resp.txt";

            File reqFile = new File(filePath);
            LineIterator lineIterator = FileUtils.lineIterator(reqFile, "UTF-8");
            String line = "";
            try {
                while (lineIterator.hasNext()) {
                    line = lineIterator.next();
                    if (!StringUtils.isBlank(line)) {
                        Map<String, String> lineMap = JSON.parseObject(line, Map.class);
                        lineMap.put("status", "1");
                        String string = JSON.toJSONString(lineMap, SerializerFeature.WriteMapNullValue);
                        list.add(string);
                    }
                }
            } catch (Exception e) {
                System.out.println(reqFile.getName());
                System.out.println(line);
                e.printStackTrace();
            } finally {
                LineIterator.closeQuietly(lineIterator);
            }

            File respFile = new File(respFilePath);
            FileUtils.writeLines(respFile, "UTF-8", list);

            //上传到服务器
            iFileService.uploadFile(respFile);

            FileUtils.deleteQuietly(respFile);
            FileUtils.deleteQuietly(reqFile);
        }


    }


    private List<InstitutionFile> downFiles(BatchState current) {

        List<InstitutionFile> files = iFileService.lookupDownloadFile(InstitutionType.Cmbfae, current.getDate(), filename -> {
            if (filename.contains("req")) {
                return true;
            }
            return false;
        });
        return files;
    }


}
