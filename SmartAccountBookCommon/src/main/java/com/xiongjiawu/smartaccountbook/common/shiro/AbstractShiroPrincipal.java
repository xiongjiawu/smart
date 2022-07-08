package com.xiongjiawu.smartaccountbook.common.shiro;


import com.xiongjiawu.smartaccountbook.common.enums.EnumUserType;
import com.zhangzlyuyx.easy.shiro.ShiroPrincipal;
import com.zhangzlyuyx.easy.shiro.util.ShiroUtils;

public abstract class AbstractShiroPrincipal extends ShiroPrincipal {

	private static final long serialVersionUID = -4125782473721429760L;
	
	/**
	 * 获取当前登录后台用户实例
	 * @return
	 */
	public static AbstractShiroPrincipal getInstance() {
		ShiroPrincipal shiroPrincipal = ShiroUtils.getShiroPrincipal();
		if(shiroPrincipal == null) {
			return null;
		}
		if(shiroPrincipal instanceof AbstractShiroPrincipal) {
			return (AbstractShiroPrincipal)shiroPrincipal;
		} else {
			return null;
		}
	}
	
	/**
	 * 获取当前用户类型
	 */
	private EnumUserType userType;
	
	public EnumUserType getUserType() {
		return this.userType;
	}
	
	public void setUserType(EnumUserType userType) {
		this.userType = userType;
	}

}
