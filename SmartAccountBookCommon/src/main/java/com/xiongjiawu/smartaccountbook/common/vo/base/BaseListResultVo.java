package com.xiongjiawu.smartaccountbook.common.vo.base;

import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(value = "列表结果vo")
public class BaseListResultVo<T> extends BaseResultVo<List<T>> {

	private static final long serialVersionUID = 7945405640752951709L;

	public BaseListResultVo(boolean code, String msg) {
		super(code, msg);
	}

	public BaseListResultVo(String status, String msg) {
		super(status, msg);
	}

	public BaseListResultVo(boolean code, String msg, List<T> data) {
		super(code, msg, data);
	}

	public BaseListResultVo(String code, String msg, List<T> data) {
		super(code, msg, data);
	}
}
