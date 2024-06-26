package com.example.springbootdemo;

import com.example.springbootdemo.controller.RestTemplateController;
import com.example.springbootdemo.service.Impl.RestTemplateServiceImpl;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.system.OutputCaptureExtension;
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
@DisplayName("test controller")
@TestMethodOrder(MethodOrderer.MethodName.class)
@ExtendWith(OutputCaptureExtension.class)
@DBRider
@SpringBootTest()
@AutoConfigureMockMvc

public class TestController {
    @Autowired
    RestTemplateController restTemplateController;
    @MockBean
    RestTemplateServiceImpl restTemplateServiceImpl;
    private MockRestServiceServer mockServer;
    @Mock
    private RestTemplate restTemplate;
    @Resource
    private MockMvc mockMvc;

    private Logger logger;

    @BeforeEach
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
//    @Test
//    public void TestMockWithParameter() throws Exception {
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/RestTemplateTest")
//                        .header("accessToken","686").param("cifNO","668"))
//                .andExpect(MockMvcResultMatchers.content().string("success"))
//                .andExpect(MockMvcResultMatchers.forwardedUrlTemplate(restTemplateServiceImpl.RestTemplate()))
//                //.andExpect(MockMvcResultMatchers.jsonPath("accesstoken").value("688"))
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//        restTemplateServiceImpl.RestTemplate();

    @Test
    public void Test() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/Testone"))
                //.andExpect(MockMvcResultMatchers.status().isOk())  请求状态是ok 200
//                .andExpect(MockMvcResultMatchers.content().string("MockMvcTest"))
//                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}

