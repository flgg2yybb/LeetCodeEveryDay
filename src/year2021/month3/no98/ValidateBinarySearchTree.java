package year2021.month3.no98;

public class ValidateBinarySearchTree {
    public static void main(String[] args) {
        TreeNode root1 = null;
        TreeNode root2 = new TreeNode(2,
                new TreeNode(1),
                new TreeNode(3));
        TreeNode root3 = new TreeNode(5,
                new TreeNode(1),
                new TreeNode(4,
                        new TreeNode(3),
                        new TreeNode(6)));
        TreeNode root4 = new TreeNode(5,
                new TreeNode(1),
                new TreeNode(7,
                        new TreeNode(3),
                        new TreeNode(8)));
        System.out.println(isValidBST(root1));
        System.out.println(isValidBST(root2));
        System.out.println(isValidBST(root3));
        System.out.println(isValidBST(root4));
    }

    private static long prev = Long.MIN_VALUE;

    private static boolean isValidBST(TreeNode root) {
        // BST中序遍历为递增序列
        if (root == null) {
            return true;
        }
        if (!isValidBST(root.left)) {
            return false;
        }
        if (root.val <= prev) {
            return false;
        }
        prev = root.val;
        return isValidBST(root.right);
    }

    public static boolean isValidBST1(TreeNode root) {
        return dfsValidBST(root, Long.MAX_VALUE, Long.MIN_VALUE);
    }

    private static boolean dfsValidBST(TreeNode root, long max, long min) {
        if (root == null) {
            return true;
        }
        if (root.val >= max || root.val <= min) {
            return false;
        }
        boolean left = dfsValidBST(root.left, root.val, min);
        boolean right = dfsValidBST(root.right, max, root.val);
        return left && right;
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
* 给定一个二叉树，判断其是否是一个有效的二叉搜索树。

假设一个二叉搜索树具有如下特征：

节点的左子树只包含小于当前节点的数。
节点的右子树只包含大于当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。
示例1:

输入:
    2
   / \
  1   3
输出: true
示例2:

输入:
    5
   / \
  1   4
    / \
   3   6
输出: false
解释: 输入为: [5,1,4,null,null,3,6]。
    根节点的值为 5 ，但是其右子节点值为 4 。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/validate-binary-search-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
