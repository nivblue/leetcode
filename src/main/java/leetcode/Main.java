package leetcode;

import leetcode.LeetInfra.LeetRunFailedException;
import leetcode.LeetInfra.LeetRunner;
import leetcode.LeetInfra.result.LeetAssertion;

import static leetcode.LeetInfra.consts.Constants.DEFAULT_EXERCISES_DIR;

public class Main {
    public static void main(String args[]) throws LeetRunFailedException {
        LeetRunner leetRunner = new LeetRunner(DEFAULT_EXERCISES_DIR);

        LeetAssertion leetAssertion = new LeetAssertion();
    }
}