package com.jinhui.scheduler.data.core.mapper.cmbfae;

import java.util.List;
import com.jinhui.scheduler.domain.cmbfae.TransStatistics;
import org.apache.ibatis.annotations.Param;

import com.jinhui.scheduler.domain.cmbfae.template.TransDetailContent;



/**
 * 投资人交易记录
 *
 */
public interface TransDao {
	


	/**
	 *根据批次，交易所，查询交易所下的子产品，根据子产品代码，交易类型分组统计交易金额和笔数
	 * @return
	 */
	List<TransStatistics> queryTransStatistics(@Param("exchangeNo") String exchangeNo,@Param("batchCode") int batchCode);


	/**
	 * 根据批次，交易所，分页查询交易所下的产品的交易记录
	 */
	List<TransDetailContent> queryTransDetail(@Param("exchangeNo") String exchangeNo,@Param("batchCode") int batchCode);


	/**
	 *查询这个批次下的某个交易所下的产品的交易纪录数目
	 */
	int queryTransCount(@Param("batchCode") int batchCode, @Param("exchangeNo") String exchangeNo);

}
