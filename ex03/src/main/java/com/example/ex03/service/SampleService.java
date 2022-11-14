package com.example.ex03.service;

import com.example.ex03.aspect.LogStatus;
import com.example.ex03.aspect.Plus10;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.lang.Integer.*;

@Service
@Slf4j
public class SampleService {

    @Plus10
    @LogStatus
    public Integer doAdd(String str1, String str2) throws Exception {   // PointCut
        log.info("핵심 로직");                                            // 여러 시점(Around, Before, After ...)이 많은데 어떤 시점이 정해지고 그 메소드를 PointCut이라고 부름
        return parseInt(str1) + parseInt(str2);
    }
}
