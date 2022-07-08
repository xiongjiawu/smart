package com.xiongjiawu.smartaccountbook.common.vo.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "分页结果vo")
public class BasePageResultVo<T> extends BaseVo {

	/**
	 *
	 */
	private static final long serialVersionUID = -6332302828446602263L;

	@ApiModelProperty(value = "总记录数")
	private Long total;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	@ApiModelProperty(value = "数据行")
	private List<T> rows;

	public List<T> getRows() {
		if(this.rows == null) {
			this.rows = new ArrayList<>();
		}
		return this.rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public BasePageResultVo(boolean code, String msg) {
		this.setCode(code ? "success" : "error");
		this.setStatus(code ? "200" : "400");
		this.setMsg(msg);
		this.setRows(null);
	}

	public BasePageResultVo(String status, String msg) {
		this.setStatus(status);
		this.setMsg(msg);
		this.setRows(null);
	}

	public BasePageResultVo(boolean code, String msg, List<T> rows) {
		this.setCode(code ? "success" : "error");
		this.setStatus(code ? "200" : "400");
		this.setMsg(msg);
		this.setRows(rows);
	}

	public BasePageResultVo(String code, String msg, List<T> rows) {
		this.setCode(code);
		this.setMsg(msg);
		this.setRows(rows);
	}
}
