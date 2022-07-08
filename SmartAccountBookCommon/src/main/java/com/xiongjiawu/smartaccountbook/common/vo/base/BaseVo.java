package com.xiongjiawu.smartaccountbook.common.vo.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("vo")
public class BaseVo implements Serializable {

	private static final long serialVersionUID = -5709726275954017468L;

	@ApiModelProperty(value  = "响应代码(success:成功,error:失败,denied:用户认证失败)")
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@ApiModelProperty(value = "代码(200:成功,400:失败,401:认证失败,denied:拒绝)", dataType = "String")
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@ApiModelProperty(value = "响应消息")
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
