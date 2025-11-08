package leetcode;

import leetcode.LeetInfra.LeetRunFailedException;
import leetcode.LeetInfra.LeetRunner;
import leetcode.LeetInfra.result.LeetAssertion;

import java.util.List;
import java.util.Map;

import static leetcode.LeetInfra.consts.Constants.DEFAULT_EXERCISES_DIR;

public class Main {
    public static void main(String args[]) throws LeetRunFailedException, ClassNotFoundException {
//        LeetRunner leetRunner = new LeetRunner(DEFAULT_EXERCISES_DIR);
//
//        leetRunner.runLeets();


        LeetAssertion leetAssertion = new LeetAssertion();

        int[] actual = {1, 2, 3};
        int[] expected = {1, 2, 3};
        System.out.println(leetAssertion.assertResult(actual, expected));

        List<Integer> actualList = List.of(1, 2, 3);
        List<Integer> expectedList = List.of(1, 2, 3);
        System.out.println(leetAssertion.assertResult(actualList, expectedList));

        Map<Integer, Integer> actualMap = Map.of(1, 1, 2, 2, 3 , 3);
        Map<Integer, Integer> expectedMap = Map.of(1, 1, 2, 2, 3 , 3);
        System.out.println(leetAssertion.assertResult(actualMap, expectedMap));


        // failing

        expected = new int[]{1, 2, 4};
        System.out.println(leetAssertion.assertResult(actual, expected));


        expectedList = List.of(1, 5, 3);
        System.out.println(leetAssertion.assertResult(actualList, expectedList));


        expectedMap = Map.of(1, 1, 3 , 3);
        System.out.println(leetAssertion.assertResult(actualMap, expectedMap));


        System.out.println("null = " + leetAssertion.assertResult(null, null));
        System.out.println("null = " + leetAssertion.assertResult(null, 123));
        System.out.println("null = " + leetAssertion.assertResult("heelo world", null));
    }
}