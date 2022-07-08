package com.xiongjiawu.smartaccountbook.common.vo.user;

import com.xiongjiawu.smartaccountbook.common.model.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

/**
 * 系统-用户表
 * s_user
 */
@ApiModel(value = "用户信息新增编辑vo")
@Data
public class UserInfoVo {

   /**
    * id
    *
    * bigint(20)  [不可空]
    */
    @ApiModelProperty(value = "id")
    private Long id;

   /**
    * 用户名
    *
    * varchar(50)  [不可空]
    */
    @ApiModelProperty(value = "用户名")
    private String userName;


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


    public UserInfoVo() {
    }

    public UserInfoVo(User item) {
        BeanUtils.copyProperties(item, this);
    }
}
