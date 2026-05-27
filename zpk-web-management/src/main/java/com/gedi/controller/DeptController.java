package com.gedi.controller;

import com.gedi.anno.Log;
import com.gedi.pojo.Dept;
import com.gedi.pojo.Result;
import com.gedi.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping
    public Result list() {
        log.info("Query all departments");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    @Log
    @DeleteMapping
    public Result delete(@RequestParam Integer id) {
        log.info("Delete department by id: {}", id);
        deptService.deleteById(id);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        log.info("Add department: {}", dept);
        deptService.add(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        log.info("Query department by id: {}", id);
        Dept dept = deptService.findById(id);
        return Result.success(dept);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        log.info("Update department: {}", dept);
        deptService.update(dept);
        return Result.success();
    }
}
