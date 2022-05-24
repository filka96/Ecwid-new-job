package com.ecwid.newjob.deepclone.clone;

import sun.reflect.ReflectionFactory;

import java.lang.reflect.Constructor;
import java.util.Collection;

public class CloneUtil {
    public static <T> T deepClone(T object) throws Exception {
        return switch (object) {
            case null -> null;
            case Collection c -> (T) CollectionUtil.copyCollection(c);
            case Number n -> (T) n;
            case String s -> (T) s;
            default -> deepClone(object, (Class<T>) object.getClass());
        };

    }

    private static <T> T deepClone(T object, Class<T> tClass) throws Exception {
        T newEntity;
        ReflectionFactory reflectionFactory = ReflectionFactory.getReflectionFactory();
        Constructor<?> constructor = reflectionFactory.newConstructorForSerialization(tClass, Object.class.getConstructor((Class[]) null));
        newEntity = (T) constructor.newInstance();
        FieldUtil.copyFields(object, newEntity, tClass);
        return newEntity;
    }
}