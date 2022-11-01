package com.example.ex00.dependency;

import com.example.ex00.qualifier.Computer;
import com.example.ex00.qualifier.Restaurant;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest @Slf4j
public class QualifierTest {
    @Autowired  @Qualifier("desktop") // 인터페이스를 Autowired 하면 구현체를 가져옴(Desktop). 구현체가 여러개일 경우 키값을 가져와 지정해줌
    private Computer desktop;
    @Autowired  @Qualifier("laptop")
    private Computer laptop;
    @Autowired
    private Computer computer;

    @Autowired @Qualifier("outback")
    private Restaurant outback;
    @Autowired @Qualifier("vips")
    private Restaurant vips;
    @Autowired
    private Restaurant resturant;


    @Test
    public void qualifierTest(){
        log.info(desktop + "");
        log.info(laptop + "");
        log.info(computer + "");

        log.info(outback + "");
        log.info(vips + "");
        log.info(resturant + "");


    }
}
