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
 * 代码模板分组
 * </p>
 *
 * @author kk
 * @since 2023-08-02
 */
@Data
@TableName("code_template_group")
@Schema(description = "代码模板分组")
public class CodeTemplateGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 模板分组编号
    */
    @Schema(description ="模板分组编号")
    @TableId(value = "template_group_id", type = IdType.AUTO)
    private Integer templateGroupId;

    /**
    * 模板分组名称
    */
    @Schema(description ="模板分组名称")
    @TableField("template_group_name")
    private String templateGroupName;

    /**
    * 模板分组分类【sys：系统，per：个人】
    */
    @Schema(description ="模板分组分类【sys：系统，per：个人】")
    @TableField("template_group_class")
    private String templateGroupClass;

    /**
    * 模板分组创建人
    */
    @Schema(description ="模板分组创建人")
    @TableField("create_id")
    private String createId;

    /**
    * 模板语言【ftl、btl、vm】
    */
    @Schema(description ="模板语言【ftl、btl、vm】")
    @TableField("template_language")
    private String templateLanguage;

    /**
    * 创建时间
    */
    @Schema(description ="创建时间")
    @TableField("create_time")
    private Date createTime;


}
