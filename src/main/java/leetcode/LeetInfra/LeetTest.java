package leetcode.LeetInfra;

import java.util.Map;
import java.util.function.Supplier;

@LeetCodeToRun
public class LeetTest extends LeetClass<Integer> {
    @Override
    Map<Supplier<Integer>, Integer> runToResultMap() {
        return Map.ofEntries(
                Map.entry(() -> add(2,4), 6),
                Map.entry(() -> add(2,4), 5),
                Map.entry(() -> add(3,4), 7)
        );
    }

    public int add(int num1, int num2) {
        return num1 + num2;
    }
}
