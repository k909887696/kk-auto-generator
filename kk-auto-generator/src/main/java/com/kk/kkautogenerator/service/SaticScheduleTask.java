package com.kk.kkautogenerator.service;

import com.kk.kkautogenerator.config.SystemSettingConfig;
import com.kk.kkautogenerator.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import jakarta.annotation.Resource;
import java.time.LocalDateTime;

/**
 * created with IntelliJ IDEA.
 * description:
 * auth: kk
 * date: 2026-04-01
 */
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
@Slf4j
public class SaticScheduleTask {
    @Resource
    public SystemSettingConfig systemSettingConfig;
    /**
     * 定时清空临时文件，凌晨 0点 1点 2点
     */
    @Scheduled(cron = "0 0 1,2 * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {
        FileUtil.deleteFileAll(systemSettingConfig.getCustomCodeRootPath());
        log.info("定时清空临时文件执行完成: " + LocalDateTime.now());
    }
}