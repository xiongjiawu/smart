package com.xiongjiawu.smartaccountbook.common.dto.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 响应分页 bo
 *
 * @param <T>
 */
public class BasePageRespDto<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5467918680614000662L;

	private Long total;
	
	public Long getTotal() {
		return total;
	}
	
	public void setTotal(Long total) {
		this.total = total;
	}
	
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
}
