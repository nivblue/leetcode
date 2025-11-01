package leetcode.LeetInfra.result;

import leetcode.LeetInfra.LeetRunFailedException;
import lombok.AllArgsConstructor;

import static leetcode.LeetInfra.logger.LeetLogger.success;

@AllArgsConstructor
public class ResultHandler<TOUTPUT> {

    private LeetAssertion leetAssertion;

    public ResultHandler() {
        leetAssertion = new LeetAssertion();
    }

    public void handleTestResult(TOUTPUT actual, TOUTPUT expected) throws LeetRunFailedException {
        if (this.leetAssertion.assertResult(actual, expected))  {
            success("Test passed successfully");
        } else {
            throw new LeetRunFailedException("Expected : " + expected + " but got : " + actual);
        }
    }
}
