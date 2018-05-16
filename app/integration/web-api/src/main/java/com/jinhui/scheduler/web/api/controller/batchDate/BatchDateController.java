package com.jinhui.scheduler.web.api.controller.batchDate;

import java.util.HashMap;

import com.jinhui.scheduler.biz.core.batch.BatchStateService;
import com.jinhui.scheduler.web.api.controller.confirmExport.ConfrimExportController;
import com.jinhui.scheduler.web.api.vo.base.WebConstants;
import com.jinhui.scheduler.web.api.vo.preparing.result.BaseResult;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinhui.scheduler.biz.core.common.HolidayService;
import com.jinhui.scheduler.domain.cmbfae.model.BatchState;


@Controller
@RequestMapping("/batchDate")
public class BatchDateController {

	private static Logger logger = LoggerFactory.getLogger(BatchDateController.class);

	@Autowired
	private HolidayService holidayService;

	@Autowired
	private BatchStateService batchStateService;
	
	/**
	 * 查询batch_state_current的批次日期，只有在“开始下一批次”时才会更新此日期
	 * */
    @RequestMapping(value="/queryBatchDate",method = RequestMethod.GET)
    @ResponseBody
    public BaseResult getBatchDate() throws Exception{
		BaseResult<HashMap> result=new BaseResult<>();
		try{
			BatchState current = batchStateService.getBatchStateCurrent();
			DateTime start = new DateTime(current.getDate());

			HashMap<String, String> map = new HashMap<>();
			map.put("start", start.toString("yyyy-MM-dd"));
			Boolean isWorkDay = holidayService.isWorkDay(start.plusDays(1).toString("yyyyMMdd"));
			if (isWorkDay) {
				map.put("end", "");
			} else {
				String nextWorkDay = holidayService.getNextWorkDay(start.toString("yyyyMMdd"));
				DateTime dt = DateTime.parse(nextWorkDay, DateTimeFormat.forPattern("yyyyMMdd"));
				map.put("end", dt.minusDays(1).toString("yyyy-MM-dd"));

			}
			result.setMessage("查询成功");
			result.setResultCode(WebConstants.RESULT_SUCCESS_CODE);
			result.setData(map);

			return result;
		}catch (Exception e){
			logger.error("查询当前批次时间错误",e);
			result.setMessage("查询失败");
			result.setResultCode(WebConstants.RESULT_FAIL_CODE);
			return result;
		}

    }
    
	
}
