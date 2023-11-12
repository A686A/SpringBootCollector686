package com.example.springbootdemo.controller;

import com.example.springbootdemo.service.BatchService;
import org.springframework.batch.core.*;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

@RestController
public class BatchController {

    //job调度器
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private JobExplorer jobExplorer;

    @Autowired
    @Qualifier("csvToDBJob")
    private Job csvToDBJob;

    @Autowired
    @Qualifier("dbToDBJob")  //将spring容器中名为： csvToDBJob job对象注入当前变量中
    private Job dbToDBJob;

    @Autowired
    BatchService batchService;

    //http://localhost:8080/dataInit
    @GetMapping("/dataInit")
    public String dataInit() throws IOException {
        batchService.dataInit();
        return "ok";
    }

    //http://localhost:8080/csvToDB
    @GetMapping("/csvToDB")
    public String csvToDB() throws Exception {
        batchService.truncateTemp(); //清空数据运行多次执行

        //需要多次执行，run.id 必须重写之前，再重构一个新的参数对象
        JobParameters jobParameters = new JobParametersBuilder(new JobParameters(),jobExplorer)
                .addLong("time", new Date().getTime())
                .getNextJobParameters(csvToDBJob).toJobParameters();
        JobExecution run = jobLauncher.run(csvToDBJob, jobParameters);
        return run.getId().toString();
    }

    //http://localhost:8080/dbToDB
    @GetMapping("/dbToDB")
    public String dbToDB() throws Exception {
        batchService.truncateAll();
        JobParameters jobParameters = new JobParametersBuilder(new JobParameters(),jobExplorer)
                .addLong("time", new Date().getTime())
                .getNextJobParameters(dbToDBJob).toJobParameters();
        JobExecution run = jobLauncher.run(dbToDBJob, jobParameters);
        return run.getId().toString();
    }
}


