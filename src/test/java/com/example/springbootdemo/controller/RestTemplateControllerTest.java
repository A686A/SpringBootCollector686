package com.example.springbootdemo.controller;

import com.example.springbootdemo.entity.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class RestTemplateControllerTest {
//    @Autowired
//    private MockMvc mockMvc;

    @Mock
    private RestTemplate restTemplate;

    
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void RestTemplateList() throws Exception {
        List<User> userList = new ArrayList<>();
        String apiURL = "http://localhost:8080/user";
        ParameterizedTypeReference<List<User>> responseBodyType = new ParameterizedTypeReference<List<User>>() {
        };
        ResponseEntity<List<User>> responseEntity = new ResponseEntity<List<User>>(userList, HttpStatus.OK);
        Mockito.when(restTemplate.exchange(apiURL, HttpMethod.GET, null, responseBodyType))
                .thenReturn(Mockito.any());

    }
}