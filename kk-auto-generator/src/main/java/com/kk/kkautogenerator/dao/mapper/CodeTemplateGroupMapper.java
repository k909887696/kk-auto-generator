package com.kk.kkautogenerator.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.kkautogenerator.dao.entity.CodeTemplateGroup;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.kkautogenerator.model.dto.CodeTemplateGroupListDto;
import com.kk.kkautogenerator.model.vo.CodeTemplateGroupListVo;
/**
 * <p>
 * 代码模板分组 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2023-08-02
 */
public interface CodeTemplateGroupMapper extends RootMapper<CodeTemplateGroup> {
     /**
     * 查询列表
     */
     Page selectPageList(IPage page, CodeTemplateGroupListVo codeTemplateGroupListVo);
}
