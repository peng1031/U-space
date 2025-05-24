package com.xiaou.userinfo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaou.common.domain.R;
import com.xiaou.common.page.PageReqDto;
import com.xiaou.common.page.PageRespDto;
import com.xiaou.userinfo.domain.bo.UStudentBO;
import com.xiaou.userinfo.domain.entity.Student;
import com.xiaou.userinfo.domain.entity.StudentInfoLink;
import com.xiaou.userinfo.domain.vo.StudentInfoVO;
import com.xiaou.userinfo.domain.vo.StudentVO;
import com.xiaou.userinfo.mapper.*;
import com.xiaou.userinfo.page.StudentPageReqDto;
import com.xiaou.userinfo.service.StudentService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.xiaou.userinfo.utils.UserContextUtil.getCurrentUsername;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {


    @Resource
    private StudentMapper studentMapper;
    @Resource
    private CollegeMapper collegeMapper;
    @Resource
    private MajorMapper majorMapper;
    @Resource
    private ClassMapper classMapper;
    @Resource
    private StudentInfoLinkMapper studentInfoLinkMapper;

    @Override
    @Transactional
    public R<StudentVO> addStudent(UStudentBO studentBO) {
        Student student = new Student();
        BeanUtils.copyProperties(studentBO, student);
        student.setCreatedTime(LocalDateTime.now());
        student.setCreatedBy(getCurrentUsername());
        studentMapper.insert(student);

        //传入的几个id查询查询专业
        Long collegeId = studentBO.getCollegeId();
        Long majorId = studentBO.getMajorId();
        Long classId = studentBO.getClassId();
        //根据id查询名字
        String collegeName = collegeMapper.selectById(collegeId).getName();
        String majorName = majorMapper.selectById(majorId).getName();
        String className = classMapper.selectById(classId).getName();

        //添加到学生信息关系表中
        StudentInfoLink studentInfoLink = new StudentInfoLink();
        studentInfoLink.setStudentNumber(student.getStudentNumber());
        studentInfoLink.setCollegeId(collegeId);
        studentInfoLink.setMajorId(majorId);
        studentInfoLink.setClassId(classId);
        studentInfoLink.setCreatedTime(LocalDateTime.now());
        studentInfoLinkMapper.insert(studentInfoLink);

        //封装数据
        return R.ok(StudentVO.fromEntity(student, collegeName, majorName, className));

    }

    @Override
    public R<PageRespDto<StudentInfoVO>> allStudentPage(StudentPageReqDto req) {
        IPage<StudentInfoVO> page = new Page<>(req.getPageNum(), req.getPageSize());
        IPage<StudentInfoVO> result = studentMapper.selectStudentPage(page, req);
        return R.ok(PageRespDto.of(req.getPageNum(), req.getPageSize(), result.getTotal(),
                result.getRecords().stream().toList()));
    }

}
