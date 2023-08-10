package com.kk.kkautogenerator.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GenerateCodeDto {
    @ApiModelProperty("绝对路劲")
    private String absolutePath;
    @ApiModelProperty("相对路劲")
    private String relativePath;
    @ApiModelProperty("文件名称")
    private String fileName;

}

