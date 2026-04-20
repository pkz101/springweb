package com.gedi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClazzQueryParam {
    private String name; //班级名称
    private Integer page = 1; //页码
    private Integer pageSize = 10; //每页展示记录数

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin; //开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end; //结束时间
}
