package com.example.springbootdemo.config;

import com.example.springbootdemo.entity.Employee;
import com.example.springbootdemo.partitioner.DBToDBPartitioner;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 将数据从employee_temp中读取，并写入employe 表
 */
@Configuration
public class DBToDBJobConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    public static int PAGESIZE = 100;   //mybatis分页读取数据，跟chunkSize 一样
    public static int RANGE = 10;  //每个分区读取数据范围(理解为个数)
    public static int GRIDSIZE = 50;  //分区个数


    //读数据-从employee_temp 表读 -- mybatis
    @Bean
    @StepScope
    public MyBatisPagingItemReader<Employee> dBToDBJobItemReader(
            @Value("#{stepExecutionContext[from]}") final Integer from,
            @Value("#{stepExecutionContext[to]}") final Integer to,
            @Value("#{stepExecutionContext[range]}") final Integer range){

        System.out.println("----------MyBatisPagingItemReader开始-----from: " + from + "  -----to:" + to + "  -----每片数量:" + range);
        MyBatisPagingItemReader<Employee> itemReader = new MyBatisPagingItemReader<Employee>();
        itemReader.setSqlSessionFactory(sqlSessionFactory);
        itemReader.setQueryId("com.example.springbootdemo.mapper.BatchMapper.selectTempForList");
        itemReader.setPageSize(DBToDBJobConfig.PAGESIZE);
        Map<String, Object> map = new HashMap<>();
        map.put("from", from);
        map.put("to", to);
        itemReader.setParameterValues(map);

        return itemReader;
    }


    //数据库写- 写入到employee 表中
    @Bean
    public MyBatisBatchItemWriter<Employee> dbToDBItemWriter(){
        MyBatisBatchItemWriter<Employee> itemWriter = new MyBatisBatchItemWriter<>();
        itemWriter.setSqlSessionFactory(sqlSessionFactory);
        itemWriter.setStatementId("com.example.springbootdemo.mapper.BatchMapper.save");  //操作sql
        return itemWriter;
    }

    //文件分区处理器-处理分区
    @Bean
    public PartitionHandler dbToDBPartitionHandler() {
        TaskExecutorPartitionHandler handler = new TaskExecutorPartitionHandler();
        handler.setGridSize(DBToDBJobConfig.GRIDSIZE);
        handler.setTaskExecutor(new SimpleAsyncTaskExecutor());
        handler.setStep(workStep());
        try {
            handler.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return handler;
    }

    //每个从分区操作步骤
    @Bean
    public Step workStep() {
        return stepBuilderFactory.get("workStep")
                .<Employee, Employee>chunk(DBToDBJobConfig.PAGESIZE)
                .reader(dBToDBJobItemReader(null, null, null))
                .writer(dbToDBItemWriter())
                .build();
    }

    @Bean
    public DBToDBPartitioner dbToDBPartitioner(){
        return new DBToDBPartitioner();
    }

    //主分区操作步骤
    @Bean
    public Step masterStep() {
        return stepBuilderFactory.get("masterStep")
                .partitioner(workStep().getName(),dbToDBPartitioner())
                .partitionHandler(dbToDBPartitionHandler())
                .build();
    }

    @Bean
    public Job dbToDBJob(){
        return jobBuilderFactory.get("dbToDB-step-job")
                .start(masterStep())
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
