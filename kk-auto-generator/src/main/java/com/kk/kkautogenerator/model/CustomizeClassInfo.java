package com.kk.kkautogenerator.model;

import lombok.Data;

import java.util.List;

/**
 * 自定义生成对象属性
 * description:
 * auth: kk
 * date: 2026-04-23
 */
@Data
public class CustomizeClassInfo {
    /**
     * 名称
     */
    private String name;
    /**
     * 描述
     */
    private String comment;
    /**
     * 方法
     */
    private List<CustomizeInfo> methods;
}
