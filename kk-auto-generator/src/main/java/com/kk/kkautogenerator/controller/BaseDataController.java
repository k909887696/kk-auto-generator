package com.kk.kkautogenerator.controller;

import com.kk.common.web.model.ApiResult;
import com.kk.kkautogenerator.model.dto.BaseDataItemMapGetDto;
import com.kk.kkautogenerator.model.vo.BaseDataItemMapGetVo;
import com.kk.kkautogenerator.service.IBaseDataService;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@Tag(name = "基础数据", description = "基础数据")
@ResponseBody
@RestController
@RequestMapping({"/kkautogenerator/api/v1/basedata"})
public class BaseDataController {


    @Resource
    public IBaseDataService baseDataService;


    @Operation(summary ="查询基础数据字典")
    @Parameters({@Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false)
            , @Parameter(name = "signature", description = "签名", in = ParameterIn.HEADER, required = false)
            , @Parameter(name = "timestamp", description = "时间戳", in = ParameterIn.HEADER, required = false)
            , @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false)
            , @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER)})
    @PostMapping({"/get_base_data_item_map"})
    public ApiResult<BaseDataItemMapGetDto> getBaseDataItemMap(@Valid @RequestBody BaseDataItemMapGetVo vo) {
        return new ApiResult(this.baseDataService.getBaseDataItemMap(vo));
    }


    @Operation(summary = "公共文件下载接口", description = "自定义请求头sessionId，sessionId的值是登陆接口返回的")
    @Parameters({@Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false), @Parameter(name = "signature", description = "签名", in = ParameterIn.HEADER, required = false), @Parameter(name = "timestamp", description = "时间戳", in = ParameterIn.HEADER, required = false), @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false), @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)})
    @RequestMapping(value = {"download_file"}, method = {RequestMethod.GET})
    public void downloadFile(HttpServletRequest request, HttpServletResponse response
            , @Parameter(required = true, description = "文件存储路径") @RequestParam("path") String path
            , @Parameter(required = true, description = "文件名称") @RequestParam("fileName") String fileName
            , @Parameter(required = false, description = "是否预览（1：预览，其余或者空：下载）") @RequestParam(name = "preView", required = false) String preView) {
        this.baseDataService.downloadFile(request, response, path, fileName, preView);
    }


}


