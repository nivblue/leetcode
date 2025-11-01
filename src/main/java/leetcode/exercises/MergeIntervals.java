package leetcode.exercises;

import leetcode.LeetInfra.LeetClass;
import leetcode.LeetInfra.LeetRun;
import leetcode.LeetInfra.annotations.LeetCodeToRun;

import java.util.List;

@LeetCodeToRun
public class MergeIntervals extends LeetClass<Integer[][]> {
    @Override
    protected List<LeetRun<Integer[][]>> leetRunList() {

        // 1
        int[][] input = new int[][]{{1,3},{2,6},{8,10},{15,18}};
        int[][] expected = new int[][]{{1,6},{8,10},{15,18}};

        // 2
        int[][] input2 = new int[][]{{1,4},{4,5}};
        int[][] output2 = new int[][]{{1,5}};

        // 3
        int[][] input3 = new int[][]{{4,7},{1,4}};
        int[][] output3 = new int[][]{{1,7}};

        LeetRun leetRun = new LeetRun("First case", () -> merge(input), input);
        LeetRun leetRun2 = new LeetRun("Second case", () -> merge(input2), output2);
        LeetRun leetRun3 = new LeetRun("Third case", () -> merge(input3), output3);
//        LeetRun leetRun = new LeetRun("First case", () -> merge(input), expected);

        return List.of(
                leetRun
        );
    }

    public int[][] merge(int[][] intervals) {
        return intervals;
    }
}
