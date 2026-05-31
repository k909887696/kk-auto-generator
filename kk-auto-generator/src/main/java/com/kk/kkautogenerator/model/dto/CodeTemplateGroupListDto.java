package com.kk.kkautogenerator.model.dto;

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
 * 代码模板分组列表返回实体
 * </p>
 *
 * @author kk
 * @since 2023-08-02
 */
@Data
@Schema( description = "代码模板分组列表返回实体")
public class CodeTemplateGroupListDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 模板分组编号
    */
    @Schema(description ="模板分组编号")
    private Integer templateGroupId;
    /**
    * 模板分组名称
    */
    @Schema(description ="模板分组名称")
    private String templateGroupName;
    /**
    * 模板分组分类【sys：系统，per：个人】
    */
    @Schema(description ="模板分组分类【sys：系统，per：个人】")
    private String templateGroupClass;
    /**
    * 模板分组创建人
    */
    @Schema(description ="模板分组创建人")
    private String createId;
    /**
    * 模板语言【ftl、btl、vm】
    */
    @Schema(description ="模板语言【ftl、btl、vm】")
    private String templateLanguage;
    /**
    * 创建时间
    */
    @Schema(description ="创建时间")
    private Date createTime;


}
