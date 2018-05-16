package com.jinhui.scheduler.domain.common;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by jinhui on 2017/5/23.
 */
public enum InstitutionType implements IInstitution, IFile {

    Cmbfae("招银前海"){
        @Override
        public Type type() {
            return Type.TA;
        }

        @Override
        public String fileNameWithoutSeq(String filePattern, Date batchDate) {
            return String.format(filePattern.replace("(\\d{8})", "%1$tY%1$tm%1$td"), batchDate);
        }

        @Override
        public String filePattern(String fileName) {
            String patterns[] = fileName.split("\\d{8}",2);
            String filePattern = patterns[0] + "(\\d{8})" + patterns[1];
            patterns = filePattern.split("\\d{3}[.]", 2);
            return patterns[0] + "(\\d{3})." + patterns[1];
        }

        @Override
        public String downloadFilePath(String basePath, Date batchDate) {
            String date = new SimpleDateFormat("yyyyMMdd").format(batchDate);
            return basePath + "/" + date;
        }

        @Override
        public String uploadFilePath(String basePath, Date batchDate) {
            String date = new SimpleDateFormat("yyyyMMdd").format(batchDate);
            return basePath + "/" + date;
        }

        @Override
        public boolean multiBatch() {
            return true;
        }

        @Override
        public String fileNameWithSeq(String fileNamePatternWithDate, int seq) {
            if(!multiBatch())
                throw new UnsupportedOperationException("该机构不支持一天多批次文件");
            return fileNamePatternWithDate.replace("(\\d{3})", String.format("%03d", seq));
        }
    },
    Imiqian("爱蜜钱") {
        @Override
        public Type type() {
            return Type.Channel;
        }

        @Override
        public String fileNameWithoutSeq(String filePattern, Date batchDate) {
            String fileKeyPatternWithDate = String.format(filePattern.replace("(\\d{8})", "%1$tY%1$tm%1$td"),
                    batchDate);
            return fileKeyPatternWithDate.replace("(\\d{4})", ChannelCode.typeOf(this).getCode());
        }

        @Override
        public String filePattern(String fileName) {
            String patterns[] = fileName.split("\\d{8}", 2);
            String filePattern = patterns[0] + "(\\d{8})" + patterns[1];
            patterns = filePattern.split("\\d{4}", 2);
            return patterns[0] + "(\\d{4})" + patterns[1];
        }

        @Override
        public String fileNameWithSeq(String fileNamePatternWithDate, int seq) {
            throw new UnsupportedOperationException("该机构不支持一天多批次文件");
        }
    },
    XWBank("新网银行") {
        @Override
        public Type type() {
            return Type.Channel;
        }

        @Override
        public String fileNameWithoutSeq(String filePattern, Date batchDate) {
            String fileKeyPatternWithDate = String.format(filePattern.replace("(\\d{8})", "%1$tY%1$tm%1$td"),
                    batchDate);
            return fileKeyPatternWithDate.replace("(\\d{4})", ChannelCode.typeOf(this).getCode());
        }

        @Override
        public String filePattern(String fileName) {
            String patterns[] = fileName.split("\\d{8}", 2);
            String filePattern = patterns[0] + "(\\d{8})" + patterns[1];
            patterns = filePattern.split("\\d{4}", 2);
            return patterns[0] + "(\\d{4})" + patterns[1];
        }

        @Override
        public String fileNameWithSeq(String fileNamePatternWithDate, int seq) {
            throw new UnsupportedOperationException("该机构不支持一天多批次文件");
        }
    },
    ZLRT("证联") {
        //// TODO: 2017/12/6
        @Override
        public String fileNameWithoutSeq(String filePattern, Date batchDate) {
            String filename = String.format(filePattern.replace("(\\d{8})", "%1$tY%1$tm%1$td"),
                    batchDate);
            return filename;
        }

        @Override
        public String filePattern(String fileName) {
            String patterns[] = fileName.split("_\\d{8}",2);
            String filePattern = patterns[0] + "_(\\d{8})" + patterns[1];
            return filePattern;
        }

        @Override
        public String fileNameWithSeq(String fileNamePatternWithDate, int seq) {
            throw new UnsupportedOperationException("该机构不支持一天多批次文件");
        }

        @Override
        public String downloadFilePath(String basePath, Date batchDate) {
            String date = new SimpleDateFormat("yyyyMMdd").format(batchDate);
            return basePath;// + "/" + date;
        }

        @Override
        public String childDirByName(String name) {
            if(name.startsWith("redemption")) {
                return File.separator + "REDEEM_SETTLE";
            } else if(name.startsWith("purchase")) {
                return File.separator + "PURCHASE_SETTLE";
            } else if(name.startsWith("Redeem")) {
                return File.separator + "REDEEM";
            } else if(name.startsWith("RECONCILIATION")) {
                return File.separator + "RECONCILIATION";
            }
            throw new IllegalArgumentException("未知类型文件: "+ name);
        }

        @Override
        public String downloadChildDir() {
            return File.separator + "RECONCILIATION";
        }

        @Override
        public String uploadFilePath(String basePath, Date batchDate) {
            String date = new SimpleDateFormat("yyyyMMdd").format(batchDate);
            return basePath;// + "/" + date;
        }
        
        @Override
        public Type type() {
            return Type.Pay;
        }
    },

