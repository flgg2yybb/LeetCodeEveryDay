package year2021.month1.no94;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeInorderTraversal {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null));
        TreeNode root2 = null;
        TreeNode root3 = new TreeNode(1);
        TreeNode root4 = new TreeNode(1, new TreeNode(2), null);
        TreeNode root5 = new TreeNode(1, null, new TreeNode(2));
        System.out.println(inorderTraversal1(root1));
//        System.out.println(inorderTraversal1(root2));
//        System.out.println(inorderTraversal1(root3));
//        System.out.println(inorderTraversal1(root4));
//        System.out.println(inorderTraversal1(root5));
    }

    private static List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            result.add(node.val);
            node = node.right;
        }
        return result;
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, result);
        return result;
    }

    private static void dfs(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        dfs(root.left, result);
        result.add(root.val);
        dfs(root.right, result);
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
* 给定一个二叉树的根节点 root ，返回它的 中序遍历。



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


进阶:递归算法很简单，你可以通过迭代算法完成吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
