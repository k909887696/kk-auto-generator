package ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.serviceViewNameSpace};


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.modelVoBaseNameSpace}.req.${entity}ListReqVo;
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.modelVoBaseNameSpace}.res.${entity}ListResVo;
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.modelVoBaseNameSpace}.req.${entity}AddReqVo;
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.modelVoBaseNameSpace}.req.${entity}EditReqVo;
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.modelVoBaseNameSpace}.res.${entity}ResVo;
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.modelVoBaseNameSpace}.req.${entity}DetailsReqVo;
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.modelVoBaseNameSpace}.req.${entity}DeleteReqVo;
<#list baseNameSpace.serviceViewImport as imp>
import ${imp};
</#list>
/**
 * <p>
 * ${table.comment!} View服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${entity}ServiceView   {

    @Resource
    public ${table.serviceName} ${entity?uncap_first}ServiceApi;



}
</#if>
