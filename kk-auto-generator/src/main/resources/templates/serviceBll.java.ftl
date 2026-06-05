package ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.bllNameSpace};


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
<#list baseNameSpace.bllImport as imp>
import ${imp};
</#list>
/**
 * <p>
 * ${table.comment!} Bll业务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
public class ${entity}Bll   {

    @Resource
    public ${table.serviceName} ${entity?uncap_first}Service;

    /**
    * 分批批量插入${table.comment!}
    * @param list 数据列表
    * @return
    */
    public void insert${entity}BatchSomeColumn(List<${entity}> list)
    {
        ${entity?uncap_first}Service.insert${entity}BatchSomeColumn(list);
    }
    /**
    * 单条插入${table.comment!}
    * @param vo 请求参数
    * @return 结果集
    */
    public void insert${entity}(${entity}AddReqVo vo)
    {
        ${entity?uncap_first}Service.insert${entity}(vo);
    }
    /**
    * 更新${table.comment!}
    * @param vo 请求参数
    * @return 结果集
    */
    public int update${entity}(${entity}EditReqVo vo)
    {
        return ${entity?uncap_first}Service.update${entity}(vo);
    }
    /**
    * 单条查询${table.comment!}
    * @param vo 请求参数
    * @return 结果集
    */
    public ${entity}ResVo select${entity}ById(${entity}DetailsReqVo vo)
    {
        return ${entity?uncap_first}Service.select${entity}ById(vo);
    }
    /**
    * 删除${table.comment!}
    * @param vo 请求参数
    * @return 结果集
    */
    public int delete${entity}ById(${entity}DeleteReqVo vo)
    {
        return ${entity?uncap_first}Service.delete${entity}ById(vo);
    }
    /**
    * 分页获取${table.comment!}结果集
    * @param vo 请求参数
    * @return 结果集
    */
    public ${baseNameSpace.basePageResult}<${entity}ListResVo>  select${entity}PageList(${entity}ListReqVo vo){
        return ${entity?uncap_first}Service.select${entity}PageList(vo);
    }



}

