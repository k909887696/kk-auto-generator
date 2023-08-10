/*    */ package com.kk.kkautogenerator.model.dto;
/*    */ 
/*    */ import io.swagger.annotations.ApiModelProperty;
/*    */ import java.io.Serializable;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class BaseDataItemMapGetDto
/*    */   implements Serializable {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @ApiModelProperty("返回处理结果")
/*    */   private boolean value;
/*    */   @ApiModelProperty("响应实体集合")
/*    */   private Map<String, Map<String, Object>> queryTypeResult;
/*    */   
/*    */   public boolean isValue() {
/* 16 */     return this.value;
/*    */   }
/*    */   public void setValue(boolean value) {
/* 19 */     this.value = value;
/*    */   }
/*    */   public Map<String, Map<String, Object>> getQueryTypeResult() {
/* 22 */     return this.queryTypeResult;
/*    */   }
/*    */   public void setQueryTypeResult(Map<String, Map<String, Object>> queryTypeResult) {
/* 25 */     this.queryTypeResult = queryTypeResult;
/*    */   }
/*    */ }


/* Location:              E:\git\kk-auto-generator\target\classes\!\com\kk\kkautogenerator\model\dto\BaseDataItemMapGetDto.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */