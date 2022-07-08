package com.xiongjiawu.smartaccountbook.api.config;


import com.xiongjiawu.smartaccountbook.common.shiro.UserShiroPrincipal;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 插入、更新前自动插入时间、操作人
 */
@Aspect
@Component
public class CommonDataInject {

    private static Log logger = LogFactory.getLog(CommonDataInject.class);

    @Pointcut("execution(* com.xiongjiawu.smartaccountbook.common.dao.*Mapper.insert*(..))")
    private void insertCutMethod() {
    }

    @Pointcut("execution(* com.xiongjiawu.smartaccountbook.common.dao.*Mapper.update*(..))")
    private void updateCutMethod() {
    }

    @Around("insertCutMethod()")
    public Object doInsertAround(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        //判空
        List<Object> collect = Arrays.asList(args).stream().filter(Objects::nonNull).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(collect)){
            return pjp.proceed();
        }
        UserShiroPrincipal shiroPrincipal = null;
        try {
            shiroPrincipal = UserShiroPrincipal.getInstance();
        } catch (Exception e) {
        }
        String userName = shiroPrincipal != null ? shiroPrincipal.getUserName() : null;
        for (Object arg : args) {
            if (arg instanceof List) {
                List<Object> objList = new ArrayList<>();
                for (Object o : (List<?>) arg) {
                    objList.add(Object.class.cast(o));
                    Class clazz = objList.get(0).getClass();
                    Field[] fields = clazz.getDeclaredFields();
                    for (Field f : fields) {
                        String fileName = f.getName();
                        if ("createTime".equalsIgnoreCase(fileName)) {
                            Field nameField = clazz.getDeclaredField(fileName);
                            nameField.setAccessible(true);
                            nameField.set(o, new Date());
                        }
                        if ("createUser".equalsIgnoreCase(fileName)) {
                            Field nameField = clazz.getDeclaredField(fileName);
                            nameField.setAccessible(true);
                            nameField.set(o, userName);
                        }
                    }
                }
            } else {
                logger.debug("[insert]" + arg);
                Class clazz = arg.getClass();// 获取到对象对应的class对象
                Field[] fields = clazz.getDeclaredFields();//
                for (Field f : fields) {
                    String fileName = f.getName();
//                if ("createTime".equalsIgnoreCase(fileName)) {
//                    Field nameField = clazz.getDeclaredField(fileName);
//                    nameField.setAccessible(true);
//                    nameField.set(arg, new Date());
//                }
                    if ("createUser".equalsIgnoreCase(fileName)) {
                        Field nameField = clazz.getDeclaredField(fileName);
                        nameField.setAccessible(true);
                        nameField.set(arg, userName);
                    }
                }
            }
        }
        Object o = pjp.proceed();
        return o;
    }

    @Around("updateCutMethod()")
    public Object doupdateAround(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        //判空
        List<Object> collect = Arrays.asList(args).stream().filter(Objects::nonNull).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(collect)){
            return pjp.proceed();
        }
        UserShiroPrincipal shiroPrincipal = null;
        try {
            shiroPrincipal = UserShiroPrincipal.getInstance();
        } catch (Exception e) {
        }
        String userName = shiroPrincipal != null ? shiroPrincipal.getUserName() : null;
        for (Object arg : args) {
            if (arg instanceof List) {
                List<Object> objList = new ArrayList<>();
                for (Object o : (List<?>) arg) {
                    objList.add(Object.class.cast(o));
                    Class clazz = objList.get(0).getClass();
                    Field[] fields = clazz.getDeclaredFields();
                    for (Field f : fields) {
                        String fileName = f.getName();
                        if ("updateTime".equalsIgnoreCase(fileName)) {
                            Field nameField = clazz.getDeclaredField(fileName);
                            nameField.setAccessible(true);
                            nameField.set(o, new Date());
                        }
                        if ("updateUser".equalsIgnoreCase(fileName)) {
                            Field nameField = clazz.getDeclaredField(fileName);
                            nameField.setAccessible(true);
                            nameField.set(o, userName);
                        }
                    }
                }
            } else {
                logger.debug("[update]" + arg);
                Class clazz = arg.getClass();// 获取到对象对应的class对象
                Field[] fields = clazz.getDeclaredFields();//
                for (Field f : fields) {
                    String fileName = f.getName();
//                if ("updateTime".equalsIgnoreCase(fileName)) {
//                    Field nameField = clazz.getDeclaredField(fileName);
//                    nameField.setAccessible(true);
//                    nameField.set(arg, new Date());
//                }
                    if ("updateUser".equalsIgnoreCase(fileName)) {
                        Field nameField = clazz.getDeclaredField(fileName);
                        nameField.setAccessible(true);
                        nameField.set(arg, userName);
                    }
                }
            }
        }
        Object o = pjp.proceed();
        return o;
    }
}
