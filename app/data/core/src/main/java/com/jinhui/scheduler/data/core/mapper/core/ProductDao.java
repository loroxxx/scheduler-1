package com.jinhui.scheduler.data.core.mapper.core;

import com.jinhui.scheduler.domain.core.Product;
import com.jinhui.scheduler.domain.core.ProductQueryParam;
import com.jinhui.scheduler.domain.core.ProductVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> selectAll();

    /**
     * 条件查询,product的全属性加上chn_product的部分属性
     */
    List<ProductVo> selectByQueryParam(ProductQueryParam param);

    /**
     *根据产品代码查询产品信息,包括定期和活期信息
     */
    ProductVo selectAllInfoByProductNo(@Param("productNo") String productNo);

    /**
     *根据产品代码查询产品信息
     */
    Product selectInfoByProductNo(@Param("productNo") String productNo);


    /**
     *根据产品代码和交易所查询活期产品信息（只查询活期的）
     */
    List<Product> selectCurrent(@Param("productNo") String productNo, @Param("exchangeNo") String exchangeNo);


    /**
     * 根据产品代码更新收益率和差额
     */
    int updateIncomeByNo(Product record);

}