package com.jinhui.scheduler.data.core.mapper.core;

import com.jinhui.scheduler.domain.core.ReceivedFile;

import java.util.List;
import java.util.Map;

public interface ReceivedFileMapper {
    ReceivedFile findByFileId(int fileId);
    void save(ReceivedFile receivedFile);
    List<ReceivedFile> queryFile(ReceivedFile.Criteria criteria);

    void rollbackFile(ReceivedFile.Criteria criteria);
}
