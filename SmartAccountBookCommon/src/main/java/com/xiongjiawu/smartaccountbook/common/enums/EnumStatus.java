package com.xiongjiawu.smartaccountbook.common.enums;

/**
 * 有效性枚举
 *
 */
public enum EnumStatus implements EnumKeyValueBase<Integer, String> {

	valid(1, "有效"),
	
	invalid(0, "无效")
	
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
	
	EnumStatus(Integer key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public static EnumStatus parseByKey(Integer key) {
		for(EnumStatus item : EnumStatus.values()) {
			if(item.key.equals(key)) {
				return item;
			}
		}
		return null;
	}
	
}
