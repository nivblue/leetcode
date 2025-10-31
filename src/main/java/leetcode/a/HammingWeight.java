package leetcode.a;

public class HammingWeight {
    public static void help(String args[]) {
//        int n = 11; // 3
//        int n = 128; // 1
        int n = 2147483645; // 30

        System.out.println(hammingWeight(n));
    }

    public static int hammingWeight(int n) {
        int count = 0;
        while (n > 0) {
            if (n % 2 == 1)  count++;

            n /= 2;
        }

        return count;
    }
}
