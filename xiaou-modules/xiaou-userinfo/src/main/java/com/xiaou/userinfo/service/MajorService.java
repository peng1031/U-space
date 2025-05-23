package com.xiaou.userinfo.service;

import com.xiaou.common.domain.R;
import com.xiaou.common.page.PageReqDto;
import com.xiaou.common.page.PageRespDto;
import com.xiaou.userinfo.domain.bo.UMajorBO;
import com.xiaou.userinfo.domain.vo.UCollegeVO;
import com.xiaou.userinfo.domain.vo.UMajorVO;

public interface MajorService {
    R<UMajorVO> addMajor(UMajorBO majorBO);

    R<UMajorVO> updateMajor(Long id, UMajorBO majorBO);

    R<Void> deleteMajor(Long id);

    R<PageRespDto<UMajorVO>> allMajorPage(PageReqDto dto);

    R<UMajorVO> getMajorById(Long id);
}
