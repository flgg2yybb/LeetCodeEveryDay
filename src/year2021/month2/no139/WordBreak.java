package year2021.month2.no139;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
    public static void main(String[] args) {
        String s1 = "leetcode";
        List<String> wordDict1 = Arrays.asList("leet", "code");
        String s2 = "applepenapple";
        List<String> wordDict2 = Arrays.asList("apple", "pen");
        String s3 = "catsandog";
        List<String> wordDict3 = Arrays.asList("cats", "dog", "sand", "and", "cat");
        System.out.println(wordBreak(s1, wordDict1));
        System.out.println(wordBreak(s2, wordDict2));
        System.out.println(wordBreak(s3, wordDict3));
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        char[] chars = s.toCharArray();
        return dfs(chars, set, 0);
    }

    private static boolean dfs(char[] chars, Set<String> set, int pos) {
        if (chars.length == pos) {
            return true;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = pos; i < chars.length; i++) {
            sb.append(chars[i]);
            if (set.contains(sb.toString()) && dfs(chars, set, i + 1)) {
                return true;
            }
        }
        return false;
    }
}

/*
* 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定  s 是否可以被空格拆分为一个或多个在字典中出现的单词。

说明：

拆分时可以重复使用字典中的单词。
你可以假设字典中没有重复的单词。
示例 1：

输入: s = "leetcode", wordDict = ["leet", "code"]
输出: true
解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
示例 2：

输入: s = "applepenapple", wordDict = ["apple", "pen"]
输出: true
解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
      注意你可以重复使用字典中的单词。
示例 3：

输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
输出: false

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/word-break
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
