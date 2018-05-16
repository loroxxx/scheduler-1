package com.jinhui.scheduler.web.api.controller.pagestep;

import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.jinhui.scheduler.data.core.mapper.core.BatchPageStepMapper;
import com.jinhui.scheduler.web.api.vo.base.WebResult;
import com.jinhui.scheduler.web.api.vo.preparing.result.ParamSettingResult;

@Controller
@RequestMapping("/pageStep")
public class PageStepController {
	private final static Logger logger = LoggerFactory.getLogger(PageStepController.class);
	
	@Autowired
	private BatchPageStepMapper batchPageStepMapper;

	/**
	 * 查询执行步骤
	 * @return 
	 */
	@RequestMapping(value="/queryPageStep",method = RequestMethod.GET)
	@ResponseBody
	public String query(){
		HashMap map=new HashMap();
		map.put("step", batchPageStepMapper.queryPageStep());
		return  JSON.toJSONString(map);
		
		
	}
	
	
	@RequestMapping(value="/updatePageStep",method=RequestMethod.POST)
	@ResponseBody
	public WebResult update(@RequestParam("step") String step) {
		
		HashMap map=new HashMap();
		map.put("step", step);
		map.put("updateTime" , new Date());
		batchPageStepMapper.updatePageStep(map);
		
		WebResult result=new ParamSettingResult();
		result.setResultCode("0");
		return result;
		
	}
	
	
	
	
}
