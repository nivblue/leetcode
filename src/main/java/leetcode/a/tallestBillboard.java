package leetcode.a;

public class tallestBillboard {
    public static int tallestBillboard(int[] rods) {
        int max = 0;
        int sum = 0;

        // Sum all the rods to get to the number
        for (int i : rods) {
            sum += i;
        }

        max = sum / 2;

        int[] sortedRods = sortArray(rods);



        return max;
    }

    private static int[] sortArray(int[] rods) {
        // implement sort logic
        for (int i = 0; i < rods.length; i++) {
            for (int j = 0; j < rods.length; j++) {
                if (rods[i] < rods[j]) {
                    int temp = rods[i];
                    rods[i] = rods[j];
                    rods[j] = temp;
                }
            }
        }

        return rods;
    }
}
