package com.kk.kkautogenerator.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 代码模板分组明细删除实体
 * </p>
 *
 * @author kk
 * @since 2023-08-02
 */
@Data
@ApiModel(value = "代码模板分组明细删除实体", description = "代码模板分组明细")
public class CodeTemplateGroupSubDeleteVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 模板分组编号
    */
    @ApiModelProperty("模板分组编号")
    private Integer templateGroupId;
    /**
    * 模板编号
    */
    @ApiModelProperty("模板编号")
    private Integer templateId;


}
