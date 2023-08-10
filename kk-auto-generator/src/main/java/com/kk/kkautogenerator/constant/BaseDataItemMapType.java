package com.kk.kkautogenerator.constant;

import java.util.HashMap;
import java.util.Map;


public class BaseDataItemMapType {
    public static final String DBTYPE = "dbtype";
    public static final String SYSCODETEMPLATETYPE = "SysCodeTemplateType";
    public static final String SYSCODETEMPLATELANGUAGE = "SysCodeTemplateLanguage";
    public static final String SYSCODETEMPLATECLASS = "SysCodeTemplateClass";
    public static final Map<String, String> CODE_VALUE_MAP = new HashMap<>();

    static {
        CODE_VALUE_MAP.put("dbtype", "数据库类型");


        CODE_VALUE_MAP.put("SysCodeTemplateType", "代码模板类型");


        CODE_VALUE_MAP.put("SysCodeTemplateLanguage", "代码模板语言");


        CODE_VALUE_MAP.put("SysCodeTemplateClass", "代码模板分类");
    }
}


