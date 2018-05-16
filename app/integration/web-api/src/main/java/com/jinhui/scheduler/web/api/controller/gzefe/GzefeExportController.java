package com.jinhui.scheduler.web.api.controller.gzefe;

import com.jinhui.scheduler.biz.core.common.IFileService;
import com.jinhui.scheduler.biz.gzefe.service.export.ExportService;
import com.jinhui.scheduler.biz.gzefe.service.gather.BizGatherService;
import com.jinhui.scheduler.biz.imiqian.common.Result;

import com.jinhui.scheduler.domain.common.InstitutionFile;
import com.jinhui.scheduler.domain.common.InstitutionType;
import com.jinhui.scheduler.web.api.vo.base.SimpleWebResult;
import com.jinhui.scheduler.web.api.vo.base.WebConstants;
import com.jinhui.scheduler.web.api.vo.confirmExport.ConfirmExportVo;
import com.jinhui.scheduler.web.api.vo.file.InstitutionFileAssembler;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/11/28 0028.
 */
@Controller
@RequestMapping("/gzefe")
public class GzefeExportController {

    private final static Logger logger = LoggerFactory.getLogger(GzefeExportController.class);

    @Autowired
    private ExportService exportService;


    @Autowired
    IFileService iFileService;

    /**
     * 股交文件报送
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    @ResponseBody
    public SimpleWebResult export(String batchDate) throws Exception {


        //生成股交文件并上传至ftp
        boolean flag = exportService.export(batchDate);

        SimpleWebResult result = new SimpleWebResult();

        if (flag) {
            result.setResultCode(WebConstants.RESULT_SUCCESS_CODE);
            result.setMessage("导出贵股交文件成功");
        } else {
            result.setResultCode(WebConstants.RESULT_FAIL_CODE);
            result.setMessage("导出贵股交文件失败");
        }

        return result;
    }


    /**
     * 股交文件查询
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public Result query(@RequestParam("batchDate") String batchDate) throws Exception {

        DateTime parse = DateTime.parse(batchDate, DateTimeFormat.forPattern("yyyy-MM-dd"));
        Date date = parse.toDate();
        ConfirmExportVo confirmExportVo = new ConfirmExportVo();
        confirmExportVo.setUploadFiles(InstitutionFileAssembler.toVoList(iFileService.lookupUploadFile(InstitutionType.Gzefe, date)));
        return Result.successData(confirmExportVo);
    }


    /**
     * 查询导出文件是否成功
     */
    @RequestMapping(value = "/findExportResult", method = RequestMethod.GET)
    @ResponseBody
    public SimpleWebResult findGzefeExportResult(@RequestParam("batchDate") String batchDate) {

        DateTime dt = DateTime.parse(batchDate, DateTimeFormat.forPattern("yyyy-MM-dd"));

        List<InstitutionFile> files = iFileService.lookupUploadFile(InstitutionType.Gzefe, dt.toDate());
        SimpleWebResult result = new SimpleWebResult();
        if (files.size() == 5) {
            result.setResultCode(WebConstants.RESULT_SUCCESS_CODE);
            result.setMessage("导出贵股交文件成功");
        } else {
            result.setResultCode(WebConstants.RESULT_SUCCESS_CODE);
            result.setMessage("导出贵股交文件失败");
        }

        return result;
    }


}
