package com.sunzj.spring.impl;

import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import org.springframework.stereotype.Service;
import com.sunzj.spring.entity.Grade;
import com.sunzj.spring.mapper.GradeMapper;
import com.sunzj.spring.service.GradeService;

@Service
public class GradeServiceImpl extends MppServiceImpl<GradeMapper,Grade> implements GradeService {
}
