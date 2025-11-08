package leetcode.exercises;

import leetcode.LeetInfra.LeetClass;
import leetcode.LeetInfra.LeetRun;
import leetcode.LeetInfra.annotations.LeetCodeToRun;

import java.util.*;

@LeetCodeToRun(enabled = false)
public class EraseOverlapingIntervals extends LeetClass {
    @Override
    protected List<LeetRun> leetRunList() {
        int[][] input = new int[][]{{1,2},{2,3},{3,4},{1,3}};
        int output = 1;

        int[][] input2 = new int[][]{{1,2},{1,2},{1,2}};
        int output2 = 2;

        int[][] input3 = new int[][]{{1,2},{2,3}};
        int output3 = 0;

        int[][] input4 = new int[][]{{1,100},{11,22},{1,11},{2,12}};
        int output4 = 2;

        int[][] input5 = new int[][]{{1,2},{2,3},{3,4},{-100,-2},{5,7}};
        int output5 = 0;

        int[][] input6 = new int[][]{{-52,31},{-73,-26},{82,97},{-65,-11},{-62,-49},{95,99},{58,95},{-31,49},{66,98},{-63,2},{30,47},{-40,-26}};
        int output6 = 7;

        return List.of(
                new LeetRun("First", () -> eraseOverlapIntervals(input), output),
                new LeetRun("Second", () -> eraseOverlapIntervals(input2), output2),
                new LeetRun("Third", () -> eraseOverlapIntervals(input3), output3),
                new LeetRun("Four", () -> eraseOverlapIntervals(input4), output4),
                new LeetRun("Five", () -> eraseOverlapIntervals(input5), output5),
                new LeetRun("six", () -> eraseOverlapIntervals(input6), output6)
        );
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int solution = 0;
        int prevEnd = intervals[0][1];
        for (int i = 0; i < intervals.length; i++) {
            int[] currentInterval = intervals[i];
            if (i == 0 || currentInterval[0] >= prevEnd) {
                prevEnd = currentInterval[1];
            } else {
                solution++;
                prevEnd = Math.min(prevEnd, currentInterval[1]);
            }
        }

        return solution;
    }
}
