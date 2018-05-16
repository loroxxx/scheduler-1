package com.jinhui.scheduler.domain.core;

import com.jinhui.scheduler.domain.common.InstitutionType;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AbstractBatchFile {
    private static final String  separator = File.separator;
    public static final String[] SCAN_FLAG = { ".OK", ".end" };

    private Integer              fileId;
    private String               fileName;
    private InstitutionType               institution;
    private String               backupLocation;
    private Date batchDate;

    public AbstractBatchFile(String fileName, InstitutionType institution, String backupLocation, Date batchDate) {
        this.fileName = fileName;
        this.institution = institution;
        this.backupLocation = backupLocation;
        this.batchDate = batchDate;
    }

    public static String takeFileKey(String name, String institution) {
        return institution + separator + name;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public InstitutionType getInstitution() {
        return institution;
    }

    public void setInstitution(InstitutionType institution) {
        this.institution = institution;
    }

    public String getBackupLocation() {
        return backupLocation;
    }

    public void setBackupLocation(String backupLocation) {
        this.backupLocation = backupLocation;
    }

    public Date getBatchDate() {
        return batchDate;
    }

    public void setBatchDate(Date batchDate) {
        this.batchDate = batchDate;
    }

    public static String inferFileKey(File file) {
        String fileName = file.getName();
        String institution = inferInstitution(file);
        return institution + separator + fileName;
    }

    public static String inferInstitution(File file) {
        return file.getParentFile().getName();
    }

    public static Date inferBatchDate(String institution, File file){
        InstitutionType institutionType = InstitutionType.codeOf(institution);
        if(institutionType==InstitutionType.Cmbfae
                || institutionType==InstitutionType.Imiqian
                || institutionType==InstitutionType.Gzefe
                || institutionType==InstitutionType.ZLRT
                || institutionType==InstitutionType.XWBank) {
            Pattern r = Pattern.compile("_\\d{8}");
            String filename = file.getName();
            Matcher m = r.matcher(filename);
            if (m.find()) {
                SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
                try {
                    return yyyyMMdd.parse(m.group().substring(1));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        throw new IllegalArgumentException("获取批次日期错误");
    }

    public static String inferUploadInstitution(File file) {
        return file.getParentFile().getName();
    }

    public static String extractInstitution(String fileKey) {
        int separatorPos = fileKey.indexOf(separator);
        return fileKey.substring(0, separatorPos);
    }

    public static String extractFileName(String fileKey) {
        int separatorPos = fileKey.indexOf(separator);
        return fileKey.substring(separatorPos + 1);
    }

    public static List<File> correspondingScanFlagFile(File file) {
        String absolutePath = file.getAbsolutePath();
        List<File> fileList = new ArrayList<>();
        for (String fileFlag : SCAN_FLAG) {
            fileList.add(new File(absolutePath + fileFlag));
        }
        return fileList;
    }

    public static File correspondingScanFlagFile(File file, String scanFlag) {
        String absolutePath = file.getAbsolutePath();
        absolutePath = absolutePath.substring(0, absolutePath.lastIndexOf("."));
        return new File(absolutePath + scanFlag);
    }

    public static File correspondingBatchFile(File scanFlagFile) {
        String scanFlagFilePath = scanFlagFile.getAbsolutePath();
        String fileFlag = null;
        for (String flag : SCAN_FLAG) {
            if (scanFlagFilePath.endsWith(flag)) {
                fileFlag = flag;
                break;
            }

        }
        if (fileFlag != null) {
            String filePath = scanFlagFilePath.substring(0,
                scanFlagFilePath.length() - fileFlag.length());
            return new File(filePath);
        }
        return null;
    }

    public static boolean isScanFlagFile(File file) {
        String fileName = file.getName();
        for (String flag : SCAN_FLAG) {
            if (fileName.endsWith(flag))
                return true;
        }
        return false;
    }

    public static Criteria criteria(){
        return new Criteria();
    }

    public static class Criteria{
        private InstitutionType institution;
        private String fileName;
        private Date batchDate;
        public Criteria institution(InstitutionType institution){
            this.institution = institution;
            return this;
        }
        public Criteria fileName(String fileName){
            this.fileName = fileName;
            return this;
        }
        public Criteria batchDate(Date batchDate){
            this.batchDate = batchDate;
            return this;
        }

        public InstitutionType getInstitution() {
            return institution;
        }

        public String getFileName() {
            return fileName;
        }

        public Date getBatchDate() {
            return batchDate;
        }

        @Override
        public String toString() {
            return "Criteria{" +
                    "institution=" + institution +
                    ", fileName='" + fileName + '\'' +
                    ", batchDate=" + batchDate +
                    '}';
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof AbstractBatchFile))
            return false;
        AbstractBatchFile that = (AbstractBatchFile) o;
        return Objects.equals(getFileName(), that.getFileName())
               && Objects.equals(getInstitution(), that.getInstitution());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFileName(), getInstitution());
    }

    AbstractBatchFile() {
        // Needed by ORM framework
    }

    public static String getFilePath(String basePath, String fileKey) {
        return basePath + separator + fileKey;
    }
}
