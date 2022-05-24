package com.ecwid.newjob.deepclone.clone;

public class FieldUtil {
    public static <T> void copyFields(T object, T newObject, Class<T> tClass) throws Exception {
        for (var field : tClass.getDeclaredFields()) {
            field.setAccessible(true);
            field.set(newObject, CloneUtil.deepClone(field.get(object)));
        }
    }
}
