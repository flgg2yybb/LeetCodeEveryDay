package year2021.month9.no212;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class WordSearchII {
    public static void main(String[] args) {
        char[][] board1 = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] words1 = {"oath", "pea", "eat", "rain"};
        char[][] board2 = {{'a', 'b'}, {'c', 'd'}};
        String[] words2 = {"abcd"};
        char[][] board3 = {{'a'}};
        String[] words3 = {"a"};
        char[][] board4 = {{'a', 'b', 'c'}, {'a', 'e', 'd'}, {'a', 'f', 'g'}};
        String[] words4 = {"abcdefg", "gfedcbaaa", "eaabcdgfa", "befa", "dgc", "ade"};
        System.out.println(findWords(board1, words1));
        System.out.println(findWords(board2, words2));
        System.out.println(findWords(board3, words3));
        System.out.println(findWords(board4, words4));
    }

    public static List<String> findWords(char[][] board, String[] words) {
        int m = board.length;
        int n = board[0].length;
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        List<String> ans = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(trie, ans, board, visited, i, j);
            }
        }
        return ans;
    }

    private static void dfs(Trie trie, List<String> ans, char[][] board, boolean[][] visited, int row, int col) {
        if (!isLegalAccess(board.length, board[0].length, row, col)) {
            return;
        }
        if (visited[row][col]) {
            return;
        }
        char c = board[row][col];
        Trie childTrie = trie.childrens.get(c);
        if (childTrie == null) {
            return;
        }
        visited[row][col] = true;   // 在正式访问前才标记为 visited，否则 visited 不会被回溯（提前 return 导致没有回退状态）
        if (childTrie.isEnd) {
            ans.add(childTrie.word);
            childTrie.isEnd = false;    // 防止重复添加
        }
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (int[] direction : directions) {
            int nextRow = row + direction[0];
            int nextCol = col + direction[1];
            dfs(childTrie, ans, board, visited, nextRow, nextCol);
        }
        if (childTrie.childrens.isEmpty()) {    // 剪枝
            trie.childrens.remove(c);
        }
        visited[row][col] = false;
    }

    private static boolean isLegalAccess(int m, int n, int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}

class Trie {

    final Map<Character, Trie> childrens;
    boolean isEnd;
    String word;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        childrens = new HashMap<>(26);
        isEnd = false;
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            node.childrens.putIfAbsent(c, new Trie());
            node = node.childrens.get(c);
        }
        node.isEnd = true;
        node.word = word;
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
