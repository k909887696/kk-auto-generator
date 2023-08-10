package com.kk.kkautogenerator.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("system-setting-config")
public class SystemSettingConfig {

    /**
     * 自动模板保存路劲（不带 /开头为程序运行根目录）
     */
    @Value("${custom-template-root-path:template/}")
    private  String customTemplateRootPath;
    /**
     * 代码生成保存文件根目录
     */
    @Value("${custom-code-root-path:code/}")
    private  String customCodeRootPath;

    @Value("${auth:kk}")
    private  String auth;

    @Value("${package-name:com.kk}")
    private  String packageName;

    @Value("${module-name:xxx}")
    private  String moduleName;





}
