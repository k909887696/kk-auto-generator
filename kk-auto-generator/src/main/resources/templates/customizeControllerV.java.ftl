package ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.controllerVNameSpace};


import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.annotation.Resource;
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.serviceApiNameSpace}.${entity}ServiceApi;
<#list baseNameSpace.controllerVImport as imp>
import ${imp};
</#list>
<#list methods! as imp>
import ${baseNameSpace.apiParentNameSpace}.${baseNameSpace.modelVoBaseNameSpace}.req.${imp.name}ReqVo;
import ${baseNameSpace.apiParentNameSpace}.${baseNameSpace.modelVoBaseNameSpace}.res.${imp.name}ResVo;
</#list>
/**
* <p>
    * ${classComment!} 接口
    * </p>
*
* @author ${author}
* @since ${date}
*/
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin + "/v1/${baseNameSpace.controllerVNamePrefix?cap_first}${entity}",description = "${table.comment!}(接口)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin + "/v1/${baseNameSpace.controllerVNamePrefix?cap_first}${entity}")
public class ${baseNameSpace.controllerVNamePrefix?cap_first}${entity}Controller {

    @Resource
    public ${entity}ServiceApi ${entity?uncap_first}ServiceApi;


    <#list methods! as imp>
    /**
    * ${imp.comment!}
    * @param vo
    * @return
    */
    @Operation(summary = "${imp.comment!}", description = "${imp.comment!}")
    @PostMapping("/${imp.name}")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    //@SaCheckPermission(value = {"v_btn_${imp.name}"})
    public ApiResult<${imp.name}ResVo> ${imp.name}(
        @RequestBody ${imp.name}ReqVo vo)   {
        return new ApiResult<>(${entity?uncap_first}ServiceApi.${imp.name?uncap_first}(vo));
    }
</#list>

}

