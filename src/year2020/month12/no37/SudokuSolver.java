package year2020.month12.no37;

public class SudokuSolver {
    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        solveSudoku(board);
        disp(board);
        System.out.println(isValidSudoku(board));
    }

    public static void solveSudoku(char[][] board) {
        int size = board.length;
        int[][] rows = new int[size][size];
        int[][] cols = new int[size][size];
        int[][] boxes = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int value = board[i][j] - '1';
                rows[i][value] = 1;
                cols[j][value] = 1;
                boxes[(i / 3) * 3 + j / 3][value] = 1;
            }
        }
        backtrack(board, rows, cols, boxes, 0, 0);
    }

    private static boolean backtrack(char[][] board, int[][] rows, int[][] cols, int[][] boxes, int row_index, int col_index) {
        int size = board.length;
        if (col_index == size) {
            col_index = 0;
            row_index++;
            if (row_index == size) {
                return true;
            }
        }
        if (board[row_index][col_index] != '.') {
            return backtrack(board, rows, cols, boxes, row_index, col_index + 1);
        }
        for (char c = '1'; c <= '9'; c++) {
            int value = c - '1';
            if (rows[row_index][value] == 1 || cols[col_index][value] == 1 || boxes[(row_index / 3) * 3 + col_index / 3][value] == 1) {
                continue;
            }
            board[row_index][col_index] = c;
            rows[row_index][value] = 1;
            cols[col_index][value] = 1;
            boxes[(row_index / 3) * 3 + col_index / 3][value] = 1;
            if (backtrack(board, rows, cols, boxes, row_index, col_index + 1)) {
                return true;
            }
            board[row_index][col_index] = '.';
            rows[row_index][value] = 0;
            cols[col_index][value] = 0;
            boxes[(row_index / 3) * 3 + col_index / 3][value] = 0;
        }
        return false;
    }

    private static void disp(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + "  ");
            }
            System.out.println();
        }
    }

    //    copy from no36/ValidSudoku.java
    private static boolean isValidSudoku(char[][] board) {
        int size = board.length;
        int[][] rows = new int[size][size];
        int[][] cols = new int[size][size];
        int[][] boxes = new int[size][size];
//        行表示第i个row、col、boxes集合
//        列表示第i个row、col、boxes集合中 1到9的元素是否出现过，0为未出现，1为出现过，用此数组代替Set/Map
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int value = board[i][j] - '1';
                int boxesIndex = (i / 3) * 3 + j / 3;
                if (rows[i][value] == 1 || cols[j][value] == 1 || boxes[boxesIndex][value] == 1) {
                    return false;
                }
                rows[i][value] = 1;
                cols[j][value] = 1;
                boxes[boxesIndex][value] = 1;
            }
        }
        return true;
    }
}

/*
* 编写一个程序，通过填充空格来解决数独问题。

一个数独的解法需遵循如下规则：

数字 1-9 在每一行只能出现一次。
数字 1-9 在每一列只能出现一次。
数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
空白格用 '.' 表示。



一个数独。



答案被标成红色。

提示：

给定的数独序列只包含数字 1-9 和字符 '.' 。
你可以假设给定的数独只有唯一解。
给定数独永远是 9x9 形式的。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sudoku-solver
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
