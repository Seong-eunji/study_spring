package com.example.ex03.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)             // 이 aspect를 적용시킬 대상을 정해줘야함(이 어노테이션은 이제 메소드에만 적용 가능)
@Retention(RetentionPolicy.RUNTIME)     // 생명주기 : 이 어노테이션이 얼마동안 메모리에 할당되어 있어야 하는지(Runtime : 실행 중인 동안)
public @interface Plus10 {;}            // 분리된 횡단 관심사
