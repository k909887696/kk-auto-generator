package com.kk.kkautogenerator.model.vo;

import com.kk.kkautogenerator.dao.entity.CodeTemplate;
import com.kk.kkautogenerator.dao.entity.DbConnect;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class GenerateCodeVo {
    private String auth;
    private String packageName;
    private String moduleName;
    private Boolean lombok;
    private DbConnect dbConnect;

    private List<String> tables;
    private Map<String, Integer> codeTemplateMap;

    private Map<String, CodeTemplate> codeTemplateList;

    private Map<String, Object> customerParamMap;

    private String tablePrefix;

}

