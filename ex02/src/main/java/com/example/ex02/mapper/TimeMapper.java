package com.example.ex02.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TimeMapper {
    public String getTime();
    @Select("SELECT CURRENT_DATE FROM DUAL")
    public String getTimeQuick();
}
