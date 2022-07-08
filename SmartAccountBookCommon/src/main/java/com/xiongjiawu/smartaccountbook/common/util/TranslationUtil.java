package com.xiongjiawu.smartaccountbook.common.util;


import com.xiongjiawu.smartaccountbook.common.annotation.TranslationDict;
import com.xiongjiawu.smartaccountbook.common.enums.EnumStatus;
import com.xiongjiawu.smartaccountbook.common.enums.EnumTranStatus;
import com.xiongjiawu.smartaccountbook.common.model.BaseData;
import com.xiongjiawu.smartaccountbook.common.model.User;
import com.xiongjiawu.smartaccountbook.common.service.BaseDataService;
import com.xiongjiawu.smartaccountbook.common.service.UserService;
import com.zhangzlyuyx.easy.mybatis.Condition;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class TranslationUtil {

    @Autowired
    private BaseDataService baseDataService;

    @Autowired
    private static BaseDataService staticBaseDataService;

    @Autowired
    private UserService userService;

    @Autowired
    private static UserService staticUserService;

    @PostConstruct
    public void init() {
        staticBaseDataService = baseDataService;
        staticUserService = userService;
    }

    public static void TransforBeanByBaseData(Object object) {
        if (object == null) {
            return;
        }
        if (object instanceof List) {
            if (CollectionUtils.isEmpty((List) object)) {
                return;
            }
            List<Object> objList = new ArrayList<>();
            for (Object o : (List<?>) object) {
                objList.add(Object.class.cast(o));
            }
            Class clazz = objList.get(0).getClass();
            List<Field> fields = new ArrayList<>();
            while (clazz.getSuperclass() != null) {
                fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
                clazz = clazz.getSuperclass();
            }
            try {
                List<String> parNodeList = new ArrayList<>();
                List<String> userNameList = new ArrayList<>();
                List<String> orgCodeList = new ArrayList<>();
                List<String> deptCodeList = new ArrayList<>();
                for (Field field : fields) {
                    Annotation[] annotations = field.getAnnotations();
                    field.setAccessible(true);
                    for (Object ob : objList) {
                        Object fieldTypeObject = field.get(ob);
                        String fieldCode = fieldTypeObject != null ? fieldTypeObject.toString() : null;
                        if (fieldTypeObject instanceof List) {
                            TransforBeanByBaseData(fieldTypeObject);
                        }
                        for (Annotation annotation : annotations) {
                            if (annotation instanceof TranslationDict && StringUtils.isNotBlank(fieldCode)) {
                                Integer translationType = ((TranslationDict) annotation).type().getKey();
                                if (EnumTranStatus.baseData.getKey() == translationType) {
                                    String annofieldType = ((TranslationDict) annotation).fieldType().trim();
                                    if (StringUtils.isNotBlank(annofieldType)) {
                                        parNodeList.add(annofieldType);
                                    }
                                } else if (EnumTranStatus.user.getKey() == translationType) {
                                    userNameList.add(fieldCode);
                                } else if (EnumTranStatus.org.getKey() == translationType) {
                                    orgCodeList.add(fieldCode);
                                } else if (EnumTranStatus.dept.getKey() == translationType) {
                                    deptCodeList.add(fieldCode);
                                }
                            }
                        }
                    }
                }
                // 基础数据查询
                List<BaseData> baseDataList = null;
                if (!CollectionUtils.isEmpty(parNodeList)) {
                    List<Condition> baseDataConditions = new ArrayList<>();
                    baseDataConditions.add(new Condition(BaseData.column_parNode, "in", parNodeList));
                    baseDataConditions.add(new Condition(BaseData.column_isValid, EnumStatus.valid.getKey()));
                    baseDataList = staticBaseDataService.selectByCondition(baseDataConditions);
                }
                // 用户查询
                List<User> userList = null;
                if (!CollectionUtils.isEmpty(userNameList)) {
                    userList = staticUserService.selectByCondition(Arrays.asList(new Condition(User.column_userName, "in", userNameList),
                            new Condition(User.column_isValid, EnumStatus.valid.getKey())));
                }
                if (CollectionUtils.isEmpty(baseDataList) && CollectionUtils.isEmpty(userList)) {
                    return;
                }

                // 赋值开始
                for (Object objClass : objList) {
                    Class clazzz = objClass.getClass();
                    List<Field> objFields = new ArrayList<>();
                    while (clazz.getSuperclass() != null) {
                        fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
                        clazz = clazz.getSuperclass();
                    }
                    for (Field field : fields) {
                        Annotation[] annotations = field.getAnnotations();
                        field.setAccessible(true);
                        Object fieldTypeObject = field.get(objClass);
                        String fieldCode = fieldTypeObject != null ? fieldTypeObject.toString() : null;
                        for (Annotation annotation : annotations) {
                            if (annotation instanceof TranslationDict && StringUtils.isNotBlank(fieldCode)) {
                                Integer translationType = ((TranslationDict) annotation).type().getKey();
                                String translationField = ((TranslationDict) annotation).field().trim();

                                if (StringUtils.isBlank(translationField)) {
                                    if (EnumTranStatus.user.getKey() == translationType) {
                                        translationField = "name";
                                    } else {
                                        translationField = field.getName() + "Desc";
                                    }
                                }
                                Field fieldByName = getFieldByList(fields, translationField, objClass);
                                fieldByName.setAccessible(true);

                                if (EnumTranStatus.baseData.getKey() == translationType) {
                                    String annofieldType = ((TranslationDict) annotation).fieldType().trim();
                                    if (StringUtils.isBlank(annofieldType)) {
                                        continue;
                                    }
                                    for (BaseData baseData : baseDataList) {
                                        String str = baseData.getParNode().trim() + "-" + baseData.getProCode().trim();
                                        String oriStr = annofieldType.trim() + "-" + fieldCode.trim();
                                        if (str.equals(oriStr)) {
                                            fieldByName.set(objClass, baseData.getProName());
                                            break;
                                        }
                                    }
                                } else if (EnumTranStatus.user.getKey() == translationType) {
                                    for (User user : userList) {
                                        if (fieldCode.trim().equals(user.getUserName().trim())) {
                                            fieldByName.set(objClass, user.getRealName());
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
            }
        } else {
            Class clazz = object.getClass();
            List<Field> fields = new ArrayList<>();
            while (clazz.getSuperclass() != null) {
                fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
                clazz = clazz.getSuperclass();
            }
            try {
                List<String> parNodeList = new ArrayList<>();
                List<String> userNameList = new ArrayList<>();
                for (Field field : fields) {
                    Annotation[] annotations = field.getAnnotations();
                    field.setAccessible(true);
                    Object fieldTypeObject = field.get(object);
                    if (fieldTypeObject instanceof List) {
                        TransforBeanByBaseData(fieldTypeObject);
                    }
                    if (fieldTypeObject != null) {
                        String fieldCode = fieldTypeObject.toString();
                        for (Annotation annotation : annotations) {
                            if (annotation instanceof TranslationDict && StringUtils.isNotBlank(fieldCode)) {
                                Integer translationType = ((TranslationDict) annotation).type().getKey();
                                if (EnumTranStatus.baseData.getKey() == translationType) {
                                    String annofieldType = ((TranslationDict) annotation).fieldType().trim();
                                    if (StringUtils.isNotBlank(annofieldType)) {
                                        parNodeList.add(annofieldType);
                                    }
                                } else if (EnumTranStatus.user.getKey() == translationType) {
                                    userNameList.add(fieldCode);
                                }
                            }
                        }
                    }
                }
                // 基础数据查询
                List<BaseData> baseDataList = null;
                if (!CollectionUtils.isEmpty(parNodeList)) {
                    List<Condition> baseDataConditions = new ArrayList<>();
                    baseDataConditions.add(new Condition(BaseData.column_parNode, "in", parNodeList));
                    baseDataConditions.add(new Condition(BaseData.column_isValid, EnumStatus.valid.getKey()));
                    baseDataList = staticBaseDataService.selectByCondition(baseDataConditions);
                }
                // 用户查询
                List<User> userList = null;
                if (!CollectionUtils.isEmpty(userNameList)) {
                    userList = staticUserService.selectByCondition(Arrays.asList(new Condition(User.column_userName, "in", userNameList),
                            new Condition(User.column_isValid, EnumStatus.valid.getKey())));
                }
                if (CollectionUtils.isEmpty(baseDataList) && CollectionUtils.isEmpty(userList)) {
                    return;
                }

                // 赋值开始
                for (Field field : fields) {
                    Annotation[] annotations = field.getAnnotations();
                    field.setAccessible(true);
                    Object fieldTypeObject = field.get(object);
                    if (fieldTypeObject != null) {
                        String fieldCode = fieldTypeObject.toString();
                        for (Annotation annotation : annotations) {
                            if (annotation instanceof TranslationDict && StringUtils.isNotBlank(fieldCode)) {
                                Integer translationType = ((TranslationDict) annotation).type().getKey();
                                String translationField = ((TranslationDict) annotation).field().trim();

                                if (StringUtils.isBlank(translationField)) {
                                    if (EnumTranStatus.user.getKey() == translationType) {
                                        translationField = "name";
                                    } else {
                                        translationField = field.getName() + "Desc";
                                    }
                                }
                                Field fieldByName = getFieldByList(fields, translationField, object);
                                fieldByName.setAccessible(true);

                                if (EnumTranStatus.baseData.getKey() == translationType) {
                                    String annofieldType = ((TranslationDict) annotation).fieldType().trim();
                                    if (StringUtils.isBlank(annofieldType)) {
                                        continue;
                                    }
                                    for (BaseData baseData : baseDataList) {
                                        String str = baseData.getParNode().trim() + "-" + baseData.getProCode().trim();
                                        String oriStr = annofieldType.trim() + "-" + fieldCode.trim();
                                        if (str.equals(oriStr)) {
                                            fieldByName.set(object, baseData.getProName());
                                            break;
                                        }
                                    }
                                } else if (EnumTranStatus.user.getKey() == translationType) {
                                    for (User user : userList) {
                                        if (fieldCode.trim().equals(user.getUserName().trim())) {
                                            fieldByName.set(object, user.getRealName());
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
    }


    private static Field getFieldByList(List<Field> fields, String annofieldName, Object object) {
        for (Field field : fields) {
            if (field.getName().equals(annofieldName)) {
                return field;
            }
        }
        return null;
    }
}
