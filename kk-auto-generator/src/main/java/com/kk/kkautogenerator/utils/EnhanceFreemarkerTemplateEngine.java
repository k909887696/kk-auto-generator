package com.kk.kkautogenerator.utils;

import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.kk.common.utils.FileUtil;
import com.kk.common.utils.JsonUtil;
import com.kk.kkautogenerator.config.SystemSettingConfig;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;

/**
 * @author：kk 2023/5/15
 * 自定义模板freemarker引擎 - 适配 MyBatis-Plus Generator 3.5.15+
 */
@Component
public class EnhanceFreemarkerTemplateEngine extends AbstractTemplateEngine {

    private static final Log logger = LogFactory.getLog(EnhanceFreemarkerTemplateEngine.class);

    @Resource
    public SystemSettingConfig systemSettingConfig;

    private Configuration configuration;

    public EnhanceFreemarkerTemplateEngine() {
    }

    @NotNull
    @Override
    public EnhanceFreemarkerTemplateEngine init(@NotNull ConfigBuilder configBuilder) {
        this.configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        this.configuration.setDefaultEncoding(ConstVal.UTF8);

        File templateFile = new File(systemSettingConfig.getCustomTemplateRootPath());
        if (!templateFile.exists()) {
            templateFile.mkdirs();
        }

        try {
            MultiTemplateLoader multiTemplateLoader = new MultiTemplateLoader(new TemplateLoader[]{
                    new ClassTemplateLoader(EnhanceFreemarkerTemplateEngine.class, "/"),
                    new FileTemplateLoader(templateFile)
            });
            this.configuration.setTemplateLoader(multiTemplateLoader);
        } catch (IOException e) {
            throw new RuntimeException("初始化 Freemarker 模板引擎失败", e);
        }

        return this;
    }

    @NotNull
    @Override
    public String templateFilePath(@NotNull String filePath) {
        return filePath + ".ftl";
    }

    /**
     * 重写自定义文件输出路径
     *
     * @param customFile 自定义文件映射
     * @param tableInfo 表信息
     * @param objectMap 对象映射
     */
    @Override
    protected void outputCustomFile(@NotNull List<CustomFile> customFile, @NotNull TableInfo tableInfo, @NotNull Map<String, Object> objectMap) {
        String entityName = tableInfo.getEntityName();
        String rootOutputFilePath = objectMap.getOrDefault("rootOutputFilePath", "").toString();

        if (logger.isDebugEnabled()) {
            logger.debug("rootOutputFilePath: " + rootOutputFilePath);
        }

        customFile.forEach((key) -> {
            if (logger.isDebugEnabled()) {
                logger.debug("outputCustomFile, key: " + JsonUtil.getJSONString( key) );
            }

            String fileName;

            fileName = String.format(rootOutputFilePath + File.separator + key.getFileName(), entityName);

            this.outputFile(new File(fileName), objectMap, key.getTemplatePath(),true);
        });
    }

    public void outputCustomFile(@NotNull List<CustomFile> customFile, @NotNull String className, @NotNull Map<String, Object> objectMap)
    {
        String entityName = className;

        customFile.forEach((key) -> {

            String fileName;

            fileName = String.format(key.getFileName(), entityName);

            this.outputFile(new File(fileName), objectMap, key.getTemplatePath(),true);
        });
    }


    /**
     * 追加自定义实体映射
     *
     * @param config 配置构建器
     * @param tableInfo 表信息
     * @return 对象映射
     */
    @Override
    public Map<String, Object> getObjectMap(@NotNull ConfigBuilder config, @NotNull TableInfo tableInfo) {
        String entityName = tableInfo.getEntityName();
        Map<String, Object> objectMap = super.getObjectMap(config, tableInfo);

        String otherPath = (String) ((Map<?, ?>) objectMap.get("package")).get("Other");

        List<String> modelList = CustomEnum.getModel();
        modelList.forEach(it -> {
            String var = it.toLowerCase();
            objectMap.put(var, otherPath + "." + var + "." + entityName + it);
        });

        objectMap.put("commonConverter", "com.zhanghp.common.converter.CommonConverter");
        objectMap.put("converterUtil", "com.zhanghp.common.converter.utils.ConverterUtil");
        objectMap.put("pageParent", "com.zhanghp.common.model.PageParent");
        objectMap.put("objectUtils", "com.zhanghp.common.utils.ObjectUtils");
        objectMap.put("r", "com.zhanghp.common.response.R");

        int multiIdCount = 0;
        for (TableField field : tableInfo.getFields()) {
            if (field.isKeyFlag()) {
                multiIdCount++;
            }
        }
        objectMap.put("mppMultiIdCount", multiIdCount);

        return objectMap;
    }

    @Override
    public void writer(@NotNull Map<String, Object> objectMap, @NotNull String templatePath, @NotNull File outputFile) throws Exception {
        Template template = this.configuration.getTemplate(templatePath);

        try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, ConstVal.UTF8)) {
            template.process(objectMap, outputStreamWriter);
        } catch (Exception e) {
            logger.error("模板写入失败: " + templatePath + " -> " + outputFile.getAbsolutePath(), e);
            throw e;
        }
    }

    @Override
    public AbstractTemplateEngine batchOutput() {
        return super.batchOutput();
    }

    @Override
    public String writer(@NotNull Map<String, Object> objectMap, @NotNull String templateName, @NotNull String templateString) throws Exception {
        return null;
    }
}
