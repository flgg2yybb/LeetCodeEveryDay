package twenty.december.no104;

public class MaximumDepthOfBinaryTree {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        TreeNode root2 = null;
        TreeNode root3 = new TreeNode(1, new TreeNode(2, new TreeNode(3, new TreeNode(4, new TreeNode(5), null), null), null), null);
        System.out.println(maxDepth(root1));
        System.out.println(maxDepth(root2));
        System.out.println(maxDepth(root3));
    }

    private static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getMaxDepth(root, 1);
    }

    private static int getMaxDepth(TreeNode root, int depth) {
        if (root.left == null && root.right == null) {
            return depth;
        }
        if (root.left != null && root.right != null) {
            return Math.max(getMaxDepth(root.left, depth + 1), getMaxDepth(root.right, depth + 1));
        }
        return root.left == null ? getMaxDepth(root.right, depth + 1) : getMaxDepth(root.left, depth + 1);
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
* 给定一个二叉树，找出其最大深度。

二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

说明: 叶子节点是指没有子节点的节点。

示例：
给定二叉树 [3,9,20,null,null,15,7]，

    3
   / \
  9  20
    /  \
   15   7
返回它的最大深度 3 。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
