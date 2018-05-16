/*
 * Copyright 2006-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jinhui.scheduler.biz.core;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.job.AbstractJob;
import org.springframework.batch.core.job.SimpleJob;
import org.springframework.batch.core.job.flow.FlowJob;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.test.StepRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.SyncTaskExecutor;

/**
 * <p>
 * Utility class for testing batch jobs. It provides methods for launching an
 * entire {@link AbstractJob}, allowing for end to end testing of individual
 * steps, without having to run every step in the job. Any test classes using
 * this utility can set up an instance in the {@link ApplicationContext} as part
 * of the Spring test framework.
 * </p>
 * 
 * <p>
 * This class also provides the ability to run {@link Step}s from a
 * {@link FlowJob} or {@link SimpleJob} individually. By launching {@link Step}s
 * within a {@link Job} on their own, end to end testing of individual steps can
 * be performed without having to run every step in the job.
 * </p>
 * 
 * <p>
 * It should be noted that using any of the methods that don't contain
 * {@link JobParameters} in their signature, will result in one being created
 * with the current system time as a parameter. This will ensure restartability
 * when no parameters are provided.
 * </p>
 */
public class JobLauncherTestUtils {

    private static final long   JOB_PARAMETER_MAXIMUM = 1000000;

    /** Logger */
    private final static Logger logger                = LoggerFactory
        .getLogger(JobLauncherTestUtils.class);

    private JobLauncher         jobLauncher;

    private JobRepository       jobRepository;

    private StepRunner          stepRunner;

    /**
     * The {@link JobRepository} to use for creating new {@link JobExecution}
     * instances.
     * 
     * @param jobRepository a {@link JobRepository}
     */
    @Autowired
    public void setJobRepository(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    /**
     * @return the job repository
     */
    public JobRepository getJobRepository() {
        return jobRepository;
    }

    /**
     * A {@link JobLauncher} instance that can be used to launch jobs.
     * 
     * @param jobLauncher a job launcher
     */
    @Autowired
    public void setJobLauncher(JobLauncher jobLauncher) {
        this.jobLauncher = jobLauncher;
    }

    /**
     * @return the job launcher
     */
    public JobLauncher getJobLauncher() {
        SimpleJobLauncher simpleLauncher = (SimpleJobLauncher) jobLauncher;
        simpleLauncher.setTaskExecutor(new SyncTaskExecutor());
        return jobLauncher;
    }

    /**
     * @return a new JobParameters object containing only a parameter for the
     * current timestamp, to ensure that the job instance will be unique.
     */
    public JobParameters getUniqueJobParameters() {
        Map<String, JobParameter> parameters = new HashMap<String, JobParameter>();
        parameters.put("random", new JobParameter((long) (Math.random() * JOB_PARAMETER_MAXIMUM)));
        return new JobParameters(parameters);
    }

    /**
     * Convenient method for subclasses to grab a {@link StepRunner} for running
     * steps by name.
     * 
     * @return a {@link StepRunner}
     */
    protected StepRunner getStepRunner() {
        if (this.stepRunner == null) {
            this.stepRunner = new StepRunner(getJobLauncher(), getJobRepository());
        }
        return this.stepRunner;
    }
}
