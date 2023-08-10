package com.kk.kkautogenerator.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * <p>
 * 数据库链接
 * </p>
 *
 * @author kk
 * @since 2023-08-02
 */
@Data
@TableName("db_connect")
@ApiModel(value = "数据库链接对象", description = "数据库链接")
public class DbConnect implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * db链接编号
    */
    @ApiModelProperty("db链接编号")
    @TableId(value = "db_connect_code", type = IdType.AUTO)
    private Integer dbConnectCode;

    /**
    * db链接地址
    */
    @ApiModelProperty("db链接地址")
    @TableField("db_connect_url")
    private String dbConnectUrl;

    /**
    * db链接名称
    */
    @ApiModelProperty("db链接名称")
    @TableField("db_connect_name")
    private String dbConnectName;

    /**
    * db链接用户
    */
    @ApiModelProperty("db链接用户")
    @TableField("db_connect_user")
    private String dbConnectUser;

    /**
    * db链接密码
    */
    @ApiModelProperty("db链接密码")
    @TableField("db_connect_password")
    private String dbConnectPassword;

    /**
    * db链接端口
    */
    @ApiModelProperty("db链接端口")
    @TableField("db_connect_port")
    private String dbConnectPort;

    /**
    * db链接数据库名
    */
    @ApiModelProperty("db链接数据库名")
    @TableField("db_connect_database")
    private String dbConnectDatabase;

    /**
    * 创建时间
    */
    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;

    /**
    * 创建人
    */
    @ApiModelProperty("创建人")
    @TableField("create_id")
    private String createId;

    /**
    * db链接数据库类型【mysql/mssq】
    */
    @ApiModelProperty("db链接数据库类型【mysql/mssq】")
    @TableField("db_connect_type")
    private String dbConnectType;


}
