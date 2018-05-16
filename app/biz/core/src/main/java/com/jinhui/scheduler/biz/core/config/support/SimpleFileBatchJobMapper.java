package com.jinhui.scheduler.biz.core.config.support;

import java.util.ArrayList;
import java.util.List;

import com.jinhui.scheduler.biz.core.config.FileBatchJobMapper;
import com.jinhui.scheduler.domain.common.InstitutionType;
import org.springframework.batch.core.Job;

public class SimpleFileBatchJobMapper implements FileBatchJobMapper {
    private final List<InstitutionFileBatchJobMapper> mappers = new ArrayList<>();

    @Override
    public Job fetchJob(String fileKey) {
        Job job = null;

        for (InstitutionFileBatchJobMapper mapper : mappers) {
            job = mapper.getJob(fileKey);
            if (job != null) {
                break;
            }
        }
        return job;
    }

    @Override
    public String fetchFileKeyPattern(String jobName) {
        String fetchFileKeyPattern = null;
        for (InstitutionFileBatchJobMapper mapper : mappers) {
            fetchFileKeyPattern = mapper.getFilePattern(jobName);
            if (fetchFileKeyPattern != null) {
                break;
            }
        }
        return fetchFileKeyPattern;
    }

    @Override
    public InstitutionType getInstitution(String jobName) {
        InstitutionType institution = null;
        for (InstitutionFileBatchJobMapper mapper : mappers) {
            if (mapper.getFilePattern(jobName) != null) {
                return mapper.getInstitution();
            }
        }
        return institution;
    }

    @Override
    public void registerFileJobMapper(InstitutionFileBatchJobMapper mapper) {
        mappers.add(mapper);
    }

}
