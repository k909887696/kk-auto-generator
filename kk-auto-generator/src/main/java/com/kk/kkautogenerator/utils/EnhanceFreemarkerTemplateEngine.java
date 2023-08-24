package com.kk.kkautogenerator.utils;

import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.kk.common.utils.FileUtil;
import com.kk.kkautogenerator.config.SystemSettingConfig;
import com.kk.kkautogenerator.constant.SysCodeTemplateType4FileUrl;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;

/**
 * @author：kk 2023/5/15
 * 自定义模板freemarker引擎
 */
@Component
public class EnhanceFreemarkerTemplateEngine extends AbstractTemplateEngine {

    @Resource
    public SystemSettingConfig systemSettingConfig;
    public EnhanceFreemarkerTemplateEngine() {
    }

    private Configuration configuration;

    @NotNull
    public EnhanceFreemarkerTemplateEngine init(@NotNull ConfigBuilder configBuilder) {
        this.configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        this.configuration.setDefaultEncoding(ConstVal.UTF8);
        File templateFile = new File( systemSettingConfig.getCustomTemplateRootPath());
        FileUtil.deleteFile(templateFile);
        try {
            MultiTemplateLoader multiTemplateLoader = new MultiTemplateLoader(new TemplateLoader[]{
                    new ClassTemplateLoader(EnhanceFreemarkerTemplateEngine.class, "/"),
                    new FileTemplateLoader(templateFile)
            });
            this.configuration.setTemplateLoader(multiTemplateLoader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        return this;
    }

    @NotNull
    public String templateFilePath(@NotNull String filePath) {
        return filePath + ".ftl";
    }


    /**
     * 重写自定义模板输出文件格式路劲
     *
     * @param customFile
     * @param tableInfo
     * @param objectMap
     */
    @Override
    protected void outputCustomFile(@NotNull Map<String, String> customFile, @NotNull TableInfo tableInfo, @NotNull Map<String, Object> objectMap) {
        String entityName = tableInfo.getEntityName();
        String otherPath = this.getPathInfo(OutputFile.other);
        logger.info(otherPath);
        customFile.forEach((key, value) -> {
            if (key.toLowerCase().contains(".js") || key.toLowerCase().contains(".vue") || key.toLowerCase().contains(".html") || key.toLowerCase().contains(".htm")) {
                String fileName = String.format( systemSettingConfig.getCustomCodeRootPath() + this.getConfigBuilder().getPackageConfig().getModuleName() +"/view" + File.separator + key, tableInfo.getName());
                this.outputFile(new File(fileName), objectMap, value);
            } else {
                String fileName = String.format(otherPath + File.separator + key, entityName);
                this.outputFile(new File(fileName), objectMap, value);
            }
        });
    }

    /**
     * 追加自定义实体映射
     *
     * @param config
     * @param tableInfo
     * @return
     */
    @Override
    public Map<String, Object> getObjectMap(@NotNull ConfigBuilder config, @NotNull TableInfo tableInfo) {
        // 获取实体类名字
        String entityName = tableInfo.getEntityName();
        // 获取object map
        Map<String, Object> objectMap = super.getObjectMap(config, tableInfo);//获取系统实体映射
        // 获取Other的盘符路径
        String otherPath = (String) ((Map<String, Object>) objectMap.get("package")).get("Other");
        // 自定义枚举
        List<String> modelList = CustomEnum.getModel();
        // 循环
        modelList.forEach(it -> {
            // 转小写
            String var = it.toLowerCase();
            // 存入object map
            objectMap.put(var, otherPath + "." + var + "." + entityName + it);
        });
        // 追加自定义实体映射
        // 自定义converter
        objectMap.put("commonConverter", "com.zhanghp.common.converter.CommonConverter");
        // converter utils
        objectMap.put("converterUtil", "com.zhanghp.common.converter.utils.ConverterUtil");
        // 分页
        objectMap.put("pageParent", "com.zhanghp.common.model.PageParent");
        // utils
        objectMap.put("objectUtils", "com.zhanghp.common.utils.ObjectUtils");
        // 返回结果封装
        objectMap.put("r", "com.zhanghp.common.response.R");
        int i = 0;
        for (TableField field : tableInfo.getFields()
        ) {
            if (field.isKeyFlag()) {
                i++;
                //System.out.println(tableInfo.getComment()+":"+i);
            }
        }

        objectMap.put("mppMultiIdCount", i);


        return objectMap;
    }

    public void writer(@NotNull Map<String, Object> objectMap, @NotNull String templatePath, @NotNull File outputFile) throws Exception {
        Template template = this.configuration.getTemplate(templatePath);
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
        Throwable var6 = null;

        try {
            template.process(objectMap, new OutputStreamWriter(fileOutputStream, ConstVal.UTF8));
        } catch (Throwable var15) {
            var6 = var15;
            throw var15;
        } finally {
            if (fileOutputStream != null) {
                if (var6 != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable var14) {
                        var6.addSuppressed(var14);
                    }
                } else {
                    fileOutputStream.close();
                }
            }

        }

    }

}
