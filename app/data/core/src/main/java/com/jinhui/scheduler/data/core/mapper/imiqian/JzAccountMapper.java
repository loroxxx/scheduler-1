package com.jinhui.scheduler.data.core.mapper.imiqian;


import com.jinhui.scheduler.domain.imiqian.pojo.JzAccountInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/3/6.
 */
public interface JzAccountMapper {
    void save(JzAccountInfo jzAccountInfo);
    void update(JzAccountInfo jzAccountInfo);
    List<JzAccountInfo> findList(JzAccountInfo jzAccountInfo);
    Integer findListCount(JzAccountInfo jzAccountInfo);
    void delete(JzAccountInfo jzAccountInfo);
}
