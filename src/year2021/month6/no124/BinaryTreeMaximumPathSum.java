package year2021.month6.no124;

public class BinaryTreeMaximumPathSum {

    private static int max = 0;

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1,
                new TreeNode(2),
                new TreeNode(3));
        TreeNode root2 = new TreeNode(-10,
                new TreeNode(9),
                new TreeNode(20,
                        new TreeNode(15),
                        new TreeNode(7)));
        System.out.println(maxPathSum(root1));
        System.out.println(maxPathSum(root2));
    }

    public static int maxPathSum(TreeNode root) {
        max = Integer.MIN_VALUE;
        dfsFindMaxPathSum(root);
        return max;
    }

    private static int dfsFindMaxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int rootVal = root.val;
        int leftVal = dfsFindMaxPathSum(root.left);
        int rightVal = dfsFindMaxPathSum(root.right);
        int rootPathVal = rootVal + leftVal + rightVal;
        int maxChildPathVal = Math.max(rootVal, rootVal + Math.max(leftVal, rightVal));
        max = Math.max(max, Math.max(rootPathVal, maxChildPathVal));
        return maxChildPathVal;
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
* 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。

路径和 是路径中各节点值的总和。

给你一个二叉树的根节点 root ，返回其 最大路径和 。

 

示例 1：


输入：root = [1,2,3]
输出：6
解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
示例 2：


输入：root = [-10,9,20,null,null,15,7]
输出：42
解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 

提示：

树中节点数目范围是 [1, 3 * 104]
-1000 <= Node.val <= 1000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
