package com.kk.kkautogenerator.utils;

import jdk.nashorn.internal.objects.annotations.Getter;


import java.util.ArrayList;
import java.util.List;

/**
 * @author：kk 2023/5/15
 */

public enum CustomEnum {
    VO("VO", "templates/model/vo.java.vm"),
    RO("RO", "templates/model/ro.java.vm"),
    QO("QO", "templates/model/qo.java.vm"),
    CONVERTER("Converter", "templates/converter/converter.java.vm");

    CustomEnum(String modelSuffix, String path) {
        this.modelSuffix = modelSuffix;
        this.path = path;
    }

    private String modelSuffix;

    private String path;

    public static List<String> getModel() {
        List<String> result = new ArrayList<>();
        CustomEnum[] values = CustomEnum.values();
        for (CustomEnum it : values) {
            result.add(it.getModelSuffix());
        }
        return result;
    }

    public String getModelSuffix() {
        return modelSuffix;
    }

    public void setModelSuffix(String modelSuffix) {
        this.modelSuffix = modelSuffix;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
