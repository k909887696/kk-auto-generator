package com.kk.kkautogenerator.model.vo;

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
 * 代码模板分组查询详情实体
 * </p>
 *
 * @author kk
 * @since 2023-08-02
 */
@Data
@ApiModel(value = "代码模板分组查询详情实体", description = "代码模板分组")
public class CodeTemplateGroupDetailsVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 模板分组编号
    */
    @ApiModelProperty("模板分组编号")
    private Integer templateGroupId;


}
