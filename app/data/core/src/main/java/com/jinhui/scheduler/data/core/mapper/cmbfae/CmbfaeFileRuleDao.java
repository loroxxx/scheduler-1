package com.jinhui.scheduler.data.core.mapper.cmbfae;

import org.apache.ibatis.annotations.Param;

import com.jinhui.scheduler.domain.cmbfae.model.CmbfaeFileRule;

/**
 * 控制每天的文件生成标识
 * 
 * @author luoyuanq
 * @date 2017-06-15 17:57:39
 */
public interface CmbfaeFileRuleDao  {
	
	public CmbfaeFileRule queryCmbfaeFileRule(@Param("batchDate") String batchDate);
	
	public int save(CmbfaeFileRule rule);
	
	public int update(@Param("flag") String flag,@Param("batchDate") String batchDate);

	public int updateResult(@Param("flag") String flag,@Param("batchDate") String batchDate,@Param("result") String result);
	
	
}
