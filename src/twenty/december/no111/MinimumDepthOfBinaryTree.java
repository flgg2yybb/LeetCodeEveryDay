package twenty.december.no111;

public class MinimumDepthOfBinaryTree {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        TreeNode root2 = null;
        TreeNode root3 = new TreeNode(1, new TreeNode(2, new TreeNode(3, new TreeNode(4, new TreeNode(5), null), null), null), null);
        System.out.println(minDepth(root1));
        System.out.println(minDepth(root2));
        System.out.println(minDepth(root3));
    }

    private static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getMinDepth(root, 1);
    }

    private static int getMinDepth(TreeNode root, int depth) {
        if (root.left == null && root.right == null) {
            return depth;
        }
        if (root.left != null && root.right != null) {
            return Math.min(getMinDepth(root.left, depth + 1), getMinDepth(root.right, depth + 1));
        }
        return root.left == null ? getMinDepth(root.right, depth + 1) : getMinDepth(root.left, depth + 1);
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
* 给定一个二叉树，找出其最小深度。

最小深度是从根节点到最近叶子节点的最短路径上的节点数量。

说明：叶子节点是指没有子节点的节点。

 

示例 1：


输入：root = [3,9,20,null,null,15,7]
输出：2
示例 2：

输入：root = [2,null,3,null,4,null,5,null,6]
输出：5
 

提示：

树中节点数的范围在 [0, 105] 内
-1000 <= Node.val <= 1000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
