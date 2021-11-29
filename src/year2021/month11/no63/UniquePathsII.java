package year2021.month11.no63;

public class UniquePathsII {

    public static void main(String[] args) {
        int[][] obstacleGrid1 = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        int[][] obstacleGrid2 = {
                {0, 1},
                {0, 0}
        };
        System.out.println(uniquePathsWithObstacles(obstacleGrid1));
        System.out.println(uniquePathsWithObstacles(obstacleGrid2));
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        /*
         * DP, time is O(mn), space is O(mn)
         * 状态定义
         * dp[i][j] 表示从左上角到 (i,j) 的不同路径数
         * 状态转移方程
         * if obstacleGrid[i][j] != 1
         *   dp[i][j] = dp[i-1][j] + dp[i][j-1]
         * 初始值
         * dp[0][j] = 1 (obstacleGrid[0][j] != 1)
         * dp[i][0] = 1 (obstacleGrid[i][0] != 1)
         * */
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m + 1][n + 1];     // 将dp矩阵大小设置为 m+1 * n+1 可以避免初始化第一行与第一列
        dp[1][1] = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (obstacleGrid[i - 1][j - 1] != 1) {
                    dp[i][j] += dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m][n];
    }

}

/*
* 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？



网格中的障碍物和空位置分别用 1 和 0 来表示。

 

示例 1：


输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
输出：2
解释：
3x3 网格的正中间有一个障碍物。
从左上角到右下角一共有 2 条不同的路径：
1. 向右 -> 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右 -> 向右
示例 2：


输入：obstacleGrid = [[0,1],[0,0]]
输出：1
 

提示：

m == obstacleGrid.length
n == obstacleGrid[i].length
1 <= m, n <= 100
obstacleGrid[i][j] 为 0 或 1

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/unique-paths-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
