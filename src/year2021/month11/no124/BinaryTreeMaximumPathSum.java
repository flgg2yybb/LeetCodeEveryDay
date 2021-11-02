package year2021.month11.no124;

public class BinaryTreeMaximumPathSum {
    private static int ans = -1000;

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1,
                new TreeNode(2),
                new TreeNode(3));
        TreeNode root2 = new TreeNode(-10,
                new TreeNode(9),
                new TreeNode(20,
                        new TreeNode(15),
                        new TreeNode(7)));
        TreeNode root3 = new TreeNode(-3);
        System.out.println(maxPathSum(root1));  //6
        System.out.println(maxPathSum(root2));  //42
        System.out.println(maxPathSum(root3));  //-3
    }

    public static int maxPathSum(TreeNode root) {
        ans = -1000;
        dfs(root);
        return ans;
    }

    private static int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        int pathValue = Math.max(0, Math.max(left, right)) + root.val;
        int totalValue = Math.max(pathValue, left + right + root.val);
        ans = Math.max(ans, Math.max(pathValue, totalValue));
        return pathValue;
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
