package year2021.month6.no44;

import java.util.stream.IntStream;

public class WildcardMatching {

    public static void main(String[] args) {
        String s1 = "aa";
        String p1 = "a";
        String s2 = "aa";
        String p2 = "*";
        String s3 = "cb";
        String p3 = "?a";
        String s4 = "adceb";
        String p4 = "*a*b";
        String s5 = "acdcb";
        String p5 = "a*c?b";
        String s6 = "";
        String p6 = "*";
        String s7 = "";
        String p7 = "";
        String s8 = "a";
        String p8 = "";
        System.out.println(isMatch(s1, p1));
        System.out.println(isMatch(s2, p2));
        System.out.println(isMatch(s3, p3));
        System.out.println(isMatch(s4, p4));
        System.out.println(isMatch(s5, p5));
        System.out.println(isMatch(s6, p6));
        System.out.println(isMatch(s7, p7));
        System.out.println(isMatch(s8, p8));
    }

    private static boolean isMatch(String s, String p) {
        /*
         * DP, time is O(nm), space is O(nm)
         * n == s.length, m == p.length
         * 状态定义
         * dp[i][j] 表示 s 串中的 [0,i) 的子串能否与 p 串中的 [0,j) 的子串进行匹配
         * 初始值
         * dp[0][0] = true （空串可以和空串匹配）
         * dp[0][j] = dp[0][j - 1] && p.charAt(j - 1) == '*', j > 0
         * dp[i][0] = false, i > 0
         * 状态转移方程
         * 需要求解状态 dp[i][j], i > 0 && j > 0
         * 若 p[j] == '?'，由于 '?' 可以匹配单个字符
         *   则 dp[i][j] = dp[i-1][j-1]
         * 若 p[j] == '*'，则 '*' 可以匹配 零到多个 字符
         *   则 dp[i][j] = dp[i-1][j] || dp[i-1][j-1] || dp[i][j-1]
         *                 匹配多个字符     匹配一个字符      不匹配
         *   可简化为 dp[i][j] = dp[i-1][j] || dp[i][j-1]
         *   注：dp[i][j] == dp[i-1][j] 意味着 s[0,i)与 p[0,j) 的匹配结果 等于 s[0,i-1)与 p[0,j) 的匹配结果，即 s[i] 这个字符可以忽略，可体现为被 * 所匹配
         * 否则, p[j] 为普通字符, 则有,
         *   dp[i][j] = s[i] == p[j] && dp[i-1][j-1]
         * 状态转移方向，dp数组从左上到右下
         * */
        int n = s.length();
        int m = p.length();
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int j = 1; j <= m; j++) {
            dp[0][j] = dp[0][j - 1] && p.charAt(j - 1) == '*';
        }
        for (int i = 1; i <= n; i++) {  // 可省略
            dp[i][0] = false;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int sIndex = i - 1;
                int pIndex = j - 1;
                if (p.charAt(pIndex) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(pIndex) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else {
                    dp[i][j] = s.charAt(sIndex) == p.charAt(pIndex) && dp[i - 1][j - 1];
                }
            }
        }
        return dp[n][m];
    }

    public static boolean isMatch1(String s, String p) {
        return backtrack(s, 0, p, 0);
    }

    private static boolean backtrack(String s, int i, String p, int j) {
        if (i == s.length() && j == p.length()) {
            // 如果字符串 s 和 p 均遍历完成，则匹配
            return true;
        }
        if (i == s.length()) {
            // 如果字符串 s 遍历完成，且 p 串剩下的字符均为 * 则匹配
            return IntStream.range(j, p.length()).boxed().allMatch(index -> p.charAt(index) == '*');
        }
        if (j == p.length()) {
            // 如果只有 p 串遍历完成，而 s 串未遍历完成，则不匹配
            return false;
        }
        char sChar = s.charAt(i);
        char pChar = p.charAt(j);
        if (pChar == '?') {
            return backtrack(s, i + 1, p, j + 1);
        }
        if (pChar == '*') {
            //  * 匹配空字符 || * 匹配多个字符 || * 匹配一个字符
            return backtrack(s, i, p, j + 1) || backtrack(s, i + 1, p, j) || backtrack(s, i + 1, p, j + 1);
        }
        if (sChar != pChar) {
            return false;
        }
        return backtrack(s, i + 1, p, j + 1);
    }

}

/*
* 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。

'?' 可以匹配任何单个字符。
'*' 可以匹配任意字符串（包括空字符串）。
两个字符串完全匹配才算匹配成功。

说明:

s 可能为空，且只包含从 a-z 的小写字母。
p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
示例 1:

输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。
示例 2:

输入:
s = "aa"
p = "*"
输出: true
解释: '*' 可以匹配任意字符串。
示例 3:

输入:
s = "cb"
p = "?a"
输出: false
解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
示例 4:

输入:
s = "adceb"
p = "*a*b"
输出: true
解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
示例 5:

输入:
s = "acdcb"
p = "a*c?b"
输出: false

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/wildcard-matching
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
