package leetcode;

import leetcode.LeetInfra.LeetRunFailedException;
import leetcode.LeetInfra.LeetRunner;
import leetcode.LeetInfra.utils.StringUtils;

import static leetcode.LeetInfra.consts.Constants.DEFAULT_EXERCISES_DIR;

public class Main {
    public static void main(String args[]) throws LeetRunFailedException, ClassNotFoundException {
        LeetRunner leetRunner = new LeetRunner(DEFAULT_EXERCISES_DIR);

//        LeetAssertion leetAssertion = new LeetAssertion();

//        leetRunner.runLeets();


        System.out.println(StringUtils.toJavaArray("[[1,4],[4,5]]", "int", 2, "input"));
        System.out.println(StringUtils.toJavaArray("[[1,5]]", "int", 2, "output"));

        System.out.println(StringUtils.toJavaArray("[[4,7],[1,4]]", "int",2, "input"));
        System.out.println(StringUtils.toJavaArray("[[1,7]]", "int", 2, "output"));
    }
}