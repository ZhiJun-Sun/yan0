package com.sunzj.spring.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sunzj.spring.entity.Grade;
import com.sunzj.spring.impl.GradeServiceImpl;

import java.util.List;

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
                                 @RequestParam(defaultValue = "") String cid,
                                 @RequestParam(defaultValue = "") String sid)
    {
        QueryWrapper<Grade> queryWrapper=new QueryWrapper<>();
        IPage<Grade> iPage=new Page<>(pageNum,pageSize);
        queryWrapper.like("cid",cid);
        queryWrapper.like("sid",sid);
        queryWrapper.orderByDesc("cid");
        return gradeService.page(iPage,queryWrapper);
    }
}
