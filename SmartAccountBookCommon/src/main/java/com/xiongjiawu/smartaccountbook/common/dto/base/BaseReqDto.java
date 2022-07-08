package com.xiongjiawu.smartaccountbook.common.dto.base;


import com.xiongjiawu.smartaccountbook.common.shiro.UserShiroPrincipal;

import java.io.Serializable;

/**
 * 请求 bo 基类
 *
 */
public class BaseReqDto implements Serializable {

	private static final long serialVersionUID = -4704436847564523298L;

	/**
	 * 获取 shiro 用户
	 */
	private UserShiroPrincipal shiroUser;
	
	public UserShiroPrincipal getShiroUser() {
		if(this.shiroUser == null) {
			this.shiroUser = UserShiroPrincipal.getInstance();
		}
		return this.shiroUser;
	}
	
	public void setShiroUser(UserShiroPrincipal shiroUser) {
		this.shiroUser = shiroUser;
	}
}
