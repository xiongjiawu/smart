package com.xiongjiawu.smartaccountbook.common.vo.base;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

@ApiModel(value = "map结果vo")
public class BaseMapResultVo<T> extends BaseVo {

	private static final long serialVersionUID = 7945405640752951709L;

	@ApiModelProperty(value = "数据")
	private Map<String,Object> data;

	public Map<String,Object> getData() {
		return data;
	}

	public void setData(Map<String,Object> data) {
		this.data = data;
	}

	public BaseMapResultVo() {

	}

	public BaseMapResultVo(boolean code, String msg) {
		this.setCode(code ? "success" : "error");
		this.setStatus(code ? "200" : "400");
		this.setMsg(msg);
		this.setData(null);
	}

	public BaseMapResultVo(String status, String msg) {
		this.setStatus(status);
		this.setMsg(msg);
		this.setData(null);
	}

	public BaseMapResultVo(boolean code, String msg, Map<String,Object> data) {
		this.setCode(code ? "success" : "error");
		this.setStatus(code ? "200" : "400");
		this.setMsg(msg);
		this.setData(data);
	}

	public BaseMapResultVo(String code, String msg, Map<String,Object> data) {
		this.setCode(code);
		this.setMsg(msg);
		this.setData(data);
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this, SerializerFeature.WriteNullStringAsEmpty);
	}
}
