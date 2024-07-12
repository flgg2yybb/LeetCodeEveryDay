package year2021.month6.no130;

import java.util.Arrays;

public class SurroundedRegions {

    public static void main(String[] args) {
        char[][] board1 = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        char[][] board2 = {
                {'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'O', 'X', 'X'},
                {'X', 'O', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X', 'X'},
                {'X', 'X', 'X', 'O', 'X'}
        };
        System.out.println(Arrays.deepToString(board1));
        solve1(board1);
        System.out.println(Arrays.deepToString(board1));
        System.out.println(Arrays.deepToString(board2));
        solve1(board2);
        System.out.println(Arrays.deepToString(board2));
    }

    private static void solve1(char[][] board) {
        // 并查集，将边界的 O 节点与虚拟头结点 union
        // 将非边界的 O 节点与其上下左右的 O 节点做 union操作
        // 则所有 O 节点被分成两批，一批是与虚拟头结点相连，另一批不是
        int row = board.length;
        int col = board[0].length;
        int size = row * col + 1;
        UnionFind unionFind = new UnionFind(size);
        int dummyNode = size - 1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] != 'O') {
                    continue;
                }
                if (i == 0 || i == row - 1 || j == 0 || j == col - 1) {
                    unionFind.union(dummyNode, getIndex(col, i, j));
                } else {
                    if (board[i - 1][j] == 'O') {
                        unionFind.union(getIndex(col, i, j), getIndex(col, i - 1, j));
                    }
                    if (board[i + 1][j] == 'O') {
                        unionFind.union(getIndex(col, i, j), getIndex(col, i + 1, j));
                    }
                    if (board[i][j - 1] == 'O') {
                        unionFind.union(getIndex(col, i, j), getIndex(col, i, j - 1));
                    }
                    if (board[i][j + 1] == 'O') {
                        unionFind.union(getIndex(col, i, j), getIndex(col, i, j + 1));
                    }
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] != 'O') {
                    continue;
                }
                if (!unionFind.isConnected(dummyNode, getIndex(col, i, j))) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private static int getIndex(int col, int i, int j) {
        return col * i + j;
    }

    public static void solve(char[][] board) {
        // dfs, 由于边界上的 O 或者与边界上的 O 相连通的 O 不会被转成 X
        // 故对，边界上的 O 做 dfs 遍历，并将连通的 O 标记成 #
        // 则 dfs 完成后， board 上 O 的元素需要转成 X，# 元素需要转回 O
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == 'O') {
                dfs(board, i, 0);
            }
            if (board[i][board[i].length - 1] == 'O') {
                dfs(board, i, board[i].length - 1);
            }
        }
        for (int j = 0; j < board[0].length; j++) {
            if (board[0][j] == 'O') {
                dfs(board, 0, j);
            }
            if (board[board.length - 1][j] == 'O') {
                dfs(board, board.length - 1, j);
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private static void dfs(char[][] board, int i, int j) {
        board[i][j] = '#';
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (int[] direction : directions) {
            int nextRow = i + direction[0];
            int nextCol = j + direction[1];
            if (isLegalAccess(board, nextRow, nextCol) && board[nextRow][nextCol] == 'O') {
                dfs(board, nextRow, nextCol);
            }
        }
    }

    private static boolean isLegalAccess(char[][] board, int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board[x].length;
    }

}

class UnionFind {

    private final int[] rank;
    private final int[] parent;

    UnionFind(int size) {
        this.rank = new int[size];
        this.parent = new int[size];    // 最后一个位置存贮虚拟根节点
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        return parent[x] == x ? x : find(parent[x]);
    }

    public void union(int x, int y) {
        int root1 = find(x);
        int root2 = find(y);
        if (root1 == root2) {
            return;
        }
        if (rank[root1] < rank[root2]) {
            parent[root1] = root2;
        } else if (rank[root1] > rank[root2]) {
            parent[root2] = root1;
        } else {
            parent[root2] = root1;
            rank[root1]++;
        }
    }

    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

}

/*
* 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 

示例 1：


输入：board = [['X','X','X','X'],['X','O','O','X'],['X','X','O','X'],['X','O','X','X']]
输出：[['X','X','X','X'],['X','X','X','X'],['X','X','X','X'],['X','O','X','X']]
解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
示例 2：

输入：board = [['X']]
输出：[['X']]
 

提示：

m == board.length
n == board[i].length
1 <= m, n <= 200
board[i][j] 为 'X' 或 'O'

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/surrounded-regions
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
