package com.sunzj.spring.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.Data;

@Data
@TableName("grade")
public class Grade {

    @MppMultiId
    @TableField("sid")
    private Integer sid;

    @MppMultiId
    @TableField("cid")
    private Integer cid;

    private Integer score;

}
