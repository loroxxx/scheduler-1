package com.jinhui.scheduler.data.core.mapper.imiqian;


import com.jinhui.scheduler.domain.imiqian.pojo.JzProductFileInfo;

/**
 * Created by Administrator on 2017/3/6.
 */
public interface JzProductFileMapper {
    void save(JzProductFileInfo jzProductFileInfo);
    void update(JzProductFileInfo jzProductFileInfo);
    void delete(JzProductFileInfo jzProductFileInfo);
    int findListCount(JzProductFileInfo jzProductFileInfo);
}
