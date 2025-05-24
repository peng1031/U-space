package com.xiaou.userinfo.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentInfoVO {

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

    private String major;              // 专业名称
    private String className;          // 班级名称
    private String college;            // 学院名称
}
