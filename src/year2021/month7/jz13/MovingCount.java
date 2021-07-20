package year2021.month7.jz13;

import java.util.LinkedList;
import java.util.Queue;

public class MovingCount {

    public static void main(String[] args) {
        int m1 = 2, n1 = 3, k1 = 1;
        int m2 = 3, n2 = 1, k2 = 0;
        System.out.println(movingCount(m1, n1, k1));
        System.out.println(movingCount(m2, n2, k2));
    }

    public static int movingCount(int m, int n, int k) {
        // BFS，从坐标 [0,0] 出发，向右上方扩散，边界顶点为 [m-1,n-1]
        int count = 0;
        Queue<Position> queue = new LinkedList<>();
        Position start = new Position(0, 0);
        boolean[][] visited = new boolean[m][n];
        queue.offer(start);
        visited[0][0] = true;
        int[][] directions = {{0, 1}, {1, 0}};
        while (!queue.isEmpty()) {
            Position pos = queue.poll();
            count++;
            for (int[] direction : directions) {
                int nextX = pos.x + direction[0];
                int nextY = pos.y + direction[1];
                if (isLegalAccess(m, n, k, nextX, nextY) && !visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;
                    queue.offer(new Position(nextX, nextY));
                }
            }
        }
        return count;
    }

    private static boolean isLegalAccess(int m, int n, int k, int x, int y) {
        if (x < 0 || x >= m || y < 0 || y >= n) {
            return false;
        }
        int sum = 0;
        while (x != 0) {
            sum += x % 10;
            x /= 10;
        }
        while (y != 0) {
            sum += y % 10;
            y /= 10;
        }
        return sum <= k;
    }

}

class Position {

    int x;
    int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

/*
* 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？

 

示例 1：

输入：m = 2, n = 3, k = 1
输出：3
示例 2：

输入：m = 3, n = 1, k = 0
输出：1
提示：

1 <= n,m <= 100
0 <= k <= 20

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
