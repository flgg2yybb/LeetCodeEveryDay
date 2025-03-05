package year2025.month3.no54;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    public static void main(String[] args) {
        int[][] matrix1 = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] matrix2 = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        System.out.println(spiralOrder(matrix1));
        System.out.println(spiralOrder(matrix2));
    }

    // times: O(mn), space: O(mn)
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int top = 0;
        int right = matrix[0].length - 1;
        int bottom = matrix.length - 1;
        int left = 0;
        while (top <= bottom && left <= right) {
            // top
            for (int j = left; j <= right; j++) {
                res.add(matrix[top][j]);
            }
            top++;
            // right
            for (int i = top; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }
            right--;
            // bottom，因 top++ 之后，top 可能大于 bottom，所以需要再次判断
            for (int j = right; top <= bottom && j >= left; j--) {
                res.add(matrix[bottom][j]);
            }
            bottom--;
            // left，因 right-- 之后，right 可能小于 left，所以需要再次判断
            for (int i = bottom; left <= right && i >= top; i--) {
                res.add(matrix[i][left]);
            }
            left++;
        }
        return res;
    }
}

/*
给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。

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
*/

