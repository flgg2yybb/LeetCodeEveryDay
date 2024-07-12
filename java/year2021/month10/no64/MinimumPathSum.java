package year2021.month10.no64;

import java.util.Arrays;

public class MinimumPathSum {

    public static void main(String[] args) {
        int[][] grid1 = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        int[][] grid2 = {
                {1, 2, 3},
                {4, 5, 6}
        };
        System.out.println(minPathSum(grid1));
        System.out.println(minPathSum(grid2));
    }

    private static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] cache = new int[m][n];
        for (int[] arr : cache) {
            Arrays.fill(arr, -1);
        }
        return dfs(grid, cache, m - 1, n - 1);
    }

    private static int dfs(int[][] grid, int[][] cache, int x, int y) {
        if (x == 0 && y == 0) {
            return grid[0][0];
        }
        if (cache[x][y] != -1) {
            return cache[x][y];
        }
        int cur = grid[x][y];
        int top = x == 0 ? Integer.MAX_VALUE : dfs(grid, cache, x - 1, y);
        int left = y == 0 ? Integer.MAX_VALUE : dfs(grid, cache, x, y - 1);
        cache[x][y] = Math.min(top, left) + cur;
        return cache[x][y];
    }

    public static int minPathSum1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

}

/*
* 给定一个包含非负整数的 m   x   n   网格   grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

说明：每次只能向下或者向右移动一步。

   

示例 1：


输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
输出：7
解释：因为路径 1→3→1→1→1 的总和最小。
示例 2：

输入：grid = [[1,2,3],[4,5,6]]
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
