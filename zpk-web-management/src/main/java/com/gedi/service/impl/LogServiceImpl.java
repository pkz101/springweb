package com.gedi.service.impl;


import com.gedi.mapper.LogMapper;
import com.gedi.pojo.OperateLog;
import com.gedi.pojo.PageResult;
import com.gedi.service.LogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public PageResult<OperateLog> logPage(Integer page, Integer pageSize) {
        // 设置PageHelper的参数
        PageHelper.startPage(page, pageSize);
        // 调用mapper查询
        List<OperateLog> logList = logMapper.findlist();
        Page<OperateLog> p = (Page<OperateLog>) logList;
        return new PageResult<OperateLog>(p.getTotal(), p.getResult());
    }
}
