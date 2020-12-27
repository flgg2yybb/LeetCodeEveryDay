package twenty.december.no200;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {
    public static void main(String[] args) {
        char[][] grid1 = new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        char[][] grid2 = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println(numIslands3(grid1));
        System.out.println(numIslands3(grid2));
    }

    private static int numIslands3(char[][] grid) {
        UnionFind unionFind = new UnionFind(grid);
        int n = grid.length;
        int m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
//                    从左上角开始遍历，只需要查询下以及右即可
                    int[][] directions = new int[][]{{1, 0}, {0, 1}};
                    for (int[] direction : directions) {
                        int newRow = i + direction[0];
                        int newCol = j + direction[1];
                        if (newRow < n && newCol < m && grid[newRow][newCol] == '1') {
                            unionFind.union(i * m + j, newRow * m + newCol);
                        }
                    }
                }
            }
        }
        return unionFind.getCount();
    }

    private static int numIslands2(char[][] grid) {
//        bfs
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int count = 0;
        Queue<Position> queue = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (visited[i][j] || grid[i][j] != '1') {
                    continue;
                }
                count++;
                queue.offer(new Position(i, j));
                visited[i][j] = true;
                while (!queue.isEmpty()) {
                    int size = queue.size();
                    for (int k = 0; k < size; k++) {
                        Position pos = queue.poll();
                        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
                        for (int[] direction : directions) {
                            int newX = pos.x + direction[0];
                            int newY = pos.y + direction[1];
                            if (isLegalAccess(grid, newX, newY) && !visited[newX][newY] && grid[newX][newY] == '1') {
                                visited[newX][newY] = true;
                                queue.offer(new Position(newX, newY));
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    public static int numIslands(char[][] grid) {
//        dfs
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    dfs(grid, visited, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private static void dfs(char[][] grid, boolean[][] visited, int row, int col) {
        visited[row][col] = true;
        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            if (isLegalAccess(grid, newRow, newCol) && grid[newRow][newCol] == '1' && !visited[newRow][newCol]) {
                dfs(grid, visited, newRow, newCol);
            }
        }
    }

    private static boolean isLegalAccess(char[][] grid, int newRow, int newCol) {
        return newRow >= 0 && newRow <= grid.length - 1 && newCol >= 0 && newCol <= grid[0].length - 1;
    }
}

//并查集
class UnionFind {
    private final int[] parent;   //父亲节点的索引
    private final int[] rank;     //相当于深度
    private int count;      //集合个数

    public UnionFind(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        count = 0;
        parent = new int[n * m];
        rank = new int[n * m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    parent[i * m + j] = i * m + j;
                    count++;
                }
            }
        }
    }

    public int find(int i) {
        return parent[i] == i ? i : find(parent[i]);
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) {
//            已经为一个集合
            return;
        }
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        count--;
    }

    public int getCount() {
        return count;
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

/*给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。

岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。

此外，你可以假设该网格的四条边均被水包围。

 

示例 1：

输入：grid = [
  ['1','1','1','1','0'],
  ['1','1','0','1','0'],
  ['1','1','0','0','0'],
  ['0','0','0','0','0']
]
输出：1
示例 2：

输入：grid = [
  ['1','1','0','0','0'],
  ['1','1','0','0','0'],
  ['0','0','1','0','0'],
  ['0','0','0','1','1']
]
输出：3
 

提示：

m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] 的值为 '0' 或 '1'

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/number-of-islands
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * */
