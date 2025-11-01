package leetcode.LeetInfra;

import leetcode.LeetInfra.result.ResultHandler;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public abstract class LeetClass<TOUTPUT> {
    private static final String DEFAULT_TEST_RUN_TITLE = "Test run number #%d";

    private ResultHandler<TOUTPUT> resultHandler;

    protected LeetClass() {
        this(new ResultHandler<>());
    }

    protected LeetClass(ResultHandler<TOUTPUT> resultHandler) {
        this.resultHandler = resultHandler;
    }

    abstract List<LeetRun<TOUTPUT>> leetRunList();

    private static <T> T measure(String name, Supplier<T> supplier) {
        long start = System.nanoTime();
        T result = supplier.get();
        long end = System.nanoTime();
        System.out.printf("%s took %.3f ms%n", name, (end - start) / 1_000_000.0);
        return result;
    }

    // TODO: Make more robust solution
    private void handleTestResult(TOUTPUT actual, TOUTPUT expected) throws LeetRunFailedException {
        if (actual == expected)  {
            System.out.println("Test passed successfully");
        } else {
            throw new LeetRunFailedException("Expected : " + expected + " but got : " + actual);
        }
    }


    public void testRunner() throws LeetRunFailedException {
        AtomicInteger leetRunCounter = new AtomicInteger(1);

        for (LeetRun<TOUTPUT> leetRun : this.leetRunList()) {
            Supplier<TOUTPUT> leetRunSupplier = leetRun.getLeetRunSupplier();
            TOUTPUT expected = leetRun.getExpected();

            String runTitle = leetRun.getRunName()
                    .orElseGet(() -> String.format(DEFAULT_TEST_RUN_TITLE, leetRunCounter.get()));

            TOUTPUT actual = measure(runTitle, leetRunSupplier);

            this.resultHandler.handleTestResult(actual, expected);

            leetRunCounter.incrementAndGet();
        }
    }
}
