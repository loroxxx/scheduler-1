package com.jinhui.scheduler.web.api.controller.applyImport;

import com.alibaba.fastjson.JSON;
import com.jinhui.scheduler.biz.core.common.IFileService;
import com.jinhui.scheduler.biz.imiqian.common.Result;
import com.jinhui.scheduler.biz.imiqian.service.ImiqianJobService;
import com.jinhui.scheduler.biz.imiqian.pojo.ApplyImport;
import com.jinhui.scheduler.biz.zlrt.service.ZlrtJobService;
import com.jinhui.scheduler.data.core.mapper.imiqian.BatchStateMapper;
import com.jinhui.scheduler.data.core.mapper.imiqian.ClearErrorLogMapper;
import com.jinhui.scheduler.data.core.mapper.imiqian.ClearResultMapper;
import com.jinhui.scheduler.domain.common.ChannelCode;
import com.jinhui.scheduler.domain.common.InstitutionType;
import com.jinhui.scheduler.domain.imiqian.domain.BatchState;
import com.jinhui.scheduler.domain.imiqian.domain.ClearErrorLog;
import com.jinhui.scheduler.domain.imiqian.domain.ClearResult;
import com.jinhui.scheduler.web.api.vo.applyImport.ApplyImportVo;
import com.jinhui.scheduler.web.api.vo.file.InstitutionFileAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 *
 * 申请文件导入API
 * create by wsc 2017-06-07 14:30
 *
 **/
@RestController
@RequestMapping("/applyImport")
public class ApplyImportController {
    private static Logger logger = LoggerFactory.getLogger(ApplyImportController.class);

    @Autowired
    ImiqianJobService imiqianJobService;
    @Autowired
    ClearResultMapper clearResultMapper;
    @Autowired
    BatchStateMapper batchStateMapper;
    @Autowired
    IFileService iFileService;
    @Autowired
    ClearErrorLogMapper clearErrorLogMapper;

    /**
     * 爱蜜钱 --- 申请文件导入
     * @return
     */
    @RequestMapping(value="/imiqianApplyImport",method = RequestMethod.POST)
    @ResponseBody
    public Result<ApplyImportVo> imiqianApplyImport() throws Exception{
        logger.info("imiqian......开始导入申请文件............");

        ApplyImportVo applyImportVo = new ApplyImportVo();
        Result<ApplyImport> result = imiqianJobService.applyImport();
        ApplyImport applyImport = result.getData();
        if(result.getStatus() == 0){
            //applyImportVo.setSuccessList(InstitutionFileAssembler.toVoList(applyImport.getSuccessList()));
            logger.info("导入申请文件结果 = "+ JSON.toJSONString(Result.successData(applyImportVo)));
            return Result.successData(applyImportVo);
        }else{
            applyImportVo.setErrorLogList(result.getData().getErrorLogList());
            logger.info("导入申请文件结果 = "+ JSON.toJSONString(Result.failData(applyImportVo)));
            return Result.failData(applyImportVo);
        }
    }

    /**
     * 查询导入结果
     * @param batchDate
     * @return
     */
    @RequestMapping(value="/findImportResult",method = RequestMethod.GET)
    @ResponseBody
    public Result findImportResult(@RequestParam("batchDate") String batchDate) throws ParseException {
        logger.info("查询导入结果： batchDate=" +batchDate);

        ApplyImportVo applyImportVo = new ApplyImportVo();
        BatchState batchState = batchStateMapper.findBatchStateByDate(batchDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(batchDate));

        ClearResult clearResult = new ClearResult();
        clearResult.setBatchCode(batchState.getBatchCode());
        int count =clearResultMapper.findImportCount(clearResult);
        if(count == 2){
            //applyImportVo.setSuccessList(InstitutionFileAssembler.toVoList(iFileService.lookupFinishedFile(InstitutionType.Imiqian,calendar.getTime())));
            logger.info("导入文件结果 = "+ JSON.toJSONString(Result.successData(applyImportVo)));
            return Result.successData(applyImportVo);
        }else{
            List<ClearErrorLog> errorList = clearErrorLogMapper.findErrorLogList(String.valueOf(batchState.getBatchCode()));
            applyImportVo.setErrorLogList(errorList);
            logger.info("导入文件结果 = "+ JSON.toJSONString(Result.failData(applyImportVo)));
            return Result.failData(applyImportVo);
        }
    }



}
