package com.zjmzxfzhl.modules.app.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author 庄金明
 * @date 2020年3月24日
 */
@Data
public class AppRegisterForm {
    @NotBlank
    private String mobile;
    @NotBlank
    private String password;

}
