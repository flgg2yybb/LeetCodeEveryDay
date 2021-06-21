package year2021.month6.no10;

public class RegularExpressionMatching {

    public static void main(String[] args) {
        String s1 = "aa";
        String p1 = "a";
        String s2 = "aa";
        String p2 = "a*";
        String s3 = "ab";
        String p3 = ".*";
        String s4 = "aab";
        String p4 = "c*a*b";
        String s5 = "mississippi";
        String p5 = "mis*is*p*.";
        String s6 = "";
        String p6 = "";
        String s7 = "";
        String p7 = "a";
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

    public static boolean isMatch(String s, String p) {
        /*
         * DP, time is O(nm), space is O(nm)
         * 状态定义
         * dp[i][j] 表示 s 串中的 [0,i) 子串能否和 p 串中的 [0,j) 子串进行匹配
         * 状态转移方程
         * 若 p[j-1] = '.'，则
         *   dp[i][j] = dp[i-1][j-1]
         * 若 p[j-1] = '*'，则
         *   dp[i][j] = dp[i-1][j] && s[i-1] match p[j-2] || dp[i][j-2]
         *               匹配多个字符                          不匹配（需要把 * 前面的字符也去掉）
         * 否则，p[j-1] 为小写字母，则
         *   dp[i][j] = s[i-1] == p[j-1] && dp[i-1][j-1]
         * 初始值
         * dp[0][0] = true，（空串可以和空串进行匹配）
         * dp[0][2j+1] = false，（列为奇数时，比不可与空串匹配）
         * dp[0][2j] = dp[0][2j-2] && p[2j-1] == '*'，（列为偶数时，若前面的偶数列均可匹配，且 p 的当前字符为 *，则匹配，否则均不匹配）
         * dp[i][0] = false
         * */
        int n = s.length();
        int m = p.length();
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int j = 1; j <= m; j++) {
            if ((j & 1) == 1) {
                // 奇数
                dp[0][j] = false;
            } else {
                // 偶数
                dp[0][j] = dp[0][j - 2] && p.charAt(j - 1) == '*';
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                char sChar = s.charAt(i - 1);
                char pCurrChar = p.charAt(j - 1);
                if (pCurrChar == '*') {
                    char pPrevChar = p.charAt(j - 2);
                    dp[i][j] = dp[i - 1][j] && isMatchChar(sChar, pPrevChar) || dp[i][j - 2];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] && isMatchChar(sChar, pCurrChar);
                }
            }
        }
        return dp[n][m];
    }

    private static boolean isMatchChar(char sChar, char pChar) {
        if (pChar == '.') {
            return true;
        }
        return sChar == pChar;
    }

}

/*
* 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。

'.' 匹配任意单个字符
'*' 匹配零个或多个前面的那一个元素
所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。

 
示例 1：

输入：s = "aa" p = "a"
输出：false
解释："a" 无法匹配 "aa" 整个字符串。
示例 2:

输入：s = "aa" p = "a*"
输出：true
解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
示例 3：

输入：s = "ab" p = ".*"
输出：true
解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
示例 4：

输入：s = "aab" p = "c*a*b"
输出：true
解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
示例 5：

输入：s = "mississippi" p = "mis*is*p*."
输出：false
 

提示：

0 <= s.length <= 20
0 <= p.length <= 30
s 可能为空，且只包含从 a-z 的小写字母。
p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
保证每次出现字符 * 时，前面都匹配到有效的字符

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/regular-expression-matching
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
