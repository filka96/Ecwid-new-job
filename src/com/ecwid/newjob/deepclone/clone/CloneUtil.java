package com.ecwid.newjob.deepclone.clone;

import sun.reflect.ReflectionFactory;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Map;

public class CloneUtil {
    public static <T> T deepClone(T object) throws Exception {
        return switch (object) {
            case null -> null;
            case Collection c -> (T) CollectionUtil.copyCollection(c);
            case Map m -> (T) MapUtil.copyMap(m);
            case Enum e -> (T) e;
            case Number n -> (T) n;
            case String s -> (T) s;
            case Boolean b -> (T) b;
            case Character c ->(T)c;
            default -> deepClone(object, (Class<T>) object.getClass());
        };
    }

    private static <T> T deepClone(T object, Class<T> tClass) throws Exception {
        T newEntity;
        var reflectionFactory = ReflectionFactory.getReflectionFactory();
        var constructor = reflectionFactory.newConstructorForSerialization(tClass, Object.class.getConstructor((Class[]) null));
        newEntity = (T) constructor.newInstance();
        FieldUtil.copyFields(object, newEntity, tClass);
        return newEntity;
    }
}