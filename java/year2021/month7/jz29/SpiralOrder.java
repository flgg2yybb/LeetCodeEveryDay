package year2021.month7.jz29;

import java.util.Arrays;

public class SpiralOrder {

    public static void main(String[] args) {
        int[][] matrix1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};    //3*3
        int[][] matrix2 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};    //3*4
        int[][] matrix3 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};  //4*3
        int[][] matrix4 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};  //4*4
        int[][] matrix5 = {{1}, {2}, {3}};
        int[][] matrix6 = {{1}, {2}, {3}, {4}};
        int[][] matrix7 = {{1, 2, 3}};
        int[][] matrix8 = {{1, 2, 3, 4}};
        System.out.println(Arrays.toString(spiralOrder(matrix1)));
        System.out.println(Arrays.toString(spiralOrder(matrix2)));
        System.out.println(Arrays.toString(spiralOrder(matrix3)));
        System.out.println(Arrays.toString(spiralOrder(matrix4)));
        System.out.println(Arrays.toString(spiralOrder(matrix5)));
        System.out.println(Arrays.toString(spiralOrder(matrix6)));
        System.out.println(Arrays.toString(spiralOrder(matrix7)));
        System.out.println(Arrays.toString(spiralOrder(matrix8)));
    }

    public static int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int[] ans = new int[row * col];
        int pos = 0;
        int top = 0;
        int right = col - 1;
        int bottom = row - 1;
        int left = 0;
        while (top <= bottom && left <= right) {
            // 打上面的行，包括头尾
            for (int i = left; i <= right; i++) {
                ans[pos++] = matrix[top][i];
            }
            // 打右边的列，不包括头尾
            for (int i = top + 1; i < bottom; i++) {
                ans[pos++] = matrix[i][right];
            }
            // 打下面的行，包括头尾，需保证 top != bottom 防止重复打印
            for (int i = right; i >= left && top != bottom; i--) {
                ans[pos++] = matrix[bottom][i];
            }
            // 打左边的列，不包括头尾，需保证 left != right 防止重复打印
            for (int i = bottom - 1; i > top && left != right; i--) {
                ans[pos++] = matrix[i][left];
            }
            top++;
            right--;
            bottom--;
            left++;
        }
        return ans;
    }

}

/*
* 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。

 

示例 1：

输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[1,2,3,6,9,8,7,4,5]
示例 2：

输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 

限制：

0 <= matrix.length <= 100
0 <= matrix[i].length <= 100
注意：本题与主站 54 题相同：https://leetcode-cn.com/problems/spiral-matrix/

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
