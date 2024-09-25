package com.example.springbootdemo;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import com.example.springbootdemo.controller.RestTemplateController;
import com.example.springbootdemo.controller.TestController;
import com.example.springbootdemo.service.Impl.RestTemplateServiceImpl;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import lombok.Getter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

//23.8.22
//随机端口启动
//@RunWith(SpringRunner.class)
@SpringBootTest()
@DBRider
//@ActiveProfiles("prod")
@DisplayName("test controller")
@TestMethodOrder(MethodOrderer.MethodName.class)
@ContextConfiguration(classes = SpringBootDemoApplication.class)
@ExtendWith(OutputCaptureExtension.class)
@AutoConfigureMockMvc

public class JunitTestController {
    @Autowired
    RestTemplateController restTemplateController;
    @MockBean
    RestTemplateServiceImpl restTemplateServiceImpl;
    private MockRestServiceServer mockServer;
    @Mock
    private RestTemplate restTemplate;
    @Resource
    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @DisplayName("Appender输出log")
    @Test
    public void TestLog1() throws Exception {
        Appender appender= new Appender();
        Logger logger= (Logger) LoggerFactory.getLogger(TestController.class);
        logger.addAppender(appender);
        appender.start();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/testLog"))
                .andReturn();
        Assertions.assertEquals("[info.001]実行成功",
                (appender.getEvents().get(0).getMessage()));

    }

    @DisplayName("CapturedOutput判断log是否存在")
    @Test
    public void TestLog2(CapturedOutput logCheck) throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/testLog"))
                .andReturn();
        String logAll = logCheck.getOut();
        Assertions.assertTrue(logAll.contains("[info.001]実行成功"));
    }

    @DisplayName("DataSet测试")
    @Test
    @DataSet(executeScriptsBefore = "test/controller/JunitTestController.sql")
    public void DataSet() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/dataSet"))
                .andReturn();
    }

    @Getter
    static class Appender extends AppenderBase<ILoggingEvent>{
        private  final List<ILoggingEvent> events = new ArrayList<>();
        @Override
        protected void append(ILoggingEvent iLoggingEvent) {
            events.add(iLoggingEvent);
        }
    }
}

