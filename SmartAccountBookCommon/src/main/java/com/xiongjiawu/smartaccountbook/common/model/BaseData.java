package com.xiongjiawu.smartaccountbook.common.model;

import java.util.Date;
import javax.persistence.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 基础数据表
 * s_basedata
 */
@Table(name = "s_basedata")
@ApiModel(value = "基础数据表Entity")
public class BaseData implements java.io.Serializable {
    private static final long serialVersionUID = 177932266009759117L;
    
    /** [属性] id  bigint(11)  [不可空] */
    public static final String property_id = "id";
    /** [列] id  bigint(11)  [不可空] */
    public static final String column_id = "id";
    /** [属性] 父节点编码  varchar(50) */
    public static final String property_parNode = "parNode";
    /** [列] 父节点编码  varchar(50) */
    public static final String column_parNode = "parNode";
    /** [属性] 项目编码  varchar(50) */
    public static final String property_proCode = "proCode";
    /** [列] 项目编码  varchar(50) */
    public static final String column_proCode = "proCode";
    /** [属性] 项目名称  varchar(255) */
    public static final String property_proName = "proName";
    /** [列] 项目名称  varchar(255) */
    public static final String column_proName = "proName";
    /** [属性] 排序  int(11) */
    public static final String property_sorting = "sorting";
    /** [列] 排序  int(11) */
    public static final String column_sorting = "sorting";
    /** [属性] 备注  varchar(255) */
    public static final String property_memo = "memo";
    /** [列] 备注  varchar(255) */
    public static final String column_memo = "memo";
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
    * bigint(11)  [不可空]
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = column_id)
    @ApiModelProperty(value = "id")
    private Long id;

   /**
    * 父节点编码
    *
    * varchar(50)
    */
    @Column(name = column_parNode)
    @ApiModelProperty(value = "父节点编码")
    private String parNode;

   /**
    * 项目编码
    *
    * varchar(50)
    */
    @Column(name = column_proCode)
    @ApiModelProperty(value = "项目编码")
    private String proCode;

   /**
    * 项目名称
    *
    * varchar(255)
    */
    @Column(name = column_proName)
    @ApiModelProperty(value = "项目名称")
    private String proName;

   /**
    * 排序
    *
    * int(11)
    */
    @Column(name = column_sorting)
    @ApiModelProperty(value = "排序")
    private Integer sorting;

   /**
    * 备注
    *
    * varchar(255)
    */
    @Column(name = column_memo)
    @ApiModelProperty(value = "备注")
    private String memo;

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
     * 获取 父节点编码     
     */
    public String getParNode() {
        return parNode;
    }

    /**
     * 设置 父节点编码     
     */
    public void setParNode(String parNode) {
        this.parNode = parNode;
    }


    /**
     * 获取 项目编码     
     */
    public String getProCode() {
        return proCode;
    }

    /**
     * 设置 项目编码     
     */
    public void setProCode(String proCode) {
        this.proCode = proCode;
    }


    /**
     * 获取 项目名称     
     */
    public String getProName() {
        return proName;
    }

    /**
     * 设置 项目名称     
     */
    public void setProName(String proName) {
        this.proName = proName;
    }


    /**
     * 获取 排序     
     */
    public Integer getSorting() {
        return sorting;
    }

    /**
     * 设置 排序     
     */
    public void setSorting(Integer sorting) {
        this.sorting = sorting;
    }


    /**
     * 获取 备注     
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 设置 备注     
     */
    public void setMemo(String memo) {
        this.memo = memo;
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
