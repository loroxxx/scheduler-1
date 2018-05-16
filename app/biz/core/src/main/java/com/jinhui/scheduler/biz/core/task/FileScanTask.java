package com.jinhui.scheduler.biz.core.task;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;

import com.jinhui.scheduler.biz.core.config.FileBatchJobMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.InitializingBean;

import com.jinhui.scheduler.domain.core.AbstractBatchFile;

public class FileScanTask implements InitializingBean {

    /** Logger */
    private final static Logger logger = LoggerFactory.getLogger(ScanFileVisitor.class);

    private String              scanPath;
    private FileBatchJobMapper fileJobMapper;
    private JobLauncher         jobLauncher;

    private ScanFileVisitor     fileVisitor;

    public void scan() {
        Path rootPath = Paths.get(scanPath);
        try {
            Files.walkFileTree(rootPath, fileVisitor);
        } catch (IOException e) {
            logger.error(String.format("扫描文件路径（%s）出现异常", rootPath), e);
        }
    }

    public void setScanPath(String scanPath) {
        this.scanPath = scanPath;
    }

    public void setFileJobMapper(FileBatchJobMapper fileJobMapper) {
        this.fileJobMapper = fileJobMapper;
    }

    public void setJobLauncher(JobLauncher jobLauncher) {
        this.jobLauncher = jobLauncher;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.fileVisitor = new ScanFileVisitor(jobLauncher, fileJobMapper);
    }

    private static class ScanFileVisitor extends SimpleFileVisitor<Path> {
        /** Logger */
        private final static Logger logger = LoggerFactory.getLogger(ScanFileVisitor.class);

        private JobLauncher         jobLauncher;
        private FileBatchJobMapper  fileJobMapper;

        public ScanFileVisitor(JobLauncher jobLauncher, FileBatchJobMapper fileJobMapper) {
            this.jobLauncher = jobLauncher;
            this.fileJobMapper = fileJobMapper;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            Objects.requireNonNull(file);
            File scanFlagFile = file.toFile();
            if (AbstractBatchFile.isScanFlagFile(scanFlagFile)) {
                File batchFile = AbstractBatchFile.correspondingBatchFile(scanFlagFile);
                if (batchFile.exists()) {
                    String fileKey = AbstractBatchFile.inferFileKey(batchFile);
                    Job job = fileJobMapper.fetchJob(fileKey);
                    if (job != null) {
                        String absolutePath = batchFile.getAbsolutePath();
                        JobParameters parameters = new JobParametersBuilder()
                                .addString("file.path", "file:///" + absolutePath).toJobParameters();
                        try {
                            jobLauncher.run(job, parameters);
                        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException ie) {
                            // ignore
                        } catch (Exception e) {
                            logger.error(String.format("对于文件（%s）启动Job（%s）失败", absolutePath, job.getName()), e);
                        }
                    } else {
                        logger.warn("文件（{}）对应的任务不存在", fileKey);
                    }
                }
            }

            return FileVisitResult.CONTINUE;
        }
    }
}
