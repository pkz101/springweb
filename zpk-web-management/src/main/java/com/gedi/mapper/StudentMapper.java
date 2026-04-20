package com.gedi.mapper;

import com.gedi.pojo.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    /**
     * 根据班级id查询班级下有多少个学员
     * @param id
     * @return
     */
    @Select("select count(*) from student where clazz_id = #{id}")
    Integer countByClazzId(Integer id);

    /**
     * 查询所有学员分页
     * @param name
     * @param degree
     * @param clazzId
     * @return
     */
    List<Student> list(String name, Integer degree, Integer clazzId);

    /**
     * 新增学员
     * @param student
     */
    @Insert("insert into student(name, no, gender, phone,id_card, is_college, address, degree, graduation_date,clazz_id, create_time, update_time) VALUES " +
            "(#{name},#{no},#{gender},#{phone},#{idCard},#{isCollege},#{address},#{degree},#{graduationDate},#{clazzId},#{createTime},#{updateTime})")
    void insert(Student student);

    /**
     * 根据id查询学员信息
     * @param id
     * @return
     */
    @Select("select * from student where id = #{id}")
    Student getInfo(Integer id);

    /**
     * 修改学员信息
     * @param student
     */
    void update(Student student);

    /**
     * 批量删除学员信息
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 添加违纪信息
     * @param id
     * @param score
     */
    @Update("update student set violation_count = violation_count + 1 , " +
            "violation_score = violation_score + #{score} , update_time = now() where id = #{id}")
    void violation(Integer id, Integer score);



    /**
     * 统计学员学历信息
     * @return
     */
    @MapKey("name")
    List<Map> countStudentDegreeData();

    /**
     * 统计班级职位数据
     * @return
     */
    @MapKey("clazzList")
    List<Map<String, Object>> getStudentCount();
}
