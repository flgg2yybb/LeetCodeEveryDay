package twenty.december.no37;

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
