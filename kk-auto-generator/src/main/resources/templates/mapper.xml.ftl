<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.mapperName}">

<#if enableCache>
    <!-- 开启二级缓存 -->
    <cache type="${cacheClassName}"/>

</#if>
<#if baseResultMap>
    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="${package.Entity}.${entity}">
<#list table.fields as field>
<#if field.keyFlag><#--生成主键排在第一位-->
        <id column="${field.name}" property="${field.propertyName}" />
</#if>
</#list>
<#list table.commonFields as field><#--生成公共字段 -->
        <result column="${field.name}" property="${field.propertyName}" />
</#list>
<#list table.fields as field>
<#if !field.keyFlag><#--生成普通字段 -->
        <result column="${field.name}" property="${field.propertyName}" />
</#if>
</#list>
    </resultMap>

</#if>
<#if baseColumnList>
    <!-- 通用查询结果列 -->
    <sql id="BaseColumnList">
<#list table.fields as field>
        ${field.name} as '${field.propertyName}'<#if field_has_next>,</#if>
</#list>

    </sql>
    <!-- 通用查询条件 -->
    <sql id="BaseWhere">
        <trim prefix="where" prefixOverrides="and | or">
        <#list table.fields as field>
           <#if field.propertyName ?lower_case ?contains("name")>
           <if test="${entity?uncap_first}ListVo.${field.propertyName} != null and ${entity?uncap_first}ListVo.${field.propertyName} != ''">
               and ${field.name} like concat('%',#${r"{"}${entity?uncap_first}ListVo.${field.propertyName}${r"}"},'%')
           </if>
           <#elseif field.propertyType == "Date" || field.propertyName ?lower_case?contains("date") >
           <if test="${entity?uncap_first}ListVo.${field.propertyName}Start != null and ${entity?uncap_first}ListVo.${field.propertyName}Start != ''">
               and ${field.name} >= #${r"{"}${entity?uncap_first}ListVo.${field.propertyName}Start${r"}"}
           </if>
           <if test="${entity?uncap_first}ListVo.${field.propertyName}End != null and ${entity?uncap_first}ListVo.${field.propertyName}End != ''">
               and ${field.name} &lt;= #${r"{"}${entity?uncap_first}ListVo.${field.propertyName}End${r"}"}
           </if>
           <#else>
           <if test="${entity?uncap_first}ListVo.${field.propertyName} != null and ${entity?uncap_first}ListVo.${field.propertyName} != ''">
               and ${field.name} = #${r"{"}${entity?uncap_first}ListVo.${field.propertyName}${r"}"}
           </if>
           </#if>
        </#list>
        </trim>
    </sql>

    <!-- 分页查询 -->
    <select id="selectPageList" resultType="${package.Other}.dto.${entity}ListDto">
        select <include refid="BaseColumnList"/>
        from ${table.name}
        <include refid="BaseWhere"/>

    </select>

</#if>


</mapper>
