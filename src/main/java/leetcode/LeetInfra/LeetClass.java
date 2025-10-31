package leetcode.LeetInfra;

import java.util.Map;
import java.util.function.Supplier;

public abstract class LeetClass<TOUTPUT> {

    public static final String TEST_RUN_TITLE = "Test run number #%d";

    abstract Map<Supplier<TOUTPUT>, TOUTPUT> runToResultMap();

    private static <T> T measure(String name, Supplier<T> supplier) {
        long start = System.nanoTime();
        T result = supplier.get();
        long end = System.nanoTime();
        System.out.printf("%s took %.3f ms%n", name, (end - start) / 1_000_000.0);
        return result;
    }

    private void handleTestResult(TOUTPUT actual, TOUTPUT expected) throws LeetRunFailedException {
        if (actual == expected)  {
            System.out.println("Test passed successfully");
        } else {
            throw new LeetRunFailedException("Expected : " + expected + " but got : " + actual);
        }
    }

    public void testRunner() throws LeetRunFailedException {
        Map<Supplier<TOUTPUT>, TOUTPUT> supplierTOUTPUTHashMap = this.runToResultMap();
        int number = 1;

        for(Map.Entry<Supplier<TOUTPUT>, TOUTPUT> entry : supplierTOUTPUTHashMap.entrySet()){
            Supplier<TOUTPUT> key = entry.getKey();
            TOUTPUT expected = entry.getValue();

            String runTitle = String.format(TEST_RUN_TITLE, number);

            TOUTPUT actual = measure(runTitle, key);

            this.handleTestResult(actual, expected);

            number++;
        }
    }
}
