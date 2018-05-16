package com.jinhui.scheduler.data.core.mapper.imiqian;


import com.jinhui.scheduler.domain.imiqian.domain.ChnInfo;

import java.util.List;

public interface ChnInfoMapper {

    //根据渠道代码查渠道信息
    ChnInfo findChnInfo(String chnCode);

    //查询接入的渠道列表
    List<ChnInfo> findChnInfoList();

}