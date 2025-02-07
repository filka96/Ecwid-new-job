package com.ecwid.newjob.ipcounter;

import java.util.function.Function;

/** если нужна приблизительная точность можно использовать
    <a href="https://en.wikipedia.org/wiki/HyperLogLog">HyperLogLog</a>
 */

public class Main {
    public static void main(String[] args) {
        var filePath = "ip_addresses";
//        measureTime("readOnlyLines", CounterUtil::readOnlyLines, filePath);
        measureTime("count", CounterUtil::count, filePath);
    }

    public static void measureTime(String name, Function<String, Long> fn, String path) {
        System.out.println("-----------------------------------------------------------");
        System.out.println("run: " + name);
        long startTime = System.nanoTime();
        Long l = fn.apply(path);
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("lines: " + l);
        System.out.println("estimatedTime: " + estimatedTime / 1_000_000_000.);
    }
}
