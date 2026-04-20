package com.gedi.service.impl;

import com.gedi.mapper.EmpExprMapper;
import com.gedi.mapper.EmpMapper;
import com.gedi.pojo.*;
import com.gedi.service.EmpService;
import com.gedi.utils.JwtUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;


    @Autowired
    private EmpExprMapper empExprMapper;
    /*
    * 分页查询
    * */
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        // 设置PageHelper的参数
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        // 2.调用mapper查询数据
        List<Emp> empList = empMapper.list(empQueryParam);
        // 3.封装PageResult
        Page< Emp> p = (Page< Emp>) empList;
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }
    /*
    *
    * 新增员工
    * */
    @Transactional
    @Override
    public void add(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.add(emp);

        // 批量插入员工工作经历
        Integer empId = emp.getId();
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(empId));
            empExprMapper.insertBatch(exprList);
        }

    }
    @Transactional
    @Override
    public void deleteByIds(List<Integer> ids) {
        //1. 根据ID批量删除员工基本信息
        empMapper.deleteByIds(ids);

        //2. 根据员工的ID批量删除员工的工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }
    /*
    * 查询员工信息
    * */
    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getInfo(id);
    }
    /*
    * 更新员工信息
    * */
    @Transactional(rollbackFor ={ Exception.class})  // 指定回滚的异常类型
    @Override
    public void update(Emp emp) {
        //1. 根据ID更新员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        //2. 根据员工ID删除员工的工作经历信息 【删除老的】
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        //3. 新增员工的工作经历数据 【新增新的】
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }

    }

    @Override
    public List<Emp> listAll() {
        return empMapper.listAll();
    }

    @Override
    public LoginInfo login(Emp emp) {
        Emp empLogin = empMapper.getUsernameAndPassword(emp);
        if(empLogin != null){
            // 调用JWT 生成token

            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("id", empLogin.getId());
            dataMap.put("username", empLogin.getUsername());
            String jwt = JwtUtils.generateJwt(dataMap);

            LoginInfo loginInfo = new LoginInfo(empLogin.getId(), empLogin.getUsername(), empLogin.getName(), jwt);
            return loginInfo;
        }
        return null;
    }


}

