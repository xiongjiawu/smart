package com.xiongjiawu.smartaccountbook.common.enums;

/**
 * 客户端类型枚举
 *
 */
public enum EnumClientType implements EnumKeyValueBase<String, String> {

	/**
	 * 电脑端
	 */
	pc("pc", "电脑端"),
	
	/**
	 * app端
	 */
	app("app", "app端"),
	
	/**
	 * 微信公众号端
	 */
	wxmp("wxmp", "微信公众号端")
	
	;
	
	private String key;
	
	private String value;
	
	@Override
	public String getKey() {
		return this.key;
	}
	
	@Override
	public String getValue() {
		return this.value;
	}
	
	EnumClientType(String key, String value){
		this.key = key;
		this.value = value;
	}
	
	public static EnumClientType parseByKey(String key) {
		for(EnumClientType item : EnumClientType.values()) {
			if(item.key.equalsIgnoreCase(key)) {
				return item;
			}
		}
		return null;
	}
}
