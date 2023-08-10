package ${package.ServiceImpl};


import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import ${package.Other}.vo.${entity}ListVo;
import ${package.Other}.dto.${entity}ListDto;
import ${package.Other}.vo.${entity}AddVo;
import ${package.Other}.vo.${entity}EditVo;
import ${package.Other}.dto.${entity}Dto;
import ${package.Other}.vo.${entity}DetailsVo;
import ${package.Other}.vo.${entity}DeleteVo;
<#list customImplNamespace as imp>
import ${imp};
</#list>
/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    @Resource
    public MapperUtils mapperUtils;
    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    public void insertIgnoreBatch(List<${entity}> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<${entity}> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertIgnoreBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    public void insert(${entity}AddVo vo)
    {
        ${entity} model = mapperUtils.map(vo,${entity}.class);
        this.baseMapper.insert(model);
    }
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    public int update(${entity}EditVo vo)
    {
        ${entity} model = mapperUtils.map(vo,${entity}.class);
        <#if mppMultiIdCount <= 1 >
        int r = this.baseMapper.updateById(model);
        <#else >
        int r = this.baseMapper.updateByMultiId(model);
        </#if>
        if(r != 1)
        {
            throw new BusinessException("${table.comment!}更新失败!");
        }
        return r;
    }
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    public ${entity}Dto selectById(${entity}DetailsVo vo)
    {
        ${entity} model = mapperUtils.map(vo,${entity}.class);
        <#if mppMultiIdCount <= 1>
        ${entity} res = this.baseMapper.selectById(model);
        <#else >
        ${entity} res = this.baseMapper.selectByMultiId(model);
        </#if>
        ${entity}Dto dto = mapperUtils.map(res,${entity}Dto.class);
        return dto;
    }
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    public int deleteById(${entity}DeleteVo vo)
    {
        ${entity} model = mapperUtils.map(vo,${entity}.class);
        <#if mppMultiIdCount <= 1>
        int r = this.baseMapper.deleteById(model);
        <#else >
        int r = this.baseMapper.deleteByMultiId(model);
        </#if>
        if(r != 1)
        {
            throw new BusinessException("${table.comment!}删除失败!");
        }
        return r;
    }
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    public PageResult<${entity}ListDto>  selectPageList(${entity}ListVo vo){

        IPage<${entity}ListDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        page = this.baseMapper.selectPageList(page,vo);
        PageResult<${entity}ListDto>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }



}
</#if>
