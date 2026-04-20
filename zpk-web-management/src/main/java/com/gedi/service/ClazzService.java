package com.gedi.service;

import com.gedi.pojo.Clazz;
import com.gedi.pojo.ClazzQueryParam;
import com.gedi.pojo.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ClazzService {

    /*
    * 查询分页
    * */
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);
    /*
    * 删除班级
    * */
    void deleteById(Integer id);
    /*
    * 新增班级
    * */
    void add(Clazz clazz);

    /*
    * 根据id员工
    * */
    Clazz getInfo(Integer id);

    /*
    * 修改员工信息
    * */
    void update(Clazz clazz);
    /*
    * 查询所有班级进行返回
    * */
    List<Clazz> list();
}
