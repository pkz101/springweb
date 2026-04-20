package com.gedi.service;

import com.gedi.pojo.OperateLog;
import com.gedi.pojo.PageResult;

public interface LogService {
    /*
    * 分页查询日志
    * */
    PageResult<OperateLog> logPage(Integer page, Integer pageSize);
}
