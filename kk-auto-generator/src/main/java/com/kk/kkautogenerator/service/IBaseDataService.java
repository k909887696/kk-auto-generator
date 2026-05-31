package com.kk.kkautogenerator.service;

import com.kk.kkautogenerator.model.dto.BaseDataItemMapGetDto;
import com.kk.kkautogenerator.model.vo.BaseDataItemMapGetVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public interface IBaseDataService {
    BaseDataItemMapGetDto getBaseDataItemMap(BaseDataItemMapGetVo paramBaseDataItemMapGetVo);

    void downloadFile(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, String paramString1, String paramString2, String paramString3);
}


