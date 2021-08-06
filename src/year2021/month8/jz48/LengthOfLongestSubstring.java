package year2021.month8.jz48;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        String s1 = "abcabcbb";
        String s2 = "bbbbb";
        String s3 = "pwwkew";
        String s4 = "abba";
        System.out.println(lengthOfLongestSubstring1(s1));
        System.out.println(lengthOfLongestSubstring1(s2));
        System.out.println(lengthOfLongestSubstring1(s3));
        System.out.println(lengthOfLongestSubstring1(s4));
    }

    private static int lengthOfLongestSubstring1(String s) {
        int maxLength = 0;
        Map<Character, Integer> charMap = new HashMap<>();
        int left = -1;
        int right = 0;
        // window => (left, right]
        while (right < s.length()) {
            char c = s.charAt(right);
            if (charMap.containsKey(c)) {
                left = Math.max(left, charMap.get(c)); // 不能回退
            }
            charMap.put(c, right);
            maxLength = Math.max(maxLength, right - left);
            right++;
        }
        return maxLength;
    }

    public static int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        Set<Character> charSet = new HashSet<>();
        int left = 0;
        int right = 0;
        // window => [left, right]
        while (right < s.length()) {
            char c = s.charAt(right);
            while (charSet.contains(c)) {
                charSet.remove(s.charAt(left));
                left++;
            }
            charSet.add(c);
            maxLength = Math.max(maxLength, charSet.size());
            right++;
        }
        return maxLength;
    }

}

/*
* 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。

 

示例 1:

输入: "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
示例 2:

输入: "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
示例 3:

输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 

提示：

s.length <= 40000
注意：本题与主站 3 题相同：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
