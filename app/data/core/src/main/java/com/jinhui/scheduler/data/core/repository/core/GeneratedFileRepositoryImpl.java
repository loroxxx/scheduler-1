package com.jinhui.scheduler.data.core.repository.core;

import com.jinhui.scheduler.data.core.mapper.core.GeneratedFileMapper;
import com.jinhui.scheduler.domain.core.GeneratedFile;
import com.jinhui.scheduler.domain.core.GeneratedFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by zhouxf on 2016/11/3.
 */
@Repository
public class GeneratedFileRepositoryImpl implements GeneratedFileRepository {
    @Autowired
    protected GeneratedFileMapper generatedFileMapper;

    @Override
    public GeneratedFile find(int fileId) {
        return generatedFileMapper.findByGeFileId(fileId);
    }
    @Override
    public void save(GeneratedFile file) {
        generatedFileMapper.save(file);
    }
}
