package ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.controllerInnNameSpace};


import org.springframework.web.bind.annotation.*;
<#if swagger>
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
</#if>
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.annotation.Resource;
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.serviceApiNameSpace}.${entity}ServiceApi;
<#list baseNameSpace.controllerVImport as imp>
import ${imp};
</#list>
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.modelVoBaseNameSpace}.req.${entity}ListReqVo;
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.modelVoBaseNameSpace}.res.${entity}ListResVo;
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.modelVoBaseNameSpace}.req.${entity}AddReqVo;
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.modelVoBaseNameSpace}.req.${entity}EditReqVo;
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.modelVoBaseNameSpace}.res.${entity}ResVo;
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.modelVoBaseNameSpace}.req.${entity}DetailsReqVo;
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.modelVoBaseNameSpace}.req.${entity}DeleteReqVo;
/**
 * <p>
 * ${table.comment!} 内部接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if swagger>
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/${baseNameSpace.controllerInnNamePrefix?cap_first}${entity}",description = "${table.comment!}(内部接口)")
</#if>
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/${baseNameSpace.controllerInnNamePrefix?cap_first}${entity}")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${baseNameSpace.controllerInnNamePrefix?cap_first}${entity}Controller {
</#if>

    @Resource
    public ${entity}ServiceApi ${entity?uncap_first}ServiceApi;

    @Operation(summary = "获取${table.comment!}分页结果集", description = "获取${table.comment!}分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/get${entity}PageList")
    //@SaCheckPermission(value = {"v_menu_select_${entity}"})
    public ApiResult<${baseNameSpace.basePageResult}<${entity}ListResVo>> get${entity}PageList(
            @RequestBody ${entity}ListReqVo vo)   {
        return new ApiResult<>(${entity?uncap_first}ServiceApi.select${entity}PageList(vo));
    }
    @Operation(summary = "删除${table.comment!}", description = "删除${table.comment!}")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/delete${entity}ById")
    //@SaCheckPermission(value={"v_btn_delete_${entity}"})
    public ApiResult<?> delete${entity}ById(
            @RequestBody ${entity}DeleteReqVo vo)   {
        ${entity?uncap_first}ServiceApi.delete${entity}ById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入${table.comment!}", description = "插入${table.comment!}")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insert${entity}")
    //@SaCheckPermission(value={"v_btn_modify_${entity}"})
    public ApiResult<?> insert${entity}(
            @RequestBody ${entity}AddReqVo vo)   {
        ${entity?uncap_first}ServiceApi.insert${entity}(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新${table.comment!}", description = "更新${table.comment!}")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/update${entity}")
    //@SaCheckPermission(value={"v_btn_modify_${entity}"})
    public ApiResult<?> update${entity}(
            @RequestBody ${entity}EditReqVo vo)   {
        ${entity?uncap_first}ServiceApi.update${entity}(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询${table.comment!}详情", description = "查询${table.comment!}详情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/get${entity}Details")
    //@SaCheckPermission(value={"inc_menu_select_${entity}"})
    public ApiResult< ${entity}ResVo > get${entity}Details(
            @RequestBody ${entity}DetailsReqVo vo)   {
        return new  ApiResult<>(${entity?uncap_first}ServiceApi.select${entity}ById(vo));
    }



}
</#if>
