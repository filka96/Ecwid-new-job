package com.ecwid.newjob.deepclone.clone;

import java.lang.reflect.Modifier;

public class FieldUtil {
    public static <T> void copyFields(T object, T newObject, Class<T> tClass) throws Exception {
        for (var field : tClass.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) continue;
            field.setAccessible(true);
            field.set(newObject, CloneUtil.deepClone(field.get(object)));
        }
    }
}
