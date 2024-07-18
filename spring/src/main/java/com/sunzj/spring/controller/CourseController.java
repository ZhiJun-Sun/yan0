package com.sunzj.spring.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sunzj.spring.entity.Course;
import com.sunzj.spring.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/findAll")
    public List<Course> findAll()
    {
        return courseService.list();
    }

    @PostMapping("/save")
    public boolean save(@RequestBody Course course)
    {
        return courseService.saveOrUpdate(course);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Integer id)
    {
        return courseService.removeById(id);
    }


    @GetMapping("/page")
    public IPage<Course> findPage(@RequestParam Integer pageNum,
                                  @RequestParam Integer  pageSize,
                                  @RequestParam(defaultValue = "") String courseName,
                                  @RequestParam(defaultValue = "") String grade){
        QueryWrapper<Course> queryWrapper=new QueryWrapper<>();
        IPage<Course> courseIPage=new Page<>(pageNum,pageSize);
        queryWrapper.like("coursename",courseName);
        queryWrapper.like("grade",grade);
        queryWrapper.orderByDesc("cid");
        return courseService.page(courseIPage,queryWrapper);
    }
}
