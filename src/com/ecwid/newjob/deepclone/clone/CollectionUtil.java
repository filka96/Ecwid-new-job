package com.ecwid.newjob.deepclone.clone;

import java.util.Collection;

public class CollectionUtil {
    public static <C extends Collection<T>, T> C copyCollection(C object) throws Exception {
        C newCollection = (C) object.getClass().getConstructor().newInstance();
        for (T t : object) newCollection.add(CloneUtil.deepClone(t));
        return newCollection;
    }
}
