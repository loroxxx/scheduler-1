package com.jinhui.scheduler.data.core.mapper.cmbfae;

import java.util.List;

import com.jinhui.scheduler.domain.cmbfae.model.Product;
import com.jinhui.scheduler.domain.cmbfae.template.ProductContentVo;
import com.jinhui.scheduler.domain.cmbfae.template.ProfitContent;
import org.apache.ibatis.annotations.Param;


/**
 * 产品表
 *
 */
public interface CmbfaeProductDao {
	int save(Product product);
	
//	int insertList(List list);

	/**
	 * 根据产品代码查询state=0，待确认的子产品，
	 * @return
	 */
	List<ProductContentVo> select2JsonTemplate(@Param("productNo") String productNo);


	/**招银前海的产品列表
	 * @return
	 */
	List<ProfitContent> queryProfitContentList(@Param("exchangeNo") String exchangeNo);

	List<Product> queryList(String date);
	
	Product queryProduct(Product P);
	

}
