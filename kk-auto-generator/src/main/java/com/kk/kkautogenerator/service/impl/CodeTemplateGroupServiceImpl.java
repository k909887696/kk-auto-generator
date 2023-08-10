package com.kk.kkautogenerator.service.impl;


import com.kk.kkautogenerator.constant.SysCodeTemplateType;
import com.kk.kkautogenerator.dao.entity.CodeTemplateGroupSub;
import com.kk.kkautogenerator.model.dto.CodeTemplateGroupSubListDto;
import com.kk.kkautogenerator.model.vo.*;
import com.kk.kkautogenerator.service.ICodeTemplateGroupSubService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.kkautogenerator.dao.entity.CodeTemplateGroup;
import com.kk.kkautogenerator.dao.mapper.CodeTemplateGroupMapper;
import com.kk.kkautogenerator.service.ICodeTemplateGroupService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.kkautogenerator.model.dto.CodeTemplateGroupListDto;
import com.kk.kkautogenerator.model.dto.CodeTemplateGroupDto;
import com.kk.common.utils.MapperUtils;
import com.kk.common.base.model.PageResult;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 代码模板分组 服务实现类
 * </p>
 *
 * @author kk
 * @since 2023-08-02
 */
@Service
public class CodeTemplateGroupServiceImpl extends MppServiceImpl<CodeTemplateGroupMapper, CodeTemplateGroup> implements ICodeTemplateGroupService {

    @Resource
    public MapperUtils mapperUtils;
    @Resource
    public ICodeTemplateGroupSubService codeTemplateGroupSubService;
    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    public void insertIgnoreBatch(List<CodeTemplateGroup> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<CodeTemplateGroup> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertIgnoreBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    public void insert(CodeTemplateGroupAddVo vo)
    {
        CodeTemplateGroup model = mapperUtils.map(vo,CodeTemplateGroup.class);
        model.setCreateId("sys");
        model.setCreateTime(new Date());
        this.baseMapper.insert(model);
        if( vo.getCodeTemplateGroupSubList() == null)
        {
            vo.setCodeTemplateGroupSubList(new ArrayList<>());
        }
        for(int i=0;i<vo.getCodeTemplateGroupSubList().size();i++)
        {
            vo.getCodeTemplateGroupSubList().get(i).setTemplateGroupId(model.getTemplateGroupId());
        }
        SysCodeTemplateType.CODE_VALUE_MAP.forEach( (k, v) ->{
            List<CodeTemplateGroupSub> list = vo.getCodeTemplateGroupSubList().stream().filter(t->t.getTemplateType().equals(k)).collect(Collectors.toList());
            if(list ==null || list.size() <=0)
            {
                CodeTemplateGroupSub tem = new CodeTemplateGroupSub();
                tem.setTemplateId(-1);
                tem.setTemplateType(k);
                tem.setTemplateGroupId(model.getTemplateGroupId());
                vo.getCodeTemplateGroupSubList().add(tem);
            }
        });
        codeTemplateGroupSubService.insertIgnoreBatch(vo.getCodeTemplateGroupSubList());
    }
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    public int update(CodeTemplateGroupEditVo vo)
    {
        if(vo.getTemplateGroupId() == null)
        {
            throw new BusinessException("模板分组编号不能为空！");
        }
        CodeTemplateGroup model = mapperUtils.map(vo,CodeTemplateGroup.class);
        model.setCreateTime(null);
        model.setCreateId(null);
        model.setTemplateLanguage(null);
        int r = this.baseMapper.updateById(model);
        if(r != 1)
        {
            throw new BusinessException("代码模板分组更新失败!");
        }
        if(vo.getCodeTemplateGroupSubList()==null)
        {
            vo.setCodeTemplateGroupSubList(new ArrayList<>());
        }
        for(int i=0;i<vo.getCodeTemplateGroupSubList().size();i++)
        {
            vo.getCodeTemplateGroupSubList().get(i).setTemplateGroupId(model.getTemplateGroupId());
        }
        SysCodeTemplateType.CODE_VALUE_MAP.forEach( (k, v) ->{
            List<CodeTemplateGroupSub> list = vo.getCodeTemplateGroupSubList().stream().filter(t->t.getTemplateType().equals(k)).collect(Collectors.toList());
            if(list ==null || list.size() <= 0)
            {
                CodeTemplateGroupSub tem = new CodeTemplateGroupSub();
                tem.setTemplateId(SysCodeTemplateType.CODE_VALUE_MAP.get(k));
                tem.setTemplateType(k);
                tem.setTemplateGroupId(model.getTemplateGroupId());
                vo.getCodeTemplateGroupSubList().add(tem);
            }
        });
        codeTemplateGroupSubService.deleteByCodeTemplateGroupId(vo.getTemplateGroupId());
        codeTemplateGroupSubService.insertIgnoreBatch(vo.getCodeTemplateGroupSubList());
        return r;
    }
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    public CodeTemplateGroupDto selectById(CodeTemplateGroupDetailsVo vo)
    {
        CodeTemplateGroup model = mapperUtils.map(vo,CodeTemplateGroup.class);
        CodeTemplateGroup res = this.baseMapper.selectById(model);
        CodeTemplateGroupDto dto = mapperUtils.map(res,CodeTemplateGroupDto.class);
        CodeTemplateGroupSubListVo subVo = new CodeTemplateGroupSubListVo();
        subVo.setPageIndex(1);
        subVo.setPageSize(100);
        subVo.setTemplateGroupId(vo.getTemplateGroupId());
        PageResult<CodeTemplateGroupSubListDto> subList = codeTemplateGroupSubService.selectPageList(subVo);
        dto.setCodeTemplateGroupSubList(subList.getResult());
        return dto;
    }
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    public int deleteById(CodeTemplateGroupDeleteVo vo)
    {
        CodeTemplateGroup model = mapperUtils.map(vo,CodeTemplateGroup.class);
        codeTemplateGroupSubService.deleteByCodeTemplateGroupId(vo.getTemplateGroupId());
        int r = this.baseMapper.deleteById(model);
        if(r != 1)
        {
            throw new BusinessException("代码模板分组删除失败!");
        }
        return r;
    }
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    public PageResult<CodeTemplateGroupListDto>  selectPageList(CodeTemplateGroupListVo vo){

        IPage<CodeTemplateGroupListDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        page = this.baseMapper.selectPageList(page,vo);
        PageResult<CodeTemplateGroupListDto>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }



}
