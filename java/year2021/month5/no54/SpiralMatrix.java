package year2021.month5.no54;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

    public static void main(String[] args) {
        int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] matrix2 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        int[][] matrix3 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {10, 11, 12}
        };

        System.out.println(spiralOrder(matrix1));
        System.out.println(spiralOrder(matrix2));
        System.out.println(spiralOrder(matrix3));
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        // 模拟转圈，计算每个方向的步数，time is O(mn), space is O(1)
        int row = matrix.length;
        int col = matrix[0].length;
        // 总共的圈数
        int loop = (Math.min(row, col) + 1) / 2;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < loop; i++) {
            // 起始坐标
            int x = i;
            int y = i;
            ans.add(matrix[x][y]);
            // 方向：右 -> 下 -> 左 -> 上
            int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            // 向右和向左个方向的步数
            int rightStep = col - 2 * i - 1;
            // 向下的步数
            int downStep = row - 2 * i - 1;
            // 向左的步数
            int leftStep = downStep <= 0 ? 0 : rightStep;
            // 向上的步数
            int upStep = leftStep <= 0 ? 0 : downStep - 1;
            // 各个方向的步数 （右 -> 下 -> 左 -> 上）
            int[] steps = {rightStep, downStep, leftStep, upStep};
            for (int k = 0; k < 4; k++) {
                int[] direction = directions[k];
                int step = steps[k];
                int stepCount = 0;
                while (stepCount < step) {
                    x += direction[0];
                    y += direction[1];
                    ans.add(matrix[x][y]);
                    stepCount++;
                }
            }
        }
        return ans;
    }

}

/*
* 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。

 

示例 1：


输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[1,2,3,6,9,8,7,4,5]
示例 2：


输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 

提示：

m == matrix.length
n == matrix[i].length
1 <= m, n <= 10
-100 <= matrix[i][j] <= 100

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/spiral-matrix
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
