package com.gedi.service;

import com.gedi.pojo.ClazzCountOption;
import com.gedi.pojo.JobOption;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface ReportService {
    /*
    * 统计员工数量
    * */
    JobOption getEmpJobData();
    /*
    * 性别
    * */
    List<Map> getEmpGenderData();
    /*
    * 班级人数
    * */
    ClazzCountOption getStudentCountData();
    /*
    * 学历
    * */
    List<Map> getStudentDegreeData();
}
