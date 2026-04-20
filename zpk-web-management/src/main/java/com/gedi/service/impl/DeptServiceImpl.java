package com.gedi.service.impl;

import com.gedi.exception.BusinessException;
import com.gedi.mapper.DeptMapper;
import com.gedi.mapper.EmpMapper;
import com.gedi.pojo.Dept;
import com.gedi.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService{

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;
    /*
    * 查询所有部门
    * */
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();

    }
    /*
    * 根据id删除部门
    * */
    @Override
    public void deleteById(Integer id) {
        // 查询部门下有员工，则不能删除
        if (empMapper.countByDeptId(id) > 0) {
            throw new BusinessException("部门下有员工，不能直接删除~");
        }
        // 如果没有, 再删除部门信息
        deptMapper.deleteById(id);

    }
    /*
    * 新增部门
    * */
    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.add(dept);
    }
    /*
    * 根据id查询部门
    * */
    @Override
    public Dept findById(Integer id) {
        return deptMapper.findById(id);
    }
    /*
    * 修改部门
    * */
    @Override
    public void update(Dept dept) {
        // 更新时间
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
