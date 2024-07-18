package com.sunzj.spring.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.sunzj.spring.entity.Student;
import com.sunzj.spring.mapper.StudentMapper;

@Service
public class StudentService extends ServiceImpl<StudentMapper,Student>
{
    public boolean saveStudent(Student student){
        /*if(student.getId()==null) {
            return save(student);  //mybatisplus提供的方法，表示插入数据
        }
        else {
            return updateById(student);
        }*/
        return saveOrUpdate(student);
    }
}
/*public class StudentService  //mybatis老方法
{
    @Autowired
    private StudentMapper studentMapper;

    public int save(Student student)
    {
        if(student.getId()== null)
        {
            return studentMapper.insert(student);
        }
        else
        { //更新
            return studentMapper.update(student);
        }
    }
}*/
