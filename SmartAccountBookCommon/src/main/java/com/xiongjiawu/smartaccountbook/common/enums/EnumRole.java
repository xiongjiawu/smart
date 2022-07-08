package com.xiongjiawu.smartaccountbook.common.enums;

/**
 * 固定角色枚举
 */
public enum EnumRole implements EnumKeyValueBase<String, String> {

    admin("admin", "超级管理员"),

    general("general", "普通公务员"),

    personInCharge("personInCharge", "科及以下公务员"),

    leadersInCharge("leadersInCharge", "单位分管领导"),

    unitLeader("unitLeader", "单位主要领导"),

    countyLeader("countyLeader", "组织部"),

    personnelSection("personnelSection", "人事科");

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

    EnumRole(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static EnumRole parseByKey(String key) {
        for (EnumRole item : EnumRole.values()) {
            if (item.key.equals(key)) {
                return item;
            }
        }
        return null;
    }

}
