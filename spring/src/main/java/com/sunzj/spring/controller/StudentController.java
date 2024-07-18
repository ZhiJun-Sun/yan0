package com.sunzj.spring.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sunzj.spring.entity.Student;
import com.sunzj.spring.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/findAll")
    public List<Student> index()
    {
        //return studentMapper.findAll(); //改造一下,studentservice已经继承了mybatisplus提供的接口了
        return studentService.list();
    }

    @PostMapping("/save")
    public boolean save(@RequestBody Student student){
        return studentService.saveStudent(student);
    }

    @DeleteMapping("/delete/{sid}")
    public boolean delete(@PathVariable Integer sid){
        //return studentMapper.deleteById(id); //同上
        return studentService.removeById(sid);
    }

    //mybatisplus方法查询
    @GetMapping("/page")
    public IPage<Student> findPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam(defaultValue = "") String studentName
//            , @RequestParam(defaultValue = "") String studentClass
    ){
        QueryWrapper<Student> queryWrapper=new QueryWrapper<>();
        IPage<Student> userPage=new Page<>(pageNum,pageSize);
        queryWrapper.like("name",studentName);
//        queryWrapper.like("classname",studentClass);
        queryWrapper.orderByDesc("sid");
        return studentService.page(userPage,queryWrapper);
    }

/*
    //分页 limit第一个参数
    //limit第一个参数=(pageNum-1)*pageSize,limit第二个参数pageSize
    @GetMapping("/page")
    public Map<String,Object> findPage(@RequestParam Integer pageNum,
                                       @RequestParam Integer pageSize,
                                       @RequestParam String studentName,
                                       @RequestParam String studentClass){
        pageNum=(pageNum-1)*pageSize;
        int total=studentMapper.selectTotal(studentName,studentClass);
        Map<String,Object> res= new HashMap<>();
        res.put("total",total);
        res.put("data",studentMapper.selectPage(pageNum,pageSize,studentName,studentClass));
        return res;
    }
    //这个是mybatis的写法
 */

}
