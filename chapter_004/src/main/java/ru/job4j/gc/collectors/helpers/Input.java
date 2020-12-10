package ru.job4j.gc.collectors.helpers;

import ru.job4j.gc.User;

import java.util.Scanner;

public class Input {
    public static long getLongFromConsole() {
        return new Scanner(System.in).nextLong();
    }

    /**
     * If n is zero that means we should use limit
     * number of iterations for starting garbage \
     * collector. Otherwise, we set custom number
     * of iterations.
     *
     * @return number of iterations
     */
    public static long getNumberOfIterations() {
        long n = Input.getLongFromConsole();
        if (n == 0) {
            n = HelperRuntime.getLimit(User.getSizeWithEmptyFields());
            long coefficient = Input.getLongFromConsole();
            n *= coefficient;
        }
        return n;
    }
}
