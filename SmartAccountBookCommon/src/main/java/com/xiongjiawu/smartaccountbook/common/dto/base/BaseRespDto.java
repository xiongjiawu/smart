package com.xiongjiawu.smartaccountbook.common.dto.base;

import java.io.Serializable;

public abstract class BaseRespDto implements Serializable {

	private static final long serialVersionUID = 15671470600316786L;

	public BaseRespDto load(BaseRespDto respBo) {
		
		return this;
	}
}
