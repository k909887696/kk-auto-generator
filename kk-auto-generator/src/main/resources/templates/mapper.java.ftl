package ${package.Mapper};


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${package.Entity}.${entity};
<#list baseNameSpace.daoMapperImport as imp>
import ${imp};
</#list>
<#if mapperAnnotation>
import org.apache.ibatis.annotations.Mapper;
</#if>
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.modelVoBaseNameSpace}.res.${entity}ListResVo;
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.modelVoBaseNameSpace}.req.${entity}ListReqVo;
/**
 * <p>
 * ${table.comment!} Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if mapperAnnotation>
@Mapper
</#if>
public interface ${table.mapperName} extends ${baseNameSpace.mapperBaseClass}<${entity}> {
     /**
     * 查询${table.comment!}列表
     */
     Page<${entity}ListResVo> select${entity}PageList(Page page, ${entity}ListReqVo ${entity?uncap_first}ListReqVo);
}
