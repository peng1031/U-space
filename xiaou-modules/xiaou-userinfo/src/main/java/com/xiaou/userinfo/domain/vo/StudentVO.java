package com.xiaou.userinfo.domain.vo;


import com.xiaou.userinfo.domain.entity.Student;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 学生视图对象（用于前端展示）
 */
@Data
@Builder
public class StudentVO {

    private String studentNumber;      // 学号
    private String name;               // 姓名
    private String gender;             // 性别
    private String phone;              // 联系电话
    private Integer enrollmentYear;    // 入学年份
    private String status;             // 学籍状态

    private String createdBy;          // 创建人
    private String updatedBy;          // 更新人
    private LocalDateTime createdTime; // 创建时间
    private LocalDateTime updatedTime; // 更新时间

    private String major;           // 专业名称

    private String className;       // 班级名称

    private String college;         // 学院名称

    public static StudentVO fromEntity(Student student,String major,String classname,String college) {
        return StudentVO.builder()
                .studentNumber(student.getStudentNumber())
                .name(student.getName())
                .gender(student.getGender())
                .phone(student.getPhone())
                .enrollmentYear(student.getEnrollmentYear())
                .status(student.getStatus())
                .createdBy(student.getCreatedBy())
                .updatedBy(student.getUpdatedBy())
                .createdTime(student.getCreatedTime())
                .updatedTime(student.getUpdatedTime())
                .major(major)
                .className(classname)
                .college(college)
                .build();
    }
}
