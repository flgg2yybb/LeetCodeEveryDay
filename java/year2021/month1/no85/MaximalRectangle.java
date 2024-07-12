package year2021.month1.no85;

import java.util.Deque;
import java.util.LinkedList;

public class MaximalRectangle {
    public static void main(String[] args) {
        char[][] matrix1 = new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        System.out.println(maximalRectangle(matrix1));
    }

    public static int maximalRectangle(char[][] matrix) {
//        调用No. 84的解法，遍历每行，将每行的在竖直方向上的1看做是柱子，即可求解
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int maxArea = 0;
        int[] height = new int[matrix[0].length];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                height[col] = matrix[row][col] == '0' ? 0 : matrix[row][col] - '0' + height[col];
            }
            maxArea = Math.max(maxArea, largestRectangleArea(height));
        }
        return maxArea;
    }

    private static int largestRectangleArea(int[] height) {
//        Monotone Stack
        Deque<Integer> stack = new LinkedList<>();
        int maxArea = 0;
        int[] leftBound = new int[height.length];
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peekLast()] >= height[i]) {
                Integer pop = stack.pollLast();
                leftBound[pop] = stack.isEmpty() ? -1 : stack.peekLast();
            }
            stack.offerLast(i);
        }
        while (!stack.isEmpty()) {
            Integer pop = stack.pollLast();
            leftBound[pop] = stack.isEmpty() ? -1 : stack.peekLast();
        }
        int[] rightBound = new int[height.length];
        for (int i = height.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && height[stack.peekLast()] >= height[i]) {
                Integer pop = stack.pollLast();
                rightBound[pop] = stack.isEmpty() ? height.length : stack.peekLast();
            }
            stack.offerLast(i);
        }
        while (!stack.isEmpty()) {
            Integer pop = stack.pollLast();
            rightBound[pop] = stack.isEmpty() ? height.length : stack.peekLast();
        }
        for (int i = 0; i < height.length; i++) {
            int width = rightBound[i] - leftBound[i] - 1;
            maxArea = Math.max(maxArea, width * height[i]);
        }
        return maxArea;
    }
}

/*
* 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。

 

示例 1：


输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
输出：6
解释：最大矩形如上图所示。
示例 2：

输入：matrix = []
输出：0
示例 3：

输入：matrix = [["0"]]
输出：0
示例 4：

输入：matrix = [["1"]]
输出：1
示例 5：

输入：matrix = [["0","0"]]
输出：0
 

提示：

rows == matrix.length
cols == matrix[0].length
0 <= row, cols <= 200
matrix[i][j] 为 '0' 或 '1'

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/maximal-rectangle
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
