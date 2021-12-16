package year2021.month12.no114;

public class FlattenBinaryTreeToLinkedList {

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(3),
                        new TreeNode(4)),
                new TreeNode(5,
                        null,
                        new TreeNode(6)));
        TreeNode root2 = null;
        TreeNode root3 = new TreeNode(0);
        flatten(root1);
        flatten(root2);
        flatten(root3);
        disp(root1);
        disp(root2);
        disp(root3);
    }

    public static void flatten(TreeNode root) {
        // 递归方法定义为将某一个树节点做 flatten 操作
        // 首先将其左、右子树做 flatten
        // 其次记录当前右子树 right ，并将右子树指针指向左子树 left，左子树指针置为空
        // 再遍历原树，找到最右的尾节点，将其右指针指向右子树right即可
        if (root == null) {
            return;
        }
        flatten(root.left);
        flatten(root.right);
        TreeNode right = root.right;
        root.right = root.left;
        root.left = null;
        TreeNode rear = root;
        while (rear.right != null) {
            rear = rear.right;
        }
        rear.right = right;
    }

    private static void disp(TreeNode root) {
        for (TreeNode p = root; p != null; p = p.right) {
            System.out.print(p.val + " ");
        }
        System.out.println();
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