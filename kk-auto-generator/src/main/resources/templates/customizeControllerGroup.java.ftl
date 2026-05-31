package ${parentApiPackageName}.controller.group;

import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.annotation.Resource;
import ${parentApiPackageName}.serviceapi.${entity}ServiceApi;
<#list custControllerNamespace as imp>
import ${imp};
</#list>
<#list methods! as imp>
import ${parentApiPackageName}.entityvo.vobase.req.${imp.name}ReqVo;
import ${parentApiPackageName}.entityvo.vobase.res.${imp.name}ResVo;
</#list>
/**
 * <p>
 * ${classComment!} 集团内调用控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Tag(name = "${classComment!}(集团内调用)",description = "${classComment!}(集团内调用)")
@RestController
@RequestMapping(BaseWebApiFinal.PathGroupApi + CustomClientFinal.CustomSystemPath + "/Group${entity}")
@BaseWebApiGroupToken
public class Group${entity}Controller {


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
    public BaseApiTRspVo<${imp.name}ResVo> ${imp.name}(
        @RequestHeader(name = BaseWebApiFinal.HeaderGroupApiToken) String token,
        @RequestHeader(name = BaseWebApiFinal.HeaderGroupApiTimestamp) String timestamp,
        @RequestBody ${imp.name}ReqVo vo)   {

        return new BaseApiTRspVo<>(${entity?uncap_first}ServiceApi.${imp.name?uncap_first}(vo));

        }
</#list>


}
