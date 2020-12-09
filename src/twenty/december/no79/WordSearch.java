package twenty.december.no79;

public class WordSearch {

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        System.out.println(exist(board, "ABCCED"));
        System.out.println(exist(board, "SEE"));
        System.out.println(exist(board, "ABCB"));
        System.out.println(exist(board, "CCC"));
        System.out.println(exist(board, "CCEE"));
        System.out.println(exist(board, "CCES"));
        System.out.println(exist(board, ""));
    }

    public static boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0 || board.length == 0 || board[0].length == 0) {
            return false;
        }
        char start = word.charAt(0);
        int[][] visited = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == start) {
                    visited[i][j] = 1;
                    if (dfs2(board, visited, i, j, word, 0)) {
                        return true;
                    }
                    visited[i][j] = 0;
                }
            }
        }
        return false;
    }

    private static boolean dfs2(char[][] board, int[][] visited, int row, int col, String word, int pos) {
        if (pos == word.length() - 1) {
            return true;
        }
        int rowMax = board.length - 1;      //y
        int colMax = board[0].length - 1;   //x
        int nextPos = pos + 1;
        int nextChar = word.charAt(nextPos);
        int[][] direction = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int i = 0; i < direction.length; i++) {
            int newRow = row + direction[i][0];
            int newCol = col + direction[i][1];
            if (newRow >= 0 && newRow <= rowMax && newCol >= 0 && newCol <= colMax) {
                if (visited[newRow][newCol] == 0 && board[newRow][newCol] == nextChar) {
                    visited[newRow][newCol] = 1;
                    if (dfs2(board, visited, newRow, newCol, word, nextPos)) {
                        return true;
                    }
                    visited[newRow][newCol] = 0;
                }
            }
        }
        return false;
    }

    private static boolean dfs(char[][] board, int[][] visited, int row, int col, String word, int pos) {
        if (pos == word.length() - 1) {
            return true;
        }
        int rowMax = board.length - 1;      //y
        int colMax = board[0].length - 1;   //x
        int nextPos = pos + 1;
        int nextChar = word.charAt(nextPos);
        if (row - 1 >= 0 && visited[row - 1][col] == 0 && board[row - 1][col] == nextChar) {
            visited[row - 1][col] = 1;
            if (dfs(board, visited, row - 1, col, word, nextPos)) {
                return true;
            }
            visited[row - 1][col] = 0;
        }
        if (row + 1 <= rowMax && visited[row + 1][col] == 0 && board[row + 1][col] == nextChar) {
            visited[row + 1][col] = 1;
            if (dfs(board, visited, row + 1, col, word, nextPos)) {
                return true;
            }
            visited[row + 1][col] = 0;
        }
        if (col - 1 >= 0 && visited[row][col - 1] == 0 && board[row][col - 1] == nextChar) {
            visited[row][col - 1] = 1;
            if (dfs(board, visited, row, col - 1, word, nextPos)) {
                return true;
            }
            visited[row][col - 1] = 0;
        }
        if (col + 1 <= colMax && visited[row][col + 1] == 0 && board[row][col + 1] == nextChar) {
            visited[row][col + 1] = 1;
            if (dfs(board, visited, row, col + 1, word, nextPos)) {
                return true;
            }
            visited[row][col + 1] = 0;
        }
        return false;
    }
}

/*
* 给定一个二维网格和一个单词，找出该单词是否存在于网格中。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

 

示例:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

给定 word = "ABCCED", 返回 true
给定 word = "SEE", 返回 true
给定 word = "ABCB", 返回 false
 

提示：

board 和 word 中只包含大写和小写英文字母。
1 <= board.length <= 200
1 <= board[i].length <= 200
1 <= word.length <= 10^3

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/word-search
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */