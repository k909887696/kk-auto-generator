package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};

import java.util.List;
import ${package.Other}.vo.${entity}ListVo;
import ${package.Other}.dto.${entity}ListDto;
import ${package.Other}.vo.${entity}AddVo;
import ${package.Other}.vo.${entity}EditVo;
import ${package.Other}.dto.${entity}Dto;
import ${package.Other}.vo.${entity}DetailsVo;
import ${package.Other}.vo.${entity}DeleteVo;
<#list customServiceNamespace as imp>
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
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    void insertIgnoreBatch(List<${entity}> list);
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<${entity}ListDto>  selectPageList(${entity}ListVo vo);
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    void insert(${entity}AddVo vo);
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    int update(${entity}EditVo vo);
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    ${entity}Dto selectById(${entity}DetailsVo vo);
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteById(${entity}DeleteVo vo);

}
</#if>
