package com.sunzj.spring.mapper;

import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import com.sunzj.spring.entity.Course;
import com.sunzj.spring.entity.Grade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GradeMapper extends MppBaseMapper<Grade> {

    List<Grade> selectGradePage(@Param("start") Integer start,@Param("pageSize") Integer pageSize,@Param("cname") String cname);

    long selectGradeCount(@Param("cname") String cname);

    List<Course> selectCourseByGroup();

}
