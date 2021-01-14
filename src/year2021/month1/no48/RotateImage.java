package year2021.month1.no48;

public class RotateImage {
    public static void main(String[] args) {
        int[][] matrix1 = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        int[][] matrix2 = new int[][]{
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        };
        rotate(matrix1);
        rotate(matrix2);
        disp(matrix1);
        disp(matrix2);
    }

    public static void rotate(int[][] matrix) {
        if (matrix == null || matrix.length < 2) {
            return;
        }
        int len = matrix.length;
        Position leftTop = new Position();
        Position rightTop = new Position();
        Position rightBottom = new Position();
        Position leftBottom = new Position();
        for (int i = 0; i < len / 2; i++) {
            int swapTimes = len - i * 2 - 1;
            leftTop.setPosition(i, i);
            rightTop.setPosition(i, len - 1 - i);
            rightBottom.setPosition(len - 1 - i, len - 1 - i);
            leftBottom.setPosition(len - 1 - i, i);
            while (swapTimes > 0) {
                circleSwap(matrix, leftTop, rightTop, rightBottom, leftBottom);
                leftTop.y++;
                rightTop.x++;
                rightBottom.y--;
                leftBottom.x--;
                swapTimes--;
            }
        }
    }

    private static void circleSwap(int[][] matrix, Position leftTop, Position rightTop,
                                   Position rightBottom, Position leftBottom) {
        int temp = matrix[leftBottom.x][leftBottom.y];
        matrix[leftBottom.x][leftBottom.y] = matrix[rightBottom.x][rightBottom.y];
        matrix[rightBottom.x][rightBottom.y] = matrix[rightTop.x][rightTop.y];
        matrix[rightTop.x][rightTop.y] = matrix[leftTop.x][leftTop.y];
        matrix[leftTop.x][leftTop.y] = temp;
    }

    private static void disp(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            System.out.println("EMPTY");
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}

class Position {
    int x;
    int y;

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

/*
* 给定一个 n × n 的二维矩阵表示一个图像。

将图像顺时针旋转 90 度。

说明：

你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。

示例 1:

给定 matrix = 
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

原地旋转输入矩阵，使其变为:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
示例 2:

给定 matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
], 

原地旋转输入矩阵，使其变为:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/rotate-image
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
