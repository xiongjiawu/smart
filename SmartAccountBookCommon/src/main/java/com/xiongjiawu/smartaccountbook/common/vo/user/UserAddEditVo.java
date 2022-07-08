package com.xiongjiawu.smartaccountbook.common.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 系统-用户表
 * s_user
 */
@ApiModel(value = "用户信息新增编辑vo")
@Data
public class UserAddEditVo  {

   /**
    * id
    *
    * bigint(20)  [不可空]
    */
    @ApiModelProperty(value = "id(编辑时使用)")
    private Long id;

   /**
    * 用户名
    *
    * varchar(50)  [不可空]
    */
   @NotBlank(message = "用户名不能为空!")
   @ApiModelProperty(value = "用户名")
    private String userName;

   /**
    * 密码
    *
    * varchar(255)  [不可空]
    */
    @ApiModelProperty(value = "密码")
    private String password;

   /**
    * 真实姓名
    *
    * varchar(255)
    */
    @ApiModelProperty(value = "真实姓名")
    private String realName;

   /**
    * 手机号
    *
    * varchar(50)
    */
    @ApiModelProperty(value = "手机号")
    private String phone;

   /**
    * 总余额
    *
    * decimal(20,2)
    */
    @ApiModelProperty(value = "总余额")
    private BigDecimal totalbalance;

   /**
    * 备注
    *
    * varchar(500)
    */
    @ApiModelProperty(value = "备注")
    private String remark;


}
