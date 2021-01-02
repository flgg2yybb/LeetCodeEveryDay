package year2020.month12.no212;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WordSearch2 {
    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        String[] words = new String[]{"oath", "pea", "eat", "rain"};
        List<String> existedWords = findWords(board, words);
        existedWords.forEach(System.out::println);
    }

    public static List<String> findWords(char[][] board, String[] words) {
        if (words == null || board == null) {
            return Collections.emptyList();
        }
        return Arrays.stream(words).filter(word -> exist(board, word)).collect(Collectors.toList());
    }

    public static boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0 || board.length == 0 || board[0].length == 0) {
            return false;
        }
        char start = word.charAt(0);
        int[][] visited = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == start) {
                    visited[i][j] = 1;
                    if (dfs(board, visited, i, j, word, 0)) {
                        return true;
                    }
                    visited[i][j] = 0;
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
        int colMax = board[row].length - 1;   //x
        int nextPos = pos + 1;
        int nextChar = word.charAt(nextPos);
        int[][] direction = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int i = 0; i < direction.length; i++) {
            int newRow = row + direction[i][0];
            int newCol = col + direction[i][1];
            if (newRow >= 0 && newRow <= rowMax && newCol >= 0 && newCol <= colMax) {
                if (visited[newRow][newCol] == 0 && board[newRow][newCol] == nextChar) {
                    visited[newRow][newCol] = 1;
                    if (dfs(board, visited, newRow, newCol, word, nextPos)) {
                        return true;
                    }
                    visited[newRow][newCol] = 0;
                }
            }
        }
        return false;
    }
}


/*
* 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。

单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。

 

示例 1：


输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
输出：["eat","oath"]
示例 2：


输入：board = [["a","b"],["c","d"]], words = ["abcb"]
输出：[]
 

提示：

m == board.length
n == board[i].length
1 <= m, n <= 12
board[i][j] 是一个小写英文字母
1 <= words.length <= 3 * 104
1 <= words[i].length <= 10
words[i] 由小写英文字母组成
words 中的所有字符串互不相同

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/word-search-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
