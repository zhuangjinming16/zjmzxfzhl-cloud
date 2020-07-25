package com.zjmzxfzhl.modules.app.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author 庄金明
 * @date 2020年3月24日
 */
@Data
public class AppTransVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    private String transId;
    @NotBlank
    private String version;
    @NotBlank
    private String data;
    @NotBlank
    private String checkcode;
}
