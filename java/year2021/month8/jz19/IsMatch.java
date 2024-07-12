package year2021.month8.jz19;

public class IsMatch {

    public static void main(String[] args) {
        String s1 = "aa";
        String p1 = "a";
        String s2 = "aa";
        String p2 = "a*";
        String s3 = "aa";
        String p3 = ".*";
        String s4 = "aab";
        String p4 = "c*a*b";
        String s5 = "mississippi";
        String p5 = "mis*is*p*.";
        String s6 = "mississippi";
        String p6 = "mis*is*ip*.";
        String s7 = "";
        String p7 = "";
        String s8 = "";
        String p8 = ".*";
        String s9 = "a";
        String p9 = "";
        String s10 = "aaa";
        String p10 = "a*";
        System.out.println(isMatch(s1, p1));
        System.out.println(isMatch(s2, p2));
        System.out.println(isMatch(s3, p3));
        System.out.println(isMatch(s4, p4));
        System.out.println(isMatch(s5, p5));
        System.out.println(isMatch(s6, p6));
        System.out.println(isMatch(s7, p7));
        System.out.println(isMatch(s8, p8));
        System.out.println(isMatch(s9, p9));
        System.out.println(isMatch(s10, p10));
    }

    public static boolean isMatch(String s, String p) {
        /*
         * DP, dp[i][j]表示字符串 s 中的 [0,i) 子串与字符串 p 中的 [0,j) 子串匹配
         * 状态转移方程
         * if p[j-1] 为字母，则
         *   dp[i][j] = dp[i-1][j-1] && s[i-1] == p[j-1]
         * else i-1f p[j-1] == '.'，则
         *   dp[i][j] = dp[i-1][j-1]
         * else // p[j-1] = '*'
         *   dp[i][j] = dp[i][j-2] || dp[i-1][j] && s[i-1] match p [j-2]
         *                   不匹配             匹配多个
         * 初始值
         * dp[0][0] = true
         * dp[0][j] = j % 2 == 0 && dp[0][j-2] && p[j-1] == '*', j > 0
         * dp[i][0] = false, i > 0
         * 备注：
         * '*'匹配多个的情况，假设
         * 串 s : xxxxxxa
         * 串 p : xxb*
         * 若先前 'xxxxxx' 能与 'xxb*' 匹配，则 a == b 时，
         * 串 s 也能与串 p 匹配
         * */
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 2; j <= n; j += 2) {
            dp[0][j] = dp[0][j - 2] && p.charAt(j - 1) == '*';
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int sIndex = i - 1;
                int pIndex = j - 1;
                if (p.charAt(pIndex) != '*') {
                    dp[i][j] = dp[i - 1][j - 1] && match(s, sIndex, p, pIndex);
                } else {    // p[j-1] == '*'
                    dp[i][j] = dp[i][j - 2] || dp[i - 1][j] && match(s, sIndex, p, pIndex - 1);
                }
            }
        }
        return dp[m][n];
    }

    private static boolean match(String s, int sIndex, String p, int pIndex) {
        if (p.charAt(pIndex) == '.') {
            return true;
        }
        return s.charAt(sIndex) == p.charAt(pIndex);
    }

}

/*
* 请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。

示例 1:

输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。
示例 2:

输入:
s = "aa"
p = "a*"
输出: true
解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
示例 3:

输入:
s = "ab"
p = ".*"
输出: true
解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
示例 4:

输入:
s = "aab"
p = "c*a*b"
输出: true
解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
示例 5:

输入:
s = "mississippi"
p = "mis*is*p*."
输出: false
s 可能为空，且只包含从 a-z 的小写字母。
p 可能为空，且只包含从 a-z 的小写字母以及字符 . 和 *，无连续的 '*'。
注意：本题与主站 10 题相同：https://leetcode-cn.com/problems/regular-expression-matching/

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/zheng-ze-biao-da-shi-pi-pei-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
