package ${parentApiPackageName}.serviceview;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
<#list methods! as imp>
import ${parentApiPackageName}.entityvo.vobase.req.${imp.name}ReqVo;
import ${parentApiPackageName}.entityvo.vobase.res.${imp.name}ResVo;
</#list>
import ${parentApiPackageName}.serviceapi.${serviceApiName};
/**
* <p>
    * ${classComment!} 视图类
    * </p>
*
* @author ${author}
* @since ${date}
*/
@Service
public class ${serviceViewName} {

    @Resource
    public ${serviceApiName} ${entity?uncap_first}ServiceApi;

<#list methods! as imp>
    /**
    * ${imp.comment!}
    * @param ${imp.name?uncap_first}ReqVo
    * @return
    */
    public ${imp.name}ResVo ${imp.name?uncap_first}(${imp.name}ReqVo ${imp.name?uncap_first}ReqVo)
    {
        return ${entity?uncap_first}ServiceApi.${imp.name?uncap_first}(${imp.name?uncap_first}ReqVo);
    }
</#list>


}

