package com.kk.kkautogenerator.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kk.common.utils.FileUtil;
import com.kk.kkautogenerator.config.SystemSettingConfig;
import com.kk.kkautogenerator.constant.SysCodeTemplateType;
import com.kk.kkautogenerator.constant.SysCodeTemplateType4FileUrl;
import com.kk.kkautogenerator.constant.SysCodeTemplateType4Suffix;
import com.kk.kkautogenerator.model.vo.*;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.kkautogenerator.dao.entity.CodeTemplate;
import com.kk.kkautogenerator.dao.mapper.CodeTemplateMapper;
import com.kk.kkautogenerator.service.ICodeTemplateService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.kkautogenerator.model.dto.CodeTemplateListDto;
import com.kk.kkautogenerator.model.dto.CodeTemplateDto;
import com.kk.common.utils.MapperUtils;
import com.kk.common.base.model.PageResult;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 代码模板 服务实现类
 * </p>
 *
 * @author kk
 * @since 2023-08-02
 */
@Service
public class CodeTemplateServiceImpl extends MppServiceImpl<CodeTemplateMapper, CodeTemplate> implements ICodeTemplateService {

    @Resource
    public MapperUtils mapperUtils;
    @Resource
    public SystemSettingConfig systemSettingConfig;
    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    public void insertIgnoreBatch(List<CodeTemplate> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<CodeTemplate> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertIgnoreBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    public void insert(CodeTemplateAddVo vo)
    {
        CodeTemplate model = mapperUtils.map(vo,CodeTemplate.class);
        model.setCreateId("sys");
        model.setCreateTime(new Date());

        ((CodeTemplateMapper)this.baseMapper).insert(model);
        String sourceFilePath = vo.getTemplateLanguage()
                + "/" + model.getTemplateId().toString()
                + "_" + model.getTemplateType()
                + "." + (String) SysCodeTemplateType4Suffix.CODE_VALUE_MAP.get(model.getTemplateType())
                ;
        FileUtil.createAndWriteTxtFile(systemSettingConfig.getCustomTemplateRootPath() + sourceFilePath, model.getTemplateContent());
        model.setTemplateFileUrl(sourceFilePath);
        this.baseMapper.insert(model);
    }
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    public int update(CodeTemplateEditVo vo)
    {
        CodeTemplate model = mapperUtils.map(vo,CodeTemplate.class);
        String sourceFilePath = vo.getTemplateLanguage()
                + "/" + model.getTemplateId().toString()
                + "_" + model.getTemplateType()
                + "." + (String)SysCodeTemplateType4Suffix.CODE_VALUE_MAP.get(model.getTemplateType())
                ;
        FileUtil.createAndWriteTxtFile(systemSettingConfig.getCustomTemplateRootPath() + sourceFilePath, model.getTemplateContent());
        model.setTemplateFileUrl(sourceFilePath);
        model.setCreateTime(null);
        model.setCreateId(null);
        model.setTemplateLanguage(null);
        model.setTemplateClass(null);
        int r = this.baseMapper.updateById(model);
        if(r != 1)
        {
            throw new BusinessException("代码模板更新失败!");
        }
        return r;
    }
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    public CodeTemplateDto selectById(CodeTemplateDetailsVo vo)
    {
        CodeTemplate model = mapperUtils.map(vo,CodeTemplate.class);
        CodeTemplate res = this.baseMapper.selectById(model);
        CodeTemplateDto dto = mapperUtils.map(res,CodeTemplateDto.class);

        return dto;
    }
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    public int deleteById(CodeTemplateDeleteVo vo)
    {
        CodeTemplate model = mapperUtils.map(vo,CodeTemplate.class);
        int r = this.baseMapper.deleteById(model);
        if(r != 1)
        {
            throw new BusinessException("代码模板删除失败!");
        }
        return r;
    }
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    public PageResult<CodeTemplateListDto>  selectPageList(CodeTemplateListVo vo){

        IPage<CodeTemplateListDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        page = this.baseMapper.selectPageList(page,vo);
        PageResult<CodeTemplateListDto>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }


    public Map<String, CodeTemplate> initGenerateCodeTemplate(Map<String, Integer> vo) {
        Page page = new Page(1L, 100L);
        LambdaQueryWrapper<CodeTemplate> query = new LambdaQueryWrapper();
        List<String> ids = (List<String>)vo.values().stream().map(t -> t.toString()).collect(Collectors.toList());
        query.in(CodeTemplate::getTemplateId, ids);
        IPage<CodeTemplate> iPage = this.baseMapper.selectPage(page, query);
        Map<String, CodeTemplate> res = new HashMap<>();
        for (String key : SysCodeTemplateType.CODE_VALUE_MAP.keySet()) {
            CodeTemplate tempDto = new CodeTemplate();
            tempDto.setTemplateId(SysCodeTemplateType.CODE_VALUE_MAP.get(key));
            tempDto.setTemplateType(key);
            tempDto.setTemplateClass("sys".toLowerCase());
            tempDto.setTemplateLanguage("ftl".toLowerCase());
            tempDto.setTemplateFileUrl((String) SysCodeTemplateType4FileUrl.CODE_VALUE_MAP.get(key));
            tempDto.setTemplateName("系统");
            List<CodeTemplate> cts = (List<CodeTemplate>)iPage.getRecords().stream().filter(t -> key.equals(t.getTemplateType())).collect(Collectors.toList());
            if (cts != null && cts.size() > 0) {
                res.put(key, cts.get(0));
                continue;
            }
            res.put(key, tempDto);
        }
        return res;
    }

    public Map<String, List<CodeTemplateListDto>> selectPageList4GroupByType(CodeTemplateListVo vo) {
        IPage<CodeTemplateListDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        page = this.baseMapper.selectPageList(page, vo);
        PageResult<CodeTemplateListDto> pageResult = new PageResult();
        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());
        Map<String, List<CodeTemplateListDto>> res = new HashMap<>();
        if (page.getRecords() != null && page.getRecords().size() > 0)
            res = page.getRecords().stream().collect(Collectors.groupingBy(t -> t.getTemplateType()));
        int i=-1;
        for (String key : SysCodeTemplateType.CODE_VALUE_MAP.keySet()) {
            List<CodeTemplateListDto> tempList = new ArrayList<>();
            CodeTemplateListDto tempDto = new CodeTemplateListDto();
            tempDto.setTemplateId(SysCodeTemplateType.CODE_VALUE_MAP.get(key));
            tempDto.setTemplateType(key);
            tempDto.setTemplateClass("sys".toLowerCase());
            tempDto.setTemplateLanguage("ftl".toLowerCase());
            tempDto.setTemplateFileUrl((String)SysCodeTemplateType4FileUrl.CODE_VALUE_MAP.get(key));
            tempDto.setTemplateName("系统");
                    tempList.add(tempDto);
            if (!res.containsKey(key)) {
                res.put(key, tempList);
                continue;
            }
            ((List<CodeTemplateListDto>)res.get(key)).addAll(tempList);
        }
        return res;
    }
}
