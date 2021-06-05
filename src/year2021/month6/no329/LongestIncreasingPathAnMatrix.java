package year2021.month6.no329;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class LongestIncreasingPathAnMatrix {

    public static void main(String[] args) {
        int[][] matrix1 = {
                {9, 10, 4},
                {6, 6, 8},
                {2, 1, 1}
        };
        int[][] matrix2 = {
                {3, 4, 5},
                {3, 2, 6},
                {2, 2, 1}
        };
        System.out.println(longestIncreasingPath2(matrix1));
        System.out.println(longestIncreasingPath2(matrix2));
    }

    private static int longestIncreasingPath2(int[][] matrix) {
        /*
        * 思路类似于 DP，使用拓扑排序（Topological Sorting）
        * 显而易见，此矩阵所代表的有向图无环
        * 首先计算所有节点的出度（入度也可），将出度为 0 的元素入队
        * 进行广度遍历，则一定有一个出度为 0 的起始元素其递增序列最长
        * */
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] outDegrees = new int[row][col];
        int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                for (int[] direction : directions) {
                    int nextRow = i + direction[0];
                    int nextCol = j + direction[1];
                    if (isLegalAccess(matrix.length, matrix[0].length, nextRow, nextCol) && matrix[nextRow][nextCol] > matrix[i][j]) {
                        outDegrees[i][j]++;
                    }
                }
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (outDegrees[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        int longest = 0;
        while (!queue.isEmpty()) {
            longest++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                int x = poll[0];
                int y = poll[1];
                for (int[] direction : directions) {
                    int nextRow = x + direction[0];
                    int nextCol = y + direction[1];
                    if (isLegalAccess(matrix.length, matrix[0].length, nextRow, nextCol) && matrix[nextRow][nextCol] < matrix[x][y]) {
                        outDegrees[nextRow][nextCol]--;
                        if (outDegrees[nextRow][nextCol] == 0) {
                            queue.offer(new int[]{nextRow, nextCol});
                        }
                    }
                }
            }
        }
        return longest;
    }

    private static int longestIncreasingPath1(int[][] matrix) {
        /*
        * 矩阵中的元素只会向更大的元素方向移动，由此构造邻接表
        * 分别遍历每个元素，比较其最大路径长度
        * 其中，可对每个元素进行记忆化搜索，以避免重复搜索
        * time is O(nm), space is O(nm)
        * */
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] cache = new int[row][col];
        int longest = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int len = dfsGetLen(matrix, cache, i, j);
                longest = Math.max(longest, len);
            }
        }
        return longest;
    }

    private static int dfsGetLen(int[][] matrix, int[][] cache, int x, int y) {
        if (cache[x][y] != 0) {
            return cache[x][y];
        }
        int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        int selfStep = 1;
        int nextStep = 0;
        for (int[] direction : directions) {
            int nextRow = x + direction[0];
            int nextCol = y + direction[1];
            if (isLegalAccess(matrix.length, matrix[0].length, nextRow, nextCol) && matrix[nextRow][nextCol] > matrix[x][y]) {
                nextStep = Math.max(nextStep, dfsGetLen(matrix, cache, nextRow, nextCol));
            }
        }
        int totalStep = selfStep + nextStep;
        cache[x][y] = totalStep;
        return totalStep;
    }

    public static int longestIncreasingPath(int[][] matrix) {
        /*
        * DP
        * 由题意可知，矩阵中的元素只会向更大的元素方向移动
        * 故可先将矩阵中的元素压入最大堆（优先队列），
        * 则最大的元素其的最长递增路径即为 1
        * dp[max_i][max_j] = 1
        * 而后的元素的最长递增路径满足
        * dp[i][j] = 1 + {dp[i-1][j], dp[i+1][j], dp[i][j-1], dp[i][j+1]},
        * 其中，周围元素的元素值比当前元素值大时，才会进行比较
        * 由于按照最大堆分别更新 dp 矩阵，则对于每一个当前 dp 元素，其周围的较大值
        * 一定在先前被更新过，故状态转移方程成立
        * 时间复杂度：
        * 排序 O(nm * log nm) + dp转移 O(nm)
        * 空间复杂度：
        * 优先队列，及 dp 数组 均为 O(nm)
        * */
        int row = matrix.length;
        int col = matrix[0].length;
        PriorityQueue<Element> elementQueue = new PriorityQueue<>((e1, e2) -> e2.value - e1.value);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                elementQueue.offer(new Element(i, j, matrix[i][j]));
            }
        }
        int[][] dp = new int[row][col];
        Element maxElement = elementQueue.poll();
        dp[maxElement.x][maxElement.y] = 1;
        int longest = 1;
        while (!elementQueue.isEmpty()) {
            Element element = elementQueue.poll();
            int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
            int selfStep = 1;
            int nextStep = 0;
            for (int[] direction : directions) {
                int nextRow = element.x + direction[0];
                int nextCol = element.y + direction[1];
                if (isLegalAccess(row, col, nextRow, nextCol) && matrix[nextRow][nextCol] > element.value) {
                    nextStep = Math.max(nextStep, dp[nextRow][nextCol]);
                }
            }
            int curTotalStep = selfStep + nextStep;
            dp[element.x][element.y] = curTotalStep;
            longest = Math.max(longest, curTotalStep);
        }
        return longest;
    }

    private static boolean isLegalAccess(int row, int col, int nextRow, int nextCol) {
        return nextRow >= 0 && nextRow < row && nextCol >= 0 && nextCol < col;
    }

}

class Element {

    final int x;
    final int y;
    final int value;

    public Element(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }
}

/*
* 给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。

对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。

 

示例 1：


输入：matrix = [[9,9,4],[6,6,8],[2,1,1]]
输出：4 
解释：最长递增路径为 [1, 2, 6, 9]。
示例 2：


输入：matrix = [[3,4,5],[3,2,6],[2,2,1]]
输出：4 
解释：最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
示例 3：

输入：matrix = [[1]]
输出：1
 

提示：

m == matrix.length
n == matrix[i].length
1 <= m, n <= 200
0 <= matrix[i][j] <= 231 - 1

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
