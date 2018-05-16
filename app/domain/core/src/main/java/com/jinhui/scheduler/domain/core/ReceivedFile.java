package com.jinhui.scheduler.domain.core;

import com.jinhui.scheduler.domain.common.InstitutionType;

import java.util.Date;

import javax.annotation.Nullable;

public class ReceivedFile extends AbstractBatchFile {
    private Date   receiveTime;

    public ReceivedFile(String name, InstitutionType institution, String backupLocation, Date batchDate) {
        super(name, institution, backupLocation, batchDate);
        this.receiveTime = new Date();
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    @Override
    public String toString() {
        return "ReceivedFile{" + "name='" + getFileName() + '\'' + ", institution='" + getInstitution() + '\''
               + ", receiveTime=" + receiveTime
               + ", backupLocation='" + getBackupLocation() + '\'' + '}';
    }


    ReceivedFile() {
        // Needed by ORM framework
    }
}
