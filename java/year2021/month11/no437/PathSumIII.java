package year2021.month11.no437;

import java.util.HashMap;
import java.util.Map;

public class PathSumIII {

    private static int ans = 0;

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(10,
                new TreeNode(5,
                        new TreeNode(3,
                                new TreeNode(3),
                                new TreeNode(-2)),
                        new TreeNode(2,
                                null,
                                new TreeNode(1))),
                new TreeNode(-3,
                        null,
                        new TreeNode(11)));
        int targetSum1 = 8;
        TreeNode root2 = new TreeNode(5,
                new TreeNode(4,
                        new TreeNode(11,
                                new TreeNode(7),
                                new TreeNode(2)),
                        null),
                new TreeNode(8,
                        new TreeNode(13,
                                null,
                                null),
                        new TreeNode(4,
                                new TreeNode(5),
                                new TreeNode(1))));
        int targetSum2 = 22;
        System.out.println(pathSum(root1, targetSum1));
        System.out.println(pathSum(root2, targetSum2));
    }

    public static int pathSum(TreeNode root, int targetSum) {
        ans = 0;
        Map<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0, 1);
        backtrack(root, prefixSum, 0, targetSum);
        return ans;
    }

    private static void backtrack(TreeNode root, Map<Integer, Integer> prefixSum, int curSum, int targetSum) {
        if (root == null) {
            return;
        }
        curSum += root.val;
        ans += prefixSum.getOrDefault(curSum - targetSum, 0);
        prefixSum.merge(curSum, 1, Integer::sum);
        backtrack(root.left, prefixSum, curSum, targetSum);
        backtrack(root.right, prefixSum, curSum, targetSum);
        prefixSum.merge(curSum, -1, Integer::sum);
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
* 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。

路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。

 

示例 1：



输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
输出：3
解释：和等于 8 的路径有 3 条，如图所示。
示例 2：

输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
输出：3
 

提示:

二叉树的节点个数的范围是 [0,1000]
-109 <= Node.val <= 109 
-1000 <= targetSum <= 1000 

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/path-sum-iii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
