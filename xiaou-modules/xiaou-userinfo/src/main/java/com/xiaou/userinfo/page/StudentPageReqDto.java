package com.xiaou.userinfo.page;

import com.xiaou.common.page.PageReqDto;
import lombok.Data;

@Data
public class StudentPageReqDto extends PageReqDto {
    private String keyword; // 支持模糊查询
}
