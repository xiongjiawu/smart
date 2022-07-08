package com.xiongjiawu.smartaccountbook.common.model;

import java.util.Date;
import javax.persistence.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 系统-用户认证表
 * s_userauth
 */
@Table(name = "s_userauth")
@ApiModel(value = "系统-用户认证表Entity")
public class UserAuth implements java.io.Serializable {
    private static final long serialVersionUID = -15815646347707687L;
    
    /** [属性] id  bigint(20)  [不可空] */
    public static final String property_id = "id";
    /** [列] id  bigint(20)  [不可空] */
    public static final String column_id = "id";
    /** [属性] 客户端类型  varchar(255)  [不可空] */
    public static final String property_clientType = "clientType";
    /** [列] 客户端类型  varchar(255)  [不可空] */
    public static final String column_clientType = "clientType";
    /** [属性] 用户ID  bigint(20) */
    public static final String property_userId = "userId";
    /** [列] 用户ID  bigint(20) */
    public static final String column_userId = "userId";
    /** [属性] 用户名  varchar(255)  [不可空] */
    public static final String property_username = "username";
    /** [列] 用户名  varchar(255)  [不可空] */
    public static final String column_username = "username";
    /** [属性] 用户密钥(base64字符)  varchar(255) */
    public static final String property_userSecret = "userSecret";
    /** [列] 用户密钥(base64字符)  varchar(255) */
    public static final String column_userSecret = "userSecret";
    /** [属性] 认证凭据  varchar(255)  [不可空] */
    public static final String property_accessToken = "accessToken";
    /** [列] 认证凭据  varchar(255)  [不可空] */
    public static final String column_accessToken = "accessToken";
    /** [属性] 认证有效期  datetime */
    public static final String property_tokenExpire = "tokenExpire";
    /** [列] 认证有效期  datetime */
    public static final String column_tokenExpire = "tokenExpire";
    /** [属性] 请求UA信息  varchar(1024) */
    public static final String property_userAgent = "userAgent";
    /** [列] 请求UA信息  varchar(1024) */
    public static final String column_userAgent = "userAgent";
    /** [属性] 客户端ip  varchar(255) */
    public static final String property_clientIp = "clientIp";
    /** [列] 客户端ip  varchar(255) */
    public static final String column_clientIp = "clientIp";
    /** [属性] 状态(1有效0无效)  int(11)  [不可空] */
    public static final String property_status = "status";
    /** [列] 状态(1有效0无效)  int(11)  [不可空] */
    public static final String column_status = "status";
    /** [属性] 创建人  varchar(50) */
    public static final String property_createBy = "createBy";
    /** [列] 创建人  varchar(50) */
    public static final String column_createBy = "createBy";
    /** [属性] 创建时间  timestamp  [不可空] */
    public static final String property_createTime = "createTime";
    /** [列] 创建时间  timestamp  [不可空] */
    public static final String column_createTime = "createTime";
    /** [属性] 修改人  varchar(50) */
    public static final String property_updateBy = "updateBy";
    /** [列] 修改人  varchar(50) */
    public static final String column_updateBy = "updateBy";
    /** [属性] 修改时间  timestamp  [不可空] */
    public static final String property_updateTime = "updateTime";
    /** [列] 修改时间  timestamp  [不可空] */
    public static final String column_updateTime = "updateTime";
    /** [属性] 备注  text */
    public static final String property_remark = "remark";
    /** [列] 备注  text */
    public static final String column_remark = "remark";

   /**
    * id
    *
    * bigint(20)  [不可空]
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = column_id)
    @ApiModelProperty(value = "id")
    private Long id;

   /**
    * 客户端类型
    *
    * varchar(255)  [不可空]
    * 默认值:'pc'
    */
    @Column(name = column_clientType)
    @ApiModelProperty(value = "客户端类型")
    private String clientType;

   /**
    * 用户ID
    *
    * bigint(20)
    */
    @Column(name = column_userId)
    @ApiModelProperty(value = "用户ID")
    private Long userId;

