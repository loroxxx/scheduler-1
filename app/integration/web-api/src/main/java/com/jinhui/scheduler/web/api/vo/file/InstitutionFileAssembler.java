package com.jinhui.scheduler.web.api.vo.file;


import com.jinhui.scheduler.domain.common.InstitutionFile;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jinhui on 2017/6/12.
 */
public class InstitutionFileAssembler {

    public static List<InstitutionFileVo> toVoList(List<InstitutionFile> institutionFiles){
        List<InstitutionFileVo> voList = new ArrayList<>(institutionFiles.size());
        for(InstitutionFile institutionFile:institutionFiles){
            InstitutionFileVo vo = new InstitutionFileVo();
            BeanUtils.copyProperties(institutionFile, vo);
            vo.setInstitution(institutionFile.getInstitution().toString());
            vo.setInstitutionName(institutionFile.getInstitution().getText());
            vo.setInstitutionCode(institutionFile.getInstitution().institutionCode().code());
            vo.setFileLocation(institutionFile.getFileLocation());
            voList.add(vo);
        }
        return voList;
    }

}
