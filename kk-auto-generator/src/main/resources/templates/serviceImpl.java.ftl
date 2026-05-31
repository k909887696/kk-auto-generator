package ${package.ServiceImpl};


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.modelVoBaseNameSpace}.req.${entity}ListReqVo;
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.modelVoBaseNameSpace}.res.${entity}ListResVo;
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.modelVoBaseNameSpace}.req.${entity}AddReqVo;
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.modelVoBaseNameSpace}.req.${entity}EditReqVo;
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.modelVoBaseNameSpace}.res.${entity}ResVo;
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.modelVoBaseNameSpace}.req.${entity}DetailsReqVo;
import ${baseNameSpace.businessParentNameSpace}.${baseNameSpace.modelVoBaseNameSpace}.req.${entity}DeleteReqVo;
<#list baseNameSpace.serviceImplImport as imp>
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
open class ${table.serviceImplName} : ServiceImpl<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ServiceImpl<${table.mapperName}, ${entity}> implements ${table.serviceName} {


    /**
    * 分批批量插入${table.comment!}
    * @param list 数据列表
    * @return
    */
    @Override
    public void insert${entity}BatchSomeColumn(List<${entity}> list)
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
            this.baseMapper.insertBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入${table.comment!}
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public void insert${entity}(${entity}AddReqVo vo)
    {
        ${entity} model = new ${entity}();
        BeanUtil.copyProperties(vo,model);
        this.baseMapper.insert(model);
    }
    /**
    * 更新${table.comment!}
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int update${entity}(${entity}EditReqVo vo)
    {
        ${entity} model = new ${entity}();
        BeanUtil.copyProperties(vo,model);
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
    * 单条查询${table.comment!}
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public ${entity}ResVo select${entity}ById(${entity}DetailsReqVo vo)
    {
        ${entity} model = new ${entity}();
        BeanUtil.copyProperties(vo,model);
        <#if mppMultiIdCount <= 1>
        ${entity} res = this.baseMapper.selectById(model);
        <#else >
        ${entity} res = this.baseMapper.selectByMultiId(model);
        </#if>
        ${entity}ResVo resVo = new ${entity}ResVo();
        BeanUtil.copyProperties(res,resVo);
        return resVo;
    }
    /**
    * 删除${table.comment!}
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int delete${entity}ById(${entity}DeleteReqVo vo)
    {
        ${entity} model = new ${entity}();
        BeanUtil.copyProperties(vo,model);
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
    * 分页获取${table.comment!}结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public ${baseNameSpace.basePageResult}<${entity}ListResVo>  select${entity}PageList(${entity}ListReqVo vo){

        Page<${entity}ListResVo> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        Page<${entity}ListResVo> results = this.baseMapper.select${entity}PageList(page,vo);
        ${baseNameSpace.basePageResult}<${entity}ListResVo>  pageResult = new ${baseNameSpace.basePageResult}<>();

        return pageResult.convertPage(results);
    }



}
</#if>
