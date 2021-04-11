package year2021.month4.no3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        String s1 = "abcabcbb";
        String s2 = "bbbbb";
        String s3 = "pwwkew";
        String s4 = "";
        System.out.println(lengthOfLongestSubstring1(s1));
        System.out.println(lengthOfLongestSubstring1(s2));
        System.out.println(lengthOfLongestSubstring1(s3));
        System.out.println(lengthOfLongestSubstring1(s4));
    }

    private static int lengthOfLongestSubstring1(String s) {
        // 优化版：sliding window -> (left, right]
        // 用 map 来记录 right 已经遍历过的元素，其中 key 为元素
        // value 为 key 这个元素在 s 中的下标
        // 则在 right 指针右移至已出现的元素时
        // 可直接将 left 指针移动到 map 中记录的 value （重复元素的下标）
        // **注意**：map 不移除元素，故 map 作 contains 操作时，可能会被窗口外的元素影响，
        // 只需保证 left 右移（增加）即可
        Map<Character, Integer> map = new HashMap<>();
        int len = 0;
        for (int left = -1, right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (map.containsKey(c)) {
                // 用 max 来防止 left 后退（被窗口外的元素影响）
                left = Math.max(left, map.get(c));
            }
            len = Math.max(len, right - left);
            map.put(c, right);
        }
        return len;
    }

    public static int lengthOfLongestSubstring(String s) {
        // sliding window -> (left, right]
        // 用 set 来存储滑动窗口里的元素集合，依次右移右指针，并将元素加入 set 中，
        // 当右指针移动到已在 set 中出现过的的元素时，
        // 右移左指针，并将左指针指向的元素从 set 中移除，
        // 直至将当前已出现的元素在 set 中移除，
        // 此时窗口里的元素为以 left + 1 字符开始的最长不重复子串
        Set<Character> set = new HashSet<>();
        int left = -1;
        int right = 0;
        int len = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            if (set.contains(c)) {
                do {
                    left++;
                    set.remove(s.charAt(left));
                } while (s.charAt(left) != c);
            }
            set.add(c);
            len = Math.max(len, right - left);
            right++;
        }
        return len;
    }

}

/*
* 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

 

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
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
