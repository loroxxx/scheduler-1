package com.jinhui.scheduler.domain.core;

import com.jinhui.scheduler.domain.common.InstitutionType;

import java.util.Date;

public class GeneratedFile extends AbstractBatchFile {

    public GeneratedFile(String fileName, InstitutionType institution, String backupLocation, Date batchDate) {
        super(fileName, institution, backupLocation,batchDate);
    }

    @Override
    public String toString() {
        return "GeneratedFile{" + "fileName='" + getFileName() + '\'' + ", institution='" + getInstitution() + '\''
                + ", backupLocation='" + getBackupLocation() + '\'' + '}';
    }

    GeneratedFile() {
        // Needed by ORM framework
    }
}
