package com.kk.kkautogenerator.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;

import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.common.base.model.BasePage;
import com.kk.common.base.model.PageResult;
import com.kk.common.constant.SystemKey;
import com.kk.common.dao.mapper.RootMapper;

import com.kk.common.exception.BusinessException;
import com.kk.common.utils.DateUtil;
import com.kk.common.utils.StringOperationUtil;
import com.kk.common.web.model.ApiResult;
import com.kk.kkautogenerator.config.SystemSettingConfig;
import com.kk.kkautogenerator.constant.SysCodeTemplateType;
import com.kk.kkautogenerator.dao.entity.CodeTemplate;
import com.kk.kkautogenerator.model.BaseNameSpace;
import com.kk.kkautogenerator.model.CustomizeClassInfo;
import com.kk.kkautogenerator.model.CustomizeInfo;
import com.kk.kkautogenerator.model.vo.GenerateCodeVo;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.*;

@Component
public class Generator {
    @Resource
    public SystemSettingConfig systemSettingConfig;
    @Resource
    public EnhanceFreemarkerTemplateEngine enhanceFreemarkerTemplateEngine;


    private BaseNameSpace initGeneratorParame(GenerateCodeVo vo)
    {
        BaseNameSpace baseNameSpace = new BaseNameSpace();

        String apiModuleName = "api."+vo.getModuleName() ;
        String clientModuleName = "client."+vo.getModuleName() ;
        String businessModuleName = "business."+vo.getModuleName() ;
        String viewModuleName = "view."+vo.getModuleName() ;
        String pathPrefix = "/src/main";
        baseNameSpace.setApiPrefixLogin(SystemKey.ApiPrefixLogin);
        baseNameSpace.setMapperBaseClass(RootMapper.class.getSimpleName());
        baseNameSpace.setBasePage(BasePage.class.getSimpleName());
        baseNameSpace.setBasePageResult(PageResult.class.getSimpleName());

        baseNameSpace.setTopNameSpace(vo.getPackageName());
        baseNameSpace.setModuleName(vo.getModuleName());
        baseNameSpace.setApiModuleName(apiModuleName);
        baseNameSpace.setClientModuleName(clientModuleName);
        baseNameSpace.setBusinessModuleName(businessModuleName);
        baseNameSpace.setViewModuleName(viewModuleName);

        baseNameSpace.setBllNameSpace("service.bll");
        baseNameSpace.setModelNameSpace("model");
        baseNameSpace.setModelVoBaseNameSpace("model.vobase");
        baseNameSpace.setClientModelVoBaseNameSpace("model.voinn");
        baseNameSpace.setClientServiceNameSpace("client");
        baseNameSpace.setDaoNameSpace("dao.entity");
        baseNameSpace.setDaoMapperNameSpace("dao.mapper");
        baseNameSpace.setServiceNameSpace("service");
        baseNameSpace.setServiceImplNameSpace("service.impl");
        baseNameSpace.setServiceApiNameSpace("serviceapi");
        baseNameSpace.setServiceViewNameSpace("serviceview");
        baseNameSpace.setXmlMapperNameSpace("xml");
        baseNameSpace.setControllerVNameSpace("controller.v");
        baseNameSpace.setControllerExtNameSpace("controller.ext");
        baseNameSpace.setControllerNlNameSpace("controller.nl");
        baseNameSpace.setControllerInnNameSpace("controller.inn");

        baseNameSpace.setControllerNlNamePrefix("nl");
        baseNameSpace.setControllerVNamePrefix("v");
        baseNameSpace.setControllerExtNamePrefix("ext");
        baseNameSpace.setControllerInnNamePrefix("inn");

        String parentApiPackageName = vo.getPackageName() + "." + apiModuleName;
        String parentClientPackageName = vo.getPackageName() + "." + clientModuleName;
        String parentBusinessPackageName = vo.getPackageName() + "." + businessModuleName;


        baseNameSpace.setApiParentNameSpace(parentApiPackageName);
        baseNameSpace.setClientParentNameSpace(parentClientPackageName);
        baseNameSpace.setBusinessParentNameSpace(parentBusinessPackageName);
        // 自定义导入 import
        baseNameSpace.setDaoMapperImport(List.of(RootMapper.class.getName()));
        baseNameSpace.setServiceImport(List.of(PageResult.class.getName()));
        baseNameSpace.setVoImport(List.of(BasePage.class.getName()));
        baseNameSpace.setServiceImplImport(List.of(PageResult.class.getName()
                , "com.kk.common.utils.BeanUtil"
                , BusinessException.class.getName()
        ));
        baseNameSpace.setServiceApiImport(List.of(PageResult.class.getName()
                ,"com.kk.common.annotation.BaseTransactional"
        ));
        baseNameSpace.setServiceViewImport(List.of(PageResult.class.getName()));
        baseNameSpace.setBllImport(List.of(PageResult.class.getName()));
        baseNameSpace.setControllerVImport(List.of(PageResult.class.getName()
                , ApiResult.class.getName()
                ,"com.kk.business."+vo.getModuleName()+".constant.ServiceSystemKey"
                ,"com.kk.common.constant.SystemKey"
        ));

        baseNameSpace.setClientVoImport(List.of("java.io.Serializable","java.util.Date"));
        baseNameSpace.setClientServiceImport(List.of("com.kk.common.constant.SystemKey"));


        return baseNameSpace;
    }

