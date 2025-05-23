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
import com.xiaou.userinfo.domain.bo.UMajorBO;
import com.xiaou.userinfo.domain.entity.College;
import com.xiaou.userinfo.domain.entity.Major;
import com.xiaou.userinfo.domain.vo.UMajorVO;
import com.xiaou.userinfo.mapper.CollegeMapper;
import com.xiaou.userinfo.mapper.MajorMapper;
import com.xiaou.userinfo.service.MajorService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major> implements MajorService {
    @Resource
    private MajorMapper majorMapper;

    @Resource
    private CollegeMapper collegeMapper;

    @Override
    public R<UMajorVO> addMajor(UMajorBO majorBO) {
        Major major = new Major();
        BeanUtils.copyProperties(majorBO, major);
        major.setCreatedTime(LocalDateTime.now());
        major.setCreatedBy(getCurrentUsername());
        majorMapper.insert(major);

        String collegeName = getCollegeNameById(major.getCollegeId());
        return R.ok(UMajorVO.fromEntity(major, collegeName));
    }


    @Override
    public R<UMajorVO> updateMajor(Long id, UMajorBO majorBO) {
        Major major = majorMapper.selectById(id);
        if (major == null) {
            return R.fail("专业不存在，无法更新");
        }
        BeanUtils.copyProperties(majorBO, major);
        major.setMajorId(id);
        major.setUpdatedTime(LocalDateTime.now());
        major.setUpdatedBy(getCurrentUsername());
        majorMapper.updateById(major);

        String collegeName = getCollegeNameById(major.getCollegeId());
        return R.ok(UMajorVO.fromEntity(major, collegeName));
    }


    @Override
    public R<Void> deleteMajor(Long id) {
        int rows = majorMapper.deleteById(id);
        if (rows > 0) {
            return R.ok();
        } else {
            return R.fail("专业不存在或已被删除");
        }
    }

    @Override
    public R<PageRespDto<UMajorVO>> allMajorPage(PageReqDto dto) {
        IPage<Major> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        IPage<Major> majPage = majorMapper.selectPage(page, new QueryWrapper<>());

        // 转换为 VO 并填充 collegeName
        List<UMajorVO> voList = majPage.getRecords().stream().map(major -> {
            String collegeName = getCollegeNameById(major.getCollegeId());
            return UMajorVO.fromEntity(major, collegeName);
        }).toList();

        return R.ok(PageRespDto.of(
                dto.getPageNum(),
                dto.getPageSize(),
                majPage.getTotal(),
                voList
        ));
    }


    /**
     * 从 Sa-Token Session 中解析当前用户名
     */
    private String getCurrentUsername() {
        Object obj = StpUtil.getSession().get("user_login");
        JSONObject json = JSON.parseObject(JSON.toJSONString(obj));
        return json.getString("username");
    }

    /**
     * 根据学院ID获取学院名称
     *
     * @param collegeId
     * @return
     */
    private String getCollegeNameById(Long collegeId) {
        if (collegeId == null) return null;
        College college = collegeMapper.selectById(collegeId);
        return college != null ? college.getName() : null;
    }

}
