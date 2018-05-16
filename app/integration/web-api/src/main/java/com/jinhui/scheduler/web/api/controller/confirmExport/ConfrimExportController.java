package com.jinhui.scheduler.web.api.controller.confirmExport;

import com.alibaba.fastjson.JSON;
import com.jinhui.scheduler.biz.core.common.HolidayService;
import com.jinhui.scheduler.biz.core.common.IFileService;
import com.jinhui.scheduler.biz.imiqian.common.Result;
import com.jinhui.scheduler.biz.imiqian.pojo.ConfrimExport;
import com.jinhui.scheduler.biz.imiqian.service.ImiqianJobService;
import com.jinhui.scheduler.biz.imiqian.utils.DateUtils;
import com.jinhui.scheduler.biz.zlrt.sync.HttpSyncHelper;
import com.jinhui.scheduler.data.core.mapper.imiqian.BatchStateCurrentMapper;
import com.jinhui.scheduler.data.core.mapper.imiqian.BatchStateMapper;
import com.jinhui.scheduler.data.core.mapper.imiqian.ClearResultMapper;
import com.jinhui.scheduler.domain.common.ChannelCode;
import com.jinhui.scheduler.domain.common.InstitutionType;
import com.jinhui.scheduler.domain.imiqian.domain.BatchState;
import com.jinhui.scheduler.domain.imiqian.domain.BatchStateCurrent;
import com.jinhui.scheduler.domain.imiqian.domain.ClearResult;
import com.jinhui.scheduler.web.api.controller.clear.ClearController;
import com.jinhui.scheduler.web.api.vo.confirmExport.ConfirmExportVo;
import com.jinhui.scheduler.web.api.vo.file.InstitutionFileAssembler;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * 确认文件导出API
 * create by wsc 2017-06-07 14:54
 *
 **/
@RestController
@RequestMapping("confirmExport")
public class ConfrimExportController {
    private static Logger logger = LoggerFactory.getLogger(ConfrimExportController.class);

    @Autowired
    ImiqianJobService imiqianJobService;
    @Autowired
    BatchStateMapper batchStateMapper;
    @Autowired
    BatchStateCurrentMapper batchStateCurrentMapper;
    @Autowired
    HolidayService holidayService;
    @Autowired
    ClearResultMapper clearResultMapper;
    @Autowired
    IFileService iFileService;

    /**
     * 爱蜜钱 --- 导出确认文件
     * @return
     */
    @RequestMapping(value="/imiqianConfirmExport",method = RequestMethod.POST)
    @ResponseBody
    public Result<ConfirmExportVo> imiqianConfirmExport() throws Exception{
        logger.info("imiqian......开始导出确认文件............");

        ConfirmExportVo confirmExportVo = new ConfirmExportVo();
        Result<ConfrimExport> result = imiqianJobService.confirmExport();
        ConfrimExport confirmExport = result.getData();
        confirmExportVo.setErrorMsg(confirmExport.getErrorMsg());
        if(result.getStatus() == 0){
            //confirmExportVo.setUploadFiles(InstitutionFileAssembler.toVoList(confirmExport.getUploadFiles()));
            logger.info("导出确认文件结果 = "+ JSON.toJSONString(Result.successData(confirmExportVo)));
            return Result.successData(confirmExportVo);
        }else{
            logger.info("导出确认文件结果 = "+ JSON.toJSONString(Result.failData(confirmExportVo)));
            confirmExportVo.setErrorMsg(confirmExport.getErrorMsg());
            return Result.failData(confirmExportVo);
        }
    }

    /**
     *
     * @return
     */
    @RequestMapping(value="/executeNextBatch",method = RequestMethod.GET)
    @ResponseBody
    public String executeNextBatch(){
        try {
            //同步渠道产品数据到交易平台系统
            HttpSyncHelper.syncChnProduct();
            //同步T日客户和交易状态到交易平台系统
            HttpSyncHelper.syncStatus();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //更新当前批次
        BatchState batchState = batchStateMapper.findNewest();
        batchStateCurrentMapper.delete();
        BatchStateCurrent batchStateCurrent = new BatchStateCurrent();
        batchStateCurrent.setBatchCode(batchState.getBatchCode());
        batchStateCurrent.setDate(batchState.getDate());
        batchStateCurrent.setCreateTime(DateUtils.getCurrentDatetime());
        batchStateCurrentMapper.save(batchStateCurrent);

        BatchState state = batchStateMapper.findNewest();
        HashMap<String,String> map=new HashMap<>();
        if(holidayService.isWorkDay(DateUtils.getNextDate(state.getDate()))){
            map.put("start",DateUtils.formatDate(state.getDate()));
            map.put("end","");
        }else{
            map.put("start",DateUtils.formatDate(state.getDate()));
            map.put("end",DateUtils.formatDate(DateUtils.getPreDate(holidayService.getNextWorkDay(state.getDate()))));
        }
        return JSON.toJSONString(map);

    }

    /**
     * 查询导出文件列表
     * @param batchDate
     * @return
     */
    @RequestMapping(value="/findExportFiles",method = RequestMethod.GET)
    @ResponseBody
    public Result findExportFiles(@RequestParam("batchDate") String batchDate,@RequestParam("chnCode") String chnCode) throws ParseException {
        logger.info("查询导出文件列表： batchDate=" +batchDate);
        ConfirmExportVo confirmExportVo = new ConfirmExportVo();
        BatchState batchState = batchStateMapper.findBatchStateByDate(batchDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(batchDate));

        ClearResult clearResult = new ClearResult();
        clearResult.setBatchCode(batchState.getBatchCode());
        clearResult.setChnCode(chnCode);
        int count =clearResultMapper.findExportCount(clearResult);
        if(count == 5){
            confirmExportVo.setUploadFiles(InstitutionFileAssembler.toVoList(iFileService.lookupUploadFile(ChannelCode.codeOf(chnCode).getType(),calendar.getTime())));
            logger.info("导出确认文件结果 = "+ JSON.toJSONString(Result.successData(confirmExportVo)));
            return Result.successData(confirmExportVo);
        }else{
            logger.info("导出确认文件结果 = "+ JSON.toJSONString(Result.failData(confirmExportVo)));
            return Result.failData(confirmExportVo);
        }
    }

    /**
     * 查询导出结果
     * @param batchDate
     * @return
     */
    @RequestMapping(value="/findExportResult",method = RequestMethod.GET)
    @ResponseBody
    public Result findExportResult(@RequestParam("batchDate") String batchDate){
        logger.info("查询导出是否成功： batchDate=" +batchDate);
        Result result = imiqianJobService.findExportResult(batchDate);
        logger.info("输出 = "+ JSON.toJSONString(result));
        return result;
    }

}
