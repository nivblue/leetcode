package leetcode.LeetInfra;

import java.util.List;

@LeetCodeToRun
public class LeetDemo extends LeetClass<Integer> {
    @Override
    List<LeetRun<Integer>> leetRunList() {
        return List.of(
                new LeetRun<>("Adding 2 & 4 case", () -> add(2,4), 6),
                new LeetRun<>(() -> add(14, 28), 42)
//                new LeetRun<>("Adding 3 & 5 case", () -> add(3,5), 10),
//                new LeetRun<>(() -> add(1,3), 5)
        );
    }

    public int add(int num1, int num2) {
        return num1 + num2;
    }
}
