
package com.xiaou.userinfo.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaou.common.domain.R;
import com.xiaou.common.page.PageReqDto;
import com.xiaou.common.page.PageRespDto;
import com.xiaou.userinfo.domain.bo.UCollegeBO;
import com.xiaou.userinfo.domain.entity.College;
import com.xiaou.userinfo.domain.vo.UCollegeVO;
import com.xiaou.userinfo.mapper.CollegeMapper;
import com.xiaou.userinfo.service.CollegeService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class CollegeServiceImpl extends ServiceImpl<CollegeMapper, College> implements CollegeService {

    @Resource
    private CollegeMapper collegeMapper;

    @Override
    public R<UCollegeVO> addCollege(UCollegeBO collegeBO) {
        College college = new College();
        BeanUtils.copyProperties(collegeBO, college);
        college.setCreatedTime(LocalDateTime.now());
        college.setCreatedBy(getCurrentUsername());
        collegeMapper.insert(college);
        return R.ok(UCollegeVO.fromEntity(college));
    }

    @Override
    public R<UCollegeVO> updateCollege(Long id, UCollegeBO collegeBO) {
        College college = collegeMapper.selectById(id);
        if (college == null) {
            return R.fail("学院不存在，无法更新");
        }
        BeanUtils.copyProperties(collegeBO, college);
        college.setCollegeId(id);
        college.setUpdatedTime(LocalDateTime.now());
        college.setUpdatedBy(getCurrentUsername());
        collegeMapper.updateById(college);
        return R.ok(UCollegeVO.fromEntity(college));
    }

    @Override
    public R<Void> deleteCollege(Long id) {
        int rows = collegeMapper.deleteById(id);
        if (rows > 0) {
            return R.ok();
        } else {
            return R.fail("学院不存在或已被删除");
        }
    }

    @Override
    public R<PageRespDto<College>> allCollegePage(PageReqDto dto) {
        IPage<College> page = new Page<>();
        page.setCurrent(dto.getPageNum());
        page.setSize(dto.getPageSize());
        IPage<College> collegeIPage = collegeMapper.selectPage(page, new QueryWrapper<>());
        return R.ok(PageRespDto.of(dto.getPageNum(), dto.getPageSize(), page.getTotal(),
                collegeIPage.getRecords().stream().toList()));
    }

    @Override
    public R<UCollegeVO> getCollegeById(Long id) {
        College college = collegeMapper.selectById(id);
        if (college == null) {
            return R.fail("学院不存在");
        }
        return R.ok(UCollegeVO.fromEntity(college));
    }


    /**
     * 从 Sa-Token Session 中解析当前用户名
     */
    private String getCurrentUsername() {
        Object obj = StpUtil.getSession().get("user_login");
        JSONObject json = JSON.parseObject(JSON.toJSONString(obj));
        return json.getString("username");
    }
}
