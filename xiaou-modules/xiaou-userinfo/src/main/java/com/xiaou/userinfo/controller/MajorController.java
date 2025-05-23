package com.xiaou.userinfo.controller;

import com.xiaou.common.domain.R;
import com.xiaou.common.page.PageReqDto;
import com.xiaou.common.page.PageRespDto;
import com.xiaou.userinfo.domain.bo.UMajorBO;
import com.xiaou.userinfo.domain.entity.Major;
import com.xiaou.userinfo.domain.vo.UCollegeVO;
import com.xiaou.userinfo.domain.vo.UMajorVO;
import com.xiaou.userinfo.service.MajorService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/major")
@Validated
public class MajorController {

    @Resource
    private MajorService majorService;

    /**
     * 添加专业
     */
    @PostMapping("/add")
    public R<UMajorVO> addMajor(@RequestBody @Valid UMajorBO majorBO) {
        return majorService.addMajor(majorBO);
    }

    /**
     * 修改专业
     */
    @PutMapping("/update/{id}")
    public R<UMajorVO> updateMajor(
            @PathVariable("id") Long id,
            @RequestBody @Valid UMajorBO majorBO) {
        return majorService.updateMajor(id, majorBO);
    }

    /**
     * 删除专业
     */
    @DeleteMapping("/delete/{id}")
    public R<Void> deleteMajor(@PathVariable("id") Long id) {
        return majorService.deleteMajor(id);
    }

    /**
     * 分页查询专业
     */
    @PostMapping("/page")
    public R<PageRespDto<UMajorVO>> allMajorPage(@RequestBody PageReqDto pageReqDto) {
        return majorService.allMajorPage(pageReqDto);
    }

    /**
     * 根据id查询专业
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<UMajorVO> getCollegeById(@PathVariable Long id) {
        return majorService.getMajorById(id);
    }

}
