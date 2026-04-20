package com.gedi.controller;

import com.gedi.pojo.*;
import com.gedi.service.EmpService;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    /*
    * 分页查询
    * */
    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("查询请求参数： {}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    /*
    * 新增员工
    * */
    @PostMapping
    public Result add(@RequestBody Emp emp) {
        log.info("新增员工{}", emp);
        empService.add(emp);
        return Result.success();
    }

    /*
    * 批量删除员工
    * */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("批量删除员工: ids={} ", ids);
        empService.deleteByIds(ids);
        return Result.success();
    }

    /*
    * 根据ID查询
    * */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("根据{}查询员工", id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    /**
     * 更新员工信息
     */
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工信息, {}", emp);
        empService.update(emp);
        return Result.success();
    }
    /*
     * 2.6 查询全部员工
     * */
    @GetMapping("/list")
    public Result listAll() {
        log.info("查询全部员工");
        List<Emp> empList = empService.listAll();
        return Result.success(empList);
    }

}
