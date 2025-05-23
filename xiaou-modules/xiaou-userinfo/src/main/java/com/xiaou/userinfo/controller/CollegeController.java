package com.xiaou.userinfo.controller;

import com.xiaou.common.domain.R;
import com.xiaou.userinfo.domain.bo.UCollegeBO;
import com.xiaou.userinfo.domain.vo.UCollegeVO;
import com.xiaou.userinfo.service.CollegeService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/college")
@Validated
public class CollegeController {

    @Resource
    private CollegeService collegeService;

    /**
     * 添加学院
     */
    @PostMapping("/add")
    public R<UCollegeVO> addCollege(@RequestBody @Valid UCollegeBO collegeBO) {
        return collegeService.addCollege(collegeBO);
    }

    /**
     * 修改学院
     */
    @PutMapping("/update/{id}")
    public R<UCollegeVO> updateCollege(
            @PathVariable("id") Long id,
            @RequestBody @Valid UCollegeBO collegeBO) {
        return collegeService.updateCollege(id, collegeBO);
    }

    /**
     * 删除学院
     */
    @DeleteMapping("/delete/{id}")
    public R<Void> deleteCollege(@PathVariable("id") Long id) {
        return collegeService.deleteCollege(id);
    }
}
