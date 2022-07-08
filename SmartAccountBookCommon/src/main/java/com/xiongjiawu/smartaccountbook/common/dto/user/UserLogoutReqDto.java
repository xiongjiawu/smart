package com.xiongjiawu.smartaccountbook.common.dto.user;

import java.io.Serializable;

/**
 * 用户注销请求 bo
 *
 */
public class UserLogoutReqDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4354542515635406530L;

	private String accessToken;
	
	public String getAccessToken() {
		return accessToken;
	}
	
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}
