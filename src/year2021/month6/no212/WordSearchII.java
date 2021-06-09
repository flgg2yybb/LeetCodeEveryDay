package year2021.month6.no212;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        System.out.println(findWords1(board1, words1));
        System.out.println(findWords1(board2, words2));
        System.out.println(findWords1(board3, words3));
        System.out.println(findWords1(board4, words4));
    }

    private static List<String> findWords1(char[][] board, String[] words) {
        Trie root = new Trie();
        for (String word : words) {
            root.insert(word);
        }
        List<String> ans = new ArrayList<>(words.length);
        int row = board.length;
        int col = board[0].length;
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (root.containsChild(board[i][j])) {
                    visited[i][j] = true;
                    dfs(board, visited, ans, i, j, root);
                    visited[i][j] = false;
                }
            }
        }
        return ans;
    }

    private static void dfs(char[][] board, boolean[][] visited, List<String> ans, int x, int y, Trie parent) {
        char currentChar = board[x][y];
        Trie currentNode = parent.getChild(currentChar);
        if (currentNode.isEnd) {
            ans.add(currentNode.value);
            currentNode.isEnd = false;     // remove word from trie to avoid duplicated result set
            currentNode.value = null;
        }
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int[] direction : directions) {
            int nextX = x + direction[0];
            int nextY = y + direction[1];
            if (isLegalAccess(board, nextX, nextY) && !visited[nextX][nextY]) {
                char nextChar = board[nextX][nextY];
                if (currentNode.containsChild(nextChar)) {
                    visited[nextX][nextY] = true;
                    dfs(board, visited, ans, nextX, nextY, currentNode);
                    visited[nextX][nextY] = false;
                }
            }
        }
        // pruning optimization, remove empty node when children is empty (the leaf will be removed recursively)
        if (currentNode.noChild()) {
            parent.removeChild(currentChar);
        }
    }


    public static List<String> findWords(char[][] board, String[] words) {
        return Stream.of(words).filter(word -> canFind(board, word)).collect(Collectors.toList());
    }

    private static boolean canFind(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (backtrack(board, visited, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean backtrack(char[][] board, boolean[][] visited, int x, int y, String word, int pos) {
        if (pos == word.length()) {
            return true;
        }
        if (!isLegalAccess(board, x, y)) {
            return false;
        }
        if (visited[x][y] || board[x][y] != word.charAt(pos)) {
            return false;
        }
        visited[x][y] = true;
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (int[] direction : directions) {
            int nextX = x + direction[0];
            int nextY = y + direction[1];
            if (backtrack(board, visited, nextX, nextY, word, pos + 1)) {
                return true;
            }
        }
        // 回溯，当前节点未被选中，恢复访问状态
        visited[x][y] = false;
        return false;
    }

    private static boolean isLegalAccess(char[][] board, int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
    }

}

class Trie {

    private final Map<Character, Trie> childrens;
    boolean isEnd;  // is end of word
    String value;   // present only if isEnd = true

    public Trie() {
        this.childrens = new HashMap<>();
        this.isEnd = false;
    }

    public boolean containsChild(char c) {
        return childrens.containsKey(c);
    }

    public Trie getChild(char c) {
        return childrens.get(c);
    }

    public boolean noChild() {
        return childrens.isEmpty();
    }

    public void removeChild(char c) {
        childrens.remove(c);
    }

    public void insert(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!node.childrens.containsKey(c)) {
                node.childrens.put(c, new Trie());
            }
            node = node.childrens.get(c);
        }
        node.isEnd = true;
        node.value = word;
    }
}

/*
* 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。

单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。

 

示例 1：


输入：board = [['o','a','a','n'],['e','t','a','e'],['i','h','k','r'],['i','f','l','v']], words = ["oath","pea","eat","rain"]
输出：['eat','oath']
示例 2：


输入：board = [['a','b'],['c','d']], words = ["abcb"]
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
