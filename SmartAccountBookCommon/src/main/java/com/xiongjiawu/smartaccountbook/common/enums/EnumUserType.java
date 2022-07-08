package com.xiongjiawu.smartaccountbook.common.enums;

/**
 * 用户类型枚举
 *
 */
public enum EnumUserType implements EnumKeyValueBase<String, String> {

	jg("1", "用户"),

	;
	
	private String key;
	
	@Override
	public String getKey() {
		return this.key;
	}
	
	private String value;
	
	@Override
	public String getValue() {
		return this.value;
	}
	
	EnumUserType(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public static EnumUserType parseByKey(String key) {
		for(EnumUserType item : EnumUserType.values()) {
			if(item.key.equalsIgnoreCase(key)) {
				return item;
			}
		}
		return null;
	}
}
