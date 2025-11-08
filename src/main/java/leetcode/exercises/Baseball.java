package leetcode.exercises;

import leetcode.LeetInfra.LeetClass;
import leetcode.LeetInfra.LeetRun;
import leetcode.LeetInfra.annotations.LeetCodeToRun;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

@LeetCodeToRun
public class Baseball extends LeetClass<Integer> {
    @Override
    protected List<LeetRun<Integer>> leetRunList() {
        String[] operations1 = new String[]{"5","2","C","D","+"};
        int solution1 = 30;

        String[] operations2 = new String[]{"5","-2","4","C","D","9","+","+"};
        int solution2 = 27;

        String[] operations3 = new String[]{"1","C"};
        int solution3 = 0;

        return List.of(
                new LeetRun<>("First run", () -> calPoints(operations1), solution1, false),
                new LeetRun<>("Second run", () -> calPoints(operations2), solution2),
                new LeetRun<>("Third run", () -> calPoints(operations3), solution3, false)
        );
    }

    public int calPoints(String[] operations) {
        if (operations.length == 0) {
            return 0;
        }

        LinkedList<Integer> stack = new LinkedList<>();

        for (String str : operations) {
            if (str.equals("C")) {
                stack.pop();
            } else if (str.equals("D")) {
                int lastNumber = stack.peek();

                stack.push(lastNumber * 2);
            } else if (str.equals("+")) {
                int lastNumber = stack.peek();
                int secondLastNumber = stack.get(1);

                stack.push(lastNumber + secondLastNumber);
            } else { // asuming number
                stack.push(Integer.valueOf(str));
            }
        }

        int sum = 0;

        while (!stack.isEmpty()) {
            sum += stack.pop();
        }

        return sum;
    }
}
