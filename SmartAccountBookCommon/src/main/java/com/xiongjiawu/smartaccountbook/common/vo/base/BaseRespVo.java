package com.xiongjiawu.smartaccountbook.common.vo.base;

import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * 响应 vo 基类
 *
 */
public class BaseRespVo implements Serializable {

	private static final long serialVersionUID = 6599306780242684776L;

	/**
	 * 加载对象数据
	 * @param t
	 */
	public <T> void load(T t) {

		//拷贝属性
		BeanUtils.copyProperties(t, this);

		return;
	}
}
