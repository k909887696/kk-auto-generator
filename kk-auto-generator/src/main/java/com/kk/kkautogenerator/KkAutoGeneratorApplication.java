/*    */ package com.kk.kkautogenerator;
/*    */ 
/*    */ import com.github.jeffreyning.mybatisplus.conf.EnableMPP;
/*    */ import org.mybatis.spring.annotation.MapperScan;
/*    */ import org.springframework.boot.SpringApplication;
/*    */ import org.springframework.boot.autoconfigure.SpringBootApplication;
/*    */ import org.springframework.context.annotation.ComponentScan;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpringBootApplication
/*    */ @EnableMPP
/*    */ @ComponentScan(basePackages = {"com.kk.kkautogenerator", "com.kk.common.web", "com.kk.common.dao", "com.kk.common.base"})
/*    */ @MapperScan({"com.kk.kkautogenerator.dao.mapper"})
/*    */ public class KkAutoGeneratorApplication
/*    */ {
/*    */   public static void main(String[] args) {
/* 21 */     SpringApplication.run(KkAutoGeneratorApplication.class, args);
/*    */   }
/*    */ }


/* Location:              E:\git\kk-auto-generator\target\classes\!\com\kk\kkautogenerator\KkAutoGeneratorApplication.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */