package com.xiaou.userinfo.domain.bo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 新增/修改专业的入参
 */
@Data
public class UMajorBO {
    @NotBlank(message = "专业名称不能为空")
    private String name;                // 专业名称

    @NotBlank(message = "专业代码不能为空")
    private String code;                // 专业代码

    @NotNull(message = "所属学院ID不能为空")
    private Long collegeId;             // 所属学院ID

    private String leader;              // 专业负责人

    @NotBlank(message = "学历层次不能为空")
    private String educationLevel;      // 学历层次（'本科','专科','研究生'）

    @NotBlank(message = "专业状态不能为空")
    private String status;              // 专业状态（'在办','停办','筹建'）
}