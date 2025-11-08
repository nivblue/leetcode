package leetcode.exercises;

import leetcode.LeetInfra.LeetClass;
import leetcode.LeetInfra.LeetRun;
import leetcode.LeetInfra.annotations.LeetCodeToRun;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@LeetCodeToRun(enabled = false)
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

        // 4
        int[][] input4 = new int[][]{{1,4},{1,4}};
        int[][] output4 = new int[][]{{1,4}};

        // 5
        int[][] input5 = new int[][]{{1,4},{1,5}};
        int[][] output5 = new int[][]{{1,5}};

        LeetRun leetRun = new LeetRun("First case", () -> merge(input), expected);
        LeetRun leetRun2 = new LeetRun("Second case", () -> merge(input2), output2);
        LeetRun leetRun3 = new LeetRun("Third case", () -> merge(input3), output3);
        LeetRun leetRun4 = new LeetRun("4 case", () -> merge(input4), output4);
        LeetRun leetRun5 = new LeetRun("5 case", () -> merge(input5), output5);

        return List.of(
                leetRun,
                leetRun2,
                leetRun3,
                leetRun4,
                leetRun5
        );
    }

    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }

        TreeMap<Integer, Integer> sortedIntervalsMap = new TreeMap<>();
        for (int[] pair : intervals) {
            int newEnd = pair[1];
            sortedIntervalsMap.putIfAbsent(pair[0], newEnd);
            sortedIntervalsMap.computeIfPresent(pair[0], (key, end) -> end > newEnd ? end : newEnd);
        }

        int[][] result = new int[intervals.length][2];
        int resultIndex = -1;

        for (Map.Entry<Integer, Integer> intervalPair : sortedIntervalsMap.entrySet()) {
            int start = intervalPair.getKey(); // must be >= lastStart (because sorted)
            int end = intervalPair.getValue(); // must be > start

            if (resultIndex == -1 || start > result[resultIndex][1]) {
                resultIndex++;
                result[resultIndex] = new int[] {start, end};
            } else {
                result[resultIndex][1] = Math.max(result[resultIndex][1], end);
            }
        }

        return Arrays.copyOf(result, resultIndex+1);
    }
}
