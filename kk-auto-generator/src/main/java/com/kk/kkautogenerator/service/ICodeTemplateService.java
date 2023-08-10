package com.kk.kkautogenerator.service;

import com.kk.kkautogenerator.dao.entity.CodeTemplate;
import com.github.jeffreyning.mybatisplus.service.IMppService;

import java.util.List;
import java.util.Map;

import com.kk.kkautogenerator.model.vo.CodeTemplateListVo;
import com.kk.kkautogenerator.model.dto.CodeTemplateListDto;
import com.kk.kkautogenerator.model.vo.CodeTemplateAddVo;
import com.kk.kkautogenerator.model.vo.CodeTemplateEditVo;
import com.kk.kkautogenerator.model.dto.CodeTemplateDto;
import com.kk.kkautogenerator.model.vo.CodeTemplateDetailsVo;
import com.kk.kkautogenerator.model.vo.CodeTemplateDeleteVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 代码模板 服务类
 * </p>
 *
 * @author kk
 * @since 2023-08-02
 */
public interface ICodeTemplateService extends IMppService<CodeTemplate> {

    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    void insertIgnoreBatch(List<CodeTemplate> list);
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<CodeTemplateListDto>  selectPageList(CodeTemplateListVo vo);
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    void insert(CodeTemplateAddVo vo);
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    int update(CodeTemplateEditVo vo);
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    CodeTemplateDto selectById(CodeTemplateDetailsVo vo);
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteById(CodeTemplateDeleteVo vo);

    /**
     * 初始化代码生成器模板列表
     * @param vo
     * @return
     */
    Map<String, CodeTemplate> initGenerateCodeTemplate(Map<String, Integer> vo);

    /**
     * 根据模板类型分组返回
     * @param vo
     * @return
     */
    Map<String, List<CodeTemplateListDto>> selectPageList4GroupByType(CodeTemplateListVo vo);

}
