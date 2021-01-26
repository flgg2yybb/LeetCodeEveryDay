package year2021.month1.no145;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreePostorderTraversal {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null));
        TreeNode root2 = null;
        TreeNode root3 = new TreeNode(1);
        TreeNode root4 = new TreeNode(1, new TreeNode(2), null);
        TreeNode root5 = new TreeNode(1, null, new TreeNode(2));
        System.out.println(postorderTraversal1(root1));
        System.out.println(postorderTraversal1(root2));
        System.out.println(postorderTraversal1(root3));
        System.out.println(postorderTraversal1(root4));
        System.out.println(postorderTraversal1(root5));
    }

    private static List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<Object> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Object pop = stack.pop();
            if (pop == null) {
                continue;
            }
            if (pop instanceof Integer) {
                Integer value = (Integer) pop;
                result.add(value);
                continue;
            }
            TreeNode node = (TreeNode) pop;
            stack.push(node.val);
            stack.push(node.right);
            stack.push(node.left);
        }
        return result;
    }

    private static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, result);
        return result;
    }

    private static void dfs(TreeNode root, List<Integer> result) {
        if (root != null) {
            dfs(root.left, result);
            dfs(root.right, result);
            result.add(root.val);
        }
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
* 给定一个二叉树，返回它的 后序遍历。

示例:

输入: [1,null,2,3]  
   1
    \
     2
    /
   3 

输出: [3,2,1]
进阶:递归算法很简单，你可以通过迭代算法完成吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
