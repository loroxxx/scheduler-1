package com.jinhui.scheduler.data.core.mapper.core;

import com.jinhui.scheduler.domain.core.AbstractBatchFile;
import com.jinhui.scheduler.domain.core.GeneratedFile;

import java.util.List;
import java.util.Map;

public interface GeneratedFileMapper {
    GeneratedFile findByGeFileId(int fileId);
    void save(GeneratedFile file);
    List<GeneratedFile> findFile(AbstractBatchFile.Criteria criteria);
}
