package com.xiongjiawu.smartaccountbook.common.vo.user;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel("编辑用户信息")
@Data
public class UserUpdateInfoVo {
    @ApiModelProperty("id")
    @NotBlank(message = "id不能为空")
    private Long id;
    @ApiModelProperty("用户密码")
    private String addpassword;
    @ApiModelProperty("用户姓名")
    private String realname;
    @ApiModelProperty("电话号码")
    private String phone;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("状态(1启用0停用)")
    private String iaValid;

}
