package com.kk.kkautogenerator.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.kkautogenerator.dao.entity.CodeTemplate;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.kkautogenerator.model.dto.CodeTemplateListDto;
import com.kk.kkautogenerator.model.vo.CodeTemplateListVo;
/**
 * <p>
 * 代码模板 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2023-08-02
 */
public interface CodeTemplateMapper extends RootMapper<CodeTemplate> {
     /**
     * 查询列表
     */
     Page selectPageList(IPage page, CodeTemplateListVo codeTemplateListVo);
}
