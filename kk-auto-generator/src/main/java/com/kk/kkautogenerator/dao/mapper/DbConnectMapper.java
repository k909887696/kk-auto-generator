package com.kk.kkautogenerator.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.kkautogenerator.dao.entity.DbConnect;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.kkautogenerator.model.dto.DbConnectListDto;
import com.kk.kkautogenerator.model.vo.DbConnectListVo;
/**
 * <p>
 * 数据库链接 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2023-08-02
 */
public interface DbConnectMapper extends RootMapper<DbConnect> {
     /**
     * 查询列表
     */
     Page selectPageList(IPage page, DbConnectListVo dbConnectListVo);
}
