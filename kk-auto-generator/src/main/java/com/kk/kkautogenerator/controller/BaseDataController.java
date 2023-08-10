package com.kk.kkautogenerator.controller;

import com.kk.common.web.model.ApiResult;
import com.kk.kkautogenerator.model.dto.BaseDataItemMapGetDto;
import com.kk.kkautogenerator.model.vo.BaseDataItemMapGetVo;
import com.kk.kkautogenerator.service.IBaseDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@Api(value = "/kkautogenerator/api/v1/basedata", tags = {"基础数据"})
@ResponseBody
@RestController
@RequestMapping({"/kkautogenerator/api/v1/basedata"})
public class BaseDataController {


    @Resource
    public IBaseDataService baseDataService;


    @ApiOperation("查询基础数据字典")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = false, dataType = "String"), @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = false, dataType = "String"), @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = false, dataType = "String"), @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = false, dataType = "String"), @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header", dataType = "String")})
    @PostMapping({"/get_base_data_item_map"})
    public ApiResult<BaseDataItemMapGetDto> getBaseDataItemMap(@Valid @RequestBody BaseDataItemMapGetVo vo) {
        return new ApiResult(this.baseDataService.getBaseDataItemMap(vo));
    }


    @ApiOperation(value = "公共文件下载接口", notes = "自定义请求头sessionId，sessionId的值是登陆接口返回的")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = false, dataType = "String"), @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = false, dataType = "String"), @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = false, dataType = "String"), @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = false, dataType = "String"), @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header", dataType = "String")})
    @RequestMapping(value = {"download_file"}, method = {RequestMethod.GET})
    public void downloadFile(HttpServletRequest request, HttpServletResponse response, @ApiParam(required = true, value = "文件存储路径") @RequestParam("path") String path, @ApiParam(required = true, value = "文件名称") @RequestParam("fileName") String fileName, @ApiParam(required = false, value = "是否预览（1：预览，其余或者空：下载）") @RequestParam(name = "preView", required = false) String preView) {
        this.baseDataService.downloadFile(request, response, path, fileName, preView);
    }


}


