package com.example.ex02.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class MemberVO {
    private String name;
    private int age;

    private List<MemberVO> members;
}