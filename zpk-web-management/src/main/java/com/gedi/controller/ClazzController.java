package com.gedi.controller;

import com.gedi.pojo.*;
import com.gedi.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;
    /*
    * 班级列表
    * */
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam) {
        log.info("查询请求参数： {}", clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    /*
    * 删除班级
    * */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除班级{}", id);
        clazzService.deleteById(id);
        return Result.success();
    }
    /*
    * 新增班级
    * */
    @PostMapping
    public Result add(@RequestBody Clazz clazz) {
        log.info("新增班级{}", clazz);
        clazzService.add(clazz);
        return Result.success();
    }

    /*
    * 3.4 根据ID查询
    * */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("根据{}查询班级", id);
        Clazz clazz = clazzService.getInfo(id);
        return Result.success(clazz);
    }

    /*
    * 3.5 修改班级
    * */
    @PutMapping
    public Result update(@RequestBody Clazz clazz) {
        log.info("修改班级{}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    /*
    * 3.6 查询所有班级
    * */
    @GetMapping("/list")
    public Result list() {
        log.info("查询所有班级");
        List<Clazz> clazzList = clazzService.list();
        return Result.success(clazzList);
    }
}
