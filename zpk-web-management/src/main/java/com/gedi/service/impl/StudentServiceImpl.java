package com.gedi.service.impl;

import com.gedi.mapper.StudentMapper;
import com.gedi.pojo.PageResult;
import com.gedi.pojo.Student;
import com.gedi.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /*
    * 分页查询
    * */
    @Override
    public PageResult<Student> page(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize) {
        // 1.分页查询
        PageHelper.startPage(page,pageSize);

        // 2.查询
        List<Student> studentsList = studentMapper.list(name,degree,clazzId);

         Page<Student> p =(Page<Student>) studentsList;

        return new PageResult< Student>(p.getTotal(),p.getResult());
    }

    @Override
    public void insert(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.insert(student);
    }

    @Override
    public Student getInfo(Integer id) {
        return studentMapper.getInfo(id);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        studentMapper.deleteByIds(ids);
    }

    @Override
    public void violation(Integer id, Integer score) {
        studentMapper.violation(id,score);
    }
}
