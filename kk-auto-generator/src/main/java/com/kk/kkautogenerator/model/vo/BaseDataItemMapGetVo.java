package com.kk.kkautogenerator.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/*    */ import java.util.List;
/*    */ import javax.validation.constraints.NotEmpty;

@Data
public class BaseDataItemMapGetVo {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("查询类型：InvokeCycleType：调度类型周期，")
    @NotEmpty(message = "查询类型不能为空")
    private List<String> queryTypeList;

}

