package com.kk.kkautogenerator.model.dto;

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
 * 数据库链接列表返回实体
 * </p>
 *
 * @author kk
 * @since 2023-08-02
 */
@Data
@ApiModel(value = "数据库链接列表返回实体", description = "数据库链接")
public class DbConnectListDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * db链接编号
    */
    @ApiModelProperty("db链接编号")
    private Integer dbConnectCode;
    /**
    * db链接地址
    */
    @ApiModelProperty("db链接地址")
    private String dbConnectUrl;
    /**
    * db链接名称
    */
    @ApiModelProperty("db链接名称")
    private String dbConnectName;
    /**
    * db链接用户
    */
    @ApiModelProperty("db链接用户")
    private String dbConnectUser;
    /**
    * db链接密码
    */
    @ApiModelProperty("db链接密码")
    private String dbConnectPassword;
    /**
    * db链接端口
    */
    @ApiModelProperty("db链接端口")
    private String dbConnectPort;
    /**
    * db链接数据库名
    */
    @ApiModelProperty("db链接数据库名")
    private String dbConnectDatabase;
    /**
    * 创建时间
    */
    @ApiModelProperty("创建时间")
    private Date createTime;
    /**
    * 创建人
    */
    @ApiModelProperty("创建人")
    private String createId;
    /**
    * db链接数据库类型【mysql/mssq】
    */
    @ApiModelProperty("db链接数据库类型【mysql/mssq】")
    private String dbConnectType;


}
