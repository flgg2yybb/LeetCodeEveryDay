package year2021.month10.no114;

public class FlattenBinaryTreeToLinkedList {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(3),
                        new TreeNode(4)),
                new TreeNode(5,
                        null,
                        new TreeNode(6)));
        flatten(root);
        disp(root);
    }

    public static void flatten(TreeNode root) {
        /*
         * 算法：
         * 对于一颗树，需要做三件事即可将树拉平为链表
         * 1. 将 root 的左子树和右子树拉平
         * 2. 将 root 的右子树接到 root 的左子树的最下方
         * 3. 将 root 的左子树变为右子树
         * */
        if (root == null) {
            return;
        }
        // 将 root 的左子树和右子树拉平，左右子树已经被拉平成一条链表
        flatten(root.left);
        flatten(root.right);
        // 将 root 的左子树变为右子树
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.right = left;
        root.left = null;
        // 将 root 原先右子树接到 root 的当前右子树的最下方
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    }

    private static void disp(TreeNode root) {
        if (root != null) {
            System.out.println(root.val + " ");
            disp(root.right);
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
