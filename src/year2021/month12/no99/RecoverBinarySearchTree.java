package year2021.month12.no99;

public class RecoverBinarySearchTree {

    private static TreeNode prev = null;
    private static TreeNode first = null;
    private static TreeNode second = null;

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1,
                new TreeNode(3,
                        null,
                        new TreeNode(2)),
                null);
        TreeNode root2 = new TreeNode(3,
                new TreeNode(1),
                new TreeNode(4,
                        new TreeNode(2),
                        null));
        inorderPrint(root1);
        recoverTree(root1);
        inorderPrint(root1);
        inorderPrint(root2);
        recoverTree(root2);
        inorderPrint(root2);
    }

    public static void recoverTree(TreeNode root) {
        // 正常情况下中序遍历值递增，对 root 做中序遍历
        // 记录出 prev.val > root.val 的第一个节点和最后一个节点
        // 再交换节点值即可
        prev = null;
        dfs(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private static void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (prev != null && prev.val > root.val) {
            if (first == null) {
                first = prev;
            }
            second = root;
        }
        prev = root;
        dfs(root.right);
    }

    private static void inorderPrint(TreeNode root) {
        dfsPrint(root);
        System.out.println();
    }

    private static void dfsPrint(TreeNode root) {
        if (root != null) {
            dfsPrint(root.left);
            System.out.print(root.val + " ");
            dfsPrint(root.right);
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
 * 给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。

 

示例 1：


输入：root = [1,3,null,null,2]
输出：[3,1,null,null,2]
解释：3 不能是 1 左孩子，因为 3 > 1 。交换 1 和 3 使二叉搜索树有效。
示例 2：


输入：root = [3,1,4,null,null,2]
输出：[2,1,4,null,null,3]
解释：2 不能在 3 的右子树中，因为 2 < 3 。交换 2 和 3 使二叉搜索树有效。
 

提示：

树上节点的数目在范围 [2, 1000] 内
-231 <= Node.val <= 231 - 1
 

进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用 O(1) 空间的解决方案吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/recover-binary-search-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
