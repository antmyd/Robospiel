package utils;

public class StopWatch {

    private static long start;
    private static long end;

    public static void start() {
        end = 0;
        start = System.currentTimeMillis();
    }

    public static long stop() {
        end = System.currentTimeMillis();
        return getDiff();
    }

    public static long getDiff() {
        return end - start;
    }

    public static boolean executedBefore(long millis) {
        return (end - start) < millis;
    }
}