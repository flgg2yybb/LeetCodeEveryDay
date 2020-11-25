package november.no98;

import java.util.ArrayList;
import java.util.List;

public class ValidateBinarySearchTree {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        TreeNode root2 = new TreeNode(5, new TreeNode(1), new TreeNode(4, new TreeNode(3), new TreeNode(6)));
        System.out.println(isValidBST1(root1));
        System.out.println(isValidBST1(root2));
    }

    private static boolean isValidBST1(TreeNode root) {
        if (root == null) {
            return true;
        }
//        if the tree is BST, then its inorder traversal(中序遍历) should be ascending
//        recursive, time is O(n), space is O(n)
        List<Integer> result = new ArrayList<>();
        inorderTraverse(root, result);
        int i = 0;
        for (; i < result.size() - 1; i++) {
            if (result.get(i) >= result.get(i + 1)) {
                break;
            }
        }
        return i + 1 == result.size();
    }

    private static void inorderTraverse(TreeNode root, List<Integer> result) {
        if (root != null) {
            inorderTraverse(root.left, result);
            result.add(root.val);
            inorderTraverse(root.right, result);
        }
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