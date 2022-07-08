package com.xiongjiawu.smartaccountbook.common.vo.user;


import com.xiongjiawu.smartaccountbook.common.dto.user.UserLoginRespDto;
import com.xiongjiawu.smartaccountbook.common.vo.base.BaseRespVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "用户登录返回Vo")
@Data
public class UserLoginRespVo extends BaseRespVo implements Serializable {

    private static final long serialVersionUID = -1847051820483866813L;

    @ApiModelProperty(value = "操作令牌")
    private String accessToken;


    @ApiModelProperty(value = "令牌过期时间")
    private Date tokenExpire;


    @ApiModelProperty(value = "用户ID")
    private Long userId;


    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "用户名称")
    private String realName;

    @ApiModelProperty(value = "总余额")
    private BigDecimal totalbalance;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "备注")
    private String remark;




    /**
     * @return
     */
    public static UserLoginRespVo parse(UserLoginRespDto respDto) {
        UserLoginRespVo respVo = new UserLoginRespVo();
        respVo.setAccessToken(respDto.getUserAuth().getAccessToken());
        respVo.setTokenExpire(respDto.getUserAuth().getTokenExpire());
        respVo.setUserId(respDto.getUser().getId());
        respVo.setUserName(respDto.getUser().getUserName());
        respVo.setRealName(respDto.getUser().getRealName());
        respVo.setPhone(respDto.getUser().getPhone());
        respVo.setTotalbalance(respDto.getUser().getTotalbalance());
        respVo.setRemark(respDto.getUser().getRemark());
        return respVo;
    }
}
