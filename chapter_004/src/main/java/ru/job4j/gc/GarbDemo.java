package ru.job4j.gc;

public class GarbDemo {
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    /**
     * Firstly, we get size of empty {@link User} object.
     * <p>
     * Then get number of objects which could be written
     * into available memory.
     * <p>
     * Finally, starts loop with 2n iterations to start
     * JVM GC. Whe it started, we'll get "removing" message
     * from {@link User}'s finalize method.
     *
     * @param args Input arguments
     */
    public static void main(String[] args) {
        long size = new User().getSize();
        long n = ENVIRONMENT.freeMemory() / size;
        for (long i = 1; i < n * 2; i++) {
            new User();
            System.out.println(i);
        }
    }
}
