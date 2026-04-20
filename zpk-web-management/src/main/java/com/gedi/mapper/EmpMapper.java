package com.gedi.mapper;

import com.gedi.pojo.Emp;
import com.gedi.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
    /*
     * 查询所有员工
     * */
    List<Emp> list(EmpQueryParam empQueryParam);
    /*
    * 新增员工
    * */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void add(Emp emp);
    /*
    * 删除员工基本信息
    * */
    void deleteByIds(List<Integer> ids);
    /*
    * 查询回显
    * */
    Emp getInfo(Integer id);

    /*
    * 更新员工基本信息
    * */
    void updateById(Emp emp);

    /*
    * 统计员工职位数据
    * */
    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();

    /**
     * 统计员工性别信息
     */
    @MapKey("name")
    List<Map> countEmpGenderData();
    /*
    * 查询所有员工
    * */
    @Select("select id, username, password, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time from emp")
    List<Emp> listAll();

    /*
    * 根据部门id统计员工数量
    * */
    @Select("select count(1) from emp where dept_id = #{id}")
    int countByDeptId(Integer id);

    /*登录*/
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getUsernameAndPassword(Emp emp);
}
