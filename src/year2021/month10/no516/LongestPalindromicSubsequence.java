package year2021.month10.no516;

public class LongestPalindromicSubsequence {

    public static void main(String[] args) {
        String s1 = "bbbab";
        String s2 = "cbbd";
        String s3 = "abcdef";
        System.out.println(longestPalindromeSubseq(s1));
        System.out.println(longestPalindromeSubseq(s2));
        System.out.println(longestPalindromeSubseq(s3));
    }

    public static int longestPalindromeSubseq(String s) {
        /*
         * DP, time is O(n^2), space is O(n^2)
         * 状态定义：
         * dp[i][j] 表示串 s 中 [i,j] 子串的最长回文子序列的长度
         * 状态庄泳方程：
         * if s[i] == s[j]
         *   dp[i][j] = dp[i+1][j-1] + 2
         * else
         *   dp[i][j] = max{dp[i][j-1], dp[i+1][j]}
         * 转移方向为：左下 => 右上，故需要行逆序遍历
         * 初始值：
         * dp[i][i] = 1
         * dp[i][i+1] = s[i] == s[i+1] ? 2 : 0
         * */
        int n = s.length();
        int[][] dp = new int[n][n];
        // 子序列长度为 1 的情况
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int i = n - 2; i >= 0; i--) {  // 行逆序遍历，从倒数第二行开始，也就是子序列长度为 2 开始
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        return dp[0][n - 1];
    }

}

/*
* 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。

子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。

 

示例 1：

输入：s = "bbbab"
输出：4
解释：一个可能的最长回文子序列为 "bbbb" 。
示例 2：

输入：s = "cbbd"
输出：2
解释：一个可能的最长回文子序列为 "bb" 。
 

提示：

1 <= s.length <= 1000
s 仅由小写英文字母组成

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-palindromic-subsequence
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
