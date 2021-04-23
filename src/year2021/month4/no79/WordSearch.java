package year2021.month4.no79;

public class WordSearch {

    public static void main(String[] args) {
        char[][] board1 = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word1 = "ABCCED";
        char[][] board2 = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word2 = "SEE";
        char[][] board3 = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word3 = "ABCB";
        System.out.println(exist(board1, word1));   //true
        System.out.println(exist(board2, word2));   //true
        System.out.println(exist(board3, word3));   //false
    }

    public static boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        int pos = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (word.charAt(pos) == board[i][j]) {
                    if (backtrack(board, i, j, visited, word, pos + 1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean backtrack(char[][] board, int row, int col, boolean[][] visited, String word, int pos) {
        if (pos == word.length()) {
            return true;
        }
        visited[row][col] = true;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int[] direction : directions) {
            int nextRow = row + direction[0];
            int nextCol = col + direction[1];
            if (isLegalAccess(board, nextRow, nextCol) && !visited[nextRow][nextCol] && board[nextRow][nextCol] == word.charAt(pos)) {
                if (backtrack(board, nextRow, nextCol, visited, word, pos + 1)) {
                    return true;
                }
            }
        }
        visited[row][col] = false;
        return false;
    }

    private static boolean isLegalAccess(char[][] board, int row, int col) {
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
    }
}

/*
* 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

 

示例 1：


输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = "ABCCED"
输出：true
示例 2：


输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = "SEE"
输出：true
示例 3：


输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = "ABCB"
输出：false
 

提示：

m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board 和 word 仅由大小写英文字母组成
 

进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/word-search
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
