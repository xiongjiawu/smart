package com.xiongjiawu.smartaccountbook.common.vo.user;


import com.xiongjiawu.smartaccountbook.common.dto.user.UserLoginReqDto;
import com.xiongjiawu.smartaccountbook.common.vo.base.BaseReqVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "用户登录请求Vo")
public class UserLoginReqVo extends BaseReqVo implements Serializable {

	private static final long serialVersionUID = 6254811934574706885L;

	@ApiModelProperty(value = "用户名")
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@ApiModelProperty(value = "密码")
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@ApiModelProperty(value = "客户端类型(pc/app)", example = "pc")
	private String clientType;

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	/**
	 *
	 * @return
	 */
	public UserLoginReqDto toUserLoginReqDto() {
		UserLoginReqDto loginReqDto = new UserLoginReqDto();
		loginReqDto.setUserName(this.getUserName());
		loginReqDto.setPassword(this.getPassword());
		return loginReqDto;
	}
}
