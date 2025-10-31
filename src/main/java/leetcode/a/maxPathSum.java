package leetcode.a;

import leetcode.old.TreeNode;

import java.util.Objects;

public class maxPathSum {
    public int maxPathSum(TreeNode root) {
        int[] result = maxPathSubSum(root);

        return Math.max(result[0], result[1]);
    }

    public int[] maxPathSubSum(TreeNode root) {
        if (Objects.isNull(root)) {
            return new int[] { 0, 0};
        }

        if (Objects.isNull(root.left) && Objects.isNull(root.right)) {
            return new int[] { root.val, root.val };
        }

        int[] right = maxPathSubSum(root.right);
        int[] left = maxPathSubSum(root.left);

        int maxSplit = Math.max(right[0], left[0]);

        if (Objects.isNull(root.right)) {
            maxSplit = left[0];
        } else if (Objects.isNull(root.left)) {
            maxSplit = right[0];
        }

        // result
        int withSplit = Math.max(maxSplit, root.val + Math.max(left[1], 0) + Math.max(right[1], 0));
        int withoutSplit = root.val + Math.max(left[1], right[1]);

        return new int[] {withSplit, withoutSplit};
    }
}
