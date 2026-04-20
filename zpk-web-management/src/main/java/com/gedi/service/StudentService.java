package com.gedi.service;

import com.gedi.pojo.PageResult;
import com.gedi.pojo.Student;

import java.util.List;

public interface StudentService {
    /*
    * 分页查询
    * */
    PageResult<Student> page(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize);

    /*
    * 新增
    * */
    void insert(Student student);

    /*
    * 查询
    * */
    Student getInfo(Integer id);

    /*
    * 修改
    * */
    void update(Student student);
    /*
    * 删除
    * */
    void deleteByIds(List<Integer> ids);

    /*
    * 违纪处理扣分
    * */
    void violation(Integer id, Integer score);
}
