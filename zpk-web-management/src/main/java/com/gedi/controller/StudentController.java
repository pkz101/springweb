package com.gedi.controller;

import com.gedi.pojo.PageResult;
import com.gedi.pojo.Result;
import com.gedi.pojo.Student;
import com.gedi.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 条件分页查询
     */
    @GetMapping
    public Result page(String name ,
                       Integer degree,
                       Integer clazzId,
                       @RequestParam(defaultValue = "1") Integer page ,
                       @RequestParam(defaultValue = "10") Integer pageSize){
        PageResult<Student> pageResult = studentService.page(name,degree,clazzId,page,pageSize);
        return Result.success(pageResult);
    }

    /*
    * 4.3 添加学员
    * */
    @PostMapping
    public Result insert(@RequestBody Student student){
        log.info("添加学员: {}", student);
        studentService.insert(student);
        return Result.success();
    }

    /*
     *根据id查询学员信息
     * */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据id查询学员信息: {}", id);
        Student student = studentService.getInfo(id);
        return Result.success(student);
    }

    /*
    * 修改学员
    * */
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("修改学员: {}", student);
        studentService.update(student);
        return Result.success();
    }

    /*
    * 批量删除学员
    * */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除学员: {}", ids);
        studentService.deleteByIds(ids);
        return Result.success();
    }

    /*
    * 违纪处理
    * */
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id,
                           @PathVariable Integer score){
        log.info("处理违纪, id: {}, score: {}", id, score);
        studentService.violation(id,score);
        return Result.success();
    }
}
