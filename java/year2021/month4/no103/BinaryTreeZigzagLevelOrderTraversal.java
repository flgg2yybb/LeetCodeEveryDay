package year2021.month4.no103;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeZigzagLevelOrderTraversal {

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20,
                        new TreeNode(15),
                        new TreeNode(7)));
        TreeNode root2 = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4),
                        null),
                new TreeNode(3,
                        null,
                        new TreeNode(5)));
        System.out.println(zigzagLevelOrder1(root1));
        System.out.println(zigzagLevelOrder1(root2));
    }

    private static List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();
            Deque<Integer> levelResult = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode pop = queue.poll();
                assert pop != null;
                // 根据层数判断是否倒序添加
                if ((level & 1) == 1) {
                    levelResult.offerLast(pop.val);
                } else {
                    levelResult.offerFirst(pop.val);
                }
                if (pop.left != null) {
                    queue.offer(pop.left);
                }
                if (pop.right != null) {
                    queue.offer(pop.right);
                }
            }
            result.add(new LinkedList<>(levelResult));
        }
        return result;
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();
            List<Integer> levelResult = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                TreeNode pop = queue.poll();
                assert pop != null;
                levelResult.add(pop.val);
                if (pop.left != null) {
                    queue.offer(pop.left);
                }
                if (pop.right != null) {
                    queue.offer(pop.right);
                }
            }
            if ((level & 1) == 0) {
                // 偶数层反转结果
                Collections.reverse(levelResult);
            }
            result.add(levelResult);
        }
        return result;
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
* 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

例如：
给定二叉树 [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回锯齿形层序遍历如下：

[
  [3],
  [20,9],
  [15,7]
]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
