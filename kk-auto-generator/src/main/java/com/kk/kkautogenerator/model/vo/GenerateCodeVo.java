package com.kk.kkautogenerator.model.vo;

import com.kk.kkautogenerator.dao.entity.CodeTemplate;
import com.kk.kkautogenerator.dao.entity.DbConnect;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class GenerateCodeVo {
    /**
     * 作者
     */
    private String auth;
    /**
     * 包名
     */
    private String packageName;
    /**
     * 模块名
     */
    private String moduleName;
    /**
     * 是否使用lombok
     */
    private Boolean lombok;
    /**
     * 是否使用swagger
     */
    /**
     * 是否使用vController
     */
    private Boolean vController;
    /**
     * 是否使用extController
     */
    private Boolean extController;
    /**
     * 是否使用innController
     */
    private Boolean innController;


    private DbConnect dbConnect;
    /**
     * 表列表
     */
    private List<String> tables;
    /**
     * 代码模板列表
     */
    private Map<String, Integer> codeTemplateMap;
    private Map<String, CodeTemplate> codeTemplateList;
    /**
     * 自定义参数
     */
    private Map<String, Object> customerParamMap;
    /**
     * 表前缀
     */

    private String tablePrefix;

    /**
     * 是否使用Dao
     */
    private Boolean useDao;
    /**
     * 是否使用Vo
     */
    private Boolean useVo;

    /**
     * 是否使用Service
     */
    private Boolean useService;
    /**
     * 是否使用ServiceApi
     */
    private Boolean useServiceApi;
    /**
     * 是否使用ServiceView
     */
    private Boolean useServiceView;

    /**
     * 是否使用Bll
     */
    private Boolean useServiceBll;

    /**
     * 自定义类
     */
    private String customizeClass;
    /**
     * 生成类型（1：数据源，2：自定义）
     */
    private String generateType;
}

