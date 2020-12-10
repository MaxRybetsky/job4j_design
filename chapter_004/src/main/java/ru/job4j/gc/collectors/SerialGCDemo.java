package ru.job4j.gc.collectors;

import ru.job4j.gc.User;
import ru.job4j.gc.collectors.helpers.HelperRuntime;
import ru.job4j.gc.collectors.helpers.Input;

import java.util.Scanner;

/**
 * JVM settings:
 * -Xmx4m -Xms4m -XX:+UseSerialGC
 */
public class SerialGCDemo {
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
//      2                 583              937040
//      20                5605             974248
//      100              28096            1198744