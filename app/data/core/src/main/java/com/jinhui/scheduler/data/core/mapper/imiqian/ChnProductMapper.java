package com.jinhui.scheduler.data.core.mapper.imiqian;


import com.jinhui.scheduler.domain.imiqian.domain.ChnProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChnProductMapper {

    //根据产品代码和渠道代码查询产品名称
    List<ChnProduct> findList(@Param("productNo") String productNo, @Param("chnCode") String chnCode);

    //查询所有产品
    List<ChnProduct> findAllProduct();

}