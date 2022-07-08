package com.xiongjiawu.smartaccountbook.common.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 系统-用户表
 * s_user
 */
@Table(name = "s_user")
@ApiModel(value = "系统-用户表Entity")
public class User implements java.io.Serializable {
    private static final long serialVersionUID = -55519409737954659L;
    
    /** [属性] id  bigint(20)  [不可空] */
    public static final String property_id = "id";
    /** [列] id  bigint(20)  [不可空] */
    public static final String column_id = "id";
    /** [属性] 用户名  varchar(50)  [不可空] */
    public static final String property_userName = "userName";
    /** [列] 用户名  varchar(50)  [不可空] */
    public static final String column_userName = "userName";
    /** [属性] 密码  varchar(255)  [不可空] */
    public static final String property_password = "password";
    /** [列] 密码  varchar(255)  [不可空] */
    public static final String column_password = "password";
    /** [属性] 真实姓名  varchar(255) */
    public static final String property_realName = "realName";
    /** [列] 真实姓名  varchar(255) */
    public static final String column_realName = "realName";
    /** [属性] 手机号  varchar(50) */
    public static final String property_phone = "phone";
    /** [列] 手机号  varchar(50) */
    public static final String column_phone = "phone";
    /** [属性] 总余额  decimal(20,2) */
    public static final String property_totalbalance = "totalbalance";
    /** [列] 总余额  decimal(20,2) */
    public static final String column_totalbalance = "totalbalance";
    /** [属性] 备注  varchar(500) */
    public static final String property_remark = "remark";
    /** [列] 备注  varchar(500) */
    public static final String column_remark = "remark";
    /** [属性] 是否有效:0-无效,1-有效  int(11) */
    public static final String property_isValid = "isValid";
    /** [列] 是否有效:0-无效,1-有效  int(11) */
    public static final String column_isValid = "isValid";
    /** [属性] 创建人  varchar(20) */
    public static final String property_createUser = "createUser";
    /** [列] 创建人  varchar(20) */
    public static final String column_createUser = "createUser";
    /** [属性] 创建时间  timestamp  [不可空] */
    public static final String property_createTime = "createTime";
    /** [列] 创建时间  timestamp  [不可空] */
    public static final String column_createTime = "createTime";
    /** [属性] 更新人  varchar(20) */
    public static final String property_updateUser = "updateUser";
    /** [列] 更新人  varchar(20) */
    public static final String column_updateUser = "updateUser";
    /** [属性] 修改时间  timestamp  [不可空] */
    public static final String property_updateTime = "updateTime";
    /** [列] 修改时间  timestamp  [不可空] */
    public static final String column_updateTime = "updateTime";

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
    * 用户名
    *
    * varchar(50)  [不可空]
    */
    @Column(name = column_userName)
    @ApiModelProperty(value = "用户名")
    private String userName;

   /**
    * 密码
    *
    * varchar(255)  [不可空]
    */
    @Column(name = column_password)
    @ApiModelProperty(value = "密码")
    private String password;

   /**
    * 真实姓名
    *
    * varchar(255)
    */
    @Column(name = column_realName)
    @ApiModelProperty(value = "真实姓名")
    private String realName;

   /**
    * 手机号
    *
    * varchar(50)
    */
    @Column(name = column_phone)
    @ApiModelProperty(value = "手机号")
    private String phone;

   /**
    * 总余额
    *
    * decimal(20,2)
    */
    @Column(name = column_totalbalance)
    @ApiModelProperty(value = "总余额")
    private BigDecimal totalbalance;

   /**
    * 备注
    *
    * varchar(500)
    */
    @Column(name = column_remark)
    @ApiModelProperty(value = "备注")
    private String remark;

   /**
    * 是否有效:0-无效,1-有效
    *
    * int(11)
    * 默认值:1
    */
    @Column(name = column_isValid)
    @ApiModelProperty(value = "是否有效:0-无效,1-有效")
    private Integer isValid;

   /**
    * 创建人
    *
    * varchar(20)
    */
    @Column(name = column_createUser)
    @ApiModelProperty(value = "创建人")
    private String createUser;

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
    * 更新人
    *
    * varchar(20)
    */
    @Column(name = column_updateUser)
    @ApiModelProperty(value = "更新人")
    private String updateUser;

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
     * 获取 用户名     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置 用户名     
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }


    /**
     * 获取 密码     
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置 密码     
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * 获取 真实姓名     
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置 真实姓名     
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }


    /**
     * 获取 手机号     
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置 手机号     
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }


    /**
     * 获取 总余额     
     */
    public BigDecimal getTotalbalance() {
        return totalbalance;
    }

    /**
     * 设置 总余额     
     */
    public void setTotalbalance(BigDecimal totalbalance) {
        this.totalbalance = totalbalance;
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


    /**
     * 获取 是否有效:0-无效,1-有效     
     */
    public Integer getIsValid() {
        return isValid;
    }

    /**
     * 设置 是否有效:0-无效,1-有效     
     */
    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }


    /**
     * 获取 创建人     
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置 创建人     
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
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
     * 获取 更新人     
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置 更新人     
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
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

}
