package com.xiongjiawu.smartaccountbook.common.base;

public abstract class BaseServiceImpl<T> extends com.zhangzlyuyx.easy.mybatis.common.BaseServiceImpl<T> implements BaseService<T> {

	@Override
	public BaseMapper<T> getMapper() {
		return null;
	}
	
	/**
	 * 插入数据前处理
	 */
	@Override
	public void beforeInsert(T t) {
		super.beforeInsert(t);
	}
	
	/**
	 * 更新数据前处理
	 */
	@Override
	public void beforeUpdate(T t) {
		super.beforeUpdate(t);
	}
}
