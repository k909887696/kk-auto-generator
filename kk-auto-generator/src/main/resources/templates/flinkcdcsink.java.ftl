<#---------- insert sql ---------->
<#assign keycount = 0>
<#assign updatekeycount = 0>
INSERT INTO ${entity?replace("([a-z])([A-Z])", "$1_$2", "r")?lower_case} (
<#list table.fields as field><#if field_index==0>${field.propertyName?replace("([a-z])([A-Z])", "$1_$2", "r")?lower_case} <#else> ,${field.propertyName?replace("([a-z])([A-Z])", "$1_$2", "r")?lower_case}</#if></#list>
) values (<#list table.fields as field><#if field_index==0>?<#else>,?</#if></#list>) ON CONFLICT (<#list table.fields as field><#if field.keyFlag><#if keycount &gt; 0 >,</#if>${field.propertyName?replace("([a-z])([A-Z])", "$1_$2", "r")?lower_case}<#assign keycount = keycount +1></#if></#list>) DO UPDATE SET
<#list table.fields as field><#if !field.keyFlag><#if updatekeycount &gt; 0>,<#else></#if>${field.propertyName?replace("([a-z])([A-Z])", "$1_$2", "r")?lower_case}=EXCLUDED.${field.propertyName?replace("([a-z])([A-Z])", "$1_$2", "r")?lower_case}<#assign updatekeycount = updatekeycount +1></#if></#list>;
<#assign deletekeycount = 0>
<#---------- delete sql ---------->
DELETE FROM ${entity?replace("([a-z])([A-Z])", "$1_$2", "r")?lower_case} WHERE <#list table.fields as field><#if field.keyFlag><#if deletekeycount &gt; 0>,<#else></#if>${field.propertyName?replace("([a-z])([A-Z])", "$1_$2", "r")?lower_case}=?<#assign deletekeycount = deletekeycount +1></#if></#list>;

<#----------sink insert params ---------->

<#list table.fields as field>
    <#if field.propertyType == "boolean">
        <#assign getprefix="is"/>
    <#else>
        <#assign getprefix="get"/>
    </#if>
    <#if field.propertyType?lower_case=="string">
        ps.setString(${field_index+1}, data.${getprefix}${field.name?cap_first}());
    <#elseif field.propertyType?lower_case=="date">
        ps.setTimestamp(${field_index+1}, data.${getprefix}${field.name?cap_first}() ==null ? null : new Timestamp( data.${getprefix}${field.name?cap_first}().getTime() ));
    <#elseif field.propertyType?lower_case=="integer">
        ps.setInt(${field_index+1}, data.${getprefix}${field.name?cap_first}() == null ? 0 : data.${getprefix}${field.name?cap_first}());
    <#elseif field.propertyType?lower_case=="long">
        ps.setLong(${field_index+1}, data.${getprefix}${field.name?cap_first}() == null ? 0 : data.${getprefix}${field.name?cap_first}());
    <#elseif field.propertyType?lower_case=="double">
        ps.setDouble(${field_index+1}, data.${getprefix}${field.name?cap_first}() == null ? 0 : data.${getprefix}${field.name?cap_first}());
    <#elseif field.metaInfo.jdbcType?lower_case=="decimal">
        ps.setBigDecimal(${field_index+1}, data.${getprefix}${field.name?cap_first}() == null ? new BigDecimal(0) : data.${getprefix}${field.name?cap_first}());
    <#elseif field.metaInfo.jdbcType?lower_case=="bigint">
        ps.setLong(${field_index+1}, data.${getprefix}${field.name?cap_first}() == null ? 0 : data.${getprefix}${field.name?cap_first}());
    </#if>
    </#list>
