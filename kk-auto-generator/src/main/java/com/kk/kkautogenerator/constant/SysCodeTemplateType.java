package com.kk.kkautogenerator.constant;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class SysCodeTemplateType {

    // ==================== DAO 层 ====================
    public static final String dao_entity = "dao_entity";
    public static final String dao_mapper = "dao_mapper";
    public static final String mapper = "mapper";

    // ==================== Service 层 ====================
    public static final String service = "service";
    public static final String service_impl = "service_impl";
    public static final String serviceImplApi = "serviceImplApi";

    public static final String serviceImplView = "serviceImplView";

    public static final String bll = "bll";

    // ==================== Controller 层 ====================
    public static final String controller = "controller";

    public static final String controllerV = "controllerV";
    public static final String controllerInn = "controllerInn";
    public static final String controllerExt = "controllerExt";


    // ==================== VO/DTO - 服务端 ====================
    public static final String addvo = "addvo";
    public static final String deletevo = "deletevo";
    public static final String detailsvo = "detailsvo";
    public static final String dto = "dto";
    public static final String editvo = "editvo";
    public static final String listdto = "listdto";
    public static final String listvo = "listvo";

    // ==================== VO/DTO - 客户端 ====================
    public static final String clientaddvo = "clientaddvo";
    public static final String clientdeletevo = "clientdeletevo";
    public static final String clientdetailsvo = "clientdetailsvo";
    public static final String clientdto = "clientdto";
    public static final String clienteditvo = "clienteditvo";
    public static final String clientlistdto = "clientlistdto";
    public static final String clientlistvo = "clientlistvo";

    // ==================== Feign ====================
    public static final String clientservicefeign = "clientservicefeign";

    //===================== Customize ================
    public static final String customizeService = "customizeService";
    public static final String customizeServiceImpl = "customizeServiceImpl";
    public static final String customizeServiceImplApi = "customizeServiceImplApi";
    public static final String customizeServiceImplView = "customizeServiceImplView";
    public static final String customizeReqVo = "customizeReqVo";
    public static final String customizeResVo = "customizeResVo";
    public static final String customizeControllerIntra = "customizeControllerIntra";

    public static final String customizeControllerIntraDispatch = "customizeControllerIntraDispatch";
    public static final String customizeControllerGroup = "customizeControllerGroup";
    public static final String customizeControllerInc = "customizeControllerInc";
    public static final String customizeControllerPlatform = "customizeControllerPlatform";
    public static final String customizeControllerSupplier = "customizeControllerSupplier";
    public static final String customizeControllerTmc = "customizeControllerTmc";

    public static final String customizeClientReqVo = "customizeClientReqVo";
    public static final String customizeClientResVo = "customizeClientResVo";
    public static final String customizeClientservicefeign = "customizeClientservicefeign";
    public static final String customizeBll = "customizeBll";


    public static final LinkedHashMap<String, Integer> CODE_VALUE_MAP = new LinkedHashMap<>();

    static {
        // DAO 层
        CODE_VALUE_MAP.put(dao_entity, -1);
        CODE_VALUE_MAP.put(dao_mapper, -5);
        CODE_VALUE_MAP.put(mapper, -6);

        // Service 层
        CODE_VALUE_MAP.put(service, -2);
        CODE_VALUE_MAP.put(service_impl, -3);
        CODE_VALUE_MAP.put(serviceImplApi, -14);
        CODE_VALUE_MAP.put(serviceImplView, -114);

        // Controller 层
        CODE_VALUE_MAP.put(controller, -4);
        CODE_VALUE_MAP.put(controllerV, -15);
        CODE_VALUE_MAP.put(controllerExt, -16);
        CODE_VALUE_MAP.put(controllerInn, -17);


        // VO/DTO - 服务端
        CODE_VALUE_MAP.put(listvo, -7);
        CODE_VALUE_MAP.put(addvo, -8);
        CODE_VALUE_MAP.put(editvo, -9);
        CODE_VALUE_MAP.put(deletevo, -10);
        CODE_VALUE_MAP.put(detailsvo, -11);
        CODE_VALUE_MAP.put(dto, -12);
        CODE_VALUE_MAP.put(listdto, -13);

        // VO/DTO - 客户端
        CODE_VALUE_MAP.put(clientaddvo, -22);
        CODE_VALUE_MAP.put(clientlistvo, -23);
        CODE_VALUE_MAP.put(clienteditvo, -24);
        CODE_VALUE_MAP.put(clientdeletevo, -25);
        CODE_VALUE_MAP.put(clientdetailsvo, -26);
        CODE_VALUE_MAP.put(clientdto, -27);
        CODE_VALUE_MAP.put(clientlistdto, -28);

        // Feign
        CODE_VALUE_MAP.put(clientservicefeign, -29);

        // customize
        CODE_VALUE_MAP.put(customizeService, -30);
        CODE_VALUE_MAP.put(customizeServiceImpl, -31);
        CODE_VALUE_MAP.put(customizeServiceImplApi, -32);
        CODE_VALUE_MAP.put(customizeServiceImplView, -33);
        CODE_VALUE_MAP.put(customizeReqVo, -34);
        CODE_VALUE_MAP.put(customizeResVo, -35);
        CODE_VALUE_MAP.put(customizeControllerIntra, -36);
        CODE_VALUE_MAP.put(customizeControllerIntraDispatch, -37);
        CODE_VALUE_MAP.put(customizeControllerGroup, -38);
        CODE_VALUE_MAP.put(customizeControllerInc, -39);
        CODE_VALUE_MAP.put(customizeControllerPlatform, -40);
        CODE_VALUE_MAP.put(customizeControllerSupplier, -41);
        CODE_VALUE_MAP.put(customizeControllerTmc, -42);
        CODE_VALUE_MAP.put(customizeClientReqVo, -43);
        CODE_VALUE_MAP.put(customizeClientResVo, -44);
        CODE_VALUE_MAP.put(customizeClientservicefeign, -45);
        CODE_VALUE_MAP.put(customizeBll, -46);
        CODE_VALUE_MAP.put(bll, -47);
    }
}
