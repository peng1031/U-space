package com.xiaou.userinfo.domain.bo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UClassBO {

    @NotBlank(message = "班级名称不能为空")
    private String name;         // 班级名称

    @NotNull(message = "所属专业ID不能为空")
    private Long majorId;        // 所属专业ID

    @NotNull(message = "入学年份不能为空")
    private Integer gradeYear;   // 入学年份

    private String classNo;      // 班号（如01、A1）

    private String headTeacher;  // 班主任

    @NotBlank(message = "班级状态不能为空")
    private String status;       // 班级状态（'在读', '毕业', '停办'）
}