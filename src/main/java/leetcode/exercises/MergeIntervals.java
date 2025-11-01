package leetcode.exercises;

import leetcode.LeetInfra.LeetClass;
import leetcode.LeetInfra.LeetRun;
import leetcode.LeetInfra.annotations.LeetCodeToRun;

import java.util.List;

@LeetCodeToRun
public class MergeIntervals extends LeetClass<Integer[][]> {
    @Override
    protected List<LeetRun<Integer[][]>> leetRunList() {
        int[][] input = new int[][]{{1,3},{2,6},{8,10},{15,18}};
        int[][] expected = new int[][]{{1,6},{8,10},{15,18}};

        LeetRun leetRun = new LeetRun("First case", () -> merge(input), input);
//        LeetRun leetRun = new LeetRun("First case", () -> merge(input), expected);

        return List.of(
                leetRun
        );
    }

    public int[][] merge(int[][] intervals) {
        return intervals;
    }
}
