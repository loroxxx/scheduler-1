package com.jinhui.scheduler.biz.cmbfae.service.position;

import java.util.List;
import com.jinhui.scheduler.domain.cmbfae.template.PositionSuContent;

public interface PositionService {



	/**
	 * 查询最新的持仓记录的统计信息
	 * ps:如果下一天是非工作日，要统计的是下一个工作日的前一日的持仓
	 * 举个栗子：今天是周五清算，那么给交易所的是周日那天的持仓，但文件的日期还是周五
	 * @return
	 */
	List<PositionSuContent> querySummarize();
	
	
}
