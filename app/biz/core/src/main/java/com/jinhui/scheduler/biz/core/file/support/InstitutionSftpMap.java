package com.jinhui.scheduler.biz.core.file.support;

import com.jinhui.scheduler.domain.common.InstitutionType;

import java.util.Map;

/**
 * Created by jinhui on 2017/6/5.
 */
public class InstitutionSftpMap {

    /**
     * key : 小写字母的机构简写,  value : 对应机构 sftp客户端
     */
    private Map<String, Sftp> sftpMap;

    public void setSftpMap(Map<String, Sftp> sftpMap) {
        this.sftpMap = sftpMap;
    }

    public Sftp getInstitution(InstitutionType institution){
        return sftpMap.get(institution.getAbbr());
    }
}
