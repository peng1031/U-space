package com.xiaou.userinfo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaou.userinfo.domain.entity.Student;
import com.xiaou.userinfo.domain.vo.StudentInfoVO;
import com.xiaou.userinfo.page.StudentPageReqDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    IPage<StudentInfoVO> selectStudentPage(
            @Param("page") IPage<StudentInfoVO> page,
            @Param("req") StudentPageReqDto req
    );
}

