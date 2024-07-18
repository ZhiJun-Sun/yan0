package com.sunzj.spring.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.sunzj.spring.entity.Course;
import com.sunzj.spring.mapper.CourseMapper;

@Service
public class CourseService extends ServiceImpl<CourseMapper, Course> {
    public boolean saveCourse(Course course){
        return saveOrUpdate(course);  //增或改
    }
}
