package com.kk.kkautogenerator.constant;

import java.util.HashMap;
import java.util.Map;


public class SysCodeTemplateLanguage {
    public static final String FTL = "ftl";
    public static final String BTL = "btl";
    public static final String VM = "vm";
    public static final Map<String, String> CODE_VALUE_MAP = new HashMap<>();

    static {
        CODE_VALUE_MAP.put("ftl", "ftl");

        CODE_VALUE_MAP.put("btl", "btl");


        CODE_VALUE_MAP.put("vm", "vm");
    }
}


