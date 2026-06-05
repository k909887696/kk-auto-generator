package ${baseNameSpace.clientParentNameSpace}.${baseNameSpace.clientServiceNameSpace};


import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
<#list methods! as imp>
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.clientModelVoBaseNameSpace}.req.${imp.name}ReqVo;
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.clientModelVoBaseNameSpace}.res.${imp.name}ResVo;
</#list>
<#list baseNameSpace.clientServiceImport as imp>
import ${imp};
</#list>
/**
 * <p>
 * ${classComment!} 客户端
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Component
@FeignClient(name = "${r'${'}customClient.${moduleName}${r'}'}", configuration = BaseFeignMonitorLoggerConfig.class, contextId = "kk-core-feign-${entity}")
public interface ${entity}ClientProxy  {

<#list methods! as imp>
        /**
        * ${imp.comment!}
        * @param vo
        * @return
        */
        @PostMapping(value = BaseWebApiFinal.PathInternalApi + CustomClientFinal.CustomSystemPath + "/${entity}/${imp.name}")
        ${imp.name}ResVo ${imp.name?uncap_first}(@RequestBody ${imp.name}ReqVo vo);
</#list>

}

