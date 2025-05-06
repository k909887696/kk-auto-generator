<#-- ----------  BEGIN 字段循环遍历  ---------->
<#assign keycount = 0>
<#assign updatekeycount = 0>
CREATE TABLE ${entity?replace("([a-z])([A-Z])", "$1_$2", "r")?lower_case} (
<#list table.fields as field>

    <#if field.keyFlag>
        ${field.propertyName?replace("([a-z])([A-Z])", "$1_$2", "r")?lower_case}  <#if field.type?lower_case == "nvarchar" || field.type?lower_case == "varchar">varchar(${field.metaInfo.length?replace(",","")}) <#else> ${field.type} </#if> not null,
    <#else>
       ${field.propertyName?replace("([a-z])([A-Z])", "$1_$2", "r")?lower_case} <#if field.type?lower_case == "nvarchar" || field.type?lower_case == "varchar" ><#if field.metaInfo.length &gt; 4000 >text<#else>varchar(${field.metaInfo.length?replace(",","")})</#if><#elseif field.type?lower_case == "text" || field.type?lower_case =="longtext">text <#elseif field.type?lower_case == "datetime">timestamp<#elseif field.propertyType?lower_case == "integer">int<#elseif field.metaInfo.jdbcType?lower_case == "bit">bit<#elseif field.metaInfo.jdbcType?lower_case == "bigint">bigint<#elseif field.type?lower_case == "decimal">decimal(${field.metaInfo.length?replace(",","")},${field.metaInfo.scale})<#else> ${field.type} </#if>  null,
    </#if>
</#list>
CONSTRAINT ${entity?replace("([a-z])([A-Z])", "$1_$2", "r")?lower_case}_pk PRIMARY KEY (<#list table.fields as field><#if field.keyFlag><#if keycount &gt; 0 >,</#if> ${field.propertyName?replace("([a-z])([A-Z])", "$1_$2", "r")?lower_case}<#assign keycount = keycount +1></#if></#list>)
);
<#------------  END 字段循环遍历  ---------->
COMMENT ON TABLE ${entity?replace("([a-z])([A-Z])", "$1_$2", "r")?lower_case} IS '${table.comment!}';

<#list table.fields as field>
    COMMENT ON COLUMN ${entity?replace("([a-z])([A-Z])", "$1_$2", "r")?lower_case}.${field.propertyName?replace("([a-z])([A-Z])", "$1_$2", "r")?lower_case} IS '${field.comment}';
</#list>



