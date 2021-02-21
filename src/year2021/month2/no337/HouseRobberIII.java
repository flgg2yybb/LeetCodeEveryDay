package year2021.month2.no337;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class HouseRobberIII {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(3,
                new TreeNode(2, null, new TreeNode(3)),
                new TreeNode(3, null, new TreeNode(1)));
        TreeNode root2 = new TreeNode(3,
                new TreeNode(4, new TreeNode(1), new TreeNode(3)),
                new TreeNode(5, null, new TreeNode(1)));
        TreeNode root3 = null;
        TreeNode root4 = new TreeNode(5);
        System.out.println(rob(root1));
        System.out.println(rob(root2));
        System.out.println(rob(root3));
        System.out.println(rob(root4));
    }

    public static int rob(TreeNode root) {
        List<Integer> leverOrder = new ArrayList<>();
        leverOrder.add(0);
        bfs(root, leverOrder);
        /* 先将二叉树根据完全二叉树形式散列成一维数组，
         * 再从底向顶进行DP
         * 状态定义：
         * dp[i][0]表示不偷第i个房间所得到的以i为根的子树的最高金额
         * dp[i][1]表示偷第i个房间所得到的以i为根的子树的最高金额
         * 状态转移方程：
         * left = 2 * i, right = 2 * i + 1
         * dp[i][0] = max{dp[left][0], dp[left][1]} + max{dp[right][0], dp[right][1]}
         * dp[i][1] = dp[left][0] + dp[right][0] + levelOrder.get(i)
         * */
        int[][] dp = new int[leverOrder.size()][2];
        int index = dp.length - 1;
        while (2 * index >= dp.length) {        //处理二叉树最底层的节点
            dp[index][1] = leverOrder.get(index) == null ? 0 : leverOrder.get(index);
            index--;
        }
        while (index > 0) {
            int left = index << 1;
            int right = (index << 1) + 1;
            dp[index][0] = Math.max(dp[left][0], dp[left][1]) + Math.max(dp[right][0], dp[right][1]);
            dp[index][1] = dp[left][0] + dp[right][0] + (leverOrder.get(index) == null ? 0 : leverOrder.get(index));
            index--;
        }
        return Math.max(dp[1][0], dp[1][1]);
    }

    private static void bfs(TreeNode root, List<Integer> leverOrder) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll == null) {
                    leverOrder.add(null);
                } else {
                    leverOrder.add(poll.val);
                    queue.offer(poll.left);
                    queue.offer(poll.right);
                }
            }
        }
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/*
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。

计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。

示例 1:

输入: [3,2,3,null,3,null,1]

     3
    / \
   2   3
    \   \
     3   1

输出: 7
解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
示例 2:

输入: [3,4,5,1,3,null,1]

     3
    / \
   4   5
  / \   \
 1   3   1

输出: 9
解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/house-robber-iii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
