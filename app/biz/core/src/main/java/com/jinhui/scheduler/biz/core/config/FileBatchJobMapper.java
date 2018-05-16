package com.jinhui.scheduler.biz.core.config;

import com.jinhui.scheduler.biz.core.config.support.InstitutionFileBatchJobMapper;
import com.jinhui.scheduler.domain.common.InstitutionType;
import org.springframework.batch.core.Job;

public interface FileBatchJobMapper {
    Job fetchJob(String fileKey);

    String fetchFileKeyPattern(String jobName);

    InstitutionType getInstitution(String jobName);

    void registerFileJobMapper(InstitutionFileBatchJobMapper mapper);
}
