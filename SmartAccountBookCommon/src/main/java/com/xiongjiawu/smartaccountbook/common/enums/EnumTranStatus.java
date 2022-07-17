package com.xiongjiawu.smartaccountbook.common.enums;

/**
 * 翻译类型枚举
 *
 */
public enum EnumTranStatus implements EnumKeyValueBase<Integer, String> {

	baseData(0, "BaseData字典"),

	user(1, "用户名"),

	;

	private Integer key;

	private String value;

	@Override
	public Integer getKey() {
		return this.key;
	}

	@Override
	public String getValue() {
		return this.value;
	}

	EnumTranStatus(Integer key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public static EnumTranStatus parseByKey(Integer key) {
		for(EnumTranStatus item : EnumTranStatus.values()) {
			if(item.key.equals(key)) {
				return item;
			}
		}
		return null;
	}
	
}
