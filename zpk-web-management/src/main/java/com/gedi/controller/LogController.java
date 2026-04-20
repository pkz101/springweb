package com.gedi.controller;

import com.gedi.pojo.OperateLog;
import com.gedi.pojo.PageResult;
import com.gedi.pojo.Result;
import com.gedi.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/log/page")
    public Result logPage(@RequestParam(defaultValue = "1") Integer page ,
                          @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("分页查询日志, 参数: page={}, pageSize={}", page, pageSize);
        PageResult<OperateLog> pageResult = logService.logPage(page, pageSize);
        return Result.success(pageResult);
    }
}
