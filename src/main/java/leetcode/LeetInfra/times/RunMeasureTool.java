package leetcode.LeetInfra.times;

import java.util.function.Supplier;

public class RunMeasureTool {
    public static <T> T measure(String name, Supplier<T> supplier) throws Exception {
        long start = System.nanoTime();
        try {
            T result = supplier.get();

            long end = System.nanoTime();
            System.out.printf("%s took %.3f ms%n", name, (end - start) / 1_000_000.0);
            return result;
        } catch (Exception e) {
            throw e;
        }
    }
}
