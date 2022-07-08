package com.xiongjiawu.smartaccountbook.common.service;

import com.xiongjiawu.smartaccountbook.common.model.User;

import java.util.List;

/**
 * 系统-用户表 服务接口
 * s_user
 */
public interface UserService extends com.xiongjiawu.smartaccountbook.common.base.BaseService<User> {
    /**
     * 根据用户名获取用户信息
     * @param userName 用户名
     * @return
     */
    User getUserByUserName(String userName);

    List<User> getUsersByName(String name);
}
