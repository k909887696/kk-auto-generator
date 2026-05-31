package ${package.Controller}.supplier;


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
 * ${table.comment!} 供应商接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if swagger>
@Tag(name = "${table.comment!}(供应商接口)",description = "${table.comment!}(供应商接口)")
</#if>
@RestController
@RequestMapping(BaseWebApiFinal.PathSupplierApi + CustomClientFinal.CustomSystemPath + "/Supplier${entity}")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class Supplier${entity}Controller {
</#if>

    @Resource
    public ${entity}ServiceApi ${entity?uncap_first}ServiceApi;

    @Operation(summary = "获取${table.comment!}分页结果集", description = "获取${table.comment!}分页结果集")
    @PostMapping("/get${entity}PageList")
    public BasePageApiTRspVo<${entity}ListResVo> get${entity}PageList(@RequestBody ${entity}ListReqVo vo)   {

        return ${entity?uncap_first}ServiceApi.selectPageList(vo);

    }
    @Operation(summary = "删除${table.comment!}", description = "删除${table.comment!}")
    @PostMapping("/delete${entity}ById")
    public BaseApiTRspVo<?> delete${entity}ById(@RequestBody ${entity}DeleteReqVo vo)   {
        ${entity?uncap_first}ServiceApi.deleteById(vo);
        return new BaseApiTRspVo<>();

    }
    @Operation(summary = "插入${table.comment!}", description = "插入${table.comment!}")
    @PostMapping("/insert${entity}")
    public BaseApiTRspVo<?> insert${entity}(@RequestBody ${entity}AddReqVo vo)   {
        ${entity?uncap_first}ServiceApi.insert(vo);
        return new BaseApiTRspVo<>();

    }
    @Operation(summary = "更新${table.comment!}", description = "更新${table.comment!}")
    @PostMapping("/update${entity}")
    public BaseApiTRspVo<?> update${entity}(@RequestBody ${entity}EditReqVo vo)   {
        ${entity?uncap_first}ServiceApi.update(vo);
        return new BaseApiTRspVo<>();
    }
    @Operation(summary = "查询${table.comment!}详情", description = "查询${table.comment!}详情")
    @PostMapping("/get${entity}Details")
    public BaseApiTRspVo< ${entity}ResVo > get${entity}Details(@RequestBody ${entity}DetailsReqVo vo)   {
        return new  BaseApiTRspVo<>(${entity?uncap_first}ServiceApi.selectById(vo));
    }



}
</#if>
