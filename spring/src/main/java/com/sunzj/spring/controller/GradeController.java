package com.sunzj.spring.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunzj.spring.entity.Course;
import com.sunzj.spring.entity.CourseChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sunzj.spring.entity.Grade;
import com.sunzj.spring.impl.GradeServiceImpl;

import java.util.*;

@RestController
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    private GradeServiceImpl gradeService;

    @GetMapping("/findAll")
    public List<Grade> findAll()
    {
        return gradeService.list();
    }

    @PostMapping("/save")
    public boolean save(@RequestBody Grade grade)
    {
        return gradeService.saveOrUpdateByMultiId(grade);
    }

    @PostMapping("/delete")
    public boolean delete(@RequestBody Grade grade)
    {
        return gradeService.deleteByMultiId(grade);
    }

    @GetMapping("/page")
    public IPage<Grade> findPage(@RequestParam Integer pageNum,
                                 @RequestParam Integer pageSize,
                                 @RequestParam(required = false) String cname){
        IPage<Grade> iPage =new Page<>(pageNum,pageSize);
        List<Grade> list = gradeService.selectGradePage(pageNum,pageSize,cname);
        iPage.setRecords(list);
        iPage.setTotal(gradeService.selectGradeCount(cname));
        return iPage;
    }

    @GetMapping("/chart")
    public CourseChart getChart(){
        CourseChart courseChart = new CourseChart();
        List<Integer> yData = new ArrayList<>();
        List<String> xData = new ArrayList<>();

        List<Course> courseList = gradeService.selectCourseByGroup();
        //循环xData 课程，查询每个课程的人数
        for (Course course : courseList) {
            QueryWrapper<Grade> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("cid", course.getCid());
            long count = gradeService.count(queryWrapper);
            yData.add(Integer.valueOf(String.valueOf(count)));
            xData.add(course.getCoursename());
        }

        courseChart.setXdata(xData);
        courseChart.setYdata(yData);
        return courseChart;
    }

}
