package com.jinhui.scheduler.biz.cmbfae.service.trans;

import java.util.List;
import com.jinhui.scheduler.domain.cmbfae.template.TransSuContent;


public interface TransService {


	/**
	 * 查询当日跑批的交易的统计信息
	 * @return
	 */
	List<TransSuContent> querySummarize();


	/**
	 * 查询当前批次有没有交易信息
	 * @return
	 */
	boolean hasTrans();


	
}
