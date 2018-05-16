package com.jinhui.scheduler.biz.core.config.support;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.jinhui.scheduler.domain.common.InstitutionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.NoSuchJobException;

import com.jinhui.scheduler.domain.core.AbstractBatchFile;

public class InstitutionFileBatchJobMapper {

    /**
     * Logger
     */
    private final static Logger logger = LoggerFactory
            .getLogger(InstitutionFileBatchJobMapper.class);

    private String institution;
    private Map<Pattern, String> fileJobNameMap;
    private Map<String, Pattern> JobNameFileMap;
    private JobRegistry jobRegistry;

    public Job getJob(String fileKey) {
        String inst = AbstractBatchFile.extractInstitution(fileKey);
        if (!institution.equals(inst)) {
            return null;
        }
        String fileName = AbstractBatchFile.extractFileName(fileKey);
        for (Map.Entry<Pattern, String> mapping : fileJobNameMap.entrySet()) {
            Pattern pattern = mapping.getKey();
            boolean isMatch = pattern.matcher(fileName).matches();
            if (isMatch) {
                String jobName = mapping.getValue();
                try {
                    return jobRegistry.getJob(jobName);
                } catch (NoSuchJobException e) {
                    logger.error(String.format("Job（%s）不存在！", jobName), e);
                }
            }
        }
        return null;
    }

    public String getFilePattern(String jobName) {
        Pattern pattern = JobNameFileMap.get(jobName);
        if (pattern == null)
            return null;
        return AbstractBatchFile.takeFileKey(pattern.toString(), institution);
    }

    InstitutionFileBatchJobMapper(String institution, Map<Pattern, String> fileMappings,
                                  JobRegistry jobRegistry) {
        this.institution = institution;
        this.fileJobNameMap = fileMappings;
        this.JobNameFileMap = buildJobNameFileMap(fileMappings);
        this.jobRegistry = jobRegistry;
    }

    private Map<String, Pattern> buildJobNameFileMap(Map<Pattern, String> fileMappings) {
        Map<String, Pattern> mapping = new HashMap<>(fileMappings.size());
        for (Map.Entry<Pattern, String> entry : fileMappings.entrySet()) {
            Pattern pattern = entry.getKey();
            String jobName = entry.getValue();
            mapping.put(jobName, pattern);
        }
        return mapping;
    }

    public InstitutionType getInstitution(){
        return InstitutionType.codeOf(institution);
    }
}
