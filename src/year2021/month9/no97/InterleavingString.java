package year2021.month9.no97;

public class InterleavingString {
    public static void main(String[] args) {
        String s11 = "aabcc";
        String s12 = "dbbca";
        String s13 = "aadbbcbcac";
        String s21 = "aabcc";
        String s22 = "dbbca";
        String s23 = "aadbbbaccc";
        String s31 = "";
        String s32 = "";
        String s33 = "";
        System.out.println(isInterleave(s11, s12, s13));
        System.out.println(isInterleave(s21, s22, s23));
        System.out.println(isInterleave(s31, s32, s33));
    }

    public static boolean isInterleave(String s1, String s2, String s3) {
        /*
         * DP, time is O(nm), space is O(nm)
         * 状态定义
         * dp[i][j] 表示 s1 前 i 个子串 与 s2 前 j 个子串 能否交错形成 s3 前 (i+j) 个子串
         * 状态转移方程
         * dp[i][j]  = dp[i-1][j] && s1[i-1] == s3[i+j-1] || dp[i][j-1] && s2[j-1] == s3[i+j-1]
         * 初始值
         * dp[0][0] = true   // 空串
         * dp[i][0] = dp[i-1][0] && s1[i-1] == s3[i-1], i > 0
         * dp[0][j] = dp[0][j-1] && s2[j-1] == s3[j-1], j > 0
         * */
        int m = s1.length();
        int n = s2.length();
        if (n + m != s3.length()) {
            return false;
        }
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)
                        || dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
            }
        }
        return dp[m][n];
    }
}

/*
* 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。

两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：

s = s1 + s2 + ... + sn
t = t1 + t2 + ... + tm
|n - m| <= 1
交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
提示：a + b 意味着字符串 a 和 b 连接。

 

示例 1：


输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
输出：true
示例 2：

输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
输出：false
示例 3：

输入：s1 = "", s2 = "", s3 = ""
输出：true
 

提示：

0 <= s1.length, s2.length <= 100
0 <= s3.length <= 200
s1、s2、和 s3 都由小写英文字母组成

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/interleaving-string
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */