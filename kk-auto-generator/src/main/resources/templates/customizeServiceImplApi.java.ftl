package ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.serviceApiNameSpace};

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
<#list baseNameSpace.serviceApiImport as imp>
import ${imp};
</#list>
<#list methods! as imp>
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.modelVoBaseNameSpace}.req.${imp.name}ReqVo;
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.modelVoBaseNameSpace}.res.${imp.name}ResVo;
</#list>
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.serviceNameSpace}.${serviceName};
/**
* <p>
    * ${classComment!} 服务实现类
    * </p>
*
* @author ${author}
* @since ${date}
*/
@Service
public class ${serviceApiName}  {

    @Resource
    public ${serviceName} ${entity?uncap_first}Service;

<#list methods! as imp>
    /**
    * ${imp.comment!}
    * @param ${imp.name?uncap_first}ReqVo
    * @return
    */
    @BaseTransactional
    public ${imp.name}ResVo ${imp.name?uncap_first}(${imp.name}ReqVo ${imp.name?uncap_first}ReqVo)
    {
        return ${entity?uncap_first}Service.${imp.name?uncap_first}(${imp.name?uncap_first}ReqVo);
    }
</#list>


}

