package com.ecwid.newjob.deepclone.clone;

import java.util.Map;

public class MapUtil {
    public static <M extends Map<K, V>, K, V> M copyMap(M object) throws Exception {
        M newMap = (M) object.getClass().getConstructor().newInstance();
        for (var e : newMap.entrySet()) {
            newMap.put(CloneUtil.deepClone(e.getKey()), CloneUtil.deepClone(e.getValue()));
        }
        return newMap;
    }
}