    Gzefe("贵股交") {
        //// TODO: 2017/12/6
        @Override
        public String fileNameWithoutSeq(String filePattern, Date batchDate) {
            String filename = String.format(filePattern.replace("(\\d{8})", "%1$tY%1$tm%1$td"),
                    batchDate);
            return filename;
        }

        @Override
        public String filePattern(String fileName) {
            String patterns[] = fileName.split("_\\d{8}",2);
            String filePattern = patterns[0] + "_(\\d{8})" + patterns[1];
            return filePattern;
        }

        @Override
        public String fileNameWithSeq(String fileNamePatternWithDate, int seq) {
            throw new UnsupportedOperationException("该机构不支持一天多批次文件");
        }

        @Override
        public String downloadFilePath(String basePath, Date batchDate) {
            String date = new SimpleDateFormat("yyyyMMdd").format(batchDate);
            return basePath;// + "/" + date;
        }

        @Override
        public String uploadFilePath(String basePath, Date batchDate) {
            String date = new SimpleDateFormat("yyyyMMdd").format(batchDate);
            return basePath;// + "/" + date;
        }

        @Override
        public Type type() {
            return Type.TA;
        }
    };








    public static void main(String[] strs){
        String filename = Imiqian.fileNameWithoutSeq("OFI_(\\d{4})_JFB666_(\\d{8}).TXT.TXT", new Date());
        System.out.println(filename);
        String filePattern = Imiqian.filePattern(filename);
        System.out.println(filePattern);
        /*OFI_(\\d{4})_JFB666_(\\d{8}).TXT
        System.out.println(Imiqian.fileNameDateFormat().format(new Date()));
        System.out.println(Cmbfae.fileNameDateFormat().format(new Date()));*/
        System.out.println(InstitutionType.Imiqian.institutionCode().code());
        System.out.println(InstitutionType.ZLRT.filePattern("RECONCILIATION_B00000603_20170612.txt"));
    }

    private String text;

    InstitutionType(String text){
        this.text = text;
    }

    public String getText(){
        return this.text;
    }

    public String getAbbr(){
        return this.toString().toLowerCase();
    }

    @Override
    public InstitutionType institutionType() {
        return this;
    }

    @Override
    public InstitutionCode institutionCode() {
        return InstitutionCode.typeOf(this);
    }

    public static InstitutionType codeOf(String institution){
        for(InstitutionType ins : InstitutionType.values()){
            if(ins.getAbbr().equals(institution.toLowerCase())){
                return ins;
            }
        }
        throw new IllegalArgumentException("不支持机构");
    }

    @Override
    public DateFormat fileNameDateFormat() {
        return new SimpleDateFormat("yyyyMMdd");
    }

    @Override
    public boolean multiBatch() {
        return false;
    }

    @Override
    public String downloadFilePath(String basePath, Date batchDate) {
        String date = new SimpleDateFormat("yyyyMMdd").format(batchDate);
        return basePath + "/download/" + date;
    }

    @Override
    public String uploadFilePath(String basePath, Date batchDate) {
        String date = new SimpleDateFormat("yyyyMMdd").format(batchDate);
        return basePath + "/upload/" + date;
    }

    @Override
    public String childDirByName(String name) {
        return "";
    }

    @Override
    public String downloadChildDir() {
        return "";
    }


}
