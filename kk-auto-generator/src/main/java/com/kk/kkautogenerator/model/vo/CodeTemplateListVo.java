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
import com.kk.common.base.model.BasePage;
/**
 * <p>
 * 代码模板列表查询实体
 * </p>
 *
 * @author kk
 * @since 2023-08-02
 */
@Data
@ApiModel(value = "代码模板列表查询实体", description = "代码模板")
public class CodeTemplateListVo extends BasePage{

    private static final long serialVersionUID = 1L;

    /**
    * 模板编号
    */
    @ApiModelProperty("模板编号")
    private Integer templateId;
    /**
    * 模板类型【entity、mapper、service、controller、serviceimpl、mapperxml、po、dto、detailsvo、listvo、addvo、editvo、deletevo、listvviewue、apijs、routerjs】
    */
    @ApiModelProperty("模板类型【entity、mapper、service、controller、serviceimpl、mapperxml、po、dto、detailsvo、listvo、addvo、editvo、deletevo、listvviewue、apijs、routerjs】")
    private String templateType;
    /**
    * 模板分类【sys：系统，per：个人】
    */
    @ApiModelProperty("模板分类【sys：系统，per：个人】")
    private String templateClass;
    /**
    * 模板内容
    */
    @ApiModelProperty("模板内容")
    private String templateContent;
    /**
    * 模板文件地址
    */
    @ApiModelProperty("模板文件地址")
    private String templateFileUrl;
    /**
    * 创建人
    */
    @ApiModelProperty("创建人")
    private String createId;
    /**
    * 创建时间开始
    */
    @ApiModelProperty("创建时间开始")
    private Date createTimeStart;
    /**
    * 创建时间结束
    */
    @ApiModelProperty("创建时间结束")
    private Date createTimeEnd;

    /**
    * 模板名称
    */
    @ApiModelProperty("模板名称")
    private String templateName;
    /**
    * 模板语言【ftl、btl、vm】
    */
    @ApiModelProperty("模板语言【ftl、btl、vm】")
    private String templateLanguage;


}
