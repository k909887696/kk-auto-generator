package com.kk.kkautogenerator.constant;

import java.util.HashMap;
import java.util.Map;


public class DbSystemInfoSql {

    public static final String SELECT_TABLES_INFO_SQL_MSSQL = "SELECT *  FROM information_schema.tables WHERE table_type = 'BASE TABLE' and table_name like '%s'";
    public static final String SELECT_TABLES_INFO_SQL = "SELECT  * FROM    information_schema.`TABLES` WHERE  TABLE_SCHEMA = '%s' and (TABLE_NAME like '%s' or TABLE_COMMENT LIKE  '%s');";
    public static final String SELECT_COLUMNS_INFO_SQL = "SELECT T.*, C.* FROM information_schema.`TABLES` T LEFT JOIN information_schema.`COLUMNS` C ON T.TABLE_NAME = C.TABLE_NAME AND T.TABLE_SCHEMA = C.TABLE_SCHEMA WHERE T.TABLE_SCHEMA = '%s' and T.TABLE_NAME ='%s' ORDER BY C.TABLE_NAME, C.ORDINAL_POSITION;";
    public static final Map<String, String> CODE_VALUE_MAP = new HashMap<>();

    static {
        CODE_VALUE_MAP.put("SELECT  * FROM    information_schema.`TABLES` WHERE  TABLE_SCHEMA = '%s' and (TABLE_NAME like '%s' or TABLE_COMMENT LIKE  '%s');", "查询某个库表信息");

        CODE_VALUE_MAP.put("SELECT T.*, C.* FROM information_schema.`TABLES` T LEFT JOIN information_schema.`COLUMNS` C ON T.TABLE_NAME = C.TABLE_NAME AND T.TABLE_SCHEMA = C.TABLE_SCHEMA WHERE T.TABLE_SCHEMA = '%s' and T.TABLE_NAME ='%s' ORDER BY C.TABLE_NAME, C.ORDINAL_POSITION;", "查询某个库表的字段信息");
    }
}

