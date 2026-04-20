package com.gedi.controller;

import com.gedi.anno.Log;
import com.gedi.pojo.Dept;
import com.gedi.pojo.Result;
import com.gedi.service.DeptService;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    /*    private static final Logger log = org.slf4j.LoggerFactory.getLogger(DeptController.class);*/

    @Autowired
    private DeptService deptService;

    /*
     * 查询所有部门进行返回
     * */
    @GetMapping
    public Result list() {
        log.info("查询所有部门进行返回");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    /*
     * 根据id删除部门
     * */
    @Log
    @DeleteMapping
    public Result delete(@PathParam("id") Integer id) {
        log.info("根据{}删除部门", id);
        deptService.deleteById(id);
        return Result.success();
    }

    /*
     * 新增部门
     * */
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        log.info("新增部门{}", dept);
        deptService.add(dept);
        return Result.success();
    }

    /*
     * 根据id查询部门进行回显
     * */
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        log.info("根据{}查询部门进行回显", id);
        Dept dept = deptService.findById(id);
        return Result.success(dept);
    }

    /*
     * 修改部门
     * */
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        log.info("修改部门{}", dept);
        deptService.update(dept);
        return Result.success();
    }




}
