package com.cmc.productmanagement.config.batch;

import com.cmc.productmanagement.constants.KafkaConstants;
import com.cmc.productmanagement.entity.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.kafka.KafkaItemReader;
import org.springframework.batch.item.kafka.builder.KafkaItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig  extends DefaultBatchConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(BatchConfig.class);
    private final KafkaProperties properties;
    private final StepBuilderFactory stepBuilderFactory;
    private final JobBuilderFactory jobBuilderFactory;
    private final OrderItemProcessor orderItemProcessor;
    @Autowired
    private JobRepository jobRepository;

    @Override
    public void setDataSource(DataSource dataSource) {
        super.setDataSource(null);
    }

    @Bean(name = "myJobLauncher")
    public JobLauncher simpleJobLauncher() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
    }

    @Bean
    public Job importUserJob(Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .build();
    }

    @Bean(name = "reader")
    KafkaItemReader<OrderEntity, OrderEntity> kafkaItemReader() {
        System.out.println("Data received from kafka topic");
        Properties props = new Properties();
        props.putAll(this.properties.buildConsumerProperties());
        return new KafkaItemReaderBuilder<OrderEntity, OrderEntity>()
                .partitions(0)
                .consumerProperties(props)
                .name("customer-reader")
                .saveState(true)
                .topic(KafkaConstants.KAFKA_TOPIC)
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<OrderEntity> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<OrderEntity>().itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO product_order (product_name, numbers_of_product) VALUES (:productName, :numbersOfProduct)")
                .dataSource(dataSource)
                .build();
    }
    @Bean(name = "start")
    Step start(JdbcBatchItemWriter<OrderEntity> writer) {
        return stepBuilderFactory
                .get("step1")
                .<OrderEntity, OrderEntity>chunk(2)
                .reader(kafkaItemReader())
                .processor(orderItemProcessor)
                .writer(writer)
                .build();
    }

}
