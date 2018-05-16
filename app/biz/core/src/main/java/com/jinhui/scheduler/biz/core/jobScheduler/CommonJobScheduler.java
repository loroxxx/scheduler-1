package com.jinhui.scheduler.biz.core.jobScheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

public class CommonJobScheduler extends QuartzJobBean {
	public final static String JOB_PARAM_START_TIME         = "start_time";
    private final static Logger logger = LoggerFactory.getLogger(CommonJobScheduler.class);

	private JobLauncher jobLauncher;

	private Job job;

	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	@Override
    protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {
	    Date startTime = ctx.getScheduledFireTime();
	    if (logger.isDebugEnabled()) {
			logger.debug("CommonJobScheduler::executeInternal" + startTime);
        }

		runJob(startTime);
	}

	private void runJob(Date start) {
	    JobParameters param = new JobParametersBuilder()
                                .addDate(JOB_PARAM_START_TIME, start)
                                .toJobParameters();
		JobExecution execution;
		try {

			execution = jobLauncher.run(job, param);

			if (logger.isDebugEnabled()) {
				logger.debug("jobName:" + job.getName());
				logger.debug("Exit Status : " + execution.getStatus());
			}
		} catch (JobExecutionAlreadyRunningException | JobRestartException
				| JobInstanceAlreadyCompleteException e) {
			return;
		} catch (JobParametersInvalidException e) {
			logger.error("jobParam:" + param);
			return;
		} catch (DuplicateKeyException | IllegalStateException ex) {
			return;
		}
	}
}