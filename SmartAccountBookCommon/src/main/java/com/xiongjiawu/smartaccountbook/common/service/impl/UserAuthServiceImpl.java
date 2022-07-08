package com.xiongjiawu.smartaccountbook.common.service.impl;

import com.xiongjiawu.smartaccountbook.common.enums.EnumClientType;
import com.zhangzlyuyx.easy.core.Result;
import com.zhangzlyuyx.easy.core.util.CryptoUtils;
import com.zhangzlyuyx.easy.core.util.DateUtils;
import com.zhangzlyuyx.easy.core.util.StringUtils;
import com.zhangzlyuyx.easy.mybatis.Condition;
import com.zhangzlyuyx.easy.shiro.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiongjiawu.smartaccountbook.common.dao.UserAuthMapper;
import com.xiongjiawu.smartaccountbook.common.model.UserAuth;
import com.xiongjiawu.smartaccountbook.common.service.UserAuthService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 系统-用户认证表 服务接口
 * s_userauth
 */
@Service
public class UserAuthServiceImpl extends com.xiongjiawu.smartaccountbook.common.base.BaseServiceImpl<UserAuth> implements UserAuthService {
    @Autowired
    private UserAuthMapper userAuthMapper;
    
    public UserAuthServiceImpl() {
    
    }
    
    @Override
    public com.xiongjiawu.smartaccountbook.common.base.BaseMapper<UserAuth> getMapper() {
        return this.userAuthMapper;
    }

    @Override
    public Result<UserAuth> saveUserAuth(UserAuth userAuth){
        if(userAuth == null) {
            return new Result<>(false, "传入的用户参数不能为空");
        }
        if(StringUtils.isEmpty(userAuth.getUsername())) {
            return new Result<>(false, "用户名不能为空!");
        }
        //clientType
        if(StringUtils.isEmpty(userAuth.getClientType())) {
            userAuth.setClientType(EnumClientType.pc.getKey());
        }
        //当前时间
        Date now = this.getDate();
        //作废历史记录
        UserAuth updateEntity = new UserAuth();
        updateEntity.setStatus(0);
        List<Condition> updateConditions = new ArrayList<Condition>();
        updateConditions.add(new Condition(UserAuth.property_clientType, userAuth.getClientType()));
        updateConditions.add(new Condition(UserAuth.property_username, userAuth.getUsername()));
        updateConditions.add(new Condition(UserAuth.property_status, 1));
        this.updateByCondition(updateEntity, updateConditions);

        //添加新的记录
        String jwtId = UUID.randomUUID().toString().replace("-", "");
        String base64Key = CryptoUtils.encodeBase64(UUID.randomUUID().toString().getBytes());//随机base64密钥
        String subject = userAuth.getRemark() != null ? userAuth.getRemark() : "";//主体信息
        long issuedAt = System.currentTimeMillis();
        long ttlMillis = 1000 * 60 * 60 * 24 * 7;
        String accessToken = JwtUtils.createJWT(jwtId, subject, "sunrise", issuedAt, ttlMillis, base64Key);
        userAuth.setId(null);
        userAuth.setUserSecret(base64Key);
        userAuth.setAccessToken(accessToken);
        userAuth.setTokenExpire(new Date(issuedAt + ttlMillis));
        userAuth.setStatus(1);
        userAuth.setCreateTime(now);
        this.insert(userAuth);
        return new Result<>(true, "请求成功", userAuth);
    }

    @Override
    public Result<UserAuth> getUserAuth(String accessToken){
        if(StringUtils.isEmpty(accessToken)) {
            return new Result<>(false, "无效的 accessToken");
        }
        //查询
        UserAuth userAuth = this.selectByUnique(UserAuth.property_accessToken, accessToken);
        if(userAuth == null) {
            return new Result<>(false, "无效的 accessToken");
        }
        //验证有效性
        if(userAuth.getStatus() == null || !userAuth.getStatus().equals(1)) {
            return new Result<>(false, "登录失效，请重新登录！");
        }
        if(userAuth.getTokenExpire() == null || userAuth.getTokenExpire().getTime() <= System.currentTimeMillis()) {
            return new Result<>(false, "登录过期，请重新登录！");
        }
        //jwt验证
        Result<Claims> ret = JwtUtils.validateJWT(accessToken, userAuth.getUserSecret());
        if(!ret.isSuccess()) {
            return new Result<>(false, "accessToken 签名校验失败");
        }
        return new Result<UserAuth>(true, "请求成功", userAuth);
    }

    @Override
    public Result<String> clearUserAuth(String accessToken) {
        if(StringUtils.isEmpty(accessToken)) {
            return new Result<>(false, "授权令牌不能为空!");
        }
        //查询授权记录
        UserAuth userAuth = this.selectByUnique(UserAuth.property_accessToken, accessToken);
        if(userAuth == null || !userAuth.getStatus().equals(1)) {
            return new Result<>(false, "无效的 accessToken");
        }
        //作废历史记录
        UserAuth updateEntity = new UserAuth();
        updateEntity.setStatus(0);
        List<Condition> updateConditions = new ArrayList<Condition>();
        updateConditions.add(new Condition(UserAuth.property_accessToken, accessToken));
        updateConditions.add(new Condition(UserAuth.property_status, 1));
        if(this.updateByCondition(updateEntity, updateConditions) <= 0) {
            return new Result<>(false, "请求失败");
        }
        //删除1个月内历史授权记录
        Date limitTime = DateUtils.addMonths(DateUtils.getDate(), -1);
        List<Condition> deleteCondition  = new ArrayList<>();
        deleteCondition.add(new Condition(UserAuth.property_clientType, userAuth.getClientType()));
        deleteCondition.add(new Condition(UserAuth.property_username, userAuth.getUsername()));
        deleteCondition.add(new Condition(UserAuth.property_status, 0));
        deleteCondition.add(new Condition(UserAuth.property_createTime, "<=", limitTime));
        this.deleteByCondition(deleteCondition);
        return new Result<>(true, "请求成功");
    }

}
