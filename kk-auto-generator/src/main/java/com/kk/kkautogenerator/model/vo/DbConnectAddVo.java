package com.kk.kkautogenerator.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 数据库链接新增实体
 * </p>
 *
 * @author kk
 * @since 2023-08-02
 */
@Data
@Schema(  description = "数据库链接")
public class DbConnectAddVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * db链接编号
    */
    @Schema(description ="db链接编号")
    private Integer dbConnectCode;
    /**
    * db链接地址
    */
    @Schema(description ="db链接地址")
    private String dbConnectUrl;
    /**
    * db链接名称
    */
    @Schema(description ="db链接名称")
    private String dbConnectName;
    /**
    * db链接用户
    */
    @Schema(description ="db链接用户")
    private String dbConnectUser;
    /**
    * db链接密码
    */
    @Schema(description ="db链接密码")
    private String dbConnectPassword;
    /**
    * db链接端口
    */
    @Schema(description ="db链接端口")
    private String dbConnectPort;
    /**
    * db链接数据库名
    */
    @Schema(description ="db链接数据库名")
    private String dbConnectDatabase;
    /**
    * 创建时间
    */
    @Schema(description ="创建时间")
    private Date createTime;
    /**
    * 创建人
    */
    @Schema(description ="创建人")
    private String createId;
    /**
    * db链接数据库类型【mysql/mssq】
    */
    @Schema(description ="db链接数据库类型【mysql/mssq】")
    private String dbConnectType;
    /**
     * 模块名称
     */
    @Schema(description ="模块名称")
    private String moduleName;

}
