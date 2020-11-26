package november.no98;

public class ValidateBinarySearchTree {
    private static TreeNode prev = null;

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        TreeNode root2 = new TreeNode(5, new TreeNode(1), new TreeNode(4, new TreeNode(3), new TreeNode(6)));
        TreeNode root3 = new TreeNode(0);
        TreeNode root4 = new TreeNode(Integer.MAX_VALUE, new TreeNode(Integer.MAX_VALUE), null);
        System.out.println(isValidBST2(root1));
        System.out.println(isValidBST2(root2));
        System.out.println(isValidBST2(root3));
        System.out.println(isValidBST2(root4));
    }

    private static boolean isValidBST2(TreeNode root) {
//        recusive
        return validate(root, null, null);
    }

    private static boolean validate(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }
        if (min != null && min.val >= root.val) {
            return false;
        }
        if (max != null && max.val <= root.val) {
            return false;
        }
        return validate(root.left, min, root) && validate(root.right, root, max);
    }

    private static boolean isValidBST1(TreeNode root) {
//        if the tree is BST, then its inorder traversal(中序遍历) should be ascending
//        recursive, time is O(n), space is O(n)
        prev = null;
        return inorderRecursive(root);
    }

    private static boolean inorderRecursive(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean leftResult = inorderRecursive(root.left);
        if (prev != null && prev.val >= root.val) {
            return false;
        }
        prev = root;
        boolean rightResult = inorderRecursive(root.right);
        return leftResult && rightResult;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
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
示例 1:

输入:
    2
   / \
  1   3
输出: true
示例 2:

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