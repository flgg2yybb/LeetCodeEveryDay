package year2021.month3.no101;

import java.util.Deque;
import java.util.LinkedList;

public class SymmetricTree {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(3),
                        new TreeNode(4)),
                new TreeNode(2,
                        new TreeNode(4),
                        new TreeNode(3)));
        TreeNode root2 = new TreeNode(1,
                new TreeNode(2,
                        null,
                        new TreeNode(3)),
                new TreeNode(2,
                        null,
                        new TreeNode(3)));
        System.out.println(isSymmetric1(root1));
        System.out.println(isSymmetric1(root2));
    }

    private static boolean isSymmetric1(TreeNode root) {
        if (root == null) {
            return true;
        }
        Deque<TreeNode> leftQueue = new LinkedList<>();
        Deque<TreeNode> rightQueue = new LinkedList<>();
        leftQueue.offer(root.left);
        rightQueue.offer(root.right);
        while (!leftQueue.isEmpty() && !rightQueue.isEmpty()) {
            TreeNode left = leftQueue.poll();
            TreeNode right = rightQueue.poll();
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null || left.val != right.val) {
                return false;
            }
            leftQueue.offer(left.left);
            leftQueue.offer(left.right);
            rightQueue.offer(right.right);
            rightQueue.offer(right.left);
        }
        return true;
    }

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return dfsSymmetric(root.left, root.right);
    }

    private static boolean dfsSymmetric(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null || root1.val != root2.val) {
            return false;
        }
        return dfsSymmetric(root1.left, root2.right) && dfsSymmetric(root1.right, root2.left);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/*
 * 给定一个二叉树，检查它是否是镜像对称的。

 

例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

    1
   / \
  2   2
 / \ / \
3  4 4  3
 

但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

    1
   / \
  2   2
   \   \
   3    3
 

进阶：

你可以运用递归和迭代两种方法解决这个问题吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/symmetric-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
