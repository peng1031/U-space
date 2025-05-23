package com.xiaou.userinfo.domain.vo;

import com.xiaou.userinfo.domain.entity.Major;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class UMajorVO {
    private Long majorId;
    private String name;
    private String code;
    private Long collegeId;
    private String leader;
    private String educationLevel;
    private String status;
    private String collegeName; // 新增字段

    public static UMajorVO fromEntity(Major major, String collegeName) {
        UMajorVO vo = new UMajorVO();
        BeanUtils.copyProperties(major, vo);
        vo.setCollegeName(collegeName);
        return vo;
    }
}
