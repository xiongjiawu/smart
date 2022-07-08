package com.xiongjiawu.smartaccountbook.common.base;

import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * 响应列表结果
 *
 * @param <T>
 */
@ApiModel(value = "响应列表结果")
public class BaseListResult<T> extends BaseResult<List<T>> {

	private static final long serialVersionUID = -8720376198375041922L;

	public BaseListResult(boolean code, String msg) {
		super(code, msg);
	}
	
	public BaseListResult(boolean code, String msg, List<T> data) {
		super(code, msg, data);
	}
	
	public BaseListResult(int status, String msg) {
		super(status, msg);
	}	
	
	public BaseListResult(String code, String msg, List<T> data) {
		super(code, msg, data);
	}
}
