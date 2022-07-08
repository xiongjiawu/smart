package com.xiongjiawu.smartaccountbook.common.dto.user;

import java.io.Serializable;

/**
 * 用户信息请求 bo
 *
 */
public class UserInfoReqDto implements Serializable {

	private static final long serialVersionUID = -8901300093292276344L;

	private Long userId;
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	private String userName;
	
	public String getUsername() {
		return userName;
	}
}
