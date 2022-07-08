package com.xiongjiawu.smartaccountbook.common.shiro;


import com.alibaba.fastjson.JSONObject;
import com.xiongjiawu.smartaccountbook.common.enums.EnumUserType;
import com.zhangzlyuyx.easy.shiro.ShiroToken;
import com.zhangzlyuyx.easy.shiro.authc.AccessToken;
import com.zhangzlyuyx.easy.shiro.filter.GeneralAuthenticationFilter;
import com.zhangzlyuyx.easy.shiro.util.ShiroUtils;
import com.zhangzlyuyx.easy.spring.util.SpringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class TokenAuthenticationFilter extends GeneralAuthenticationFilter {

	public TokenAuthenticationFilter() {
		this.setGroup(EnumUserType.jg.getKey());
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		if(!super.isAccessAllowed(request, response, mappedValue)) {
			return false;
		}
		ShiroToken token = ShiroUtils.getShiroToken();
		if(!token.getGroup().equalsIgnoreCase(this.getGroup())) {
			ShiroUtils.logout();
			return this.onLogout(token, request, response);
		}
		if(!this.isAccessAllowed(request, response, mappedValue, token)) {
			return false;
		}
		return true;
	}

	/**
	 * 登录成功
	 */
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
									 ServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug( "onLoginSuccess: {}", JSONObject.toJSONString(token));
		}

		//自动添加 cookie
		if(this.isAllowAccessTokenCookie() && this.getAccessTokenCookieExpires() > 0) {
			AccessToken accessToken = ShiroUtils.getAccessToken();
			if(accessToken != null) {
				Map<String, Cookie> cookieMap = SpringUtils.getCookies((HttpServletRequest)request);
				if (cookieMap == null) {
					cookieMap = new HashMap<>();
				}
				for(String accessTokenParam : this.getAccessTokenParams()) {
					if(!cookieMap.containsKey(accessTokenParam.toLowerCase())) {
						SpringUtils.addCookie((HttpServletResponse)response, accessTokenParam, accessToken.getAccessToken(), this.getAccessTokenCookieExpires(), "/", request.getServerName());
					}
				}
			}
		}

		//get请求处理
		if(SpringUtils.isGetMethod((HttpServletRequest)request)) {

			//if(!StringUtils.isEmpty(this.getSuccessUrl()) && !this.isSuccessRequest(request, response)) {
			if(!StringUtils.isEmpty(this.getSuccessUrl()) && this.isLoginRequest(request, response) && !this.isSuccessRequest(request, response)) {
				//重定向到成功url
				this.issueSuccessRedirect(request, response);
				//返回false, 表示请求已处理
				return false;
			}
		}

		//返回true, 表示请求需要继续处理
		return true;
	}

	/**
	 * 登录失败
	 */
	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
									 ServletResponse response) {

		return super.onLoginFailure(token, e, request, response);
	}

	@Override
	public String getAccessTokenValue(ServletRequest request) {
		String[] tokenParams = this.getAccessTokenParams();
		if(tokenParams == null || tokenParams.length == 0) {
			return null;
		}
		String accessToken = null;
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		//header读取
		if(accessToken == null && this.isAllowAccessTokenHeader()) {
			for(String tokenParam : tokenParams) {
				accessToken = SpringUtils.getHeader(httpRequest, tokenParam);
				if(!StringUtils.isEmpty(accessToken)) {
					break;
				}
			}

		}
		//cookie读取
		if(accessToken == null && this.isAllowAccessTokenCookie()) {
			for(String tokenParam : tokenParams) {
				Cookie cookie = SpringUtils.getCookie(httpRequest, tokenParam);
				if(cookie != null) {
					accessToken = cookie.getValue();
					if(!StringUtils.isEmpty(accessToken)) {
						break;
					}
				}
			}

		}
		//parameter 获取
		if(accessToken == null && this.isAllowAccessTokenUrlParam()){
			for(String tokenParam : tokenParams) {
				accessToken = SpringUtils.getParameter(httpRequest, tokenParam);
				if(!StringUtils.isEmpty(accessToken)) {
					break;
				}
			}
		}
		return accessToken;
	}
}
