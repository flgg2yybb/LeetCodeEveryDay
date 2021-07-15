package year2021.month7.jz12;

public class Exist {

    public static void main(String[] args) {
        char[][] board1 = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word1 = "ABCCED";
        char[][] board2 = {
                {'a', 'b'},
                {'c', 'd'}
        };
        String word2 = "abcd";
        System.out.println(exist(board1, word1));
        System.out.println(exist(board2, word2));
    }

    public static boolean exist(char[][] board, String word) {
        /*
         * time is O(mn*3^L), space is O(nm)
         * 时间复杂度分析
         * 对于 board 中的每一个格子，我们都将其作为起点进行匹配，故有 mn 个起点需要匹配
         * 每次匹配，我们都会向格子的三个方向延伸（不走回头路），最长匹配字符串长度 L ，故可匹配的最大格子数为 3^L
         * 故总时间复杂度为 O(mn * 3^L)
         * */
        if (word == null || word.length() == 0) {
            return false;
        }
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    visited[i][j] = true;
                    if (dfs(board, visited, i, j, word, 1)) {
                        return true;
                    }
                    visited[i][j] = false;
                }
            }
        }
        return false;
    }

    private static boolean dfs(char[][] board, boolean[][] visited, int x, int y, String word, int nextPost) {
        if (nextPost == word.length()) {
            return true;
        }
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        char nextChar = word.charAt(nextPost);
        for (int[] direction : directions) {
            int nextX = x + direction[0];
            int nextY = y + direction[1];
            if (isLegalAccess(board, nextX, nextY) && !visited[nextX][nextY] && nextChar == board[nextX][nextY]) {
                visited[nextX][nextY] = true;
                if (dfs(board, visited, nextX, nextY, word, nextPost + 1)) {
                    return true;
                }
                visited[nextX][nextY] = false;
            }
        }
        return false;
    }

    private static boolean isLegalAccess(char[][] board, int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
    }

}

/*
* 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻'单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

 

例如，在下面的 3×4 的矩阵中包含单词 'ABCCED'（单词中的字母已标出）。



 

示例 1：

输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = '"ABCCED"'
输出：true
示例 2：

输入：board = [['a','b'],['c','d']], word = 'abcd'
输出：false
 

提示：

1 <= board.length <= 200
1 <= board[i].length <= 200
board 和 word 仅由大小写英文字母组成
 

注意：本题与主站 79 题相同：https://leetcode-cn.com/problems/word-search/

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
