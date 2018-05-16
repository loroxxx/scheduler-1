package com.jinhui.scheduler.data.core.mapper.imiqian;


import com.jinhui.scheduler.domain.imiqian.pojo.JzProductInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/3/6.
 */
public interface JzProductMapper {
    List<String> findByProductCode(String product_code);
    void save(JzProductInfo jzProductInfo);
    void updateByProductCode(JzProductInfo jzProductInfo);
    List<JzProductInfo> findList(JzProductInfo jzProductInfo);
    Integer findListCount(JzProductInfo jzProductInfo);
    void deleteByProductCode(JzProductInfo jzProductInfo);
}
