package com.kk.kkautogenerator.constant;

import java.util.HashMap;
import java.util.Map;


public class SysCodeTemplateType4Suffix {
    public static final String DAO_ENTITY = "dao_entity";
    public static final String SERVICE = "service";
    public static final String SERVICE_IMPL = "service_impl";
    public static final String CONTROLLER = "controller";
    public static final String DAO_MAPPER = "dao_mapper";
    public static final String MAPPER = "mapper";
    public static final String LISTVO = "listvo";
    public static final String ADDVO = "addvo";
    public static final String EDITVO = "editvo";
    public static final String DELETEVO = "deletevo";
    public static final String DETAILSVO = "detailsvo";
    public static final String DTO = "dto";
    public static final String LISTDTO = "listdto";
    public static final String API_JS = "api_js";
    public static final String LISTVIEW_VUE = "listview_vue";
    public static final String ROUTER_JS = "router_js";
    public static final Map<String, String> CODE_VALUE_MAP = new HashMap<>();

    static {
        CODE_VALUE_MAP.put("dao_entity", "java");

        CODE_VALUE_MAP.put("service", "java");


        CODE_VALUE_MAP.put("service_impl", "java");

        CODE_VALUE_MAP.put("controller", "java");

        CODE_VALUE_MAP.put("dao_mapper", "java");

        CODE_VALUE_MAP.put("mapper", "xml");

        CODE_VALUE_MAP.put("listvo", "java");

        CODE_VALUE_MAP.put("addvo", "java");

        CODE_VALUE_MAP.put("editvo", "java");

        CODE_VALUE_MAP.put("deletevo", "java");

        CODE_VALUE_MAP.put("detailsvo", "java");

        CODE_VALUE_MAP.put("dto", "java");

        CODE_VALUE_MAP.put("listdto", "java");

        CODE_VALUE_MAP.put("listview_vue", "vue");

        CODE_VALUE_MAP.put("router_js", "js");

        CODE_VALUE_MAP.put("api_js", "js");
        CODE_VALUE_MAP.put("postgresql_sql", "sql");

        CODE_VALUE_MAP.put("flinkcdcsink_java",  "java");
        CODE_VALUE_MAP.put("flinkcdcsourceentity_java", "java");


    }
}


