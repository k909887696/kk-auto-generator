package com.kk.kkautogenerator.model.vo;

import com.kk.common.base.model.BasePage;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "数据库表信息列表查询实体")
public class TablesInfoListVo extends BasePage {
    private static final long serialVersionUID = 1L;

    @Schema(description ="db链接编号")
    private Integer dbConnectCode;

    @Schema(description ="数据表名")
    private String tableName;
}