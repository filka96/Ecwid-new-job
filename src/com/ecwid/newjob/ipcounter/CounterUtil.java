package com.ecwid.newjob.ipcounter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CounterUtil {
    private static final short BYTE_SIZE = 256;

    /**
     * +|- 64mb/s 20%
     */
    public static long count(String filePath) {
        var lineCount = 0L;
        var cache = new boolean[BYTE_SIZE][BYTE_SIZE][BYTE_SIZE][BYTE_SIZE];
        var curString = "";
        long startTime = System.nanoTime();
        try (var in = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            while ((curString = in.readLine()) != null) {
                var curBytes = curString.split("\\.");
                cache[Short.parseShort(curBytes[0])]
                        [Short.parseShort(curBytes[1])]
                        [Short.parseShort(curBytes[2])]
                        [Short.parseShort(curBytes[3])] = true;
                if (lineCount % 100000000 == 0) {
                    long estimatedTime = System.nanoTime() - startTime;
                    System.out.println("lines:" + lineCount + "; estimatedTime: " + estimatedTime / 1_000_000_000.);
                    startTime = System.nanoTime();
                }
                lineCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        var counter = 0L;
        for (short b1 = 0; b1 < BYTE_SIZE; b1++) {
            for (short b2 = 0; b2 < BYTE_SIZE; b2++) {
                for (short b3 = 0; b3 < BYTE_SIZE; b3++) {
                    for (short b4 = 0; b4 < BYTE_SIZE; b4++) {
                        if (cache[b1][b2][b3][b4]) {
                            counter++;
                        }
                    }
                }
            }
        }
        return counter;
    }


    /**
     * run: readOnly
     * lines: 8000000000
     * estimatedTime: 344.0948312
     * <p>
     * +|- 320mb/s 70%
     */
    public static long readOnlyLines(String filePath) {
        long lineCount = 0L;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            while (in.readLine() != null) {
                lineCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineCount;
    }

    /**
     * boolean +/- 8GB
     * run: countCache
     * lines: 0
     * estimatedTime: 3.6521051
     */
    public static long countSimpleCache(String filePath) {
        var counter = 0L;
        var cache = new boolean[BYTE_SIZE][BYTE_SIZE][BYTE_SIZE][BYTE_SIZE];
        for (short b1 = 0; b1 < BYTE_SIZE; b1++) {
            for (short b2 = 0; b2 < BYTE_SIZE; b2++) {
                for (short b3 = 0; b3 < BYTE_SIZE; b3++) {
                    for (short b4 = 0; b4 < BYTE_SIZE; b4++) {
                        if (cache[b1][b2][b3][b4]) {
                            counter++;
                        }
                    }
                }
            }
        }
        return counter;
    }
}
