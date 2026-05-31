package ${package.Controller}.group;

import org.springframework.web.bind.annotation.*;
<#if swagger>
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
</#if>
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.annotation.Resource;
import ${package.Parent}.serviceapi.${entity}ServiceApi;
<#list custControllerNamespace as imp>
import ${imp};
</#list>
import ${entityvoPackageName}.vobase.req.${entity}ListReqVo;
import ${entityvoPackageName}.vobase.res.${entity}ListResVo;
import ${entityvoPackageName}.vobase.req.${entity}AddReqVo;
import ${entityvoPackageName}.vobase.req.${entity}EditReqVo;
import ${entityvoPackageName}.vobase.res.${entity}ResVo;
import ${entityvoPackageName}.vobase.req.${entity}DetailsReqVo;
import ${entityvoPackageName}.vobase.req.${entity}DeleteReqVo;
/**
 * <p>
 * ${table.comment!} 集团内调用控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if swagger>
@Tag(name = "${table.comment!}(集团内调用)",description = "${table.comment!}(集团内调用)")
</#if>
@RestController
@RequestMapping(BaseWebApiFinal.PathGroupApi + CustomClientFinal.CustomSystemPath + "/Group${entity}")
@BaseWebApiGroupToken
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class Group${entity}Controller {
</#if>

    @Resource
    public ${entity}ServiceApi ${entity?uncap_first}ServiceApi;

    @Operation(summary = "获取${table.comment!}分页结果集", description = "获取${table.comment!}分页结果集")
    @PostMapping("/get${entity}PageList")
    public BasePageApiTRspVo<${entity}ListResVo> get${entity}PageList(
            @RequestHeader(name = BaseWebApiFinal.HeaderGroupApiToken) String token,
            @RequestHeader(name = BaseWebApiFinal.HeaderGroupApiTimestamp) String timestamp,
            @RequestBody ${entity}ListReqVo vo)   {
        return ${entity?uncap_first}ServiceApi.selectPageList(vo);
    }

    @Operation(summary = "查询${table.comment!}详情", description = "查询${table.comment!}详情")
    @PostMapping("/get${entity}Details")
    public BaseApiTRspVo< ${entity}ResVo > get${entity}Details(
            @RequestHeader(name = BaseWebApiFinal.HeaderGroupApiToken) String token,
            @RequestHeader(name = BaseWebApiFinal.HeaderGroupApiTimestamp) String timestamp,
            @RequestBody ${entity}DetailsReqVo vo)   {
        return new  BaseApiTRspVo<>(${entity?uncap_first}ServiceApi.selectById(vo));
    }



}
</#if>
