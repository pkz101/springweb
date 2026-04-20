package com.gedi.mapper;

import com.gedi.pojo.OperateLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public  interface LogMapper {


    @Select("SELECT o.* ,e.name operateEmpName FROM operate_log o  LEFT JOIN emp  e ON o.operate_emp_id = e.id ORDER BY operate_time DESC")
    List<OperateLog> findlist();
}
