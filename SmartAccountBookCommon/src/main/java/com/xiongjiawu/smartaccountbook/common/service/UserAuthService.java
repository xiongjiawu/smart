package com.xiongjiawu.smartaccountbook.common.service;

import com.xiongjiawu.smartaccountbook.common.model.UserAuth;
import com.zhangzlyuyx.easy.core.Result;

/**
 * 系统-用户认证表 服务接口
 * s_userauth
 */
public interface UserAuthService extends com.xiongjiawu.smartaccountbook.common.base.BaseService<UserAuth> {
    Result<UserAuth> saveUserAuth(UserAuth userAuth);

    Result<UserAuth> getUserAuth(String accessToken);

    Result<String> clearUserAuth(String accessToken);
}
