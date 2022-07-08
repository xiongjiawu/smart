package com.xiongjiawu.smartaccountbook.common.vo.base;

import com.zhangzlyuyx.easy.mybatis.Condition;
import com.zhangzlyuyx.easy.mybatis.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询 vo 基础类
 *
 */
@ApiModel(value = "分页查询基础类")
public class BaseQueryPageVo implements Serializable {

	private static final long serialVersionUID = 7731272015680777822L;

	/**
	 * 分页序号
	 */
	@ApiModelProperty(value = "分页序号(从1开始)")
	private Integer pageNo;

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * 每页记录数
	 */
	@ApiModelProperty(value = "每页记录数")
	private Integer pageSize;

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

	public BaseQueryPageVo() {

	}

	public BaseQueryPageVo(Integer pageNo, Integer pageSize) {
		this(pageNo, pageSize, null, null);
	}

	public BaseQueryPageVo(Integer pageNo, Integer pageSize, String sort, String order) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.sort = sort;
		this.order = order;
	}

	public PageQuery load() {
		// 分页条件初始化
		int page = 1;
		int rows = PageQuery.DEFAULT_PAGE_SIZE;
		String sort = "id";
		String order = "desc";
		if (this != null) {
			page = this.getPageNo() == null ? page : this.getPageNo();
			rows = this.getPageSize() == null ? rows : this.getPageSize();
			sort = StringUtils.isEmpty(this.getSort()) ? sort : this.getSort();
			order = StringUtils.isEmpty(this.getOrder()) ? order : this.getOrder();
		}
		PageQuery pageQuery = new PageQuery();
		pageQuery.setOrderByClause(sort + " " + order);
		pageQuery.setPageNo(page);
		pageQuery.setPageSize(rows);
		return pageQuery;
	}

	public PageQuery load(List<Condition> conditions) {
		PageQuery pageQuery = load();
		pageQuery.setConditions(conditions);
		return pageQuery;
	}
}
