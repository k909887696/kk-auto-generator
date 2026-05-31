package ${package.Service};

import ${package.Entity}.${entity};
import java.util.List;
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.modelVoBaseNameSpace}.req.${entity}ListReqVo;
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.modelVoBaseNameSpace}.res.${entity}ListResVo;
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.modelVoBaseNameSpace}.req.${entity}AddReqVo;
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.modelVoBaseNameSpace}.req.${entity}EditReqVo;
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.modelVoBaseNameSpace}.res.${entity}ResVo;
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.modelVoBaseNameSpace}.req.${entity}DetailsReqVo;
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.modelVoBaseNameSpace}.req.${entity}DeleteReqVo;
<#list baseNameSpace.serviceImport as imp>
import ${imp};
</#list>
/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${table.serviceName}  {

    /**
    * 分批批量插入${table.comment!}
    * @param list 数据列表
    * @return
    */
    void insert${entity}BatchSomeColumn(List<${entity}> list);
    /**
    * 单条插入${table.comment!}
    * @param vo 请求参数
    * @return 结果集
    */
    void insert${entity}(${entity}AddReqVo vo);
    /**
    * 更新${table.comment!}
    * @param vo 请求参数
    * @return 结果集
    */
    int update${entity}(${entity}EditReqVo vo);
    /**
    * 单条查询${table.comment!}
    * @param vo 请求参数
    * @return 结果集
    */
    ${entity}ResVo select${entity}ById(${entity}DetailsReqVo vo);
    /**
    * 删除${table.comment!}
    * @param vo 请求参数
    * @return 结果集
    */
    int delete${entity}ById(${entity}DeleteReqVo vo);
    /**
    * 分页获取${table.comment!}结果集
    * @param vo 请求参数
    * @return 结果集
    */
    ${baseNameSpace.basePageResult}<${entity}ListResVo>  select${entity}PageList(${entity}ListReqVo vo);
}

