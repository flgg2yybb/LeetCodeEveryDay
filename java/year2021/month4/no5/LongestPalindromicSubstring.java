package year2021.month4.no5;

public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        String s1 = "babad";
        String s2 = "cbbd";
        String s3 = "a";
        String s4 = "ac";
        String s5 = "aaaaa";
        System.out.println(longestPalindrome1(s1));
        System.out.println(longestPalindrome1(s2));
        System.out.println(longestPalindrome1(s3));
        System.out.println(longestPalindrome1(s4));
        System.out.println(longestPalindrome1(s5));
    }

    private static String longestPalindrome1(String s) {
        // 中心拓展算法，time is O(n^2), space is O(1)
        // 枚举中心，依次向两边拓展
        // 可在每个字符中间加上 # 符号以统一处理奇偶情况
        char[] chars = s.toCharArray();
        // 子串为 [start, end)
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            int oddLength = centerExpand(chars, i, i);
            int evenLength = centerExpand(chars, i - 1, i);
            int len = Math.max(oddLength, evenLength);
            if (len > end - start) {
                start = i - len / 2;
                end = start + len;
            }
        }
        return s.substring(start, end);
    }

    private static int centerExpand(char[] chars, int left, int right) {
        while (left >= 0 && right < chars.length && chars[left] == chars[right]) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public static String longestPalindrome(String s) {
        /*DP, time is O(n^2), space is O(n^2)
        * 状态定义
        * dp[i][j] 表示 s 串中从下标 i 到下标 j 的元素是否为回文串
        * 状态转移方程
        * dp[i][j] = dp[i+1][j-1] && s[i] == s[j], i + 2 <= j
        * 状态转移方向 (i+1, j-1) -> (i, j)，在二维平面上，需要从 左下 向 右上迭代计算
        * 初始值
        * dp[i][i] = true
        * dp[i][i+1] = s[i] == s[i+1]
        * */
        int length = s.length();
        boolean[][] dp = new boolean[length][length];
        int start = 0;
        int end = 0;
        for (int i = 0; i < length; i++) {
            dp[i][i] = true;
        }
        for (int i = 0; i < length - 1; i++) {
            dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
            if (dp[i][i + 1] && end - start == 0) {
                start = i;
                end = i + 1;
            }
        }
        for (int i = length - 3; i >= 0; i--) {
            for (int j = i + 2; j < length; j++) {
                dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
                if (dp[i][j] && end - start < j - i) {
                    start = i;
                    end = j;
                }
            }
        }
        return s.substring(start, end + 1);
    }

}

/*
* 给你一个字符串 s，找到 s 中最长的回文子串。

 

示例 1：

输入：s = "babad"
输出："bab"
解释："aba" 同样是符合题意的答案。
示例 2：

输入：s = "cbbd"
输出："bb"
示例 3：

输入：s = "a"
输出："a"
示例 4：

输入：s = "ac"
输出："a"
 

提示：

1 <= s.length <= 1000
s 仅由数字和英文字母（大写和/或小写）组成

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-palindromic-substring
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
