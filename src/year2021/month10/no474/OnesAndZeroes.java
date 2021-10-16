package year2021.month10.no474;

public class OnesAndZeroes {

    public static void main(String[] args) {
        String[] strs1 = {"10", "0001", "111001", "1", "0"};
        int m1 = 5;
        int n1 = 3;
        String[] strs2 = {"10", "0", "1"};
        int m2 = 1;
        int n2 = 1;
        System.out.println(findMaxForm(strs1, m1, n1));
        System.out.println(findMaxForm(strs2, m2, n2));
    }

    public static int findMaxForm(String[] strs, int m, int n) {
        /*
         * DP,【0-1背包】
         * 状态定义：
         * dp[i][j][k] 表示 strs 前 [0,i) 子数组中最多有 j 个 0 和 k 个 1 的最大子集长度
         * 状态转移方程：
         * dp[i][j][k] = max{dp[i-1][j][k], dp[i-1][j-x][k-y] + 1}, strs[i] 有 x 个 0，y 个 1
         *                      不选              选
         * 最终答案为：
         * dp[len][m][n]
         * */
        int len = strs.length;
        int[][][] dp = new int[len + 1][m + 1][n + 1];
        for (int i = 1; i <= strs.length; i++) {
            int[] counts = countCurStrOneAneZero(strs[i - 1]);
            int x = counts[0];
            int y = counts[1];
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (j >= x && k >= y) {
                        dp[i][j][k] = Math.max(dp[i][j][k], 1 + dp[i - 1][j - x][k - y]);
                    }
                }
            }
        }
        return dp[len][m][n];
    }

    private static int[] countCurStrOneAneZero(String str) {
        int[] zeroOnes = new int[2];
        for (int i = 0; i < str.length(); i++) {
            zeroOnes[str.charAt(i) - '0']++;
        }
        return zeroOnes;
    }

}

/*
* 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。

请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。

如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。

 

示例 1：

输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
输出：4
解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
示例 2：

输入：strs = ["10", "0", "1"], m = 1, n = 1
输出：2
解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
 

提示：

1 <= strs.length <= 600
1 <= strs[i].length <= 100
strs[i] 仅由 '0' 和 '1' 组成
1 <= m, n <= 100

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/ones-and-zeroes
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
