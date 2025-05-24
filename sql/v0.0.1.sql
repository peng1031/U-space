-- 创建数据库（如果不存在）
CREATE DATABASE IF NOT EXISTS `u-space` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- 使用该数据库
USE `u-space`;

-- 创建管理员用户表
CREATE TABLE `admin_user`
(
    `id`       BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    `username` VARCHAR(50)  NOT NULL UNIQUE COMMENT '用户名',
    `password` VARCHAR(100) NOT NULL COMMENT '密码'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='管理员用户表';


DROP TABLE IF EXISTS u_college;

CREATE TABLE u_college
(
    college_id   BIGINT          NOT NULL AUTO_INCREMENT COMMENT '学院编号（主键）',
    name         VARCHAR(255) NOT NULL COMMENT '学院名称',
    dean         VARCHAR(100) DEFAULT NULL COMMENT '院长姓名',
    phone        VARCHAR(20)  DEFAULT NULL COMMENT '联系电话',
    email        VARCHAR(100) DEFAULT NULL COMMENT '电子邮箱',
    campus_name  VARCHAR(255) NOT NULL COMMENT '所属校区名称',
    created_by   VARCHAR(32)  DEFAULT NULL COMMENT '创建人',
    created_time DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_by   VARCHAR(32)  DEFAULT NULL COMMENT '更新人',
    updated_time DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (college_id),
    KEY idx_name (name),
    KEY idx_campus_name (campus_name)
) COMMENT ='学院信息表';


CREATE TABLE u_major (
                         major_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '专业编号（主键）',
                         name VARCHAR(255) NOT NULL COMMENT '专业名称',
                         code VARCHAR(50) NOT NULL COMMENT '专业代码',
                         college_id BIGINT NOT NULL COMMENT '所属学院ID',
                         leader VARCHAR(100) DEFAULT NULL COMMENT '专业负责人',
                         education_level ENUM('本科', '专科', '研究生') DEFAULT '本科' COMMENT '学历层次',
                         status ENUM('在办', '停办', '筹建') DEFAULT '在办' COMMENT '专业状态',
                         created_by VARCHAR(32) DEFAULT NULL COMMENT '创建人',
                         created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                         updated_by VARCHAR(32) DEFAULT NULL COMMENT '更新人',
                         updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                         PRIMARY KEY (major_id),
                         KEY idx_name (name),
                         KEY idx_college (college_id),
                         CONSTRAINT fk_major_college FOREIGN KEY (college_id) REFERENCES u_college(college_id)
                             ON DELETE CASCADE ON UPDATE CASCADE
) COMMENT='专业信息表';


DROP TABLE IF EXISTS u_class;

CREATE TABLE u_class (
                         class_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '班级编号（主键）',
                         name VARCHAR(100) NOT NULL COMMENT '班级名称',
                         major_id BIGINT NOT NULL COMMENT '所属专业ID',
                         grade_year YEAR NOT NULL COMMENT '入学年份',
                         class_no VARCHAR(20) DEFAULT NULL COMMENT '班号（如01、A1）',
                         head_teacher VARCHAR(100) DEFAULT NULL COMMENT '班主任',
                         status ENUM('在读', '毕业', '停办') DEFAULT '在读' COMMENT '班级状态',
                         created_by VARCHAR(32) DEFAULT NULL COMMENT '创建人',
                         created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                         updated_by VARCHAR(32) DEFAULT NULL COMMENT '更新人',
                         updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                         PRIMARY KEY (class_id),
                         KEY idx_major (major_id),
                         KEY idx_grade (grade_year),
                         CONSTRAINT fk_class_major FOREIGN KEY (major_id) REFERENCES u_major(major_id)
                             ON DELETE CASCADE ON UPDATE CASCADE
) COMMENT='班级信息表';



DROP TABLE IF EXISTS u_student;

CREATE TABLE u_student (
                           student_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '学生编号（主键）',
                           student_number VARCHAR(30) NOT NULL UNIQUE COMMENT '学号',
                           name VARCHAR(100) NOT NULL COMMENT '姓名',
                           gender ENUM('男', '女', '其他') DEFAULT '男' COMMENT '性别',
                           phone VARCHAR(20) DEFAULT NULL COMMENT '联系电话',
                           enrollment_year YEAR NOT NULL COMMENT '入学年份',
                           status ENUM('在读', '休学', '退学', '毕业') DEFAULT '在读' COMMENT '学籍状态',
                           created_by VARCHAR(32) DEFAULT NULL COMMENT '创建人',
                           created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           updated_by VARCHAR(32) DEFAULT NULL COMMENT '更新人',
                           updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                           PRIMARY KEY (student_id),
                           UNIQUE KEY uq_student_number (student_number),
                           KEY idx_name (name)
) COMMENT='学生信息表';


DROP TABLE IF EXISTS u_student_info_link;

CREATE TABLE u_student_info_link (
                                     link_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                     student_number VARCHAR(30) NOT NULL COMMENT '学号',
                                     major_id BIGINT NOT NULL COMMENT '专业ID',
                                     class_id BIGINT NOT NULL COMMENT '班级ID',
                                     college_id BIGINT NOT NULL COMMENT '学院ID',
                                     created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                     updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                     PRIMARY KEY (link_id),
                                     UNIQUE KEY uq_student_number (student_number),
                                     KEY idx_major (major_id),
                                     KEY idx_class (class_id),
                                     KEY idx_college (college_id)
) COMMENT='学生信息关联表';

-- u_student 主键 idx 已有
ALTER TABLE u_student_info_link
    ADD INDEX idx_student_number   (student_number),
    ADD INDEX idx_college_id       (college_id),
    ADD INDEX idx_major_id         (major_id),
    ADD INDEX idx_class_id         (class_id);
