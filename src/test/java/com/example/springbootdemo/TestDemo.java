package com.example.springbootdemo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;
//23.8.22
//随机端口启动
@SpringBootTest()
@AutoConfigureMockMvc
public class TestDemo {
    @Resource
    private MockMvc mockMvc;
    //@DisplayName("MockTest")测试名
    @Test
    public void TestMock() throws Exception {
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/MockMvcTest"))
                //.andExpect(MockMvcResultMatchers.status().isOk())  请求状态是ok 200
                .andExpect(MockMvcResultMatchers.content().string("MockMvcTest"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    @Test
    public void TestMockWithParameter() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/MockMvcTest")
                        .header("accessToken","686").param("cifNO","668"))
                .andExpect(MockMvcResultMatchers.content().string("SUCCESS"))
                .andExpect(MockMvcResultMatchers.jsonPath("accesstoken").value("688"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }



}
