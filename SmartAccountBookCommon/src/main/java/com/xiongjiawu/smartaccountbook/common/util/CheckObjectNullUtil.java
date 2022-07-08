package com.xiongjiawu.smartaccountbook.common.util;


import java.lang.reflect.Field;

public class CheckObjectNullUtil {
    /**
     * 校验对象中元素是否都为null或""
     * @param obj 校验对象
     * @return Boolean
     */
    public static Boolean isAllFieldNull(Object obj){
        Boolean flag = true;
        try{
            // 得到类对象
            Class stuCla = obj.getClass();
            //得到属性集合
            Field[] fs = stuCla.getDeclaredFields();
            //遍历属性
            for (Field f : fs) {
                // 设置属性是可以访问的(私有的也可以)
                f.setAccessible(true);
                String name = f.getName();
                //排除errorMsg和rowNum
                if ("errorMsg".equals(name) || "rowNum".equals(name)) {
                    continue;
                }
                // 得到此属性的值
                Object val = f.get(obj);
                //只要有1个属性不为空或"",那么就不是所有的属性值都为空
                if(val != null && !"".equals(val)) {
                    flag = false;
                    break;
                }
            }
        }catch (Exception e){
            System.out.println("判断对象是否为空报错: {}"+ e.getMessage());
        }
        return flag;
    }
}
