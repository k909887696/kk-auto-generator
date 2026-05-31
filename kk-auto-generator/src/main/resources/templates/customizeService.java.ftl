package ${parentApiPackageName}.service.intf;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
<#list methods! as imp>
import ${parentApiPackageName}.entityvo.vobase.req.${imp.name}ReqVo;
import ${parentApiPackageName}.entityvo.vobase.res.${imp.name}ResVo;
</#list>

/**
 * <p>
 * ${classComment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${serviceName}  {


<#list methods! as imp>
     /**
     * ${imp.comment!}
     * @param ${imp.name?uncap_first}ReqVo
     * @return
     */
    ${imp.name}ResVo ${imp.name?uncap_first}(${imp.name}ReqVo ${imp.name?uncap_first}ReqVo);
</#list>

}

