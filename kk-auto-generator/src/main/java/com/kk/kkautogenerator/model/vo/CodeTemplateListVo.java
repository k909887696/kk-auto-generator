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
@Schema( description = "代码模板")
public class CodeTemplateListVo extends BasePage{

    private static final long serialVersionUID = 1L;

    /**
    * 模板编号
    */
    @Schema(description ="模板编号")
    private Integer templateId;
    /**
    * 模板类型【entity、mapper、service、controller、serviceimpl、mapperxml、po、dto、detailsvo、listvo、addvo、editvo、deletevo、listvviewue、apijs、routerjs】
    */
    @Schema(description ="模板类型【entity、mapper、service、controller、serviceimpl、mapperxml、po、dto、detailsvo、listvo、addvo、editvo、deletevo、listvviewue、apijs、routerjs】")
    private String templateType;
    /**
    * 模板分类【sys：系统，per：个人】
    */
    @Schema(description ="模板分类【sys：系统，per：个人】")
    private String templateClass;
    /**
    * 模板内容
    */
    @Schema(description ="模板内容")
    private String templateContent;
    /**
    * 模板文件地址
    */
    @Schema(description ="模板文件地址")
    private String templateFileUrl;
    /**
    * 创建人
    */
    @Schema(description ="创建人")
    private String createId;
    /**
    * 创建时间开始
    */
    @Schema(description ="创建时间开始")
    private Date createTimeStart;
    /**
    * 创建时间结束
    */
    @Schema(description ="创建时间结束")
    private Date createTimeEnd;

    /**
    * 模板名称
    */
    @Schema(description ="模板名称")
    private String templateName;
    /**
    * 模板语言【ftl、btl、vm】
    */
    @Schema(description ="模板语言【ftl、btl、vm】")
    private String templateLanguage;


}
