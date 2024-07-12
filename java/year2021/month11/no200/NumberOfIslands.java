package year2021.month11.no200;

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
        System.out.println(numIslands(grid1));
        System.out.println(numIslands(grid2));
    }

    public static int numIslands(char[][] grid) {
        UnionFind unionFind = new UnionFind(grid);
        int m = grid.length;
        int n = grid[0].length;
        int[][] directions = {{0, 1}, {1, 0}};
        //  从左上角开始遍历，只需要查询下以及右即可
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    int origin = i * n + j;
                    for (int[] direction : directions) {
                        int nextX = i + direction[0];
                        int nextY = j + direction[1];
                        if (isLegalAccess(grid, nextX, nextY) && grid[nextX][nextY] == '1') {
                            int desc = nextX * n + nextY;
                            unionFind.union(origin, desc);
                        }
                    }
                }
            }
        }
        return unionFind.getCount();
    }

    private static boolean isLegalAccess(char[][] grid, int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }

}

class UnionFind {

    private final int[] parent;
    private final int[] rank;
    private int count;

    public UnionFind(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        parent = new int[m * n];
        rank = new int[m * n];
        count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    parent[i * n + j] = i * n + j;
                }
            }
        }
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) {
            return;
        }
        int rankX = rank[rootX];
        int rankY = rank[rootY];
        if (rankX > rankY) {
            parent[rootY] = rootX;
        } else if (rankX < rankY) {
            parent[rootX] = rootY;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        count--;
    }

    private int find(int i) {
        return parent[i] == i ? i : find(parent[i]);
    }

    public int getCount() {
        return count;
    }
}

/*
* 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。

岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。

此外，你可以假设该网格的四条边均被水包围。

 

示例 1：

输入：grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
输出：1
示例 2：

输入：grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
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
