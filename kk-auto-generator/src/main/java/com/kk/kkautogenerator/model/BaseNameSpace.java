package com.kk.kkautogenerator.model;

import lombok.Data;

import java.util.List;

/**
 * created with IntelliJ IDEA.
 * description:
 * auth: kk
 * date: 2026-05-30
 */
@Data
public class BaseNameSpace {


    /**
     * 顶层包名
     */
    private String topNameSpace;

    /**
     * 基础模块名称
     */
    private String moduleName;

    // ==================== 子模块名称 ====================

    /**
     * API子模块名称，格式: api.{moduleName}
     */
    private String apiModuleName;
    /**
     * Client子模块名称，格式: client.{moduleName}
     */
    private String clientModuleName;
    /**
     * Business子模块名称，格式: business.{moduleName}
     */
    private String businessModuleName;

    // ==================== 各层包命名空间 ====================

    /**
     * Model实体层命名空间
     */
    private String modelNameSpace;
    /**
     * Business模块请求/响应VO的基础命名空间
     */
    private String modelVoBaseNameSpace;
    /**
     * Client模块请求/响应VO的命名空间
     */
    private String clientModelVoBaseNameSpace;
    /**
     * Client模块Feign服务代理命名空间
     */
    private String clientServiceNameSpace;
    /**
     * DAO Mapper接口命名空间
     */
    private String daoMapperNameSpace;
    /**
     * DAO 实体命名空间
     */
    private String daoNameSpace;
    /**
     * Service接口命名空间
     */
    private String serviceNameSpace;
    /**
     * Service实现类命名空间
     */
    private String serviceImplNameSpace;
    /**
     * Controller V类型命名空间
     */
    private String controllerVNameSpace;
    /**
     * Controller Ext类型命名空间
     */
    private String controllerExtNameSpace;
    /**
     * Controller Nl类型命名空间
     */
    private String controllerNlNameSpace;
    /**
     * Controller 内部类命名空间
     */
    private String controllerInnNameSpace;
    /**
     * MyBatis XML Mapper文件命名空间
     */
    private String xmlMapperNameSpace;
    /**
     * BLL业务逻辑层命名空间
     */
    private String bllNameSpace;
    /**
     * ServiceApi服务接口层命名空间
     */
    private String serviceApiNameSpace;
    /**
     * ServiceView视图服务层命名空间
     */
    private String serviceViewNameSpace;

    // ==================== 各模块父级包名 ====================

    /**
     * API模块完整父包名，格式: {topNameSpace}.{apiModuleName}
     */
    private String apiParentNameSpace;
    /**
     * Business模块完整父包名，格式: {topNameSpace}.{businessModuleName}
     */
    private String businessParentNameSpace;
    /**
     * Client模块完整父包名，格式: {topNameSpace}.{clientModuleName}
     */
    private String clientParentNameSpace;

    // ===================== 各模块基础类=====================

    private String mapperBaseClass;

    private String basePage;

    private String basePageResult;

    // ===================== 各模块基础import =====================
    private List<String> daoMapperImport;
    private List<String> daoImport;
    private List<String> voImport;
    private List<String> serviceImport;
    private List<String> serviceImplImport;
    private List<String> controllerVImport;
    private List<String> controllerExtImport;
    private List<String> controllerNlImport;
    private List<String> xmlMapperImport;
    private List<String> bllImport;
    private List<String> serviceApiImport;
    private List<String> serviceViewImport;
    private List<String> modelImport;
    private List<String> baseImport;


}
