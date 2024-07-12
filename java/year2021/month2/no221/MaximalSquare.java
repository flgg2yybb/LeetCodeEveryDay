package year2021.month2.no221;

public class MaximalSquare {
    public static void main(String[] args) {
        char[][] matrix2 = new char[][]{
                {'0', '1'},
                {'1', '0'}
        };
        char[][] matrix3 = new char[][]{
                {'0'}
        };
        char[][] matrix1 = new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        char[][] matrix4 = new char[][]{
                {'1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'0', '0', '0', '0', '0'},
                {'1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1'}
        };
//        System.out.println(maximalSquare2(matrix1));
//        System.out.println(maximalSquare2(matrix2));
//        System.out.println(maximalSquare2(matrix3));
        System.out.println(maximalSquare2(matrix4));
    }

    private static int maximalSquare2(char[][] matrix) {
//        空间压缩
        int row = matrix.length;
        int col = matrix[0].length;
        int[] dp = new int[col + 1];
        int leftTopDp = 0;
        int maxSide = 0;
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                int nextLeftTopDp = dp[j];
                if (matrix[i - 1][j - 1] == '0') {      //滚动数组需要对为0的情况处理dp数组
                    dp[j] = 0;
                } else {
                    dp[j] = Math.min(leftTopDp, Math.min(dp[j - 1], dp[j])) + 1;
                    maxSide = Math.max(maxSide, dp[j]);
                }
                leftTopDp = nextLeftTopDp;
            }
        }
        return maxSide * maxSide;
    }

    private static int maximalSquare1(char[][] matrix) {
//        扩充dp数组，可使得对第一行第一列的处理与其他行列一致，简化代码
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row + 1][col + 1];
        int maxSide = 0;
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        return maxSide * maxSide;
    }

    public static int maximalSquare(char[][] matrix) {
        /*DP
         * 状态定义：
         * dp[i][j]表示以索引(i,j)为右下角，且只包含'1'的矩阵的最大边长
         * 状态转移方程：
         * if dp[i][j] == '0'
         *      dp[i][j] = 0
         * if dp[i][j] == '1'
         *      dp[i][j] = min{dp[i-1][j], dp[i-1][j-1], dp[i][j-1]} + 1
         * 初始值：
         * 对于第一行
         * dp[0][j] = matrix[0][j] == '1' ? 1 : 0
         * 对于第一列
         * dp[i][0] = matrix[i][0] == '1' ? 1 : 0
         * 则，最大正方形中即为dp数组中最大值的平方
         * */
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];
        int maxSide = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        return maxSide * maxSide;
    }
}

/*
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。

 

示例 1：


输入：matrix = [['1','0','1','0','0'],['1','0','1','1','1'],['1','1','1','1','1'],['1','0','0','1','0']]
输出：4
示例 2：


输入：matrix = [['0','1'],['1','0']]
输出：1
示例 3：

输入：matrix = [['0']]
输出：0
 

提示：

m == matrix.length
n == matrix[i].length
1 <= m, n <= 300
matrix[i][j] 为 '0' 或 '1'

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/maximal-square
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
