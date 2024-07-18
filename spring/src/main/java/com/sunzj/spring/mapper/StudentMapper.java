package com.sunzj.spring.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunzj.spring.entity.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {

}

