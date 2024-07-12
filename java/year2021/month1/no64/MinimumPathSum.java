package year2021.month1.no64;

public class MinimumPathSum {
    public static void main(String[] args) {
        int[][] grid1 = new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        int[][] grid2 = new int[][]{
                {1, 2, 3},
                {4, 5, 6}
        };
        System.out.println(minPathSum1(grid1));
        System.out.println(minPathSum1(grid2));
    }

    private static int minPathSum1(int[][] grid) {
        /*DP，
         * 状态定义：
         * dp[i][j]为左上角到(i, j)格子上的的最小路径
         * 状态转移方程：
         * dp[i][j] = grid[i][j] + min{dp[i - 1][j], dp[i][j - 1]}
         * 初始值：
         * dp[0][0] = grid[0][0]
         * dp[0][j] = grid[0][j] + dp[0][j - 1]
         * dp[i][0] = grid[i][0] + dp[i - 1][0]
         * */
        final int m = grid.length;
        final int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int j = 1; j < n; j++) {
            dp[0][j] = grid[0][j] + dp[0][j - 1];
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[m - 1][n - 1];
    }

    public static int minPathSum(int[][] grid) {
        /*DP，
         * 状态定义：
         * dp[i][j]为到右下角的最小路径
         * 状态转移方程：
         * dp[i][j] = grid[i][j] + min{dp[i + 1][j], dp[i][j + 1]}
         * 初始值：
         * dp[m - 1][n - 1] = grid[m - 1][n - 1]
         * dp[m - 1][j] = grid[m - 1][j] + dp[m - 1][j + 1]
         * dp[i][n - 1] = grid[i][n - 1] + dp[i + 1][n - 1]
         * */
        final int m = grid.length;
        final int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = grid[m - 1][n - 1];
        for (int j = n - 2; j >= 0; j--) {
            dp[m - 1][j] = grid[m - 1][j] + dp[m - 1][j + 1];
        }
        for (int i = m - 2; i >= 0; i--) {
            dp[i][n - 1] = grid[i][n - 1] + dp[i + 1][n - 1];
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
            }
        }
        return dp[0][0];
    }
}

/*
* 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

说明：每次只能向下或者向右移动一步。

 

示例 1：


输入：grid = [[1,3,1],
*            [1,5,1],
*            [4,2,1]]
输出：7
解释：因为路径 1→3→1→1→1 的总和最小。
示例 2：

输入：grid = [[1,2,3],
*            [4,5,6]]
输出：12
 

提示：

m == grid.length
n == grid[i].length
1 <= m, n <= 200
0 <= grid[i][j] <= 100

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimum-path-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
