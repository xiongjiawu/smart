package com.xiongjiawu.smartaccountbook.common.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "响应分页")
public class BasePage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5523994169637156741L;
	
	/**
	 * 分页序号
	 */
	@ApiModelProperty(value = "分页序号(从1开始)")
	private Integer pageNo;
	
	/**
	 * 每页记录数
	 */
	@ApiModelProperty(value = "每页记录数")
	private Integer pageSize;

	
	public Integer getPageNo() {
		return pageNo;
	}
	
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	
	public Integer getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * 排序字段
	 */
	@ApiModelProperty(value = "排序字段(例: id)")
	private String sort;
	
	public String getSort() {
		return sort;
	}
	
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	/**
	 * 排序方式
	 */
	@ApiModelProperty(value = "排序方式(asc/desc)")
	private String order;
	
	public String getOrder() {
		return order;
	}
	
	public void setOrder(String order) {
		this.order = order;
	}

	public BasePage() {
		
	}
	
	public BasePage(Integer pageNo, Integer pageSize) {
		this(pageNo, pageSize, null, null);
	}
	
	public BasePage(Integer pageNo, Integer pageSize, String sort, String order) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.sort = sort;
		this.order = order;
	}
}
