package leetcode.a;

import java.util.ArrayList;
import java.util.List;

public class getPermutation {
    /**
     * time - 97.99 %
     * space - 43.81 %
     * */
    public static String getPermutation(int n, int k) {
        List<Integer> numbers = getNumbers(n);
        StringBuilder result = new StringBuilder();

        while (n > 1) {
            int batchSize = factorial(n-1);
            int index = k / batchSize;
            if (k % batchSize == 0) {
                index--;
            }

            result.append(numbers.get(index));
            numbers.remove(index);

            k = k - (index * batchSize);
            n--;
        }

        result.append(numbers.get(0));

        return result.toString();
    }

    private static List<Integer> getNumbers(int n) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }

        return numbers;
    }

    private static int factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}
