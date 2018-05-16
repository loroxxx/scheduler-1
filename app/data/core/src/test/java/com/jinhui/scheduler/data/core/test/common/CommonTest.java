package com.jinhui.scheduler.data.core.test.common;


import com.jinhui.scheduler.data.core.dao.IdSequenceDAO;
import com.jinhui.scheduler.data.core.mapper.core.GeneratedFileMapper;
import com.jinhui.scheduler.data.core.mapper.core.InstitutionFileMapper;
import com.jinhui.scheduler.domain.common.InstitutionFile;
import com.jinhui.scheduler.domain.common.InstitutionRepository;
import com.jinhui.scheduler.domain.common.InstitutionType;
import com.jinhui.scheduler.domain.common.SequenceKey;
import com.jinhui.scheduler.domain.core.AbstractBatchFile;
import com.jinhui.scheduler.domain.core.GeneratedFile;
import com.jinhui.scheduler.domain.core.ReceivedFile;
import com.jinhui.scheduler.domain.core.ReceivedFileRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml", "classpath*:META-INF/spring/*.xml" })
public class CommonTest {

    @Autowired
    private IdSequenceDAO idSequenceDAO;

    @Autowired
    private InstitutionFileMapper institutionFileMapper;

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private ReceivedFileRepository receivedFileRepository;

    @Autowired
    private GeneratedFileMapper generatedFileMapper;

    @Test
    public void idTest(){
        long seq = idSequenceDAO.fetchSequence(new SequenceKey() {
            @Override
            public String getKey() {
                return "testKey";
            }
        });
        System.out.println(seq);
    }

    @Test
    public void findInstitutionFile(){
        InstitutionFile.Criteria criteria = InstitutionFile.criteria();
        criteria.institution(InstitutionType.Imiqian);
        criteria.isDownloadFile("1");
        List<InstitutionFile> fileList = institutionFileMapper.find(criteria);
        System.out.println(fileList);
    }

    @Test
    public void findFileByIdentity(){
        InstitutionFile fileList = institutionRepository.findFileByIdentity("OFI_(\\\\d{4})_JFB666_(\\\\d{8}).TXT",
                InstitutionType.Imiqian);
        System.out.println(fileList);
    }

    @Test
    public void receivedFileSave(){
        ReceivedFile receivedFile = new ReceivedFile("name",
                InstitutionType.Cmbfae, "backupLocation", new Date());
        receivedFileRepository.save(receivedFile);
    }

    @Test
    public void findGenFile() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        AbstractBatchFile.Criteria criteria = AbstractBatchFile.criteria()
                .institution(InstitutionType.Cmbfae).batchDate(dateFormat.parse("2017-06-09"));
        List<GeneratedFile> generatedFiles = generatedFileMapper.findFile(criteria);
        System.out.println(generatedFiles);
    }

    @Test
    public void saveGenFile() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        GeneratedFile generatedFile = new GeneratedFile("test", InstitutionType.Cmbfae,
                "test", dateFormat.parse("2017-06-09"));
            generatedFileMapper.save(generatedFile);
    }

}
