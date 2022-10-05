package com.techgeeknext.springbootbatch.config;

import com.techgeeknext.springbootbatch.step.MessageProcessor;
import com.techgeeknext.springbootbatch.step.MessageReader;
import com.techgeeknext.springbootbatch.step.MessageWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
@Configuration
public class SpringBootBatchConfig {
 
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
 
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    /**
     * A Job is made up of many steps and each step is
     *  a READ-PROCESS-WRITE task or a single operation task (tasklet).
      * @return job
     */
    @Bean
    public Job simpleJob() {
        return jobBuilderFactory.get("simpleJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1())
                .end()
                .build();
    }

    /**
     * Step consist of an ItemReader, ItemProcessor and an ItemWriter.
     * @return step
     */
    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<String, String> chunk(1)
                .reader(new MessageReader())
                .processor(new MessageProcessor())
                .writer(new MessageWriter())
                .build();
    }

}
