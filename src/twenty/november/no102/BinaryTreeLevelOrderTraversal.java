package twenty.november.no102;

import java.util.*;

public class BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(5), new TreeNode(7)));
        TreeNode root2 = new TreeNode(1, new TreeNode(2, new TreeNode(3, new TreeNode(4, new TreeNode(5, new TreeNode(6), null), null), null), null), null);

        List<List<Integer>> result = levelOrder(root2);
        result.forEach(System.out::println);
    }

    private static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offerLast(root);
        TreeNode last = root;
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> levelResult = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            levelResult.add(node.val);
            if (node.left != null) {
                queue.offerLast(node.left);
            }
            if (node.right != null) {
                queue.offerLast(node.right);
            }
            if (node == last) {
                result.add(levelResult);
                levelResult = new ArrayList<>();
                last = node.right != null ? node.right : node.left != null ? node.left : queue.peekLast();
            }

        }
        return result;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/*
* 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。

 

示例：
二叉树：[3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回其层次遍历结果：

[
  [3],
  [9,20],
  [15,7]
]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
