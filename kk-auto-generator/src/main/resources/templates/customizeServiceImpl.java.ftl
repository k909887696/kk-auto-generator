package ${parentApiPackageName}.service.impl;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
<#list methods! as imp>
import ${parentApiPackageName}.entityvo.vobase.req.${imp.name}ReqVo;
import ${parentApiPackageName}.entityvo.vobase.res.${imp.name}ResVo;
</#list>
import ${parentApiPackageName}.service.intf.${serviceName};
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

