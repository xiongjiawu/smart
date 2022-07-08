package com.xiongjiawu.smartaccountbook.common.facade.impl;


import com.xiongjiawu.smartaccountbook.common.dto.user.UserLoginReqDto;
import com.xiongjiawu.smartaccountbook.common.dto.user.UserLoginRespDto;
import com.xiongjiawu.smartaccountbook.common.dto.user.UserLogoutReqDto;
import com.xiongjiawu.smartaccountbook.common.dto.user.UserLogoutRespDto;
import com.xiongjiawu.smartaccountbook.common.enums.EnumStatus;
import com.xiongjiawu.smartaccountbook.common.facade.UserFacade;
import com.xiongjiawu.smartaccountbook.common.model.User;
import com.xiongjiawu.smartaccountbook.common.model.UserAuth;
import com.xiongjiawu.smartaccountbook.common.service.UserAuthService;
import com.xiongjiawu.smartaccountbook.common.service.UserService;
import com.zhangzlyuyx.easy.core.Result;
import com.zhangzlyuyx.easy.core.util.CryptoUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthService userAuthService;


    @Override
    public Result<UserLoginRespDto> saveUserLogin(UserLoginReqDto loginReqDto) {
        //验证用户名
        if (StringUtils.isEmpty(loginReqDto.getUserName())) {
            return new Result<>(false, "请输入用户名!");
        }
        String password = loginReqDto.getPassword();
        if (password.length() != 32) {
            password = CryptoUtils.encodeMd5(password);
        }
        Map<String, Object> params = new HashMap<>();
        params.put(User.column_userName, loginReqDto.getUserName());
        params.put(User.column_password, password);
        params.put(User.column_isValid, EnumStatus.valid.getKey());
        User user = userService.selectFirst(params, null);
        if (user == null) {
            return new Result<>(false, "用户名或密码错误!");
        }

        //getUserLogin
        Result<UserLoginRespDto> retRespDto = this.getUserLogin(user);
        if (!retRespDto.isSuccess()) {
            return new Result<>(false, retRespDto.getMsg());
        }
        //respBo
        UserLoginRespDto loginRespDto = retRespDto.getData();
        //保存认证信息
        UserAuth userAuth = new UserAuth();
        userAuth.setUserId(user.getId());
        userAuth.setUsername(user.getUserName());
        userAuth.setClientType(loginReqDto.getClientType().getKey());
        userAuth.setClientIp(loginReqDto.getClientIp());
        userAuth.setUserAgent(loginReqDto.getUserAgent());
        Result<UserAuth> retAuth = this.userAuthService.saveUserAuth(userAuth);
        if (!retAuth.isSuccess()) {
            return new Result<>(false, retAuth.getMsg());
        }

        loginRespDto.setUserAuth(retAuth.getData());
        return new Result<UserLoginRespDto>(true, "登录成功", loginRespDto);
    }

    @Override
    public Result<UserLogoutRespDto> saveUserLogout(UserLogoutReqDto logoutReqDto) {
        //清除认证信息
        Result<String> retClear = this.userAuthService.clearUserAuth(logoutReqDto.getAccessToken());
        if (!retClear.isSuccess()) {
            return new Result<>(false, retClear.getMsg());
        }
        return new Result<UserLogoutRespDto>(true, retClear.getMsg());
    }

    @Override
    public Result<UserLoginRespDto> getUserLoginByAccessToken(String accessToken) {
        //auth
        Result<UserAuth> retAuth = this.userAuthService.getUserAuth(accessToken);
        if (!retAuth.isSuccess()) {
            return new Result<>(false, retAuth.getMsg());
        }
        Result<UserLoginRespDto> retLogin = this.getUserLoginByUserName(retAuth.getData().getUsername());
        if (!retLogin.isSuccess()) {
            return new Result<>(false, retLogin.getMsg());
        }
        retLogin.getData().setUserAuth(retAuth.getData());
        return retLogin;
    }

    @Override
    public Result<UserLoginRespDto> getUserLogin(User user) {
        if (user == null) {
            return new Result<>(false, "获取用户信息失败!");
        }
        //验证用户状态
        if (!EnumStatus.valid.getKey().equals(user.getIsValid())) {
            return new Result<>(false, "该用户未启用!");
        }
        //respBo
        UserLoginRespDto loginRespDto = new UserLoginRespDto();
        loginRespDto.setUser(user);
        //return
        return new Result<>(true, "", loginRespDto);
    }

    @Override
    public Result<UserAuth> getUserAuth(String accessToken) {
        Result<UserAuth> retUserAuth = this.userAuthService.getUserAuth(accessToken);
        return retUserAuth;
    }

    @Override
    public Result<UserLoginRespDto> getUserLoginByUserName(String userName) {
        User user = this.userService.getUserByUserName(userName);
        return this.getUserLogin(user);
    }

}
