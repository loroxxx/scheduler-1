package com.jinhui.scheduler.data.core.mapper.imiqian;


import com.jinhui.scheduler.domain.imiqian.domain.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {

    Product selectByPrimaryKey(Integer id);


   //----------------------------------------  以下为定期产品方法  ----------------------------------
    /**
     * 查询到了产品起息日待成立的定期产品(前一天为工作日)
     * @return
     */
    List<Product> selectToSetupProductList();

    /**
     * 查询到了产品起息日待成立的定期产品(前一天为非工作日)
     * @return
     */
    List<Product> selectToSetupProductListWithChoice();

    /**
     * 定期产品成立时，更新产品状态为02-存续期
     * @return
     */
    int updateProductStateToLast(@Param("productNo") String productNo);

    /**
     * 定期产品成立时，更新产品状态为03-产品终止
     * @return
     */
    int updateProductStateToOver(@Param("productNo") String productNo);

    /**
     * 根据产品代码查询产品信息
     * @return
     */
    Product selectByProductNo(@Param("productNo") String productNo);

    /**
     * 查询活期产品
     * @return
     */
    List<Product> selectLiveProduct();

}