package com.xiongjiawu.smartaccountbook.common.base;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 响应结果
 * @author Administrator
 *
 * @param <T>
 */
@ApiModel(value = "响应结果")
public class BaseResult<T> implements Serializable {

	private static final long serialVersionUID = 5615938291621955388L;

	public static final String CODE_SUCCESS = "success";

	public static final String CODE_ERROR = "error";


	@ApiModelProperty(value = "代码(200:成功,400:失败,401:认证失败,denied:拒绝)", dataType = "String")
	private int status;


	@ApiModelProperty(value  = "响应代码(success:成功,error:失败)")
	private String code;

	@ApiModelProperty(value = "响应消息")
	private String msg;

	@ApiModelProperty(value = "响应数据")
	private T data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public BaseResult(boolean code, String msg) {
		this.setCode(code ? "success" : "error");
		this.setStatus(code ? 200 : 400);
		this.setMsg(msg);
		this.setData(null);
	}

	public BaseResult(boolean code, String msg, T data) {
		this.setCode(code ? "success" : "error");
		this.setStatus(code ? 200 : 400);
		this.setMsg(msg);
		this.setData(data);
	}

	public BaseResult(int status, String msg) {
		this.setStatus(status);
		this.setMsg(msg);
		this.setData(null);
	}

	public BaseResult(String code, String msg, T data) {
		this.setCode(code);
		this.setMsg(msg);
		this.setData(data);
	}

	/**
	 * 是否为成功结果
	 */
	public boolean isSuccess() {
		return this.code != null && this.code.equalsIgnoreCase(CODE_SUCCESS);
	}


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this, SerializerFeature.WriteNullStringAsEmpty);
	}
}
