package year2020.month11.no5;

public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        String s1 = "babad";    //bab, aba
        String s2 = "cbbd";     //bb
        String s3 = "a";        //a
        String s4 = "ac";       //a, c
        String s5 = "aaaaaaa";       //aaaaaaa
        System.out.println(longestPalindrome3(s1));
        System.out.println(longestPalindrome3(s2));
        System.out.println(longestPalindrome3(s3));
        System.out.println(longestPalindrome3(s4));
        System.out.println(longestPalindrome3(s5));
    }

    private static String longestPalindrome3(String s) {
//        dynamic programming
        int begin = 0;
        int maxLen = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];
//        len means the length of s[i...j]
        for (int len = 1; len <= s.length(); len++) {
            for (int i = 0; i <= s.length() - len; i++) {
                int j = i + len - 1;
                if (len == 1) {
                    dp[i][j] = true;
                } else if (len == 2) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                }
                if (dp[i][j] && maxLen < len) {
                    maxLen = len;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    private static String longestPalindrome2(String s) {
        if (s.length() < 2) {
            return s;
        }
//        the longest palindrome shoule be the s[begin,end)
        int begin = 0;
        int end = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
//            consider the palindrome is odd or even, and compare to get the one that more longer
            int oddLen = centerExpand(chars, i, i);
            int evenLen = centerExpand(chars, i, i + 1);
            int len = Math.max(oddLen, evenLen);
            if (len > end - begin) {
                begin = i - (len - 1) / 2;
                end = begin + len;
            }
        }
        return s.substring(begin, end);
    }

    private static int centerExpand(char[] chars, int left, int right) {
        while (left >= 0 && right < chars.length && chars[left] == chars[right]) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    private static String longestPalindrome1(String s) {
//        certer expand, add separator # to make sub palindrome string is odd
        int begin = 0;
        int len = 0;
        String tempString = getTempString(s);
        for (int i = 0; i < tempString.length(); i++) {
            int left = i;
            int right = i;
            while (left >= 0 && right < tempString.length() && tempString.charAt(left) == tempString.charAt(right)) {
                left--;
                right++;
            }
            if (len < right - left - 1) {
                len = right - left - 1;
                begin = left + 1;
            }
        }
        return tempString.substring(begin, begin + len).replace("#", "");
    }

    private static String getTempString(String s) {
        StringBuilder sb = new StringBuilder("#");
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i)).append("#");
        }
        return new String(sb);
    }
}

/*
* 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

示例 1：

输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。
示例 2：

输入: "cbbd"
输出: "bb"

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-palindromic-substring
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
