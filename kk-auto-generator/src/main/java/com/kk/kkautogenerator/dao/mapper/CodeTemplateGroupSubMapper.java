package com.kk.kkautogenerator.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.kkautogenerator.dao.entity.CodeTemplateGroupSub;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.kkautogenerator.model.dto.CodeTemplateGroupSubListDto;
import com.kk.kkautogenerator.model.vo.CodeTemplateGroupSubListVo;
/**
 * <p>
 * 代码模板分组明细 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2023-08-02
 */
public interface CodeTemplateGroupSubMapper extends RootMapper<CodeTemplateGroupSub> {
     /**
     * 查询列表
     */
     Page selectPageList(IPage page, CodeTemplateGroupSubListVo codeTemplateGroupSubListVo);
}
