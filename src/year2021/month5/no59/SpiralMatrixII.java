package year2021.month5.no59;

import java.util.Arrays;

public class SpiralMatrixII {

    public static void main(String[] args) {
        int n1 = 3;
        int n2 = 1;
        System.out.println(Arrays.deepToString(generateMatrix(n1)));
        System.out.println(Arrays.deepToString(generateMatrix(n2)));
    }

    public static int[][] generateMatrix(int n) {
        // 模拟转圈，计算每个方向的步数，time is O(n^2), space is O(n^2)
        int[][] ans = new int[n][n];
        // 总共的圈数
        int loop = (n + 1) / 2;
        int count = 1;
        for (int i = 0; i < loop; i++) {
            // 起始坐标
            int x = i;
            int y = i;
            ans[x][y] = count++;
            // 方向：右 -> 下 -> 左 -> 上
            int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            // 向右和向左个方向的步数
            int rightStep = n - 2 * i - 1;
            // 向下的步数
            int downStep = n - 2 * i - 1;
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
                    ans[x][y] = count++;
                    stepCount++;
                }
            }
        }
        return ans;
    }

}

/*
* 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。

 

示例 1：


输入：n = 3
输出：[[1,2,3],[8,9,4],[7,6,5]]
示例 2：

输入：n = 1
输出：[[1]]
 

提示：

1 <= n <= 20

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/spiral-matrix-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
