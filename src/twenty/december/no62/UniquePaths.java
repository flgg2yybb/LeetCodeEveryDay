package twenty.december.no62;

public class UniquePaths {
    public static void main(String[] args) {
        int m1 = 3, n1 = 7;
        int m2 = 3, n2 = 2;
        int m3 = 7, n3 = 3;
        int m4 = 3, n4 = 3;
        int m5 = 10, n5 = 10;
        System.out.println(uniquePaths4(m1, n1));
        System.out.println(uniquePaths4(m2, n2));
        System.out.println(uniquePaths4(m3, n3));
        System.out.println(uniquePaths4(m4, n4));
        System.out.println(uniquePaths4(m5, n5));
    }

    private static int uniquePaths4(int m, int n) {
//                                              (m-1)
//       permutation and combination, result = C(m+n-2)
        int row = Math.min(m, n) - 1;
        int col = m + n - 2;
        long son = 1;        //分子
        long parent = 1;     //分母
        for (int i = row; i > 0; i--) {
            parent *= i;
//            存在整形越界
            son *= col;
            col--;
        }
        return (int) (son / parent);
    }

    private static int uniquePaths3(int m, int n) {
//      DP, use rolling array, time is O(mn), space is O(min(m,n))
        int row = Math.min(m, n);
        int col = Math.max(m, n);
//        滚动数组
        int[] arr = new int[row];
        arr[0] = 1;
        for (int i = 0; i < col; i++) {
            for (int j = 1; j < row; j++) {
                arr[j] = arr[j] + arr[j - 1];
            }
        }
        return arr[row - 1];
    }

    private static int uniquePaths2(int m, int n) {
//        DP， change status transfer direction
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static int uniquePaths(int m, int n) {
//        dynamic programming, time is O(m * n), space is O(m * n)
        int[][] dp = new int[m + 1][n + 1];
        for (int row = m - 1; row >= 0; row--) {
            for (int col = n - 1; col >= 0; col--) {
                if (row == m - 1 && col == n - 1) {
                    dp[row][col] = 1;
                    continue;
                }
                dp[row][col] = dp[row + 1][col] + dp[row][col + 1];
            }
        }
        return dp[0][0];
    }
}

/*
* 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。

问总共有多少条不同的路径？

 

示例 1：


输入：m = 3, n = 7
输出：28
示例 2：

输入：m = 3, n = 2
输出：3
解释：
从左上角开始，总共有 3 条路径可以到达右下角。
1. 向右 -> 向右 -> 向下
2. 向右 -> 向下 -> 向右
3. 向下 -> 向右 -> 向右
示例 3：

输入：m = 7, n = 3
输出：28
示例 4：

输入：m = 3, n = 3
输出：6
 

提示：

1 <= m, n <= 100
题目数据保证答案小于等于 2 * 109

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/unique-paths
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
