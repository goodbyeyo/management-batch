package com.study.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableBatchProcessing
@SpringBootApplication
public class ManagementBatchApplication {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    public ManagementBatchApplication(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Step managementStep() {
        return stepBuilderFactory.get("managementStep")// stepName 선언
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Execute managementStep");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Job managementJob() {
        return this.jobBuilderFactory.get("managementJob")
                .start(managementStep())
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(ManagementBatchApplication.class, args);
    }

}
