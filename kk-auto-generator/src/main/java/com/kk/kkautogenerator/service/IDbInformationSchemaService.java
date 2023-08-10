package com.kk.kkautogenerator.service;

import com.kk.kkautogenerator.model.dto.GenerateCodeDto;
import com.kk.kkautogenerator.model.vo.ColumnsInfoListVo;
import com.kk.kkautogenerator.model.vo.DbDataSource;
import com.kk.kkautogenerator.model.vo.GenerateCodeVo;
import com.kk.kkautogenerator.model.vo.TablesInfoListVo;
import java.util.List;
import java.util.Map;

public interface IDbInformationSchemaService {
  /**
   * 获取数据表信息
   *
   * @param paramTablesInfoListVo
   * @return
   * @throws Exception
   */
  List<Map<String, Object>> getTablesInfo(TablesInfoListVo paramTablesInfoListVo) throws Exception;

  /**
   * 获取数据表字段信息
   * @param paramColumnsInfoListVo
   * @return
   * @throws Exception
   */
  List<Map<String, Object>> getColumnsInfo(ColumnsInfoListVo paramColumnsInfoListVo) throws Exception;

  /**
   * 测试链接
   * @param paramDbDataSource
   * @throws Exception
   */
  void testConncet(DbDataSource paramDbDataSource) throws Exception;

  /**
   * 生成代码
   * @param paramGenerateCodeVo
   * @return
   */
  GenerateCodeDto generateCode(GenerateCodeVo paramGenerateCodeVo);
}

