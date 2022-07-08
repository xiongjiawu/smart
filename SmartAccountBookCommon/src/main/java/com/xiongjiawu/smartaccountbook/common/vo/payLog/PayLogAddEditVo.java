package com.xiongjiawu.smartaccountbook.common.vo.payLog;

import com.xiongjiawu.smartaccountbook.common.annotation.TranslationDict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@ApiModel(value = "收支记录新增编辑vo")
@Data
public class PayLogAddEditVo {

   /**
    * id
    *
    * bigint(20)  [不可空]
    */
    @ApiModelProperty(value = "id")
    private Long id;


   /**
    * 钱包类型(基础数据: walletType)
    *
    * int(11)  [不可空]
    */
    @ApiModelProperty(value = "钱包类型(基础数据: walletType)")
    private Integer walletType;

    @TranslationDict(fieldType = "walletType")
    @ApiModelProperty(value = "钱包类型描述")
    private Integer walletTypeDesc;

   /**
    * 收支金额	
    *
    * decimal(20,2)
    */
    @ApiModelProperty(value = "收支金额	")
    private BigDecimal payandincomeamount;

   /**
    * 余额
    *
    * decimal(20,2)
    */
    @ApiModelProperty(value = "余额")
    private BigDecimal balance;

   /**
    * 收支时间
    *
    * datetime
    */
    @ApiModelProperty(value = "收支时间")
    private Date payandincometime;

   /**
    * 备注
    *
    * varchar(500)
    */
    @ApiModelProperty(value = "备注")
    private String remark;


}
