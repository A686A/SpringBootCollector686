package com.example.springbootdemo;

import com.example.springbootdemo.controller.RestTemplateController;
import com.example.springbootdemo.service.Impl.RestTemplateServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
//23.8.22
//随机端口启动
//@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class TestDemo {
    private MockRestServiceServer mockServer;
    @Mock
    private RestTemplate restTemplate;
    @Resource
    private MockMvc mockMvc;
    @Autowired
    RestTemplateController restTemplateController;
    @MockBean
    RestTemplateServiceImpl restTemplateServiceImpl;

  @Before
    public void init() {
       mockServer = MockRestServiceServer.createServer(restTemplate);
    }
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
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/RestTemplateTest")
                        .header("accessToken","686").param("cifNO","668"))
                .andExpect(MockMvcResultMatchers.content().string("success"))
                .andExpect(MockMvcResultMatchers.forwardedUrlTemplate(restTemplateServiceImpl.RestTemplate()))
                //.andExpect(MockMvcResultMatchers.jsonPath("accesstoken").value("688"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        restTemplateServiceImpl.RestTemplate();
    }

    @Test
    public void Test() throws Exception {


        Assertions.assertEquals("success",restTemplateController.restTemplateTest().toString());
    }



}
