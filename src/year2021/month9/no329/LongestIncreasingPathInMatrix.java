package year2021.month9.no329;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class LongestIncreasingPathInMatrix {
    public static void main(String[] args) {
        int[][] matrix1 = {
                {9, 9, 4},
                {6, 6, 8},
                {2, 1, 1}
        };
        int[][] matrix2 = {
                {3, 4, 5},
                {3, 2, 6},
                {2, 2, 1}
        };
        int[][] matrix3 = {{1}};
        System.out.println(longestIncreasingPath(matrix1));
        System.out.println(longestIncreasingPath(matrix2));
        System.out.println(longestIncreasingPath(matrix3));
    }

    private static int longestIncreasingPath(int[][] matrix) {
        // BFS + 出度表, time is O(mn), space is O(mn)
        int m = matrix.length;
        int n = matrix[0].length;
        // 初始化出度表
        int[][] outDegreeTable = new int[m][n];
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int[] direction : directions) {
                    int nextX = i + direction[0];
                    int nextY = j + direction[1];
                    if (isLegalAccess(m, n, nextX, nextY) && matrix[nextX][nextY] > matrix[i][j]) {
                        outDegreeTable[i][j]++;
                    }
                }
            }
        }
        // 出度为 0 的节点入队
        Queue<Element> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (outDegreeTable[i][j] == 0) {
                    queue.offer(new Element(i, j, matrix[i][j]));
                }
            }
        }
        // BFS
        int longest = 0;
        while (!queue.isEmpty()) {
            longest++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Element node = queue.poll();
                for (int[] direction : directions) {
                    int nextX = node.x + direction[0];
                    int nextY = node.y + direction[1];
                    if (isLegalAccess(m, n, nextX, nextY) && matrix[nextX][nextY] < matrix[node.x][node.y]) {
                        outDegreeTable[nextX][nextY]--;
                        if (outDegreeTable[nextX][nextY] == 0) {
                            queue.offer(new Element(nextX, nextY, matrix[nextX][nextY]));
                        }
                    }
                }
            }
        }
        return longest;
    }

    private static int longestIncreasingPath2(int[][] matrix) {
        // DFS + 记忆化搜索, time is O(mn), space is O(mn)
        // dfs方向为从较小的元素走向较大的元素
        int longest = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] cache = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int length = dfs(matrix, cache, i, j);
                longest = Math.max(longest, length);
            }
        }
        return longest;
    }

    private static int dfs(int[][] matrix, int[][] cache, int x, int y) {
        if (cache[x][y] != 0) {
            return cache[x][y];
        }
        int currentStep = 1;
        int nextStep = 0;
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (int[] direction : directions) {
            int nextX = x + direction[0];
            int nextY = y + direction[1];
            if (isLegalAccess(matrix.length, matrix[0].length, nextX, nextY) && matrix[nextX][nextY] > matrix[x][y]) {
                nextStep = Math.max(nextStep, dfs(matrix, cache, nextX, nextY));
            }
        }
        int totalStep = currentStep + nextStep;
        cache[x][y] = totalStep;
        return totalStep;
    }

    public static int longestIncreasingPath1(int[][] matrix) {
        /*
        * DP, time is O(mn * logmn), space is O(mn)
        * 状态定义：
        * dp[i][j] 表示以当前元素(i,j)为起点的最长递增路径的长度
        * 状态转移方程：
        * dp[i][j] = 1 + max{dp[x][y]},
        * (x,y) 为 (i,j) 四联通方向的相邻坐标，且有 matrix[x][y] > matrix[i][j]
        * 可见，状态转移由较大的元素，转向较小的元素，因此先遍历较大元素，故使用优先队列
        * */
        int m = matrix.length;
        int n = matrix[0].length;
        PriorityQueue<Element> queue = new PriorityQueue<>((c1, c2) -> c2.value - c1.value);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                queue.offer(new Element(i, j, matrix[i][j]));
            }
        }
        int[][] dp = new int[m][n];
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int longest = 0;
        while (!queue.isEmpty()) {
            Element element = queue.poll();
            int currentStep = 1;
            int nextSetp = 0;
            for (int[] direction : directions) {
                int nextX = element.x + direction[0];
                int nextY = element.y + direction[1];
                if (isLegalAccess(m, n, nextX, nextY) && matrix[nextX][nextY] > element.value) {
                    nextSetp = Math.max(nextSetp, dp[nextX][nextY]);
                }
            }
            int totalStep = currentStep + nextSetp;
            dp[element.x][element.y] = totalStep;
            longest = Math.max(longest, totalStep);
        }
        return longest;
    }

    private static boolean isLegalAccess(int m, int n, int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
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
