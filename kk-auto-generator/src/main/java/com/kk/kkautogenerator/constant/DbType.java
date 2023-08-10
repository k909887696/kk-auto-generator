package com.kk.kkautogenerator.constant;

import java.util.HashMap;
import java.util.Map;


public class DbType {
    public static final String MYSQL = "mysql";
    public static final String ORACLE = "oracle";
    public static final String SQLSERVER2000 = "sqlserver2000";
    public static final String SQLSERVER = "sqlserver";
    public static final Map<String, String> CODE_VALUE_MAP = new HashMap<>();

    static {
        CODE_VALUE_MAP.put("mysql", "mysql");

        CODE_VALUE_MAP.put("oracle", "oracle");


        CODE_VALUE_MAP.put("sqlserver2000", "sqlserver2000");

        CODE_VALUE_MAP.put("sqlserver", "sqlserver");
    }
}


