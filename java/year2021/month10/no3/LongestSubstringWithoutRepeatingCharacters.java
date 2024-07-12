package year2021.month10.no3;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        String s1 = "abcabcbb";
        String s2 = "bbbbb";
        String s3 = "pwwkew";
        String s4 = "";
        System.out.println(lengthOfLongestSubstring(s1));
        System.out.println(lengthOfLongestSubstring(s2));
        System.out.println(lengthOfLongestSubstring(s3));
        System.out.println(lengthOfLongestSubstring(s4));
    }

    private static int lengthOfLongestSubstring(String s) {
        // 优化版
        int longest = 0;
        int left = -1;
        int right = 0;
        // key 元素， value 索引
        Map<Character, Integer> window = new HashMap<>();
        // window: (left, right]
        while (right < s.length()) {
            char c = s.charAt(right);
            if (window.containsKey(c)) {
                // 用 max 来防止 left 后退（被窗口外的元素影响）
                left = Math.max(left, window.get(c));
            }
            window.put(c, right);
            longest = Math.max(longest, right - left);
            right++;
        }
        return longest;
    }

    public static int lengthOfLongestSubstring1(String s) {
        int longest = 0;
        int left = 0;
        int right = 0;
        // key 元素， value 出现次数
        Map<Character, Integer> window = new HashMap<>();
        // window: [left, right]
        while (right < s.length()) {
            char c = s.charAt(right);
            window.merge(c, 1, Integer::sum);
            while (window.get(c) > 1) {
                char remove = s.charAt(left);
                window.merge(remove, -1, Integer::sum);
                left++;
            }
            longest = Math.max(longest, right - left + 1);
            right++;
        }
        return longest;
    }

}

/*
* 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。

 

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
示例 4:

输入: s = ""
输出: 0
 

提示：

0 <= s.length <= 5 * 104
s 由英文字母、数字、符号和空格组成

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
