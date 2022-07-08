package com.xiongjiawu.smartaccountbook.common.dto.user;


import com.xiongjiawu.smartaccountbook.common.enums.EnumClientType;

import java.io.Serializable;

/**
 * 用户登录请求 bo
 */
public class UserLoginReqDto implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4354542515635406530L;

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 密码
     */
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 客户端类型
     */
    private EnumClientType clientType;

    public EnumClientType getClientType() {
        return clientType;
    }

    public void setClientType(EnumClientType clientType) {
        this.clientType = clientType;
    }

    /**
     * 客户端ip
     */
    private String clientIp;

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    /**
     * UA信息
     */
    private String userAgent;

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
