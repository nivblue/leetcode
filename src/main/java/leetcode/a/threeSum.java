package leetcode.a;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class threeSum {

    private static void help() {
        //        int[] nums = new int[] {1,-1, -1, 0, 2}; // [[-1, -1, 2], [1, -1, 0]]
//        int[] nums = new int[] {-1,0,1,2,-1,-4}; // [[-1,-1,2],[-1,0,1]]
        int[] nums = new int[] {2,-3,0,-2,-5,-5,-4,1,2,-2,2,0,2,-4,5,5,-10}; // [[-10,5,5],[-5,0,5],[-4,2,2],[-3,-2,5],[-3,1,2],[-2,0,2]]
//        int[] nums = new int[]{-1,-1,3};

        System.out.println(threeSum(nums));
        System.out.println("[[-10, 5, 5], [-5, 0, 5], [-4, 2, 2], [-3, -2, 5], [-3, 1, 2], [-2, 0, 2]]");
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return Collections.emptyList();
        }

        // sort first
        Arrays.sort(nums);

        if (nums[0] > 0 || nums[nums.length - 1] < 0) return Collections.emptyList();

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0;  nums[i] <= 0 && i < nums.length - 2; i++) {
            int a = nums[i];
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }

            int j = i+1;
            int e = nums.length - 1;
            while (j < e) {
                int sum = a + nums[j] + nums[e];
                if (sum == 0) {
                    result.add(List.of(nums[i], nums[j], nums[e]));
                    j++;
                    e--;
                    while (j < e && nums[j] == nums[j - 1]) j++;
                    while (j < e && nums[e] == nums[e + 1]) e--;
                } else if (sum < 0) {
                    j++;
                } else {
                    e--;
                }
            }
        }

        return result;
    }
}
