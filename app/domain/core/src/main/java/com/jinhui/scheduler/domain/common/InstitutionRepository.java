package com.jinhui.scheduler.domain.common;

/**
 * Created by jinhui on 2017/6/9.
 */
public interface InstitutionRepository {
    InstitutionFile findFileByIdentity(String filePattern, InstitutionType institutionType);
}
