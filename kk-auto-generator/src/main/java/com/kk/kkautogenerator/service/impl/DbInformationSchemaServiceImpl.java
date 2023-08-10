package com.kk.kkautogenerator.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.kk.common.exception.BusinessException;
import com.kk.common.utils.FileUtil;
import com.kk.common.utils.MapperUtils;
import com.kk.kkautogenerator.config.SystemSettingConfig;
import com.kk.kkautogenerator.constant.DbSystemInfoSql;
import com.kk.kkautogenerator.dao.entity.DbConnect;
import com.kk.kkautogenerator.model.dto.DbConnectDto;
import com.kk.kkautogenerator.model.dto.GenerateCodeDto;
import com.kk.kkautogenerator.model.vo.ColumnsInfoListVo;
import com.kk.kkautogenerator.model.vo.DbConnectDetailsVo;
import com.kk.kkautogenerator.model.vo.DbDataSource;
import com.kk.kkautogenerator.model.vo.GenerateCodeVo;
import com.kk.kkautogenerator.model.vo.TablesInfoListVo;
import com.kk.kkautogenerator.service.ICodeTemplateService;
import com.kk.kkautogenerator.service.IDbConnectService;
import com.kk.kkautogenerator.service.IDbInformationSchemaService;
import com.kk.kkautogenerator.service.SqlRepository;
import com.kk.kkautogenerator.utils.Generator;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;


@Service
public class DbInformationSchemaServiceImpl
        implements IDbInformationSchemaService {
    @Resource
    public SqlRepository sqlRepository;
    @Resource
    public IDbConnectService dbConnectService;
    @Resource
    public MapperUtils mapperUtils;
    @Resource
    public ICodeTemplateService codeTemplateService;

    @Resource
    public SystemSettingConfig systemSettingConfig;
    @Resource
    public Generator generator;
    /**
     * 获取数据表信息
     *
     * @param vo
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getTablesInfo(TablesInfoListVo vo) throws Exception {
        DbConnectDetailsVo dbConnectDetailsVo = new DbConnectDetailsVo();
        dbConnectDetailsVo.setDbConnectCode(vo.getDbConnectCode());
        DbConnect connect = this.dbConnectService.selectByIdWithDecrypt(dbConnectDetailsVo);
        if (connect == null) {
            throw new BusinessException("数据库链接不存在，请前往数据库链接界面维护数据！");
        }
        DbDataSource dataSource = (DbDataSource) this.mapperUtils.map(connect, DbDataSource.class);
        String tableName = "";
        if (!StringUtils.isEmpty(vo.getTableName())) {
            tableName = vo.getTableName();
        }

        List<Map<String, Object>> res = this.sqlRepository.doSelect(dataSource, String.format(DbSystemInfoSql.SELECT_TABLES_INFO_SQL, new Object[]{dataSource.getDbConnectDatabase(), "%" + tableName + "%", "%" + tableName + "%"}), null);
        return res;
    }


    /**
     * 获取数据表字段信息
     *
     * @param vo
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getColumnsInfo(ColumnsInfoListVo vo) throws Exception {
        if (StringUtils.isEmpty(vo.getTableName())) {
            throw new BusinessException("表名不能为空！");
        }
        DbConnectDetailsVo dbConnectDetailsVo = new DbConnectDetailsVo();
        dbConnectDetailsVo.setDbConnectCode(vo.getDbConnectCode());
        DbConnect connect = this.dbConnectService.selectByIdWithDecrypt(dbConnectDetailsVo);
        if (connect == null) {
            throw new BusinessException("数据库链接不存在，请前往数据库链接界面维护数据！");
        }
        DbDataSource dataSource = (DbDataSource) this.mapperUtils.map(connect, DbDataSource.class);

        List<Map<String, Object>> res = this.sqlRepository.doSelect(dataSource, String.format(DbSystemInfoSql.SELECT_COLUMNS_INFO_SQL, new Object[]{dataSource.getDbConnectDatabase(), vo.getTableName()}), null);
        return res;
    }


    /**
     * 测试数据库链接
     *
     * @param dataSource
     * @throws Exception
     */
    public void testConncet(DbDataSource dataSource) throws Exception {
        if(dataSource.getDbConnectCode() != null)
        {
            DbConnectDetailsVo dbConnectDetailsVo = new DbConnectDetailsVo();
            dbConnectDetailsVo.setDbConnectCode(dataSource.getDbConnectCode());
            DbConnect connect = this.dbConnectService.selectByIdWithDecrypt(dbConnectDetailsVo);
            if (connect == null) {
                throw new BusinessException("数据库链接不存在，请前往数据库链接界面维护数据！");
            }
            dataSource = (DbDataSource) this.mapperUtils.map(connect, DbDataSource.class);
        }
        this.sqlRepository.testConnect(dataSource);
    }

    /**
     * 生成代码
     *
     * @param vo
     * @return
     */
    public GenerateCodeDto generateCode(GenerateCodeVo vo) {
        if(vo.getDbConnect() == null )
        {
            throw new BusinessException("请选择数据源!");
        }
        DbConnectDetailsVo dbvo = new DbConnectDetailsVo();
        dbvo.setDbConnectCode(vo.getDbConnect().getDbConnectCode());
        vo.setDbConnect(dbConnectService.selectByIdWithDecrypt(dbvo));
        GenerateCodeDto res = new GenerateCodeDto();
        vo.setCodeTemplateList(this.codeTemplateService.initGenerateCodeTemplate(vo.getCodeTemplateMap()));

        String rootPath = generator.generateCode(vo);
        String zipFilePath = "";
        try {
            zipFilePath = FileUtil.fileToZip(rootPath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        res.setAbsolutePath(zipFilePath);
        res.setRelativePath(URLEncoder.encode(zipFilePath));

        res.setFileName(vo.getModuleName()+".zip");
        return res;
    }
}


