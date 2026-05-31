package com.kk.kkautogenerator.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DbDataSource {
    @Schema(description ="db链接编号")
    private Integer dbConnectCode;
    @Schema(description ="db链接地址")
    private String dbConnectUrl;
    @Schema(description ="db链接名称")
    private String dbConnectName;
    @Schema(description ="db链接用户")
    private String dbConnectUser;

    @Schema(description ="db链接密码")
    private String dbConnectPassword;
    @Schema(description ="db链接端口")
    private String dbConnectPort;
    @Schema(description ="db链接数据库名")
    private String dbConnectDatabase;
    @Schema(description ="数据库类型，支持oracle、mysql、sqlserver2000、sqlserver、postgresql")
    private String dbConnectType;

}

