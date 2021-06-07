package year2021.month6.no131;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {

    public static void main(String[] args) {
        String s1 = "aab";
        String s2 = "a";
        String s3 = "aabaab";
        System.out.println(partition1(s1));
        System.out.println(partition1(s2));
        System.out.println(partition1(s3));
    }

    private static List<List<String>> partition1(String s) {
        // 记忆化搜索 + 回溯
        int length = s.length();
        List<List<String>> ans = new ArrayList<>();
        int[][] cache = new int[length][length];
        memorySearchAndBacktrack(s, cache, ans, new ArrayList<>(), 0);
        return ans;
    }

    private static void memorySearchAndBacktrack(String s, int[][] cache, List<List<String>> ans, List<String> strings, int pos) {
        if (pos == s.length()) {
            ans.add(new ArrayList<>(strings));
            return;
        }
        for (int i = pos; i < s.length(); i++) {
            if (isPalindrome(s, cache, pos, i)) {
                strings.add(s.substring(pos, i + 1));
                memorySearchAndBacktrack(s, cache, ans, strings, i + 1);
                strings.remove(strings.size() - 1);
            }
        }
    }

    private static boolean isPalindrome(String s, int[][] cache, int i, int j) {
        // cache 取值为 0 未搜索； -1 不是回文串； 1 是回文串
        if (cache[i][j] != 0) {
            return cache[i][j] == 1;
        }
        if (i >= j) {
            cache[i][j] = 1;
        } else if (s.charAt(i) == s.charAt(j)) {
            // 当长度为 2 时，即 j = i + 1 时，回去搜索 cache[j][i]，刚好猜中上一个条件为 true
            return isPalindrome(s, cache, i + 1, j - 1);
        } else {
            cache[i][j] = -1;
        }
        return cache[i][j] == 1;
    }

    public static List<List<String>> partition(String s) {
        // DP + 回溯
        // dp[i][j] 表示 s 串中的索引为 [i, j] 的子串是否为回文串
        // 时间复杂度, DP - O(n^2)，回溯 - O(2^n * n)
        // 空间复杂度, dp数组 - O(n^2)，回溯递归栈深度 — O(n)，结果数组 - O(2^n)
        int length = s.length();
        boolean[][] dp = new boolean[length][length];
        for (int i = 0; i < length; i++) {
            dp[i][i] = true;
        }
        for (int i = 0; i < length - 1; i++) {
            dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
        }
        // dp转移方程从左下到右上，因为 dp 行从下往上遍历
        for (int i = length - 3; i >= 0; i--) {
            for (int j = i + 2; j < length; j++) {
                dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
            }
        }
        List<List<String>> ans = new ArrayList<>();
        backtrack(s, dp, ans, new ArrayList<>(), 0);
        return ans;
    }

    private static void backtrack(String s, boolean[][] dp, List<List<String>> ans, List<String> strings, int pos) {
        if (pos == s.length()) {
            ans.add(new ArrayList<>(strings));
            return;
        }
        for (int i = pos; i < s.length(); i++) {
            if (dp[pos][i]) {
                strings.add(s.substring(pos, i + 1));
                backtrack(s, dp, ans, strings, i + 1);
                strings.remove(strings.size() - 1);
            }
        }
    }

}

/*
* 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。

回文串 是正着读和反着读都一样的字符串。

 

示例 1：

输入：s = "aab"
输出：[["a","a","b"],["aa","b"]]

示例 2：

输入：s = "a"
输出：[["a"]]

示例 3：

输入：s = "aabaab"
输出：[["a","a","b","a","a","b"],["a","a","b","aa","b"],["a","a","baab"],["a","aba","a","b"],["aa","b","a","a","b"],["aa","b","aa","b"],["aa","baab"],["aabaa","b"]]
 

提示：

1 <= s.length <= 16
s 仅由小写英文字母组成

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/palindrome-partitioning
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
