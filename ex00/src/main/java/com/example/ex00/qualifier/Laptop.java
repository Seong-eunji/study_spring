package com.example.ex00.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Qualifier("laptop") @Primary   // @Primary를 붙이면 모호성을 해결할 때 가장 먼저 기본으로 들어감
public class Laptop implements Computer{
    @Override
    public int getScreenWidth() { return 1980; }
}
