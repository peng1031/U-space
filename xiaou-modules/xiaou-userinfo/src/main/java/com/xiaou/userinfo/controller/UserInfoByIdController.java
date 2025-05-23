package com.xiaou.userinfo.controller;

import com.xiaou.common.domain.R;
import com.xiaou.userinfo.domain.vo.UClassVO;
import com.xiaou.userinfo.domain.vo.UCollegeVO;
import com.xiaou.userinfo.domain.vo.UMajorVO;
import com.xiaou.userinfo.service.ClassService;
import com.xiaou.userinfo.service.CollegeService;
import com.xiaou.userinfo.service.MajorService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 根据id查询对应信息
 */
@RestController
@RequestMapping("/info")
public class UserInfoByIdController {
    @Resource
    private CollegeService collegeService;

    @Resource
    private MajorService majorService;

    @Resource
    private ClassService classService;
    /**
     * 根据学院id查询学院下面包括的专业 查询major表
     */
    @GetMapping("/major/{id}")
    public R<UCollegeVO> getCollegeById(@PathVariable Long id) {
        return collegeService.getCollegeById(id);
    }
}
