package leetcode.a;

import leetcode.old.TreeNode;

import java.util.LinkedList;
import java.util.Objects;

public class KthSmallest {
    public int kthSmallest(TreeNode root, int k) {
        LinkedList<TreeNode> treeNodeStack = new LinkedList<>();
        while (Objects.nonNull(root) || !treeNodeStack.isEmpty()) {
            if (Objects.isNull(root)) {
                root = treeNodeStack.pop();
                k--;

                if (k == 0) {
                    return root.val;
                }

                root = root.right;

                continue;
            }

            treeNodeStack.push(root);
            root = root.left;
        }

        return -1;
    }
}
