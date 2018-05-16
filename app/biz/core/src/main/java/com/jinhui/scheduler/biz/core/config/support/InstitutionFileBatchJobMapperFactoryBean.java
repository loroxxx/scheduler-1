package com.jinhui.scheduler.biz.core.config.support;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.jinhui.scheduler.domain.common.InstitutionType;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

public class InstitutionFileBatchJobMapperFactoryBean implements
                                                      FactoryBean<InstitutionFileBatchJobMapper>,
                                                      InitializingBean {
    private JobRegistry                   jobRegistry;
    /**
     * 只接收{@link InstitutionType}的英文标识
     */
    private String                        institution;
    private Map<Pattern, String>          fileMappings;

    private InstitutionFileBatchJobMapper fileJobMapper;

    private SimpleFileBatchJobMapper simpleFileBatchJobMapper;

    @Override
    public InstitutionFileBatchJobMapper getObject() throws Exception {
        return this.fileJobMapper;
    }

    @Override
    public Class<?> getObjectType() {
        return InstitutionFileBatchJobMapper.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if(simpleFileBatchJobMapper == null){
            throw new NullPointerException("simpleFileBatchJobMapper property不能为空");
        }
        Map<Pattern, String> patternJobMap = new HashMap<>(fileMappings.size());
        for (Map.Entry<Pattern, String> entry : fileMappings.entrySet()) {
            Pattern pattern = entry.getKey();
            String jobName = entry.getValue();
            patternJobMap.put(pattern, jobName);
        }

        this.fileJobMapper = new InstitutionFileBatchJobMapper(institution, patternJobMap, jobRegistry);
        this.simpleFileBatchJobMapper.registerFileJobMapper(fileJobMapper);
    }

    public void setJobRegistry(JobRegistry jobRegistry) {
        this.jobRegistry = jobRegistry;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public void setFileMappings(Map<Pattern, String> fileMappings) {
        this.fileMappings = fileMappings;
    }

    public void setSimpleFileBatchJobMapper(SimpleFileBatchJobMapper simpleFileBatchJobMapper) {
        this.simpleFileBatchJobMapper = simpleFileBatchJobMapper;
    }
}
