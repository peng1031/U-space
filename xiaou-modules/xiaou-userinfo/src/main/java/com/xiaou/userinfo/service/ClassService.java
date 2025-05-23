package com.xiaou.userinfo.service;

import com.xiaou.common.domain.R;
import com.xiaou.common.page.PageReqDto;
import com.xiaou.common.page.PageRespDto;
import com.xiaou.userinfo.domain.bo.UClassBO;
import com.xiaou.userinfo.domain.vo.UClassVO;

public interface ClassService {
    /**
     * 新增班级
     * @param classBO 班级业务对象
     * @return 新增后的班级视图对象
     */
    R<UClassVO> addClass(UClassBO classBO);

    /**
     * 根据ID更新班级
     * @param id 班级ID
     * @param classBO 班级业务对象
     * @return 更新后的班级视图对象
     */
    R<UClassVO> updateClass(Long id, UClassBO classBO);

    /**
     * 根据ID删除班级
     * @param id 班级ID
     * @return 删除结果
     */
    R<Void> deleteClass(Long id);

    /**
     * 分页查询班级列表
     * @param dto 分页请求参数
     * @return 分页后的班级视图对象列表
     */
    R<PageRespDto<UClassVO>> allClassPage(PageReqDto dto);

    R<UClassVO> getClassById(Long id);
}
