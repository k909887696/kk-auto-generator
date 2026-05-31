package ${package.Controller};


import org.springframework.web.bind.annotation.*;
<#if swagger>
import io.swagger.v3.oas.annotations.media.Schema;
mport io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
</#if>
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import com.meiya.tihb.common.BaseExceptionCustom;
import com.meiya.tihb.webapicode.BaseWebApiFinal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import ${package.Service}.${entity}ServiceApi;
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
import ${package.Entity}.${entity};
/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if swagger>
@Tag(name = "${table.comment!}",description = "${table.comment!}")
</#if>
@RestController
@RequestMapping(BaseWebApiFinal.PathInternalApi + CustomWebApiFinal.CustomSystemPath + "/Intra${entity}Api")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class Intra${entity}ApiController {
</#if>

    @Resource
    public ${entity}ServiceApi ${entity?uncap_first}ServiceApi;

    @Operation("获取${table.comment!}分页结果集")
    @PostMapping("/get${entity}PageList")
    public BasePageApiTRspVo<${entity}ListVo> get${entity}PageList(@Valid @RequestBody ${entity}ListVo vo)   {

        return ${entity?uncap_first}ServiceApi.selectPageList(vo);

    }
    @Operation("删除${table.comment!}")
    @PostMapping("/delete${entity}ById")
    public BaseApiTRspVo<?> delete${entity}ById(@Valid @RequestBody ${entity}DeleteVo vo)   {
        ${entity?uncap_first}ServiceApi.deleteById(vo);
        return new BaseApiTRspVo<>();

    }
    @Operation("插入${table.comment!}")
    @PostMapping("/insert${entity}")
    public BaseApiTRspVo<?> insert${entity}(@Valid @RequestBody ${entity}AddVo vo)   {
        ${entity?uncap_first}ServiceApi.insert(vo);
        return new BaseApiTRspVo<>();

    }
    @Operation("更新${table.comment!}")
    @PostMapping("/update${entity}")
    public BaseApiTRspVo<?> update${entity}(@Valid @RequestBody ${entity}EditVo vo)   {
        ${entity?uncap_first}ServiceApi.update(vo);
        return new BaseApiTRspVo<>();

    }
    @Operation("查询${table.comment!}详情")
    @PostMapping("/get${entity}Details")
    public BaseApiTRspVo< ${entity}ResVo > get${entity}Details(@Valid @RequestBody ${entity}DetailsReqVo vo)   {

        return new  BaseApiTRspVo<>(${entity?uncap_first}Service.selectById(vo));

    }



}
</#if>
