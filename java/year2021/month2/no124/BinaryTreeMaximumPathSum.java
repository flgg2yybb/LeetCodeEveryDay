package year2021.month2.no124;

public class BinaryTreeMaximumPathSum {
    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        TreeNode root2 = new TreeNode(-10,
                new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        TreeNode root3 = new TreeNode(-10, new TreeNode(2), new TreeNode(3));
        TreeNode root4 = new TreeNode(2, new TreeNode(-1), null);
        System.out.println(maxPathSum(root1));
        System.out.println(maxPathSum(root2));
        System.out.println(maxPathSum(root3));
        System.out.println(maxPathSum(root4));
    }

    public static int maxPathSum(TreeNode root) {
        max = Integer.MIN_VALUE;
        dfs(root);
        return max;
    }

    private static int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftVal = dfs(root.left);
        int rightVal = dfs(root.right);
        if (leftVal <= 0 && rightVal <= 0) {
            max = Math.max(max, root.val);
            return root.val;
        }
        int nodeMaxVal = root.val + Math.max(leftVal, rightVal);
        int pathMaxVal = root.val + leftVal + rightVal;
        max = Math.max(max, Math.max(nodeMaxVal, pathMaxVal));
        return nodeMaxVal;
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
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。该路径 至少包含一个 节点，且不一定经过根节点。

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
