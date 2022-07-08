package com.xiongjiawu.smartaccountbook.common.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 收支记录
 * s_paylog
 */
@Table(name = "s_paylog")
@ApiModel(value = "收支记录Entity")
public class PayLog implements java.io.Serializable {
    private static final long serialVersionUID = 975467889760986590L;
    
    /** [属性] id  bigint(20)  [不可空] */
    public static final String property_id = "id";
    /** [列] id  bigint(20)  [不可空] */
    public static final String column_id = "id";
    /** [属性] 用户名  varchar(50)  [不可空] */
    public static final String property_userName = "userName";
    /** [列] 用户名  varchar(50)  [不可空] */
    public static final String column_userName = "userName";
    /** [属性] 钱包类型(基础数据: walletType)  int(11)  [不可空] */
    public static final String property_walletType = "walletType";
    /** [列] 钱包类型(基础数据: walletType)  int(11)  [不可空] */
    public static final String column_walletType = "walletType";
    /** [属性] 收支金额	  decimal(20,2) */
    public static final String property_payandincomeamount = "payandincomeamount";
    /** [列] 收支金额	  decimal(20,2) */
    public static final String column_payandincomeamount = "payandincomeamount";
    /** [属性] 余额  decimal(20,2) */
    public static final String property_balance = "balance";
    /** [列] 余额  decimal(20,2) */
    public static final String column_balance = "balance";
    /** [属性] 收支时间  datetime */
    public static final String property_payandincometime = "payandincometime";
    /** [列] 收支时间  datetime */
    public static final String column_payandincometime = "payandincometime";
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
    * 钱包类型(基础数据: walletType)
    *
    * int(11)  [不可空]
    */
    @Column(name = column_walletType)
    @ApiModelProperty(value = "钱包类型(基础数据: walletType)")
    private Integer walletType;

   /**
    * 收支金额	
    *
    * decimal(20,2)
    */
    @Column(name = column_payandincomeamount)
    @ApiModelProperty(value = "收支金额	")
    private BigDecimal payandincomeamount;

   /**
    * 余额
    *
    * decimal(20,2)
    */
    @Column(name = column_balance)
    @ApiModelProperty(value = "余额")
    private BigDecimal balance;

   /**
    * 收支时间
    *
    * datetime
    */
    @Column(name = column_payandincometime)
    @ApiModelProperty(value = "收支时间")
    private Date payandincometime;

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
     * 获取 钱包类型(基础数据: walletType)     
     */
    public Integer getWalletType() {
        return walletType;
    }

    /**
     * 设置 钱包类型(基础数据: walletType)     
     */
    public void setWalletType(Integer walletType) {
        this.walletType = walletType;
    }


    /**
     * 获取 收支金额	     
     */
    public BigDecimal getPayandincomeamount() {
        return payandincomeamount;
    }

    /**
     * 设置 收支金额	     
     */
    public void setPayandincomeamount(BigDecimal payandincomeamount) {
        this.payandincomeamount = payandincomeamount;
    }


    /**
     * 获取 余额     
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * 设置 余额     
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


    /**
     * 获取 收支时间     
     */
    public Date getPayandincometime() {
        return payandincometime;
    }

    /**
     * 设置 收支时间     
     */
    public void setPayandincometime(Date payandincometime) {
        this.payandincometime = payandincometime;
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
