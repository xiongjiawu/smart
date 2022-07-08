package com.xiongjiawu.smartaccountbook.common.facade;


import com.xiongjiawu.smartaccountbook.common.dto.user.UserLoginReqDto;
import com.xiongjiawu.smartaccountbook.common.dto.user.UserLoginRespDto;
import com.xiongjiawu.smartaccountbook.common.dto.user.UserLogoutReqDto;
import com.xiongjiawu.smartaccountbook.common.dto.user.UserLogoutRespDto;
import com.xiongjiawu.smartaccountbook.common.model.User;
import com.xiongjiawu.smartaccountbook.common.model.UserAuth;
import com.zhangzlyuyx.easy.core.Result;

/**
 * 用户类业务封装
 *
 */
public interface UserFacade {
	
	/**
	 * 保存用户登录信息
	 * @param loginReqDto
	 * @return
	 */
	Result<UserLoginRespDto> saveUserLogin(UserLoginReqDto loginReqDto);
	
	/**
	 * 保存用户注销信息
	 * @param logoutReqDto
	 * @return
	 */
	Result<UserLogoutRespDto> saveUserLogout(UserLogoutReqDto logoutReqDto);
	
	/**
     * 获取用户登录信息
     * @param accessToken 操作令牌
     * @return
     */
    Result<UserLoginRespDto> getUserLoginByAccessToken(String accessToken);
    
    /**
     * 获取用户登录信息
     * @param userName 用户名
     * @return
     */
    Result<UserLoginRespDto> getUserLoginByUserName(String userName);
    
    /**
     * 获取用户登录信息
     * @param user 用户信息
     * @return
     */
    Result<UserLoginRespDto> getUserLogin(User user);
    
    /**
     * 获取用户认证信息
     * @param accessToken
     * @return
     */
    Result<UserAuth> getUserAuth(String accessToken);
}
