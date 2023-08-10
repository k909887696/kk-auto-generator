package com.kk.kkautogenerator.model.vo;

import com.kk.common.base.model.BasePage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "数据库表信息列表查询实体", description = "数据库表信息")
public class TablesInfoListVo extends BasePage {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("db链接编号")
    private Integer dbConnectCode;

    @ApiModelProperty("数据表名")
    private String tableName;
}