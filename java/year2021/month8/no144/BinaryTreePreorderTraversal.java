package year2021.month8.no144;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreePreorderTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,
                null,
                new TreeNode(2,
                        new TreeNode(3),
                        null));
        System.out.println(preorderTraversal(root));
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode p = root;
        Deque<TreeNode> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                res.add(p.val);
                stack.push(p);
                p = p.left;
            }
            TreeNode node = stack.pop();
            p = node.right;
        }
        return res;
    }

    public static List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }

    private static void dfs(TreeNode root, List<Integer> res) {
        if (root != null) {
            res.add(root.val);
            dfs(root.left, res);
            dfs(root.right, res);
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
* 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。

 

示例 1：


输入：root = [1,null,2,3]
输出：[1,2,3]
示例 2：

输入：root = []
输出：[]
示例 3：

输入：root = [1]
输出：[1]
示例 4：


输入：root = [1,2]
输出：[1,2]
示例 5：


输入：root = [1,null,2]
输出：[1,2]
 

提示：

树中节点数目在范围 [0, 100] 内
-100 <= Node.val <= 100
 

进阶：递归算法很简单，你可以通过迭代算法完成吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
