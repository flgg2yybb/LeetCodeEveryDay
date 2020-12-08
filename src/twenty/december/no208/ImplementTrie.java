package twenty.december.no208;

import java.util.HashMap;
import java.util.Map;

public class ImplementTrie {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));   //true
        System.out.println(trie.search("app"));     //false
        System.out.println(trie.startsWith("app"));     //true
        trie.insert("app");
        System.out.println(trie.search("app"));     //true
    }
}

class Trie {

    private static final int MAX_SIZE = 26;
    private Map<Character, Trie> childrenMap = null;
    private boolean isEnd = false;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        childrenMap = new HashMap<>(MAX_SIZE);
        isEnd = false;
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            Trie nextNode;
            if (!node.getChildrenMap().containsKey(c)) {
                nextNode = new Trie();
                node.getChildrenMap().put(c, nextNode);
            } else {
                nextNode = node.getChildrenMap().get(c);
            }
            node = nextNode;
        }
        node.setEnd(true);
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!node.getChildrenMap().containsKey(c)) {
                return false;
            }
            node = node.getChildrenMap().get(c);
        }
        return node.isEnd();
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        Trie node = this;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!node.getChildrenMap().containsKey(c)) {
                return false;
            }
            node = node.getChildrenMap().get(c);
        }
        return true;
    }

    public Map<Character, Trie> getChildrenMap() {
        return childrenMap;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }
}

/*
* 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。

示例:

Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // 返回 true
trie.search("app");     // 返回 false
trie.startsWith("app"); // 返回 true
trie.insert("app");
trie.search("app");     // 返回 true
说明:

你可以假设所有的输入都是由小写字母 a-z 构成的。
保证所有输入均为非空字符串。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
