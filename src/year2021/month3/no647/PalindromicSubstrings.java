package year2021.month3.no647;

public class PalindromicSubstrings {
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "aaa";
        String s3 = "fdsklf";
        System.out.println(countSubstrings(s1));    //3
        System.out.println(countSubstrings(s2));    //6
        System.out.println(countSubstrings(s3));    //6
    }

    public static int countSubstrings(String s) {
        /*DP
         * 状态定义：
         * dp[i][j]表示字符串s中[i,j]所构成的子串是否为回文子串
         * i <= j
         * */
        boolean[][] dp = new boolean[s.length()][s.length()];
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }
        count += s.length();
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                if (((j - i + 1) & 1) == 1) {
                    //奇数
                    dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
                } else {
                    //偶数
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                    if (j - i + 1 > 2) {
                        dp[i][j] &= dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }
}

/*
* 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。

具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。

 

示例 1：

输入："abc"
输出：3
解释：三个回文子串: "a", "b", "c"
示例 2：

输入："aaa"
输出：6
解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 

提示：

输入的字符串长度不会超过 1000 。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/palindromic-substrings
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
