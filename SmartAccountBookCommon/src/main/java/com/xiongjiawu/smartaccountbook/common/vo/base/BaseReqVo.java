package com.xiongjiawu.smartaccountbook.common.vo.base;

import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * 请求 vo 基类
 *
 */
public class BaseReqVo implements Serializable {

	private static final long serialVersionUID = -7622844861639824934L;

	/**
	 * 更新 bo 对象
	 * @param reqBo
	 * @return
	 */
	public <T> T update(T reqBo) {

		BeanUtils.copyProperties(this, reqBo);

		return reqBo;
	}
}
