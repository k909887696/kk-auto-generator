package com.kk.kkautogenerator.service;

import com.kk.kkautogenerator.dao.entity.CodeTemplateGroupSub;
import com.github.jeffreyning.mybatisplus.service.IMppService;

import java.util.List;
import com.kk.kkautogenerator.model.vo.CodeTemplateGroupSubListVo;
import com.kk.kkautogenerator.model.dto.CodeTemplateGroupSubListDto;
import com.kk.kkautogenerator.model.vo.CodeTemplateGroupSubAddVo;
import com.kk.kkautogenerator.model.vo.CodeTemplateGroupSubEditVo;
import com.kk.kkautogenerator.model.dto.CodeTemplateGroupSubDto;
import com.kk.kkautogenerator.model.vo.CodeTemplateGroupSubDetailsVo;
import com.kk.kkautogenerator.model.vo.CodeTemplateGroupSubDeleteVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 代码模板分组明细 服务类
 * </p>
 *
 * @author kk
 * @since 2023-08-02
 */
public interface ICodeTemplateGroupSubService extends IMppService<CodeTemplateGroupSub> {

    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    void insertIgnoreBatch(List<CodeTemplateGroupSub> list);
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<CodeTemplateGroupSubListDto>  selectPageList(CodeTemplateGroupSubListVo vo);
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    void insert(CodeTemplateGroupSubAddVo vo);
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    int update(CodeTemplateGroupSubEditVo vo);
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    CodeTemplateGroupSubDto selectById(CodeTemplateGroupSubDetailsVo vo);
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteById(CodeTemplateGroupSubDeleteVo vo);

    /**
     * 根据模板分组编号删除
     * @param groupId
     * @return
     */
    void deleteByCodeTemplateGroupId(int groupId);

}
