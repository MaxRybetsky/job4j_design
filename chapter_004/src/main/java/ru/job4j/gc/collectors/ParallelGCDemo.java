package ru.job4j.gc.collectors;

import ru.job4j.gc.User;
import ru.job4j.gc.collectors.helpers.HelperRuntime;
import ru.job4j.gc.collectors.helpers.Input;

import java.util.Scanner;

/**
 * JVM settings:
 * -Xmx4m -Xms4m -XX:+UseParallelGC
 */
public class ParallelGCDemo {
    public static void main(String[] args) {
        long n = Input.getNumberOfIterations();
        long time = System.currentTimeMillis();
        for (long i = 0; i < n; i++) {
            new User();
            System.out.println(i);
        }
        System.out.println("Total time: " + (System.currentTimeMillis() - time));
        System.out.println("Total free memory: " + Runtime.getRuntime().freeMemory());
    }
}
// coefficient  |   Total time, ms  |   Free Memory, bytes
//      2                 652               1288168
//      20                5871               794600
//      100              29615              1202336