package com.kk.kkautogenerator.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 代码模板分组明细新增实体
 * </p>
 *
 * @author kk
 * @since 2023-08-02
 */
@Data
@Schema( description = "代码模板分组明细")
public class CodeTemplateGroupSubAddVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 模板分组编号
    */
    @Schema(description ="模板分组编号")
    private Integer templateGroupId;
    /**
    * 模板编号
    */
    @Schema(description ="模板编号")
    private Integer templateId;
    /**
    * 模板类型
    */
    @Schema(description ="模板类型")
    private String templateType;


}
