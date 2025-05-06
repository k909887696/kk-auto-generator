package com.kk.kkautogenerator.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.common.utils.FileUtil;
import com.kk.kkautogenerator.config.SystemSettingConfig;
import com.kk.kkautogenerator.dao.entity.CodeTemplate;
import com.kk.kkautogenerator.model.vo.GenerateCodeVo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Generator {
    @Resource
    public SystemSettingConfig systemSettingConfig;
    @Resource
    public EnhanceFreemarkerTemplateEngine enhanceFreemarkerTemplateEngine;
    public  String generateCode(GenerateCodeVo vo ) {
        String DAO_ENTITY, MAPPER, DAO_MAPPER, SERVICE, SERVICE_IMPL, CONTROLLER, ADDVO, DELETEVO, LISTVO, EDITVO, DETAILSVO, DTO, LISTDTO, ROUTER_JS, API_JS, LISTVIEW_VUE, POSTGRESQL_SQL,FLINKCDCSINK_JAVA,FLINKCDCSOURCEENTITY_JAVA;
        if (vo.getCodeTemplateList() != null) {


            DAO_ENTITY = ((CodeTemplate) vo.getCodeTemplateList().get("dao_entity")).getTemplateFileUrl();

            MAPPER = ((CodeTemplate) vo.getCodeTemplateList().get("mapper")).getTemplateFileUrl();

            DAO_MAPPER = ((CodeTemplate) vo.getCodeTemplateList().get("dao_mapper")).getTemplateFileUrl();

            SERVICE = ((CodeTemplate) vo.getCodeTemplateList().get("service")).getTemplateFileUrl();

            SERVICE_IMPL = ((CodeTemplate) vo.getCodeTemplateList().get("service_impl")).getTemplateFileUrl();


            CONTROLLER = ((CodeTemplate) vo.getCodeTemplateList().get("controller")).getTemplateFileUrl();

            ADDVO = ((CodeTemplate) vo.getCodeTemplateList().get("addvo")).getTemplateFileUrl();


            DELETEVO = ((CodeTemplate) vo.getCodeTemplateList().get("deletevo")).getTemplateFileUrl();

            LISTVO = ((CodeTemplate) vo.getCodeTemplateList().get("listvo")).getTemplateFileUrl();

            EDITVO = ((CodeTemplate) vo.getCodeTemplateList().get("editvo")).getTemplateFileUrl();

            DETAILSVO = ((CodeTemplate) vo.getCodeTemplateList().get("detailsvo")).getTemplateFileUrl();


            DTO = ((CodeTemplate) vo.getCodeTemplateList().get("dto")).getTemplateFileUrl();


            LISTDTO = ((CodeTemplate) vo.getCodeTemplateList().get("listdto")).getTemplateFileUrl();

            ROUTER_JS = ((CodeTemplate) vo.getCodeTemplateList().get("router_js")).getTemplateFileUrl();


            API_JS = ((CodeTemplate) vo.getCodeTemplateList().get("api_js")).getTemplateFileUrl();

            LISTVIEW_VUE = ((CodeTemplate) vo.getCodeTemplateList().get("listview_vue")).getTemplateFileUrl();

            POSTGRESQL_SQL = ((CodeTemplate) vo.getCodeTemplateList().get("postgresql_sql")).getTemplateFileUrl();
            FLINKCDCSINK_JAVA= ((CodeTemplate) vo.getCodeTemplateList().get("flinkcdcsink_java")).getTemplateFileUrl();
            FLINKCDCSOURCEENTITY_JAVA= ((CodeTemplate) vo.getCodeTemplateList().get("flinkcdcsourceentity_java")).getTemplateFileUrl();
        } else {

            MAPPER = "/templates/mapper.xml";
            SERVICE_IMPL = "/templates/serviceImpl.java";
            SERVICE = "/templates/service.java";
            DAO_MAPPER = "/templates/mapper.java";
            DAO_ENTITY = "/templates/entity.java";
            CONTROLLER = "/templates/controller.java";
            ADDVO = "/templates/addvo.java";
            DELETEVO = "/templates/deletevo.java";
            LISTVO = "/templates/listvo.java";
            EDITVO = "/templates/editvo.java";
            DETAILSVO = "/templates/detailsvo.java";
            DTO = "/templates/dto.java";
            LISTDTO = "/templates/listdto.java";
            ROUTER_JS = "/templates/router.js";
            API_JS = "/templates/api.js";
            LISTVIEW_VUE = "/templates/listview.vue";
            POSTGRESQL_SQL = "/templates/postgresql.sql";
            FLINKCDCSINK_JAVA= "/templates/flinkcdcsink.sql";
            FLINKCDCSOURCEENTITY_JAVA= "/templates/flinkcdcsourceentity.sql";
        }

        String rootPath =  systemSettingConfig.getCustomCodeRootPath() + vo.getModuleName();

        FileUtil.deleteFileAll(rootPath);


        FastAutoGenerator.create(vo.getDbConnect().getDbConnectUrl(), vo.getDbConnect().getDbConnectUser(), vo.getDbConnect().getDbConnectPassword())

                .globalConfig(builder -> builder
                        .author(vo.getAuth()).outputDir(rootPath + "/java")
                        .enableSwagger().dateType(DateType.ONLY_DATE)
                        .commentDate("yyyy-MM-dd")
                        .disableOpenDir()
                        .fileOverride())


                .packageConfig(builder -> builder
                        .parent(vo.getPackageName())
                        .moduleName(vo.getModuleName())
                        .entity("dao.entity")
                        .service("service")
                        .serviceImpl("service.impl")
                        .controller("controller")
                        .mapper("dao.mapper")
                        .xml("mapper")
                        .other("model")
                        .pathInfo(Collections.singletonMap(OutputFile.mapperXml, rootPath + "/resources/mapper")))


                .strategyConfig(builder -> {


                    if (vo.getLombok().booleanValue()) {

                        builder.addInclude(vo.getTables()).serviceBuilder()
                                .formatServiceImplFileName("%sServiceImpl")
                                .superServiceClass(IMppService.class)
                                .superServiceImplClass(MppServiceImpl.class)
                                .entityBuilder().enableLombok().enableTableFieldAnnotation()
                                .controllerBuilder().formatFileName("%sController")
                                .enableRestStyle().mapperBuilder().enableBaseColumnList()
                                .enableBaseResultMap().superClass(RootMapper.class)
                                .formatMapperFileName("%sMapper").formatXmlFileName("%sMapper");
                    } else {

                        builder.addInclude(vo.getTables())
                                .serviceBuilder().formatServiceImplFileName("%sServiceImpl")
                                .superServiceClass(IMppService.class)
                                .superServiceImplClass(MppServiceImpl.class)
                                .entityBuilder().enableTableFieldAnnotation()
                                .controllerBuilder().formatFileName("%sController")
                                .enableRestStyle().mapperBuilder().enableBaseColumnList()
                                .enableBaseResultMap().superClass(RootMapper.class)
                                .formatMapperFileName("%sMapper").formatXmlFileName("%sMapper");
                    }
                }).templateConfig(builder -> builder.controller(CONTROLLER).entity(DAO_ENTITY)
                        .mapper(DAO_MAPPER).service(SERVICE).serviceImpl(SERVICE_IMPL).mapperXml(MAPPER))


                .templateEngine(enhanceFreemarkerTemplateEngine)
                .injectionConfig(builder -> {
                    Map<String, Object> paramMap = (vo.getCustomerParamMap() == null) ? new HashMap<>() : vo.getCustomerParamMap();

                    List<String> customImplNamespace = new ArrayList<>();

                    customImplNamespace.add("com.kk.common.utils.MapperUtils");

                    customImplNamespace.add("com.kk.common.base.model.PageResult");

                    customImplNamespace.add("com.kk.common.exception.BusinessException");

                    paramMap.put("customImplNamespace", customImplNamespace);

                    List<String> customServiceNamespace = new ArrayList<>();

                    customServiceNamespace.add("com.kk.common.base.model.PageResult");

                    paramMap.put("customServiceNamespace", customServiceNamespace);

                    paramMap.put("rootOutputFilePath", rootPath);

                    Map<String, String> customFileMap = new HashMap<>();

                    customFileMap.put("vo" + File.separator + "%sListVo.java", LISTVO);

                    customFileMap.put("vo" + File.separator + "%sAddVo.java", ADDVO);

                    customFileMap.put("vo" + File.separator + "%sEditVo.java", EDITVO);

                    customFileMap.put("vo" + File.separator + "%sDeleteVo.java", DELETEVO);

                    customFileMap.put("vo" + File.separator + "%sDetailsVo.java", DETAILSVO);
                    customFileMap.put("dto" + File.separator + "%sDto.java", DTO);
                    customFileMap.put("dto" + File.separator + "%sListDto.java", LISTDTO);
                    customFileMap.put("api" + File.separator + vo.getModuleName() + File.separator + "%s_api.js", API_JS);
                    customFileMap.put("views" + File.separator + vo.getModuleName() + File.separator + "%s_list.vue", LISTVIEW_VUE);
                    customFileMap.put("router" + File.separator + vo.getModuleName() + File.separator + "%s_router.js", ROUTER_JS);
                    customFileMap.put("flinkcdc" + File.separator + vo.getModuleName() + File.separator + "%s.sql", POSTGRESQL_SQL);

                    customFileMap.put("flinkcdc" + File.separator + "%sSink.java", FLINKCDCSINK_JAVA);//("flink-cdc-sink_java",  "java");
                    customFileMap.put("flinkcdc" + File.separator + "%s.java", FLINKCDCSOURCEENTITY_JAVA);

                    builder.customMap(paramMap).customFile(customFileMap);
                }).execute();
        return rootPath;
    }
}


