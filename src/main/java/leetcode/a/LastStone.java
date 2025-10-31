package leetcode.a;

import java.util.Arrays;
import java.util.LinkedList;

public class LastStone {
    public static void test() {
        int[] stones =  new int[] {2,7,4,1,8,1}; // 1
        System.out.println(lastStoneWeight(stones) + ", expected : 1");

        stones =  new int[] {2,2}; // 0
        System.out.println(lastStoneWeight(stones) + ", expected : 0");

        stones = new int[] {1, 3}; // 2
        System.out.println(lastStoneWeight(stones) + ", expected : 2");

        stones =  new int[] {1}; // 1
        System.out.println(lastStoneWeight(stones) + ", expected : 1");
    }

    public static int lastStoneWeight(int[] stones) {
        if (stones.length == 0) return 0;
        if (stones.length == 1) return 1;

        Arrays.sort(stones);
        LinkedList<Integer> sortedStones = new LinkedList<>();
        for(int s : stones) sortedStones.add(s);

        while (sortedStones.size() > 1) {
            int biggest = sortedStones.removeLast();
            int second = sortedStones.removeLast();

            int diff = biggest - second;

            if (sortedStones.isEmpty()) return diff;

            if (diff > 0) {
                int i = 0;
                while (i < sortedStones.size() && sortedStones.get(i) < diff) i++;

                sortedStones.add(i, diff);
            }
        }

        if (sortedStones.isEmpty()) return 0;

        return sortedStones.get(0);
    }
}
