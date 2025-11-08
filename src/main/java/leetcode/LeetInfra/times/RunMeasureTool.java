package leetcode.LeetInfra.times;

import java.util.function.Supplier;

public class RunMeasureTool {
    private final long NO_TIME = 0l;

    private long startTime;

    public RunMeasureTool() {
        startTime = NO_TIME;
    }

    public void startTimer() {
        startTime = System.nanoTime();
    }

    public double endTimer() {
        double duration = (System.nanoTime() - startTime) / 1_000_000.0;

        startTime = NO_TIME;

        return duration;
    }

    public static <T> T measure(String name, Supplier<T> supplier) throws Exception {
        long start = System.nanoTime();
        try {
            T result = supplier.get();

            long end = System.nanoTime();
            double duration = (end - start) / 1_000_000.0;
            System.out.printf("%s took %.3f ms%n", name, duration);
            return result;
        } catch (Exception e) {
            throw e;
        }
    }
}
