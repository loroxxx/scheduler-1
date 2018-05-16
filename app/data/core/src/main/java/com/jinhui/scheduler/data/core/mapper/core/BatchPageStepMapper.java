package com.jinhui.scheduler.data.core.mapper.core;

import java.util.Map;

/**
 * 保存前端页面操作步骤
 * 
 * @author luoyuanq
 * @date 2017-06-14 15:43:18
 */
public interface BatchPageStepMapper  {
	
	
	public String queryPageStep();
	
	public int updatePageStep(Map map);
	
	public int savePageStep(Map map);
	
	
	
}
