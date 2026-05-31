package ${parentApiPackageName}.service.bll;

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
    * ${classComment!} 业务处理类
    * </p>
*
* @author ${author}
* @since ${date}
*/
@Service
public class ${entity}Bll {

    @Resource
    public ${serviceName} ${entity?uncap_first}Service;

<#list methods! as imp>
    /**
    * ${imp.comment!}
    * @param ${imp.name?uncap_first}ReqVo
    * @return
    */
    public ${imp.name}ResVo ${imp.name?uncap_first}(${imp.name}ReqVo ${imp.name?uncap_first}ReqVo)
    {
        return ${entity?uncap_first}Service.${imp.name?uncap_first}(${imp.name?uncap_first}ReqVo);
    }
</#list>


}

