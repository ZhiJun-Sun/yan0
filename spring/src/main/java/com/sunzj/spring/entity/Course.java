package com.sunzj.spring.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "course")
public class Course {
    @TableId
    private Integer cid;
    private String coursename;
    private String grade;
    private String coursescore;
    private String descriptions;
}
