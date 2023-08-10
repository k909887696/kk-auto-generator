package com.kk.kkautogenerator.constant;

import java.util.HashMap;
import java.util.Map;


public class SysCodeTemplateClass {
    public static final String SYS = "sys";
    public static final String PER = "per";
    public static final Map<String, String> CODE_VALUE_MAP = new HashMap<>();

    static {
        CODE_VALUE_MAP.put("sys", "系统");

        CODE_VALUE_MAP.put("per", "个人");
    }
}


