package com.xiongjiawu.smartaccountbook.common.shiro;


import com.xiongjiawu.smartaccountbook.common.model.User;
import com.zhangzlyuyx.easy.shiro.ShiroPrincipal;
import com.zhangzlyuyx.easy.shiro.util.ShiroUtils;

/**
 * 公共用户主体信息
 *
 */
public class UserShiroPrincipal extends AbstractShiroPrincipal {

	private static final long serialVersionUID = 1035466927614991573L;
	
	/**
	 * 获取当前登录后台用户实例
	 * @return
	 */
	public static UserShiroPrincipal getInstance() {
		ShiroPrincipal shiroPrincipal = ShiroUtils.getShiroPrincipal();
		if(shiroPrincipal == null) {
			return null;
		}
		if(shiroPrincipal instanceof UserShiroPrincipal) {
			return (UserShiroPrincipal)shiroPrincipal;
		} else {
			return null;
		}
	}
	
	/**
	 * 用户信息
	 */
	private User user;
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
