package com.gedi.mapper;

import com.gedi.pojo.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {

    /*
    * 查询所有部门
    *
    * */
    @Select("SELECT id, name, create_time, update_time FROM dept ORDER BY update_time DESC")
    public List<Dept> findAll();


    /*
    * 根据id删除部门
    * */
    @Delete("DELETE FROM dept WHERE id = #{id}")
    void deleteById(Integer id);

    /*
    * 新增部门
    * */
    @Insert("INSERT INTO dept(name, create_time, update_time) VALUES(#{name}, #{createTime}, #{updateTime})")
    void add(Dept dept);
    /*
    * 根据id查询部门
    * */
    @Select("SELECT id, name, create_time, update_time FROM dept WHERE id = #{id}")
    Dept findById(Integer id);
    /*
    * 修改部门
    * */
    @Insert("UPDATE dept SET name = #{name}, update_time = #{updateTime} WHERE id = #{id}")
    void update(Dept dept);
}
