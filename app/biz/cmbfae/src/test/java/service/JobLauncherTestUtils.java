package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.test.StepRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.SyncTaskExecutor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/14 0014.
 */
public class JobLauncherTestUtils {

    private static final long   JOB_PARAMETER_MAXIMUM = 1000000;

    /** Logger */
    private final static Logger logger                = LoggerFactory
            .getLogger(JobLauncherTestUtils.class);

    private JobLauncher jobLauncher;

    private JobRepository jobRepository;

    private StepRunner stepRunner;

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
