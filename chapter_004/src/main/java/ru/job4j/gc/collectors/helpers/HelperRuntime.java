package ru.job4j.gc.collectors.helpers;

public class HelperRuntime {
    public static long getLimit(long itemSize) {
        return Runtime.getRuntime().freeMemory() / itemSize;
    }
}
