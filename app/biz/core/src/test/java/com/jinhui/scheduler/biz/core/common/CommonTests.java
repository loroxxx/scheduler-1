package com.jinhui.scheduler.biz.core.common;

import com.jinhui.scheduler.biz.core.divided.service.IDividedService;
import com.jinhui.scheduler.biz.core.file.support.InstitutionSftpMap;
import com.jinhui.scheduler.biz.core.file.support.Sftp;
import com.jinhui.scheduler.domain.common.*;
import com.jinhui.scheduler.domain.divided.InvestorDivided;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml", "classpath*:META-INF/spring/*.xml" })
public class CommonTests extends AbstractJUnit4SpringContextTests{

    @Autowired
    private IIdService idService;

    @Autowired
    private IDividedService dividedService;

    @Autowired
    private IFileService fileService;

    @Autowired
    private IFileNameService fileNameService;

    @Autowired
    private InstitutionSftpMap sftpMap;

    @Autowired
    private IFileDirectoryService directoryService;

    @Test
    public void sftpTest(){
        String p1 = "E:\\scheduler\\workspace\\download\\imiqian\\OFI_1018_JFB666_20170619.TXT";
        String p2="./download/20170601";
        Sftp sftp = new Sftp("imiqian","imiqian","106.14.208.94","22");
        sftp.asycPut(p1, p2, null);
    }

    @Test
    public void getInvestorDivided(){
        for(int i=1;i<210;i++) {
            InvestorDivided divided = new InvestorDivided(i+"", "土壕", "jh1234");
            String ccode = dividedService.getInvestorDivided(divided);
        }
        System.out.println();
    }

    @Test
    public void idTest(){
        String tid = idService.generateSerialNumber(ChannelCode.Imiqian);
        String aid = idService.generateInvestorID(CustomerType.Personal);
        System.out.println(tid + "  " + tid.length());
        System.out.println(aid + "  " + aid.length());
    }

    @Test
    public void fileService() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<InstitutionFile> files = fileService.lookupFinishedFile(InstitutionType.Imiqian,
                dateFormat.parse("2017-06-09"));
        System.out.println(files);
        files = fileService.lookupIncompletedFile(InstitutionType.Imiqian, dateFormat.parse("2017-06-08"));
        System.out.println(files);
        files = fileService.lookupDownloadFile(InstitutionType.Imiqian,dateFormat.parse("2017-06-08"));
        System.out.println(files);
        files = fileService.lookupNeededFiles(InstitutionType.Imiqian,dateFormat.parse("2017-06-08") );
        System.out.println(files);

        //Cmbfae
        /*files = fileService.lookupDownloadFile(InstitutionType.Cmbfae, dateFormat.parse("2017-06-12"),
                new IFileService.FileFilter() {
                    @Override
                    public boolean accept(String filename) {
                        return filename.equals("product_111_current_profit_20170612_001.req.txt");
                    }
                });
        System.out.println(files);
        files = fileService.lookupUploadFile(InstitutionType.Cmbfae,
                dateFormat.parse("2017-06-15"));
        System.out.println(files);*/

        //zlrt
        files = fileService.lookupNeededFiles(InstitutionType.ZLRT, dateFormat.parse("2017-06-08") );
        System.out.println(files);
        files = fileService.lookupDownloadFile(InstitutionType.ZLRT, dateFormat.parse("2017-06-12"),
                new IFileService.FileFilter() {
                    @Override
                    public boolean accept(String filename) {
                        return filename.startsWith("RECONCILIATION");
                    }
                });
        System.out.println(files);

        //xwbank
        /*files = fileService.lookupDownloadFile(InstitutionType.XWBank,dateFormat.parse("2017-07-13"));
        System.out.println(files);*/
    }

    @Test
    public void backupFile() throws ParseException {
        fileService.backupFile(new File("D:\\scheduler\\workspace\\download\\cmbfae\\test_20170524_008.req.txt"));
    }

    @Test
    public void uploadFile() throws ParseException {
        fileService.uploadFile(new File("E:\\scheduler\\workspace\\download\\imiqian\\OFI_1018_JFB666_20170619.TXT"));
    }

    @Test
    public void filename() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String filename = fileNameService.getFileName(InstitutionType.Cmbfae,
                "product_1000_current_publish_(\\d{8})_(\\d{3}).req.txt", dateFormat.parse("2017-06-08"));
        System.out.println(filename);
    }
}
