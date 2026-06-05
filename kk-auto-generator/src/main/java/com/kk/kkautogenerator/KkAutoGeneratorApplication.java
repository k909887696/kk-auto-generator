package com.kk.kkautogenerator;

import com.github.jeffreyning.mybatisplus.conf.EnableMPP;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@EnableMPP
@ComponentScan(
    basePackages = {"com.kk.kkautogenerator", "com.kk.common"}
)
@MapperScan({"com.kk.kkautogenerator.dao.mapper"})
public class KkAutoGeneratorApplication {
    public static void main(String[] args) {
        SpringApplication.run(KkAutoGeneratorApplication.class, args);
    }
}
