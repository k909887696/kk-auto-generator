package com.kk.kkautogenerator.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DbDataSource {
    @ApiModelProperty("db链接编号")
    private Integer dbConnectCode;
    @ApiModelProperty("db链接地址")
    private String dbConnectUrl;
    @ApiModelProperty("db链接名称")
    private String dbConnectName;
    @ApiModelProperty("db链接用户")
    private String dbConnectUser;

    @ApiModelProperty("db链接密码")
    private String dbConnectPassword;
    @ApiModelProperty("db链接端口")
    private String dbConnectPort;
    @ApiModelProperty("db链接数据库名")
    private String dbConnectDatabase;
    @ApiModelProperty("数据库类型，支持oracle、mysql、sqlserver2000、sqlserver、postgresql")
    private String dbConnectType;

}

