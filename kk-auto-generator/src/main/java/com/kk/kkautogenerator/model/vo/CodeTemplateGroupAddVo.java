package com.kk.kkautogenerator.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.kk.kkautogenerator.dao.entity.CodeTemplateGroupSub;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 代码模板分组新增实体
 * </p>
 *
 * @author kk
 * @since 2023-08-02
 */
@Data
@ApiModel(value = "代码模板分组新增实体", description = "代码模板分组")
public class CodeTemplateGroupAddVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 模板分组编号
    */
    @ApiModelProperty("模板分组编号")
    private Integer templateGroupId;
    /**
    * 模板分组名称
    */
    @ApiModelProperty("模板分组名称")
    private String templateGroupName;
    /**
    * 模板分组分类【sys：系统，per：个人】
    */
    @ApiModelProperty("模板分组分类【sys：系统，per：个人】")
    private String templateGroupClass;
    /**
    * 模板分组创建人
    */
    @ApiModelProperty("模板分组创建人")
    private String createId;
    /**
    * 模板语言【ftl、btl、vm】
    */
    @ApiModelProperty("模板语言【ftl、btl、vm】")
    private String templateLanguage;
    /**
    * 创建时间
    */
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("模板明细列表")
    private List<CodeTemplateGroupSub> codeTemplateGroupSubList;


}
