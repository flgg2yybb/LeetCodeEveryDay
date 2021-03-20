package year2021.month3.no48;

public class RotateImage {
    public static void main(String[] args) {
        int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] matrix2 = {
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        };
        int[][] matrix3 = {
                {1}
        };
        int[][] matrix4 = {
                {1, 2},
                {3, 4}
        };
        rotate1(matrix1);
        rotate1(matrix2);
        rotate1(matrix3);
        rotate1(matrix4);
        disp(matrix1);
        disp(matrix2);
        disp(matrix3);
        disp(matrix4);
    }

    private static void rotate1(int[][] matrix) {
        // 翻转矩阵，先水平翻转（沿x轴），再沿主对角线翻转（矩阵转置），即可得到原矩阵顺时针方向旋转90度的矩阵
        int len = matrix.length;
        // 水平翻转
        for (int top = 0; top < len / 2; top++) {
            int buttom = len - 1 - top;
            for (int col = 0; col < len; col++) {
                int temp = matrix[top][col];
                matrix[top][col] = matrix[buttom][col];
                matrix[buttom][col] = temp;
            }
        }
        // 主对角线翻转（矩阵转置）
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    public static void rotate(int[][] matrix) {
        int len = matrix.length;
        int loop = len / 2;     //loop为要替换元素的圈数
        for (int i = 0; i < loop; i++) {
            int count = len - i * 2 - 1;  //count为当前圈需要替换的元素个数
            for (int j = 0; j < count; j++) {
                swap(matrix, i, j);
            }
        }
    }

    private static void swap(int[][] matrix, int loop, int count) {
        int len = matrix.length;
//        第一个元素索引，左上角
        int firstRow = loop;
        int firstCol = loop + count;
//        第二个元素索引，右上角
        int secondRow = loop + count;
        int secondCol = len - loop - 1;
//        第三个元素索引，右下角
        int thirdRow = len - loop - 1;
        int thirdCol = len - loop - 1 - count;
//        第四个元素索引，左下角
        int fourthRow = len - loop - 1 - count;
        int fourthCol = loop;
        int temp = matrix[fourthRow][fourthCol];
        matrix[fourthRow][fourthCol] = matrix[thirdRow][thirdCol];
        matrix[thirdRow][thirdCol] = matrix[secondRow][secondCol];
        matrix[secondRow][secondCol] = matrix[firstRow][firstCol];
        matrix[firstRow][firstCol] = temp;
    }

    private static void disp(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}

/*
* 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。

你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。

 

示例 1：


输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[[7,4,1],[8,5,2],[9,6,3]]
示例 2：


输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
示例 3：

输入：matrix = [[1]]
输出：[[1]]
示例 4：

输入：matrix = [[1,2],[3,4]]
输出：[[3,1],[4,2]]
 

提示：

matrix.length == n
matrix[i].length == n
1 <= n <= 20
-1000 <= matrix[i][j] <= 1000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/rotate-image
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
