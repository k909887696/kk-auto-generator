package ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.serviceImplNameSpace};

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
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
public class ${serviceImplName} implements ${serviceName} {

<#list methods! as imp>
    /**
    * ${imp.comment!}
    * @param ${imp.name?uncap_first}ReqVo
    * @return
    */
    @Override
    public ${imp.name}ResVo ${imp.name?uncap_first}(${imp.name}ReqVo ${imp.name?uncap_first}ReqVo)
    {
        ${imp.name}ResVo response = new ${imp.name}ResVo();
        return response;
    }
</#list>


}

