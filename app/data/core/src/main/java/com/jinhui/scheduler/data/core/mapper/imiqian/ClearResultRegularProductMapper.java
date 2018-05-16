package com.jinhui.scheduler.data.core.mapper.imiqian;


import com.jinhui.scheduler.domain.imiqian.domain.ClearResultRegularProduct;

import java.util.List;

public interface ClearResultRegularProductMapper {

    int insertSelective(ClearResultRegularProduct record);

    ClearResultRegularProduct selectByChoice(ClearResultRegularProduct record);

    List<ClearResultRegularProduct> selectListByChoice(ClearResultRegularProduct record);

}