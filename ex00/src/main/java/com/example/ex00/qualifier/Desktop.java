package com.example.ex00.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("desktop")   // 키값 설정
public class Desktop implements Computer{
    @Override
    public int getScreenWidth() {
        return 2048;
    }
}
