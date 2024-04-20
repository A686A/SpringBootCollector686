package com.example.springbootdemo.controller;

import com.example.springbootdemo.service.Impl.TestServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.HttpServerErrorException;

import javax.annotation.Resource;

@SpringBootTest()
@AutoConfigureMockMvc
class TestControllerTest {
//    @Autowired
//    private MockMvc mockMvc;

    @SpyBean
    private TestServiceImpl testService;
    @Resource
    private MockMvc mockMvc;

    public String geta() {
        return "1";
    }

    @Test
    public void Test() throws Exception {
        HttpServerErrorException httpServerErrorException = new HttpServerErrorException(HttpStatus.BAD_REQUEST);
//        Mockito.when(testService.exc()).thenThrow(httpServerErrorException);
        mockMvc.perform(MockMvcRequestBuilders.get("/tan"));

    }
}
