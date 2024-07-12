package year2021.month4.no73;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SetMatrixZeroes {

    public static void main(String[] args) {
        int[][] matrix1 = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        int[][] matrix2 = {
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}
        };
        disp(matrix1);
        setZeroes1(matrix1);
        disp(matrix1);
        disp(matrix2);
        setZeroes1(matrix2);
        disp(matrix2);

    }

    private static void setZeroes1(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        boolean setFirstRowZero = Arrays.stream(matrix[0]).boxed().anyMatch(num -> num == 0);
        boolean setFirstColZero = IntStream.range(0, matrix.length).boxed().anyMatch(rowIndex -> matrix[rowIndex][0] == 0);
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for (int j = 1; j < col; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 1; i < row; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int i = 1; i < row; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < col; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (setFirstRowZero) {
            Arrays.fill(matrix[0], 0);
        }
        if (setFirstColZero) {
            for (int i = 0; i < row; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    public static void setZeroes(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        boolean[] zeroRow = new boolean[row];
        boolean[] zeroCol = new boolean[col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    zeroRow[i] = true;
                    zeroCol[j] = true;
                }
            }
        }
        for (int i = 0; i < row; i++) {
            if (zeroRow[i]) {
                for (int j = 0; j < col; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int j = 0; j < col; j++) {
            if (zeroCol[j]) {
                for (int i = 0; i < row; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    private static void disp(int[][] matrix) {
        System.out.println("----------------------");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("----------------------");
    }

}

/*
* 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。

进阶：

一个直观的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
你能想出一个仅使用常量空间的解决方案吗？
 

示例 1：


输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
输出：[[1,0,1],[0,0,0],[1,0,1]]
示例 2：


输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 

提示：

m == matrix.length
n == matrix[0].length
1 <= m, n <= 200
-231 <= matrix[i][j] <= 231 - 1

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/set-matrix-zeroes
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
