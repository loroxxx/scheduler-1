package com.jinhui.scheduler.biz.core.common.impl;

import com.jinhui.scheduler.biz.core.common.IFileNameService;
import com.jinhui.scheduler.biz.core.config.FileBatchJobMapper;
import com.jinhui.scheduler.data.core.dao.FileSequenceDAO;
import com.jinhui.scheduler.domain.common.InstitutionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;

/**
 * Created by jinhui on 2017/5/23.
 */
@Service("fileNameServiceImpl")
public class FileNameServiceImpl implements IFileNameService {

    @Autowired
    private FileSequenceDAO sequenceDAO;

    @Override
    public String getFileName(InstitutionType institution, String fileKeyPattern, Date batchDate) {
        String fileNamePatternWithDate = institution.fileNameWithoutSeq(fileKeyPattern, batchDate);
        if(institution.multiBatch()) {
            int seq = sequenceDAO.fetchSequence(institution + File.separator + fileNamePatternWithDate);
            return institution.fileNameWithSeq(fileNamePatternWithDate, seq);
        }
        return fileNamePatternWithDate;
    }

}
