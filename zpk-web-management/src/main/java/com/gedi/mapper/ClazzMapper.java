package com.gedi.mapper;

import com.gedi.pojo.Clazz;
import com.gedi.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClazzMapper {
    /*
    * 查询所有班级
    * */
    List<Clazz> findlist(ClazzQueryParam clazzQueryParam);
    /*
    * 删除班级
    * */
    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);

    /*
    * 新增班级
    * */
    void add(Clazz clazz);
    /*
    * 根据id查询班级进行回显
    * */
    @Select("select * from clazz where id = #{id}")
    Clazz getInfo(Integer id);

    /*
    * 修改班级
    * */
    void update(Clazz clazz);

    /*
    * 查询所有班级进行返回
    * */
    @Select("select * from clazz")
    List<Clazz> list();
}
