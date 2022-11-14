package com.example.ex03.service;

import com.example.ex03.service.SampleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.PostConstruct;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class SampleServiceTest {

    @Autowired
    SampleService sampleService;

    @Test
    public void doAddTest() throws Exception {
        log.info("result: " + sampleService.doAdd("한동석", "3"));
    }

}




















