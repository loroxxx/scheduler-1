package com.jinhui.scheduler.data.core.repository.core;

import com.jinhui.scheduler.domain.core.ReceivedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.jinhui.scheduler.data.core.mapper.core.ReceivedFileMapper;
import com.jinhui.scheduler.domain.core.ReceivedFileRepository;


@Repository
public class ReceivedFileRepositoryImpl implements ReceivedFileRepository {
    @Autowired
    private ReceivedFileMapper receivedFileMapper;

    @Override
    public ReceivedFile find(int fileId) {
        return receivedFileMapper.findByFileId(fileId);
    }
    @Override
    public void save(ReceivedFile file) {
        receivedFileMapper.save(file);
    }

}
