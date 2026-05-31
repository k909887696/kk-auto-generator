package com.kk.kkautogenerator.controller;

import com.kk.common.web.model.ApiResult;
import com.kk.kkautogenerator.model.dto.GenerateCodeDto;
import com.kk.kkautogenerator.model.vo.ColumnsInfoListVo;
import com.kk.kkautogenerator.model.vo.DbDataSource;
import com.kk.kkautogenerator.model.vo.GenerateCodeVo;
import com.kk.kkautogenerator.model.vo.TablesInfoListVo;
import com.kk.kkautogenerator.service.IDbInformationSchemaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Map;
import jakarta.annotation.Resource;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "数据库系统信息")
@ResponseBody
@RestController
@RequestMapping({"/kkautogenerator/api/v1/db_information_schema"})
public class DbInformationSchemaController {
    @Resource
    public IDbInformationSchemaService dbInformationSchemaService;

    @Operation(summary = "查询数据表信息")

    @PostMapping({"/get_tables_info"})
    public ApiResult<List<Map<String, Object>>> getTablesInfo(@Valid @RequestBody TablesInfoListVo vo) throws Exception {
        List<Map<String, Object>> res = this.dbInformationSchemaService.getTablesInfo(vo);
        return new ApiResult(res);
    }


    @Operation(summary = "查询数据表字段信息")
    
    @PostMapping({"/get_columns_info"})
    public ApiResult<List<Map<String, Object>>> getColumnsInfo(@Valid @RequestBody ColumnsInfoListVo vo) throws Exception {
        List<Map<String, Object>> res = this.dbInformationSchemaService.getColumnsInfo(vo);
        return new ApiResult(res);
    }


    @Operation(summary = "测试数据链接")
    
    @PostMapping({"/test_connect"})
    public ApiResult<String> testConnect(@Valid @RequestBody DbDataSource vo) throws Exception {
        this.dbInformationSchemaService.testConncet(vo);
        return ApiResult.SUCCESS;
    }


    @Operation(summary = "生成代码")
    
    @PostMapping({"/generate_code"})
    public ApiResult<GenerateCodeDto> generateCode(HttpServletResponse response, @Valid @RequestBody GenerateCodeVo vo) throws Exception {
        GenerateCodeDto res = this.dbInformationSchemaService.generateCode(vo);


        return new ApiResult(res);
    }
}


