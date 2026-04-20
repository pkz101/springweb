package com.gedi.service;

import com.gedi.pojo.Emp;
import com.gedi.pojo.EmpQueryParam;
import com.gedi.pojo.LoginInfo;
import com.gedi.pojo.PageResult;

import java.util.List;

public interface EmpService {
    /*
    * 分页查询
    * */
    PageResult<Emp> page(EmpQueryParam empQueryParam);
    /*
    * 新增员工
    * */
    void add(Emp emp);
    /*
    * 批量删除员工
    * */
    void deleteByIds(List<Integer> ids);
    /*
    * 查询员工
    * */
    Emp getInfo(Integer id);
    /*
    * 更新 员工信息
    * */
    void update(Emp emp);
    /*
    * 查询所有员工
    * */
    List<Emp> listAll();

    /*
    * 登录
    * */
    LoginInfo login(Emp emp);
}
