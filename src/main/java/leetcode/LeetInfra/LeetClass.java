package leetcode.LeetInfra;

import leetcode.LeetInfra.result.ResultHandler;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import static leetcode.LeetInfra.logger.LeetLogger.error;
import static leetcode.LeetInfra.times.RunMeasureTool.measure;

public abstract class LeetClass<TOUTPUT> {
    private static final String DEFAULT_TEST_RUN_TITLE = "Test run number #%d";

    private ResultHandler<TOUTPUT> resultHandler;

    protected LeetClass() {
        this(new ResultHandler<>());
    }

    protected LeetClass(ResultHandler<TOUTPUT> resultHandler) {
        this.resultHandler = resultHandler;
    }

    protected abstract List<LeetRun<TOUTPUT>> leetRunList();

    public void testRunner() throws LeetRunFailedException {
        AtomicInteger leetRunCounter = new AtomicInteger(1);

        for (LeetRun<TOUTPUT> leetRun : this.leetRunList()) {
            String runTitle = leetRun.getRunName()
                    .orElseGet(() -> String.format(DEFAULT_TEST_RUN_TITLE, leetRunCounter.get()));

            if (!leetRun.isRun()) {
                System.out.println("Skipping leetRun : " + runTitle);
                continue;
            }

            Supplier<TOUTPUT> leetRunSupplier = leetRun.getLeetRunSupplier();
            TOUTPUT expected = leetRun.getExpected();

            TOUTPUT actual = runAndMeasure(runTitle, leetRunSupplier);

            this.resultHandler.handleTestResult(actual, expected);

            leetRunCounter.incrementAndGet();
        }
    }

    private TOUTPUT runAndMeasure(String runTitle, Supplier<TOUTPUT> leetRunSupplier) throws LeetRunFailedException {
        try {
            return measure(runTitle, leetRunSupplier);
        } catch (LeetRunFailedException e) {
            throw e;
        } catch (Exception e) {
            error("Running case failed for another reason");
        }

        return null;
    }
}
