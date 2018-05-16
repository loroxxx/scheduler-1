package com.jinhui.scheduler.biz.core.task;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.repository.JobRepository;

public class CrashedJobScanTask {
    /** Logger */
    private final static Logger logger = LoggerFactory.getLogger(CrashedJobScanTask.class);

    private JobExplorer         jobExplorer;
    private JobRepository       jobRepository;
    private JobOperator         jobOperator;
    private int                 jobTimeoutMinutes;

    public void scan() {
        List<String> jobs = jobExplorer.getJobNames();
        for (String job : jobs) {
            Set<JobExecution> runningJobs = jobExplorer.findRunningJobExecutions(job);

            for (JobExecution runningJob : runningJobs) {
                Date now = new Date();
                Date lastUpdated = runningJob.getLastUpdated();
                long elapsedMinutes = (now.getTime()-lastUpdated.getTime())/ (60 * 1000);
                if (elapsedMinutes > jobTimeoutMinutes) {
                    try {
                        runningJob.setStatus(BatchStatus.FAILED);
                        runningJob.setEndTime(now);
                        jobRepository.update(runningJob);
                        jobOperator.restart(runningJob.getId());
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    }
                }
            }
        }
    }

    public void setJobExplorer(JobExplorer jobExplorer) {
        this.jobExplorer = jobExplorer;
    }

    public void setJobRepository(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public void setJobOperator(JobOperator jobOperator) {
        this.jobOperator = jobOperator;
    }

    public void setJobTimeoutMinutes(int jobTimeoutMinutes) {
        this.jobTimeoutMinutes = jobTimeoutMinutes;
    }
}
