package com.example.springbootdemo.service.Impl;

import com.example.springbootdemo.entity.Employee;
import com.example.springbootdemo.mapper.BatchMapper;
import com.example.springbootdemo.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

@Service
public class BatchServiceImpl implements BatchService {

    @Value("${job.data.path}")
    public String path;

    @Autowired
    BatchMapper batchMapper;

    @Override
    public void save(Employee employee) {

    }

    @Override
    public void dataInit() throws IOException {
        File file = new File(path, "employee.csv");
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        FileOutputStream out = new FileOutputStream(file);
        String txt = "";

        Random ageR = new Random();
        Random boolR = new Random();

        // 给文件中生产50万条数据
        long beginTime = System.currentTimeMillis();
        System.out.println("开始时间：【 " + beginTime + " 】");
        for (int i = 1; i <= 500; i++) {
            if(i == 500){
                txt = i+",dafei_"+ i +"," + ageR.nextInt(100) + "," + (boolR.nextBoolean()?1:0);
            }else{
                txt = i+",dafei_"+ i +"," + ageR.nextInt(100) + "," + (boolR.nextBoolean()?1:0) +"\n";
            }

            out.write(txt.getBytes());
            out.flush();
        }
        out.close();
        System.out.println("总共耗时：【 " + (System.currentTimeMillis() - beginTime) + " 】毫秒");
    }

    @Override
    public void truncateAll() {

    }

    @Override
    public void truncateTemp() {

    }
}
