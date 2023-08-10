package com.kk.kkautogenerator.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.kkautogenerator.dao.entity.CodeTemplateGroupSub;
import com.kk.kkautogenerator.dao.mapper.CodeTemplateGroupSubMapper;
import com.kk.kkautogenerator.service.ICodeTemplateGroupSubService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.kkautogenerator.model.vo.CodeTemplateGroupSubListVo;
import com.kk.kkautogenerator.model.dto.CodeTemplateGroupSubListDto;
import com.kk.kkautogenerator.model.vo.CodeTemplateGroupSubAddVo;
import com.kk.kkautogenerator.model.vo.CodeTemplateGroupSubEditVo;
import com.kk.kkautogenerator.model.dto.CodeTemplateGroupSubDto;
import com.kk.kkautogenerator.model.vo.CodeTemplateGroupSubDetailsVo;
import com.kk.kkautogenerator.model.vo.CodeTemplateGroupSubDeleteVo;
import com.kk.common.utils.MapperUtils;
import com.kk.common.base.model.PageResult;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 代码模板分组明细 服务实现类
 * </p>
 *
 * @author kk
 * @since 2023-08-02
 */
@Service
public class CodeTemplateGroupSubServiceImpl extends MppServiceImpl<CodeTemplateGroupSubMapper, CodeTemplateGroupSub> implements ICodeTemplateGroupSubService {

    @Resource
    public MapperUtils mapperUtils;
    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    public void insertIgnoreBatch(List<CodeTemplateGroupSub> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<CodeTemplateGroupSub> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertIgnoreBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    public void insert(CodeTemplateGroupSubAddVo vo)
    {
        CodeTemplateGroupSub model = mapperUtils.map(vo,CodeTemplateGroupSub.class);
        this.baseMapper.insert(model);
    }
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    public int update(CodeTemplateGroupSubEditVo vo)
    {
        CodeTemplateGroupSub model = mapperUtils.map(vo,CodeTemplateGroupSub.class);
        int r = this.baseMapper.updateByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("代码模板分组明细更新失败!");
        }
        return r;
    }
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    public CodeTemplateGroupSubDto selectById(CodeTemplateGroupSubDetailsVo vo)
    {
        CodeTemplateGroupSub model = mapperUtils.map(vo,CodeTemplateGroupSub.class);
        CodeTemplateGroupSub res = this.baseMapper.selectByMultiId(model);
        CodeTemplateGroupSubDto dto = mapperUtils.map(res,CodeTemplateGroupSubDto.class);
        return dto;
    }
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    public int deleteById(CodeTemplateGroupSubDeleteVo vo)
    {
        CodeTemplateGroupSub model = mapperUtils.map(vo,CodeTemplateGroupSub.class);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("代码模板分组明细删除失败!");
        }
        return r;
    }
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    public PageResult<CodeTemplateGroupSubListDto>  selectPageList(CodeTemplateGroupSubListVo vo){

        IPage<CodeTemplateGroupSubListDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        page = this.baseMapper.selectPageList(page,vo);
        PageResult<CodeTemplateGroupSubListDto>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }

    /**
     * 根据模板分组编号删除
     * @param groupId
     * @return
     */
    public void deleteByCodeTemplateGroupId(int groupId)
    {
        LambdaQueryWrapper<CodeTemplateGroupSub> query = new LambdaQueryWrapper<CodeTemplateGroupSub>();
        query.eq(CodeTemplateGroupSub::getTemplateGroupId,groupId);
        int r = this.baseMapper.delete(query);

    }


}
