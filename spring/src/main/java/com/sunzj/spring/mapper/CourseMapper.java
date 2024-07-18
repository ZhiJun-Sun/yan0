package com.sunzj.spring.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunzj.spring.entity.Course;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface CourseMapper extends BaseMapper<Course> {

}
