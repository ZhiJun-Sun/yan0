package com.sunzj.spring.impl;

import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.sunzj.spring.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sunzj.spring.entity.Grade;
import com.sunzj.spring.mapper.GradeMapper;
import com.sunzj.spring.service.GradeService;

import java.util.List;

@Service
public class GradeServiceImpl extends MppServiceImpl<GradeMapper,Grade> implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;
    @Override
    public List<Grade> selectGradePage(Integer pageNum, Integer pageSize, String name) {
        //计算 pageNum和pageSize的关系值，得到start
        int start = (pageNum-1)*pageSize;
        return gradeMapper.selectGradePage(start,pageSize,name);
    }

    @Override
    public long selectGradeCount(String cname) {
        return gradeMapper.selectGradeCount(cname);
    }

    @Override
    public List<Course> selectCourseByGroup() {
        return gradeMapper.selectCourseByGroup();
    }
}
