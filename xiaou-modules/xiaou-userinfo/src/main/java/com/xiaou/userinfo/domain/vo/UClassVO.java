package com.xiaou.userinfo.domain.vo;

import com.xiaou.userinfo.domain.entity.ClassEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UClassVO {
    private Long classId;
    private String name;
    private Long majorId;
    private Integer gradeYear;
    private String classNo;
    private String headTeacher;
    private String status;
    private String createdBy;
    private String createdTime;  // 如果实体是LocalDateTime，可改成对应类型
    private String updatedBy;
    private String updatedTime;
    private String majorName; // 新增字段：专业名称

    public static UClassVO fromEntity(ClassEntity clazz, String majorName) {
        return UClassVO.builder()
                .classId(clazz.getClassId())
                .name(clazz.getName())
                .majorId(clazz.getMajorId())
                .gradeYear(clazz.getGradeYear())
                .classNo(clazz.getClassNo())
                .headTeacher(clazz.getHeadTeacher())
                .status(clazz.getStatus())
                .createdBy(clazz.getCreatedBy())
                .createdTime(clazz.getCreatedTime() != null ? clazz.getCreatedTime().toString() : null)
                .updatedBy(clazz.getUpdatedBy())
                .updatedTime(clazz.getUpdatedTime() != null ? clazz.getUpdatedTime().toString() : null)
                .majorName(majorName)
                .build();
    }
}
