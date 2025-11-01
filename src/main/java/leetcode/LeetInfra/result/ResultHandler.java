package leetcode.LeetInfra.result;

import leetcode.LeetInfra.LeetRunFailedException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResultHandler<TOUTPUT> {

    private LeetAssertion leetAssertion;

    public ResultHandler() {
        leetAssertion = new LeetAssertion();
    }

    public void handleTestResult(TOUTPUT actual, TOUTPUT expected) throws LeetRunFailedException {
        if (this.leetAssertion.assertResult(actual, expected))  {
            System.out.println("Test passed successfully");
        } else {
            throw new LeetRunFailedException("Expected : " + expected + " but got : " + actual);
        }
    }
}
