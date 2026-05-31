package com.kk.kkautogenerator.dao.entity;

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
 * 代码模板
 * </p>
 *
 * @author kk
 * @since 2023-08-02
 */
@Data
@TableName("code_template")
@Schema( description = "代码模板")
public class CodeTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 模板编号
    */
    @Schema(description ="模板编号")
    @TableId(value = "template_id", type = IdType.AUTO)
    private Integer templateId;

    /**
    * 模板类型【entity、mapper、service、controller、serviceimpl、mapperxml、po、dto、detailsvo、listvo、addvo、editvo、deletevo、listvviewue、apijs、routerjs】
    */
    @Schema(description ="模板类型【entity、mapper、service、controller、serviceimpl、mapperxml、po、dto、detailsvo、listvo、addvo、editvo、deletevo、listvviewue、apijs、routerjs】")
    @TableField("template_type")
    private String templateType;

    /**
    * 模板分类【sys：系统，per：个人】
    */
    @Schema(description ="模板分类【sys：系统，per：个人】")
    @TableField("template_class")
    private String templateClass;

    /**
    * 模板内容
    */
    @Schema(description ="模板内容")
    @TableField("template_content")
    private String templateContent;

    /**
    * 模板文件地址
    */
    @Schema(description ="模板文件地址")
    @TableField("template_file_url")
    private String templateFileUrl;

    /**
    * 创建人
    */
    @Schema(description ="创建人")
    @TableField("create_id")
    private String createId;

    /**
    * 创建时间
    */
    @Schema(description ="创建时间")
    @TableField("create_time")
    private Date createTime;

    /**
    * 模板名称
    */
    @Schema(description ="模板名称")
    @TableField("template_name")
    private String templateName;

    /**
    * 模板语言【ftl、btl、vm】
    */
    @Schema(description ="模板语言【ftl、btl、vm】")
    @TableField("template_language")
    private String templateLanguage;


}
