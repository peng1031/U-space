package com.xiaou.userinfo.controller;

import com.xiaou.common.domain.R;
import com.xiaou.common.page.PageReqDto;
import com.xiaou.common.page.PageRespDto;
import com.xiaou.userinfo.domain.bo.UStudentBO;
import com.xiaou.userinfo.domain.vo.StudentInfoVO;
import com.xiaou.userinfo.domain.vo.StudentVO;
import com.xiaou.userinfo.domain.vo.UClassVO;
import com.xiaou.userinfo.domain.vo.UMajorVO;
import com.xiaou.userinfo.page.StudentPageReqDto;
import com.xiaou.userinfo.service.StudentService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
@Validated
public class StudentController {
    @Resource
    private StudentService studentService;

    /**
     * 添加学生
     *
     * @param studentBO
     * @return
     */
    @PostMapping("/add")
    public R<StudentVO> addStudent(@RequestBody @Valid UStudentBO studentBO) {
        return studentService.addStudent(studentBO);
    }


    /**
     * 分页查询专业
     */
    @PostMapping("/page")
    public R<PageRespDto<StudentInfoVO>> allStudentPage(@RequestBody StudentPageReqDto pageReqDto) {
        return studentService.allStudentPage(pageReqDto);
    }

}