    public String generateCode(GenerateCodeVo vo) {
        String DAO_ENTITY, MAPPER, DAO_MAPPER, SERVICE, SERVICE_IMPL, CONTROLLER, ADDVO, DELETEVO, LISTVO, EDITVO, DETAILSVO, DTO, LISTDTO, ROUTER_JS
                , clientaddvo, clientdeletevo, clientlistvo, clienteditvo, clientdetailsvo, clientdto, clientlistdto, clientservicefeign
                , serviceImplApi, serviceImplView
                , controllerV, controllerExt, controllerInn;

        DAO_ENTITY = ((CodeTemplate) vo.getCodeTemplateList().get(SysCodeTemplateType.dao_entity)).getTemplateFileUrl();
        MAPPER = ((CodeTemplate) vo.getCodeTemplateList().get(SysCodeTemplateType.mapper)).getTemplateFileUrl();
        DAO_MAPPER = ((CodeTemplate) vo.getCodeTemplateList().get(SysCodeTemplateType.dao_mapper)).getTemplateFileUrl();
        SERVICE = ((CodeTemplate) vo.getCodeTemplateList().get(SysCodeTemplateType.service)).getTemplateFileUrl();
        SERVICE_IMPL = ((CodeTemplate) vo.getCodeTemplateList().get(SysCodeTemplateType.service_impl)).getTemplateFileUrl();
        CONTROLLER = ((CodeTemplate) vo.getCodeTemplateList().get(SysCodeTemplateType.controller)).getTemplateFileUrl();
        serviceImplApi = ((CodeTemplate) vo.getCodeTemplateList().get(SysCodeTemplateType.serviceImplApi)).getTemplateFileUrl();
        serviceImplView = ((CodeTemplate) vo.getCodeTemplateList().get(SysCodeTemplateType.serviceImplView)).getTemplateFileUrl();

        ADDVO = ((CodeTemplate) vo.getCodeTemplateList().get(SysCodeTemplateType.addvo)).getTemplateFileUrl();
        DELETEVO = ((CodeTemplate) vo.getCodeTemplateList().get(SysCodeTemplateType.deletevo)).getTemplateFileUrl();
        LISTVO = ((CodeTemplate) vo.getCodeTemplateList().get(SysCodeTemplateType.listvo)).getTemplateFileUrl();
        EDITVO = ((CodeTemplate) vo.getCodeTemplateList().get(SysCodeTemplateType.editvo)).getTemplateFileUrl();
        DETAILSVO = ((CodeTemplate) vo.getCodeTemplateList().get(SysCodeTemplateType.detailsvo)).getTemplateFileUrl();
        DTO = ((CodeTemplate) vo.getCodeTemplateList().get(SysCodeTemplateType.dto)).getTemplateFileUrl();
        LISTDTO = ((CodeTemplate) vo.getCodeTemplateList().get(SysCodeTemplateType.listdto)).getTemplateFileUrl();
        clientaddvo = ((CodeTemplate) vo.getCodeTemplateList().get(SysCodeTemplateType.clientaddvo)).getTemplateFileUrl();
        clientdeletevo = ((CodeTemplate) vo.getCodeTemplateList().get(SysCodeTemplateType.clientdeletevo)).getTemplateFileUrl();
        clientlistvo = ((CodeTemplate) vo.getCodeTemplateList().get(SysCodeTemplateType.clientlistvo)).getTemplateFileUrl();
        clienteditvo = ((CodeTemplate) vo.getCodeTemplateList().get(SysCodeTemplateType.clienteditvo)).getTemplateFileUrl();
        clientdetailsvo = ((CodeTemplate) vo.getCodeTemplateList().get(SysCodeTemplateType.clientdetailsvo)).getTemplateFileUrl();
        clientdto = ((CodeTemplate) vo.getCodeTemplateList().get(SysCodeTemplateType.clientdto)).getTemplateFileUrl();
        clientlistdto = ((CodeTemplate) vo.getCodeTemplateList().get(SysCodeTemplateType.clientlistdto)).getTemplateFileUrl();

        controllerV = ((CodeTemplate) vo.getCodeTemplateList().get(SysCodeTemplateType.controllerV)).getTemplateFileUrl();
        controllerExt = ((CodeTemplate) vo.getCodeTemplateList().get(SysCodeTemplateType.controllerExt)).getTemplateFileUrl();
        controllerInn = ((CodeTemplate) vo.getCodeTemplateList().get(SysCodeTemplateType.controllerInn)).getTemplateFileUrl();

        clientservicefeign = ((CodeTemplate) vo.getCodeTemplateList().get(SysCodeTemplateType.clientservicefeign)).getTemplateFileUrl();
        String bll = ((CodeTemplate) vo.getCodeTemplateList().get(SysCodeTemplateType.bll)).getTemplateFileUrl();

        String viewListVue = ((CodeTemplate) vo.getCodeTemplateList().get(SysCodeTemplateType.viewListVue)).getTemplateFileUrl();
        String viewListJs = ((CodeTemplate) vo.getCodeTemplateList().get(SysCodeTemplateType.viewListJs)).getTemplateFileUrl();
        String viewRouter = ((CodeTemplate) vo.getCodeTemplateList().get(SysCodeTemplateType.viewRouter)).getTemplateFileUrl();

        BaseNameSpace baseNameSpace = initGeneratorParame(vo);

        String pathPrefix = "/src/main";
        String javaCodePathPrefix = "/java";
        String resourcesPathPrefix = "/resources";




        String parentApiPackageName = baseNameSpace.getApiParentNameSpace();
        String parentClientPackageName = baseNameSpace.getClientParentNameSpace();
        String parentBusinessPackageName = baseNameSpace.getBusinessParentNameSpace();
        String modelPackageName = baseNameSpace.getBusinessParentNameSpace() + "."+baseNameSpace.getModelNameSpace();
        String serviceApiPackageName = baseNameSpace.getBusinessParentNameSpace() + "."+baseNameSpace.getApiParentNameSpace();
        String serviceViewPackageName = baseNameSpace.getBusinessParentNameSpace() + "."+baseNameSpace.getServiceViewNameSpace();
        String serviceBllPackageName = baseNameSpace.getBusinessParentNameSpace() + "."+baseNameSpace.getBllNameSpace();
        String parentApiPath = baseNameSpace.getApiModuleName() + pathPrefix + javaCodePathPrefix+ File.separator + baseNameSpace.getApiParentNameSpace().replace(".", File.separator) + File.separator;
        String parentClientPath = baseNameSpace.getClientModuleName() + pathPrefix + javaCodePathPrefix + File.separator + baseNameSpace.getClientParentNameSpace().replace(".", File.separator) + File.separator;
        String parentBusinessPath = baseNameSpace.getBusinessModuleName() + pathPrefix + javaCodePathPrefix + File.separator + baseNameSpace.getBusinessParentNameSpace().replace(".", File.separator) + File.separator;
        String parentViewPath = baseNameSpace.getViewModuleName() + "/src"  + File.separator ;

        String rootPath = systemSettingConfig.getCustomCodeRootPath()
                + DateUtil.date2String(new Date(), DateUtil.PATTERN_STANDARD08W) + "/"
                + DateUtil.date2String(new Date(), DateUtil.PATTERN_STANDARD17W) + "/"
                + vo.getModuleName();
        FileUtil.createDir(new File(systemSettingConfig.getCustomCodeRootPath()));
        FileUtil.deleteFileAll(rootPath);
        FileUtil.createDir(new File(rootPath));

        FastAutoGenerator.create(vo.getDbConnect().getDbConnectUrl(), vo.getDbConnect().getDbConnectUser(), vo.getDbConnect().getDbConnectPassword())
                .globalConfig(builder -> builder
                        .author(vo.getAuth())
                        .outputDir(rootPath + "/" + baseNameSpace.getBusinessModuleName() + pathPrefix + javaCodePathPrefix)
                        .enableSwagger().dateType(DateType.ONLY_DATE)
                        .commentDate("yyyy-MM-dd")
                        .disableOpenDir()
                )
                .packageConfig(builder -> builder
                        .parent(baseNameSpace.getTopNameSpace())
                        .moduleName(baseNameSpace.getBusinessModuleName())
                        .entity(baseNameSpace.getDaoNameSpace())
                        .service(baseNameSpace.getServiceNameSpace())
                        .serviceImpl(baseNameSpace.getServiceImplNameSpace())
                        //.controller("controller.intra")
                        .mapper(baseNameSpace.getDaoMapperNameSpace())
                        .xml(baseNameSpace.getXmlMapperNameSpace())
                        //.other("entityvo")
                        .pathInfo(Collections.singletonMap(OutputFile.xml, rootPath + "/" + baseNameSpace.getBusinessModuleName()  + pathPrefix + resourcesPathPrefix + "/mapper")))
                .strategyConfig(builder -> {


                    builder.addInclude(vo.getTables()).serviceBuilder()
                            .formatServiceImplFileName("%sServiceImpl")
                            .superServiceClass(IMppService.class)
                            .superServiceImplClass(MppServiceImpl.class)
                            .entityBuilder().enableLombok().enableTableFieldAnnotation()
                            .controllerBuilder().disable()
                            .mapperBuilder().enableBaseColumnList().enableBaseResultMap().superClass(RootMapper.class).formatMapperFileName("%sMapper").formatXmlFileName("%sMapper")
                            .serviceBuilder().formatServiceFileName("I%sService");


                    if (!vo.getUseDao()) {
                        builder.mapperBuilder().disable();
                        builder.entityBuilder().disable();
                    }
                    if (!vo.getUseService()) {
                        builder.serviceBuilder().disable();
                    }
                }).templateConfig(builder -> builder
                        .controller("")
                        .entity(DAO_ENTITY)
                        .mapper(DAO_MAPPER).service(SERVICE).serviceImpl(SERVICE_IMPL).xml(MAPPER))


                .templateEngine(enhanceFreemarkerTemplateEngine)
                .injectionConfig(builder -> {
                    //base package name
                      Map<String, Object> paramMap = (vo.getCustomerParamMap() == null) ? new HashMap<>() : vo.getCustomerParamMap();
                    paramMap.put("baseNameSpace", baseNameSpace);
                    paramMap.put("rootOutputFilePath", rootPath);

                    //client package name
                    paramMap.put("customClientPackageName", parentClientPackageName);
                    paramMap.put("customModuleName", vo.getModuleName());
                    // client service 自定以import
                    List<String> customClientServiceFeignNamespace = new ArrayList<>();
                    customClientServiceFeignNamespace.add("com.meiya.tihb.webapicode.model.BasePageApiTRspVo");
                    customClientServiceFeignNamespace.add("com.meiya.tihb.webapicode.model.BaseApiTRspVo");
                    customClientServiceFeignNamespace.add("com.meiya.tihb.webapicode.BaseWebApiFinal");
                    customClientServiceFeignNamespace.add("com.meiya.tihb.nacos.config.BaseFeignMonitorLoggerConfig");
                    paramMap.put("customClientServiceFeignNamespace", customClientServiceFeignNamespace);
                    // client entity namespace
                    List<String> customClientEntityNamespace = new ArrayList<>();
                    customClientEntityNamespace.add("java.io.Serializable");
                    customClientEntityNamespace.add("java.util.Date");
                    paramMap.put("customClientEntityNamespace", customClientEntityNamespace);

                    Map<String, String> customFileMap = new HashMap<>();
                    if (vo.getUseVo()) {
                        customFileMap.put(parentBusinessPath + baseNameSpace.getModelVoBaseNameSpace().replace(".", File.separator) + File.separator + "req" + File.separator + "%sListReqVo.java", LISTVO);
                        customFileMap.put(parentBusinessPath + baseNameSpace.getModelVoBaseNameSpace().replace(".", File.separator) + File.separator + "req" + File.separator + "%sAddReqVo.java", ADDVO);
                        customFileMap.put(parentBusinessPath + baseNameSpace.getModelVoBaseNameSpace().replace(".", File.separator) + File.separator + "req" + File.separator + "%sEditReqVo.java", EDITVO);
                        customFileMap.put(parentBusinessPath + baseNameSpace.getModelVoBaseNameSpace().replace(".", File.separator) + File.separator + "req" + File.separator + "%sDeleteReqVo.java", DELETEVO);
                        customFileMap.put(parentBusinessPath + baseNameSpace.getModelVoBaseNameSpace().replace(".", File.separator) + File.separator + "req" + File.separator + "%sDetailsReqVo.java", DETAILSVO);
                        customFileMap.put(parentBusinessPath + baseNameSpace.getModelVoBaseNameSpace().replace(".", File.separator) + File.separator + "res" + File.separator + "%sResVo.java", DTO);
                        customFileMap.put(parentBusinessPath + baseNameSpace.getModelVoBaseNameSpace().replace(".", File.separator) + File.separator + "res" + File.separator + "%sListResVo.java", LISTDTO);
                    }
                    if (vo.getUseServiceApi()) {
                        customFileMap.put(parentBusinessPath + baseNameSpace.getServiceApiNameSpace() + File.separator + "%sServiceApi.java", serviceImplApi);
                    }
                    if (vo.getUseServiceView()) {
                        customFileMap.put(parentBusinessPath + baseNameSpace.getServiceViewNameSpace() + File.separator + "%sServiceView.java", serviceImplView);
                    }
                    if (vo.getUseServiceBll()) {
                        customFileMap.put(parentBusinessPath + baseNameSpace.getBllNameSpace().replace(".", File.separator) + File.separator + "%sBll.java", bll);
                    }
                    if (vo.getInnController()) {
                        customFileMap.put(parentApiPath +  File.separator + baseNameSpace.getControllerInnNameSpace().replace(".", File.separator) + File.separator + StringOperationUtil.capFirst(baseNameSpace.getControllerInnNamePrefix()) + "%sController.java", controllerInn);
                        customFileMap.put(parentClientPath + baseNameSpace.getClientModelVoBaseNameSpace().replace(".", File.separator) + File.separator + "req" + File.separator + "%sAddReqVo.java", clientaddvo);
                        customFileMap.put(parentClientPath + baseNameSpace.getClientModelVoBaseNameSpace().replace(".", File.separator) + File.separator + "req" + File.separator + "%sListReqVo.java", clientlistvo);
                        customFileMap.put(parentClientPath + baseNameSpace.getClientModelVoBaseNameSpace().replace(".", File.separator) + File.separator + "req" + File.separator + "%sEditReqVo.java", clienteditvo);
                        customFileMap.put(parentClientPath + baseNameSpace.getClientModelVoBaseNameSpace().replace(".", File.separator) + File.separator + "req" + File.separator + "%sDeleteReqVo.java", clientdeletevo);
                        customFileMap.put(parentClientPath + baseNameSpace.getClientModelVoBaseNameSpace().replace(".", File.separator) + File.separator + "req" + File.separator + "%sDetailsReqVo.java", clientdetailsvo);
                        customFileMap.put(parentClientPath + baseNameSpace.getClientModelVoBaseNameSpace().replace(".", File.separator) + File.separator + "res" + File.separator + "%sResVo.java", clientdto);
                        customFileMap.put(parentClientPath + baseNameSpace.getClientModelVoBaseNameSpace().replace(".", File.separator) + File.separator + "res" + File.separator + "%sListResVo.java", clientlistdto);
                        customFileMap.put(parentClientPath + baseNameSpace.getClientServiceNameSpace().replace(".", File.separator) + File.separator + "%sClientProxy.java", clientservicefeign);
                    }
                    if (vo.getExtController()) {
                        customFileMap.put(parentApiPath +  File.separator + baseNameSpace.getControllerExtNameSpace().replace(".", File.separator) + File.separator + StringOperationUtil.capFirst(baseNameSpace.getControllerExtNamePrefix()) + "%sController.java", controllerExt);
                    }
                    if (vo.getViewController()) {
                        customFileMap.put(parentApiPath +  File.separator + baseNameSpace.getControllerVNameSpace().replace(".", File.separator) + File.separator + StringOperationUtil.capFirst(baseNameSpace.getControllerVNamePrefix()) + "%sController.java", controllerV);
                    }

                    //前端
                    if(vo.getUseViewListVue())
                    {
                        customFileMap.put(parentViewPath + "/views" + File.separator + baseNameSpace.getModuleName() + File.separator + "%sList.vue", viewListVue);
                    }
                    if(vo.getUseViewListJs())
                    {
                        customFileMap.put(parentViewPath + "/api" + File.separator + baseNameSpace.getModuleName() + File.separator + "%sApi.js", viewListJs);
                    }

                    if(vo.getUseViewRouter())
                    {
                        customFileMap.put(baseNameSpace.getViewModuleName() +  File.separator + "%sRouter.js", viewRouter);
                    }


                    builder.customMap(paramMap).customFile(customFileMap);
                }).execute();
        return rootPath;
    }

