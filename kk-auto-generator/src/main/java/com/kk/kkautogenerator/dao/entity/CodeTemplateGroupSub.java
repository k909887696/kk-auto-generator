package com.kk.kkautogenerator.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
/**
 * <p>
 * 代码模板分组明细
 * </p>
 *
 * @author kk
 * @since 2023-08-02
 */
@Data
@TableName("code_template_group_sub")
@Schema( description = "代码模板分组明细")
public class CodeTemplateGroupSub implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 模板分组编号
    */
    @Schema(description ="模板分组编号")
    @MppMultiId("template_group_id")
    private Integer templateGroupId;

    /**
    * 模板编号
    */
    @Schema(description ="模板编号")
    @MppMultiId("template_id")
    private Integer templateId;

    /**
    * 模板类型
    */
    @Schema(description ="模板类型")
    @TableField("template_type")
    private String templateType;


}
