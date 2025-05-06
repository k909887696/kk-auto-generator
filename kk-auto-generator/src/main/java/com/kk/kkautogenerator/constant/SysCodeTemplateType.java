package com.kk.kkautogenerator.constant;

import java.util.HashMap;
import java.util.Map;


public class SysCodeTemplateType {
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

    public static final String POSTGRESQL_SQL = "postgresql_sql";

    public static final Map<String, Integer> CODE_VALUE_MAP = new HashMap<>();

    static {
        CODE_VALUE_MAP.put("dao_entity", -1);

        CODE_VALUE_MAP.put("service", -2);


        CODE_VALUE_MAP.put("service_impl", -3);

        CODE_VALUE_MAP.put("controller", -4);

        CODE_VALUE_MAP.put("dao_mapper", -5);

        CODE_VALUE_MAP.put("mapper", -6);

        CODE_VALUE_MAP.put("listvo", -7);

        CODE_VALUE_MAP.put("addvo", -8);

        CODE_VALUE_MAP.put("editvo", -9);

        CODE_VALUE_MAP.put("deletevo", -10);

        CODE_VALUE_MAP.put("detailsvo", -11);

        CODE_VALUE_MAP.put("dto", -12);


        CODE_VALUE_MAP.put("listdto", -13);

        CODE_VALUE_MAP.put("listview_vue", -14);

        CODE_VALUE_MAP.put("router_js", -15);

        CODE_VALUE_MAP.put("api_js", -16);

        CODE_VALUE_MAP.put("postgresql_sql", -17);

        CODE_VALUE_MAP.put("flinkcdcsink_java", -18);
        CODE_VALUE_MAP.put("flinkcdcsourceentity_java", -19);
    }
}

