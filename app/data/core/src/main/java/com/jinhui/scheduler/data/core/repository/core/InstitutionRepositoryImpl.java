package com.jinhui.scheduler.data.core.repository.core;

import com.jinhui.scheduler.data.core.mapper.core.InstitutionFileMapper;
import com.jinhui.scheduler.domain.common.InstitutionFile;
import com.jinhui.scheduler.domain.common.InstitutionRepository;
import com.jinhui.scheduler.domain.common.InstitutionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jinhui on 2017/6/9.
 */
@Repository
public class InstitutionRepositoryImpl implements InstitutionRepository {

    private final static Logger logger = LoggerFactory.getLogger(InstitutionRepositoryImpl.class);

    @Autowired
    private InstitutionFileMapper institutionFileMapper;

    @Override
    public InstitutionFile findFileByIdentity(String filePattern, InstitutionType institution) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("filePattern",filePattern);
        parameter.put("institution",institution);
        InstitutionFile institutionFile = institutionFileMapper.findByIdentity(parameter);
        if(institutionFile == null){
            logger.info("[filePattern={}, institution={}]对应机构文件未配置");
            throw new IllegalArgumentException("机构文件未配置");
        }
        return institutionFile;
    }
}
