package com.sunzj.spring.service;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.sunzj.spring.entity.Course;
import com.sunzj.spring.entity.Grade;

import java.util.List;

public interface GradeService extends IMppService<Grade> {
    List<Grade> selectGradePage(Integer pageNum, Integer pageSize, String name);

    long selectGradeCount(String cname);

    List<Course> selectCourseByGroup();

}