   /**
    * 用户名
    *
    * varchar(255)  [不可空]
    */
    @Column(name = column_username)
    @ApiModelProperty(value = "用户名")
    private String username;

   /**
    * 用户密钥(base64字符)
    *
    * varchar(255)
    */
    @Column(name = column_userSecret)
    @ApiModelProperty(value = "用户密钥(base64字符)")
    private String userSecret;

   /**
    * 认证凭据
    *
    * varchar(255)  [不可空]
    */
    @Column(name = column_accessToken)
    @ApiModelProperty(value = "认证凭据")
    private String accessToken;

   /**
    * 认证有效期
    *
    * datetime
    */
    @Column(name = column_tokenExpire)
    @ApiModelProperty(value = "认证有效期")
    private Date tokenExpire;

   /**
    * 请求UA信息
    *
    * varchar(1024)
    */
    @Column(name = column_userAgent)
    @ApiModelProperty(value = "请求UA信息")
    private String userAgent;

   /**
    * 客户端ip
    *
    * varchar(255)
    */
    @Column(name = column_clientIp)
    @ApiModelProperty(value = "客户端ip")
    private String clientIp;

   /**
    * 状态(1有效0无效)
    *
    * int(11)  [不可空]
    * 默认值:1
    */
    @Column(name = column_status)
    @ApiModelProperty(value = "状态(1有效0无效)")
    private Integer status;

   /**
    * 创建人
    *
    * varchar(50)
    */
    @Column(name = column_createBy)
    @ApiModelProperty(value = "创建人")
    private String createBy;

   /**
    * 创建时间
    *
    * timestamp  [不可空]
    * 默认值:'0000-00-00 00:00:00'
    */
    @Column(name = column_createTime)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

   /**
    * 修改人
    *
    * varchar(50)
    */
    @Column(name = column_updateBy)
    @ApiModelProperty(value = "修改人")
    private String updateBy;

   /**
    * 修改时间
    *
    * timestamp  [不可空]
    * 默认值:CURRENT_TIMESTAMP
    */
    @Column(name = column_updateTime)
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

   /**
    * 备注
    *
    * text
    */
    @Column(name = column_remark)
    @ApiModelProperty(value = "备注")
    private String remark;



    /**
     * 获取 id     
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 id     
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * 获取 客户端类型     
     */
    public String getClientType() {
        return clientType;
    }

    /**
     * 设置 客户端类型     
     */
    public void setClientType(String clientType) {
        this.clientType = clientType;
    }


    /**
     * 获取 用户ID     
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置 用户ID     
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }


    /**
     * 获取 用户名     
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置 用户名     
     */
    public void setUsername(String username) {
        this.username = username;
    }


    /**
     * 获取 用户密钥(base64字符)     
     */
    public String getUserSecret() {
        return userSecret;
    }

    /**
     * 设置 用户密钥(base64字符)     
     */
    public void setUserSecret(String userSecret) {
        this.userSecret = userSecret;
    }


    /**
     * 获取 认证凭据     
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * 设置 认证凭据     
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }


    /**
     * 获取 认证有效期     
     */
    public Date getTokenExpire() {
        return tokenExpire;
    }

    /**
     * 设置 认证有效期     
     */
    public void setTokenExpire(Date tokenExpire) {
        this.tokenExpire = tokenExpire;
    }


    /**
     * 获取 请求UA信息     
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * 设置 请求UA信息     
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }


    /**
     * 获取 客户端ip     
     */
    public String getClientIp() {
        return clientIp;
    }

    /**
     * 设置 客户端ip     
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }


    /**
     * 获取 状态(1有效0无效)     
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置 状态(1有效0无效)     
     */
    public void setStatus(Integer status) {
        this.status = status;
    }


    /**
     * 获取 创建人     
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 设置 创建人     
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }


    /**
     * 获取 创建时间     
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置 创建时间     
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    /**
     * 获取 修改人     
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * 设置 修改人     
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }


    /**
     * 获取 修改时间     
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置 修改时间     
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    /**
     * 获取 备注     
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注     
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

}
