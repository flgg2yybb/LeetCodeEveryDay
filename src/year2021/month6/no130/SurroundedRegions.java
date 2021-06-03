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
                {'X', 'X', 'O', 'O', 'X'}
        };
        System.out.println(Arrays.deepToString(board1));
        solve(board1);
        System.out.println(Arrays.deepToString(board1));
        System.out.println(Arrays.deepToString(board2));
        solve(board2);
        System.out.println(Arrays.deepToString(board2));
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
