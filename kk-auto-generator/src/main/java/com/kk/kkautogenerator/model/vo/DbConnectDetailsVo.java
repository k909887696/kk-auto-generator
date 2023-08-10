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
 * 数据库链接查询详情实体
 * </p>
 *
 * @author kk
 * @since 2023-08-02
 */
@Data
@ApiModel(value = "数据库链接查询详情实体", description = "数据库链接")
public class DbConnectDetailsVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * db链接编号
    */
    @ApiModelProperty("db链接编号")
    private Integer dbConnectCode;


}
