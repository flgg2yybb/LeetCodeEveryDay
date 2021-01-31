package year2021.month1.no114;

import java.util.ArrayList;
import java.util.List;

public class FlattenBinaryTreeToLinkedList {
    private static TreeNode pre;

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1,
                new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                new TreeNode(5, null, new TreeNode(6)));
        TreeNode root2 = null;
        TreeNode root3 = new TreeNode(0);
        flatten(root1);
        flatten(root2);
        flatten(root3);
        disp(root1);
        preOrderTraverse(root1);
        disp(root2);
        preOrderTraverse(root2);
        disp(root3);
        preOrderTraverse(root3);
    }

    public static void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        List<TreeNode> list = new ArrayList<>();
        buildPreOrderList(root, list);
        for (int i = 0; i < list.size() - 1; i++) {
            list.get(i).left = null;
            list.get(i).right = list.get(i + 1);
        }
        root = list.get(0);
    }

    private static void buildPreOrderList(TreeNode root, List<TreeNode> list) {
        if (root != null) {
            list.add(root);
            buildPreOrderList(root.left, list);
            buildPreOrderList(root.right, list);
        }
    }

    public static void disp(TreeNode root) {
        if (root == null) {
            System.out.println("EMPTY");
            return;
        }
        TreeNode node = root;
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.right;
        }
        System.out.println();
    }

    private static void preOrderTraverse(TreeNode root) {
        if (root == null) {
            System.out.println("EMPTY");
            return;
        }
        dfs(root);
        System.out.println();
    }

    private static void dfs(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            dfs(root.left);
            dfs(root.right);
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
* 给你二叉树的根结点 root ，请你将它展开为一个单链表：

展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
展开后的单链表应该与二叉树 先序遍历 顺序相同。
 

示例 1：


输入：root = [1,2,5,3,4,null,6]
输出：[1,null,2,null,3,null,4,null,5,null,6]
示例 2：

输入：root = []
输出：[]
示例 3：

输入：root = [0]
输出：[0]
 

提示：

树中结点数在范围 [0, 2000] 内
-100 <= Node.val <= 100
 

进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
