package com.xiongjiawu.smartaccountbook.common.service.impl;

import com.xiongjiawu.smartaccountbook.common.enums.EnumStatus;
import com.zhangzlyuyx.easy.core.util.StringUtils;
import com.zhangzlyuyx.easy.mybatis.Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiongjiawu.smartaccountbook.common.dao.UserMapper;
import com.xiongjiawu.smartaccountbook.common.model.User;
import com.xiongjiawu.smartaccountbook.common.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统-用户表 服务接口
 * s_user
 */
@Service
public class UserServiceImpl extends com.xiongjiawu.smartaccountbook.common.base.BaseServiceImpl<User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    
    public UserServiceImpl() {
    
    }
    
    @Override
    public com.xiongjiawu.smartaccountbook.common.base.BaseMapper<User> getMapper() {
        return this.userMapper;
    }

    @Override
    public User getUserByUserName(String userName) {
        if (StringUtils.isEmpty(userName)) {
            return null;
        }
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put(User.column_userName, userName);
        queryMap.put(User.column_isValid, EnumStatus.valid.getKey());
        return this.selectFirst(queryMap, null);
    }

    @Override
    public List<User> getUsersByName(String name) {
        if (StringUtils.isBlank(name)) {
            return new ArrayList<>();
        }
        List<Condition> conditions = new ArrayList<>();
        conditions.add(new Condition(User.column_realName, "like", "%" + name + "%"));
        conditions.add(new Condition(User.column_isValid, EnumStatus.valid.getKey()));
        return selectByCondition(conditions);
    }


}
