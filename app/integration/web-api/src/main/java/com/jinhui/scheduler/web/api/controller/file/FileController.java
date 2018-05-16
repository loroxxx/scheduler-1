package com.jinhui.scheduler.web.api.controller.file;

import com.jinhui.scheduler.biz.core.common.IFileService;
import com.jinhui.scheduler.domain.common.InstitutionFile;
import com.jinhui.scheduler.domain.common.InstitutionType;
import com.jinhui.scheduler.web.api.vo.base.WebConstants;
import com.jinhui.scheduler.web.api.vo.file.InstitutionFileAssembler;
import com.jinhui.scheduler.web.api.vo.file.InstitutionFileResult;
import com.jinhui.scheduler.web.api.vo.file.InstitutionFileVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by jinhui on 2017/6/12.
 */
@RestController
@RequestMapping(value = "/file")
@Api(value = "preparing", description = "文件相关接口")
public class FileController {

    private final static Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private IFileService fileService;

    @ApiOperation(value = "查询清算文件", response = InstitutionFileResult.class, httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "institution", dataType = "String", required = true, value = "清算机构[Cmbfae(\"招银前海\"),Imiqian(\"爱蜜钱\"),ZLRT(\"证联\")]"),
            @ApiImplicitParam(paramType = "query", name = "batchDate", dataType = "String", required = true, value = "清算日期[格式: yyyy-MM-dd]")
    })
    @ResponseBody
    @RequestMapping(value = "/clearingFile", method = GET)
    public InstitutionFileResult clearingFile(String institution, String batchDate){
        if(logger.isInfoEnabled()) {
            logger.info("查询清算文件: institution={}, batchDate={} ",institution, batchDate );
        }

        InstitutionFileResult result = new InstitutionFileResult();
        InstitutionType institutionType = InstitutionType.codeOf(institution);
        try {
            Date date = (new SimpleDateFormat("yyyy-MM-dd")).parse(batchDate);
            List<InstitutionFile> institutionFiles =  fileService.lookupNeededFiles(institutionType, date);
            List<InstitutionFileVo> neededFiles = InstitutionFileAssembler.toVoList(institutionFiles);
            result.setNeededFiles(neededFiles);
            try {
                IFileService.FileFilter fileFilter = null;
                if(institutionType == InstitutionType.ZLRT) {
                    fileFilter = new IFileService.FileFilter() {
                        @Override
                        public boolean accept(String filename) {
                            return filename.startsWith("RECONCILIATION");
                        }};
                }
                institutionFiles = fileService.lookupDownloadFile(institutionType, date, fileFilter);
            } catch (Exception e){
                institutionFiles = Collections.EMPTY_LIST;
                logger.info("获取download文件出错:{}",e);
            }
            List<InstitutionFileVo> actualFiles = InstitutionFileAssembler.toVoList(institutionFiles);
            result.setActualFiles(actualFiles);
            result.setResultCode(WebConstants.RESULT_SUCCESS_CODE);
            if(logger.isInfoEnabled()) {
                logger.info("查询清算文件: neededFilesSize={}, actualFilesSize={} ",neededFiles.size(),
                        actualFiles.size());
            }
            return result;
        } catch (ParseException e) {
            result.setResultCode(WebConstants.RESULT_FAIL_CODE);
            result.setMessage("日期格式错误");
            return result;
        }
    }

}
