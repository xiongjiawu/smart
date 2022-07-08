package com.xiongjiawu.smartaccountbook.common.util;

import com.zhangzlyuyx.easy.mybatis.Condition;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.*;

public class BeanUtil {

	 private final static Logger logger = LoggerFactory.getLogger(BeanUtil.class);

    /**/
    public static List<Condition> generatePredicate(Object entity) {
        //logger.debug("反射字段，按字段类型，进行综合查询");
        Field[] fields = entity.getClass().getDeclaredFields();
        List<Condition> conditionList = new ArrayList<Condition>();
        try {
        	for (Field field : fields) {
                String name = field.getName();
				if ("serialVersionUID".equals(name)) {
					continue;
				}
                field.setAccessible(true);
                Object value = field.get(entity);
                if (value != null) {
                	 if (value instanceof String) {
                		 String stringValue = ((String) value);
                		 if(name.equalsIgnoreCase("startDate")) {

                		 }else if(name.equalsIgnoreCase("endDate")){

                		 }else if(name.equalsIgnoreCase("cmpDateName")){

                		 }
                		 else if(StringUtils.isNotBlank(stringValue)) {
                			 conditionList.add(new Condition(name, "like", "%" +stringValue + "%"));
                		 }
                	 }else if (value instanceof Integer){
               			 conditionList.add(new Condition(name, value));

                	 } else if (value instanceof Long) {
                		 conditionList.add(new Condition(name, value));
                	 }else if (value instanceof Calendar) {

                	 }else if (value instanceof Date) {

                	 }else if (value instanceof Collection) {

                     } else {
                    	 logger.error("综合查询暂不支持传入的数据类型", name, field);
                     }
                }
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
        return conditionList;
    }

	/**
	 * 判断对象中属性值是否全为空
	 *
	 * @param object
	 * @return
	 */
	public static boolean checkObjAllFieldsIsNullForImportDto(Object object) {
		if (null == object) {
			return true;
		}
		try {
			for (Field f : object.getClass().getDeclaredFields()) {
				f.setAccessible(true);
				String name = f.getName();
				if ("errorMsg".equals(name) || "rowNum".equals(name)) {
					continue;
				}
				if (f.get(object) != null && StringUtils.isNotBlank(f.get(object).toString())) {
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}
}
