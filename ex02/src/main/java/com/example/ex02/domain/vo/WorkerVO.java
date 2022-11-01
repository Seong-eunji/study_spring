package com.example.ex02.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
@Data
public class WorkerVO {
    private String name;
    private String time;

    public String getWorkTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
        String workTime = simpleDateFormat.format(getTime());
        return workTime;
    }
}
