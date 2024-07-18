package com.sunzj.spring.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "student") //mybatisplus指定表
public class Student {
    @TableId  //mybatisplus指定主键,指定与否随意
    private Integer sid;
    private String name;
    private String age;
    private String sex;
    private String phone;
    private String address;
    private String hometown;
    private String xueyuan;
    private String shuyuan;
    private String grade;

}
