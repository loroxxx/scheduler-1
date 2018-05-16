package com.jinhui.scheduler.data.core.mapper.core;


import com.jinhui.scheduler.domain.core.ProductDivided;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScheduleProductDividedMapper {
    int insert(ProductDivided record);

    int insertSelective(ProductDivided record);


   /**
     * 查询子产品中最大的一组
     */
    ProductDivided selectMaxRecord(@Param("productNo") String productNo);



    /**
     * 查询子产品当前使用的组数
     */
    ProductDivided selectCurrent(@Param("productNo") String productNo);


    /**
     * 批量插入
     */
    int insertList(List<ProductDivided> list);


    /**
     * 根据状态和产品代码查询子产品数量
     */
    int  selectCountByState(@Param("productNo") String productNo, @Param("state") String state);


    /**
     *查询总数量
     */
    int selectCount(@Param("productNo") String productNo);


}