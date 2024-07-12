package year2021.month4.no94;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeInorderTraversal {

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1,
                null,
                new TreeNode(2,
                        new TreeNode(3),
                        null));
        TreeNode root2 = null;
        TreeNode root3 = new TreeNode(1);
        TreeNode root4 = new TreeNode(1,
                new TreeNode(2),
                null);
        TreeNode root5 = new TreeNode(1,
                null,
                new TreeNode(2));
        System.out.println(inorderTraversal2(root1));
        System.out.println(inorderTraversal2(root2));
        System.out.println(inorderTraversal2(root3));
        System.out.println(inorderTraversal2(root4));
        System.out.println(inorderTraversal2(root5));

    }

    private static List<Integer> inorderTraversal2(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>();
        Deque<Object> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Object pop = stack.pop();
            if (pop instanceof Integer) {
                result.add((Integer) pop);
                continue;
            }
            TreeNode node = (TreeNode) pop;
            if (node.right != null) {
                stack.push(node.right);
            }
            stack.push(node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return result;
    }

    private static List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode p = root;
        Deque<TreeNode> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            TreeNode pop = stack.pop();
            result.add(pop.val);
            p = pop.right;
        }
        return result;
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, result);
        return result;
    }

    private static void dfs(TreeNode root, List<Integer> result) {
        if (root != null) {
            dfs(root.left, result);
            result.add(root.val);
            dfs(root.right, result);
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
* 给定一个二叉树的根节点 root ，返回它的 中序 遍历。

 

示例 1：


输入：root = [1,null,2,3]
输出：[1,3,2]
示例 2：

输入：root = []
输出：[]
示例 3：

输入：root = [1]
输出：[1]
示例 4：


输入：root = [1,2]
输出：[2,1]
示例 5：


输入：root = [1,null,2]
输出：[1,2]
 

提示：

树中节点数目在范围 [0, 100] 内
-100 <= Node.val <= 100
 

进阶: 递归算法很简单，你可以通过迭代算法完成吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */