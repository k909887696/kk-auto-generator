package com.kk.kkautogenerator.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class GenerateCodeDto {
    @Schema(description ="绝对路劲")
    private String absolutePath;
    @Schema(description ="相对路劲")
    private String relativePath;
    @Schema(description ="文件名称")
    private String fileName;

}

