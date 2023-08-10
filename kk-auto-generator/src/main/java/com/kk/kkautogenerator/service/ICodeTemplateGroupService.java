package com.kk.kkautogenerator.service;

import com.kk.kkautogenerator.dao.entity.CodeTemplateGroup;
import com.github.jeffreyning.mybatisplus.service.IMppService;

import java.util.List;
import com.kk.kkautogenerator.model.vo.CodeTemplateGroupListVo;
import com.kk.kkautogenerator.model.dto.CodeTemplateGroupListDto;
import com.kk.kkautogenerator.model.vo.CodeTemplateGroupAddVo;
import com.kk.kkautogenerator.model.vo.CodeTemplateGroupEditVo;
import com.kk.kkautogenerator.model.dto.CodeTemplateGroupDto;
import com.kk.kkautogenerator.model.vo.CodeTemplateGroupDetailsVo;
import com.kk.kkautogenerator.model.vo.CodeTemplateGroupDeleteVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 代码模板分组 服务类
 * </p>
 *
 * @author kk
 * @since 2023-08-02
 */
public interface ICodeTemplateGroupService extends IMppService<CodeTemplateGroup> {

    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    void insertIgnoreBatch(List<CodeTemplateGroup> list);
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<CodeTemplateGroupListDto>  selectPageList(CodeTemplateGroupListVo vo);
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    void insert(CodeTemplateGroupAddVo vo);
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    int update(CodeTemplateGroupEditVo vo);
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    CodeTemplateGroupDto selectById(CodeTemplateGroupDetailsVo vo);
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteById(CodeTemplateGroupDeleteVo vo);

}