    /**
     * 自定义生成class
     *
     * @param vo
     * @return
     */
    public String generateCodeByCustomize(GenerateCodeVo vo) {
        String filePath = "";
        if (!StringUtils.isEmpty(vo.getCustomizeClass())) {
            Map<String, CustomizeClassInfo> classMethodMap = formatClassMethodMap(vo.getCustomizeClass());

            String rootPath = systemSettingConfig.getCustomCodeRootPath()
                    + DateUtil.date2String(new Date(), DateUtil.PATTERN_STANDARD08W) + "/"
                    + DateUtil.date2String(new Date(), DateUtil.PATTERN_STANDARD17W) + "/"
                    + vo.getModuleName();

            FileUtil.createDir(new File(systemSettingConfig.getCustomCodeRootPath()));
            FileUtil.deleteFileAll(rootPath);
            FileUtil.createDir(new File(rootPath));


            BaseNameSpace baseNameSpace = initGeneratorParame(vo);

            String pathPrefix = "/src/main";
            String javaCodePathPrefix = "/java/";
            String resourcesPathPrefix = "/resources";



            String parentApiPath = rootPath + "/" + baseNameSpace.getApiModuleName() + pathPrefix + javaCodePathPrefix + baseNameSpace.getApiParentNameSpace().replace(".", File.separator) + File.separator;
            String parentClientPath = rootPath + "/" + baseNameSpace.getClientModuleName() + pathPrefix + javaCodePathPrefix + baseNameSpace.getClientParentNameSpace().replace(".", File.separator) + File.separator;
            String parentBusinessPath = rootPath + "/" + baseNameSpace.getBusinessModuleName() + pathPrefix + javaCodePathPrefix + baseNameSpace.getBusinessParentNameSpace().replace(".", File.separator) + File.separator;

            Map<String, Object> baseParamMap = (vo.getCustomerParamMap() == null) ? new HashMap<>() : vo.getCustomerParamMap();

            baseParamMap.put("author", vo.getAuth());
            baseParamMap.put("date", DateUtil.date2String(new Date(), "yyyy-MM-dd"));

            baseParamMap.put("baseNameSpace", baseNameSpace);
            baseParamMap.put("rootOutputFilePath", rootPath);
            enhanceFreemarkerTemplateEngine.init(null);
            classMethodMap.forEach((className, customizeClassInfo) -> {
                String classNameUp = capitalizeFirstLetter(className);
                Map<String, Object> objectMap = new HashMap<>(baseParamMap);
                objectMap.put("className", classNameUp);
                objectMap.put("serviceName", "I" + classNameUp + "Service");
                objectMap.put("serviceViewName", classNameUp + "ServiceView");
                objectMap.put("serviceImplName", classNameUp + "ServiceImpl");
                objectMap.put("serviceApiName", classNameUp + "ServiceApi");
                objectMap.put("entity", classNameUp);
                objectMap.put("classComment", customizeClassInfo.getComment());
                objectMap.put("methods", customizeClassInfo.getMethods());
                List<CustomFile> customFile = new ArrayList<>();
                if (vo.getUseService()) {
                    String serviceFileName = parentApiPath + baseNameSpace.getServiceNameSpace().replace(".", File.separator) + File.separator + "I%sService" + ".java";
                    String serviceImplFileName = parentApiPath + baseNameSpace.getServiceImplNameSpace().replace(".", File.separator) + File.separator + "%sServiceImpl" + ".java";
                    customFile.add((new CustomFile.Builder()).fileName(serviceFileName).templatePath(vo.getCodeTemplateList().get(SysCodeTemplateType.customizeService).getTemplateFileUrl()).build());
                    customFile.add((new CustomFile.Builder()).fileName(serviceImplFileName).templatePath(vo.getCodeTemplateList().get(SysCodeTemplateType.customizeServiceImpl).getTemplateFileUrl()).build());

                }
                if (vo.getUseServiceApi()) {
                    String serviceApiFileName = parentApiPath + baseNameSpace.getServiceApiNameSpace().replace(".", File.separator) + File.separator + "%sServiceApi.java";
                    customFile.add((new CustomFile.Builder()).fileName(serviceApiFileName)
                            .templatePath(vo.getCodeTemplateList().get(SysCodeTemplateType.customizeServiceImplApi).getTemplateFileUrl()).build());

                }
                if (vo.getUseServiceView()) {
                    String serviceViewFileName = parentApiPath + baseNameSpace.getServiceViewNameSpace().replace(".", File.separator) + File.separator + "%sServiceView.java";
                    customFile.add((new CustomFile.Builder()).fileName(serviceViewFileName).templatePath(vo.getCodeTemplateList().get(SysCodeTemplateType.customizeServiceImplView).getTemplateFileUrl()).build());
                }
                if (vo.getUseServiceBll()) {
                    String bllFileName = parentApiPath + baseNameSpace.getBllNameSpace().replace(".", File.separator) + File.separator + "%sBll.java";
                    customFile.add((new CustomFile.Builder()).fileName(bllFileName).templatePath(vo.getCodeTemplateList().get(SysCodeTemplateType.customizeBll).getTemplateFileUrl()).build());
                }
                if (vo.getInnController()) {
                    String intraControllerFileName = parentApiPath + baseNameSpace.getControllerInnNameSpace().replace(".", File.separator) + File.separator +StringOperationUtil.capFirst(baseNameSpace.getControllerInnNamePrefix()) + "%sController.java";
                    String customizeClientservicefeignFileName = parentClientPath + baseNameSpace.getClientServiceNameSpace().replace(".", File.separator) + File.separator + "%sClientProxy.java";
                    customFile.add((new CustomFile.Builder()).fileName(String.format(customizeClientservicefeignFileName, classNameUp)).templatePath(vo.getCodeTemplateList().get(SysCodeTemplateType.customizeClientservicefeign).getTemplateFileUrl()).build());
                    customFile.add((new CustomFile.Builder()).fileName(String.format(intraControllerFileName, classNameUp)).templatePath(vo.getCodeTemplateList().get(SysCodeTemplateType.customizeControllerInn).getTemplateFileUrl()).build());
                }
                if (vo.getViewController()) {
                    String groupControllerFileName = parentApiPath + baseNameSpace.getControllerVNameSpace().replace(".", File.separator) + File.separator + StringOperationUtil.capFirst(baseNameSpace.getControllerVNamePrefix()) + "%sController.java";
                    customFile.add((new CustomFile.Builder()).fileName(String.format(groupControllerFileName, classNameUp)).templatePath(vo.getCodeTemplateList().get(SysCodeTemplateType.customizeControllerV).getTemplateFileUrl()).build());
                }
                if (vo.getExtController()) {
                    String incControllerFileName = parentApiPath + baseNameSpace.getControllerExtNameSpace().replace(".", File.separator) + File.separator + StringOperationUtil.capFirst(baseNameSpace.getControllerExtNamePrefix()) + "%sController.java";
                    customFile.add((new CustomFile.Builder()).fileName(String.format(incControllerFileName, classNameUp)).templatePath(vo.getCodeTemplateList().get(SysCodeTemplateType.customizeControllerExt).getTemplateFileUrl()).build());
                }

                if (customFile.size() > 0) {
                    try {
                        enhanceFreemarkerTemplateEngine.outputCustomFile(customFile, classNameUp, objectMap);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                if (customizeClassInfo.getMethods() != null && customizeClassInfo.getMethods().size() > 0) {


                    for (CustomizeInfo customizeInfo : customizeClassInfo.getMethods()) {
                        objectMap.put("methodName", customizeInfo.getName());
                        objectMap.put("methodComment", customizeInfo.getComment());
                        String methodReqVoFileName = parentApiPath + baseNameSpace.getControllerVNameSpace().replace(".", File.separator) + "/req" + File.separator + capitalizeFirstLetter(customizeInfo.getName()) + "ReqVo.java";
                        String methodResVoFileName = parentApiPath + baseNameSpace.getControllerVNameSpace().replace(".", File.separator) + "/res" + File.separator + capitalizeFirstLetter(customizeInfo.getName()) + "ResVo.java";
                        String clientMethodReqVoFileName = parentClientPath + baseNameSpace.getClientModelVoBaseNameSpace().replace(".", File.separator)+ "/req" + File.separator + "%sReqVo.java";
                        String clientMethodResVoFileName = parentClientPath + baseNameSpace.getClientModelVoBaseNameSpace() + "/res" + File.separator + "%sResVo.java";

                        List<CustomFile> customMethodFile = new ArrayList<>();
                        if (vo.getUseVo()) {
                            customMethodFile.add((new CustomFile.Builder()).fileName(methodReqVoFileName).templatePath(vo.getCodeTemplateList().get(SysCodeTemplateType.customizeReqVo).getTemplateFileUrl()).build());
                            customMethodFile.add((new CustomFile.Builder()).fileName(methodResVoFileName).templatePath(vo.getCodeTemplateList().get(SysCodeTemplateType.customizeResVo).getTemplateFileUrl()).build());
                        }
                        if (vo.getInnController()) {
                            customMethodFile.add((new CustomFile.Builder()).fileName(clientMethodReqVoFileName).templatePath(vo.getCodeTemplateList().get(SysCodeTemplateType.customizeClientReqVo).getTemplateFileUrl()).build());
                            customMethodFile.add((new CustomFile.Builder()).fileName(clientMethodResVoFileName).templatePath(vo.getCodeTemplateList().get(SysCodeTemplateType.customizeClientResVo).getTemplateFileUrl()).build());
                        }
                        if (customMethodFile.size() > 0) {
                            try {
                                enhanceFreemarkerTemplateEngine.outputCustomFile(customMethodFile, customizeInfo.getName(), objectMap);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }

            });

            filePath = rootPath;
        }
        return filePath;
    }

    /**
     * 将字符串首字母大写
     *
     * @param str 输入字符串
     * @return 首字母大写后的字符串
     */
    public static String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    private Map<String, CustomizeClassInfo> formatClassMethodMap(String customizeClass) {
        Map<String, CustomizeClassInfo> classMethodMap = new HashMap<>();
        if (!com.alibaba.druid.util.StringUtils.isEmpty(customizeClass)) {
            customizeClass = customizeClass.replace("。", ".").replace("：", ":");
            List<String> customizeClassList = Arrays.stream(customizeClass.split("\n")).toList();
            for (String item : customizeClassList) {
                if (!com.alibaba.druid.util.StringUtils.isEmpty(item)) {
                    String className = "";
                    List<String> methodNames = new ArrayList<>();
                    List<String> classMethod = Arrays.stream(item.split("\\.")).toList();
                    if (classMethod.size() >= 1) {
                        className = classMethod.get(0).trim();
                    }
                    if (classMethod.size() >= 2) {
                        methodNames = classMethod.stream().skip(1).toList();
                    }
                    if (com.alibaba.druid.util.StringUtils.isEmpty(className)) {
                        continue;
                    }
                    List<String> classNameComment = Arrays.stream(className.split("::")).toList();
                    className = classNameComment.get(0).trim();
                    String classComment = "";
                    if (classNameComment.size() >= 2) {
                        classComment = classNameComment.get(1).trim();
                    }
                    if (classMethodMap.containsKey(className)) {
                        CustomizeClassInfo customizeClassInfo = classMethodMap.get(className);
                        if (customizeClassInfo == null) {
                            customizeClassInfo = new CustomizeClassInfo();
                            customizeClassInfo.setMethods(new ArrayList<>());
                        }
                        classMethodMap.put(className, customizeClassInfo);
                    } else {
                        classMethodMap.put(className, new CustomizeClassInfo());
                    }
                    if (!com.alibaba.druid.util.StringUtils.isEmpty(classComment)) {
                        classMethodMap.get(className).setComment(classComment);
                    }
                    if (methodNames != null && methodNames.size() > 0) {
                        for (String methodName : methodNames) {
                            List<String> methodNameComment = Arrays.stream(methodName.split("::")).toList();
                            CustomizeInfo customizeInfo = new CustomizeInfo();
                            customizeInfo.setName(capitalizeFirstLetter(methodNameComment.get(0).trim()));
                            if (methodNameComment.size() >= 2) {
                                customizeInfo.setComment(methodNameComment.get(1).trim());
                            }
                            CustomizeClassInfo customizeClassInfo = classMethodMap.get(className);
                            if (customizeClassInfo.getMethods() == null) {
                                customizeClassInfo.setMethods(new ArrayList<>());
                                customizeClassInfo.getMethods().add(customizeInfo);
                            } else {
                                for (CustomizeInfo temp : customizeClassInfo.getMethods()) {
                                    if (temp.getName().equals(customizeInfo.getName())) {
                                        break;
                                    }
                                }
                                customizeClassInfo.getMethods().add(customizeInfo);
                            }
                        }
                    }
                }
            }
        }
        return classMethodMap;
    }
}


