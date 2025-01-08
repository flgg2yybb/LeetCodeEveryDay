package year2025.month1.no3;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {
    // Sliding Window, time: O(n), space: O(n)
    public static int lengthOfLongestSubstring(String s) {
        int ans = 0;
        int left = -1;
        int right = 0;
        Map<Character, Integer> map = new HashMap<>();  // k：char，v：last index
        while (right < s.length()) {
            if (map.containsKey(s.charAt(right))) {     // exist duplicated char
                // NOTE: only allow left to increase
                left = Math.max(left, map.get(s.charAt(right)));
            }
            ans = Math.max(ans, right - left);
            map.put(s.charAt(right), right);
            right++;
        }
        return ans;
    }

    public static void main(String[] args) {
        String s1 = "abcabcbb";
        String s2 = "bbbbb";
        String s3 = "pwwkew";
        String s4 = "abba";
        System.out.println(lengthOfLongestSubstring(s1));
        System.out.println(lengthOfLongestSubstring(s2));
        System.out.println(lengthOfLongestSubstring(s3));
        System.out.println(lengthOfLongestSubstring(s4));
    }
}

/*
给定一个字符串 s ，请你找出其中不含有重复字符的 最长
子串
 的长度。



示例 1:

输入: s = "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
示例 2:

输入: s = "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
示例 3:

输入: s = "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。


提示：

0 <= s.length <= 5 * 104
s 由英文字母、数字、符号和空格组成
*/
