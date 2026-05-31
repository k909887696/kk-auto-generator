package com.kk.kkautogenerator.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
/*    */ import java.util.List;


@Data
public class BaseDataItemMapGetVo {
    private static final long serialVersionUID = 1L;
    @Schema(description = "查询类型：InvokeCycleType：调度类型周期，")
    @NotEmpty(message = "查询类型不能为空")
    private List<String> queryTypeList;

}

