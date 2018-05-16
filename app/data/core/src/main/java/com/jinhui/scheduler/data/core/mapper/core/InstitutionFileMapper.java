package com.jinhui.scheduler.data.core.mapper.core;

import com.jinhui.scheduler.domain.common.InstitutionFile;
import com.jinhui.scheduler.domain.common.InstitutionType;

import java.util.List;
import java.util.Map;

/**
 * Created by jinhui on 2017/6/8.
 */
public interface InstitutionFileMapper {

    List<InstitutionFile> find(InstitutionFile.Criteria criteria);

    InstitutionFile findByIdentity(Map map);
}
