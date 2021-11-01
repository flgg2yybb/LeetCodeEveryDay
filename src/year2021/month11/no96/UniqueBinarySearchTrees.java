package year2021.month11.no96;

public class UniqueBinarySearchTrees {

    public static void main(String[] args) {
        int n1 = 3;
        int n2 = 1;
        System.out.println(numTrees(n1));
        System.out.println(numTrees(n2));
    }

    public static int numTrees(int n) {
        /*
         * DP
         * 状态定义：
         * dp[i] 表示由 i 个节点组成的互不相同的二叉搜索树的种树
         * 状态转移方程：
         * dp[i] = dp[0] * dp[i-1] + dp[1] * dp[i-2] + ... + dp[k] * dp[i-k-1] + ... + dp[i-1] * dp[0]
         * 初始值：
         * dp[0] = 1
         * dp[1] = 1
         * */
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }

}

/*
* 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。

 

示例 1：


输入：n = 3
输出：5
示例 2：

输入：n = 1
输出：1
 

提示：

1 <= n <= 19

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/unique-binary-search-trees
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
