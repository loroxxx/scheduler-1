package com.jinhui.scheduler.biz.imiqian.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by luozl on 2017/3/14.
 */
public class ObjectUtils {
    public static final Logger LOGGER = LoggerFactory
            .getLogger(ObjectUtils.class);
    /**
     * 将源对象的属性值赋给目标对象里属性名相同的属性
     * @param source
     * @param target
     * @return
     */
    public static void cloneObjectAttributeValue(Object source,Object target) {
        Field[] fields = source.getClass().getDeclaredFields();
        for(int i=0;i<fields.length;i++) {
            String fieldName = fields[i].getName();
            fieldName = fieldName.substring(0,1).toUpperCase() + fieldName.substring(1,fieldName.length());
            try {
                Method sourceGetMethod = source.getClass().getDeclaredMethod("get"+fieldName);
                Object val = sourceGetMethod.invoke(source,null);
                Method targetSetMethod = target.getClass().getDeclaredMethod("set"+fieldName,sourceGetMethod.getReturnType());
                targetSetMethod.invoke(target,val);
            } catch (Exception e) {
                LOGGER.info("属性[" + fieldName + "]值未设置成功");
            }
        }
    }
}
