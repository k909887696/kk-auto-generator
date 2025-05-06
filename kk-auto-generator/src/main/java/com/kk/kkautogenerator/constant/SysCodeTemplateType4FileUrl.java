package com.kk.kkautogenerator.constant;

import java.util.HashMap;
import java.util.Map;


public class SysCodeTemplateType4FileUrl {
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
        CODE_VALUE_MAP.put("dao_entity", "/templates/entity.java");

        CODE_VALUE_MAP.put("service", "/templates/service.java");


        CODE_VALUE_MAP.put("service_impl", "/templates/serviceImpl.java");

        CODE_VALUE_MAP.put("controller", "/templates/controller.java");

        CODE_VALUE_MAP.put("dao_mapper", "/templates/mapper.java");

        CODE_VALUE_MAP.put("mapper", "/templates/mapper.xml");

        CODE_VALUE_MAP.put("listvo", "/templates/listvo.java.ftl");

        CODE_VALUE_MAP.put("addvo", "/templates/addvo.java.ftl");

        CODE_VALUE_MAP.put("editvo", "/templates/editvo.java.ftl");

        CODE_VALUE_MAP.put("deletevo", "/templates/deletevo.java.ftl");

        CODE_VALUE_MAP.put("detailsvo", "/templates/detailsvo.java.ftl");

        CODE_VALUE_MAP.put("dto", "/templates/dto.java.ftl");

        CODE_VALUE_MAP.put("listdto", "/templates/listdto.java.ftl");

        CODE_VALUE_MAP.put("listview_vue", "/templates/listview.vue.ftl");

        CODE_VALUE_MAP.put("router_js", "/templates/router.js.ftl");

        CODE_VALUE_MAP.put("api_js", "/templates/api.js.ftl");

        CODE_VALUE_MAP.put("postgresql_sql", "/templates/flinkcdcpostgresql.sql.ftl");

        CODE_VALUE_MAP.put("flinkcdcsink_java",  "/templates/flinkcdcsink.java.ftl");
        CODE_VALUE_MAP.put("flinkcdcsourceentity_java", "/templates/flinkcdcsourceentity.java.ftl");



    }
}
