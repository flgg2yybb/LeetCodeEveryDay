package year2021.month10.no76;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {

    public static void main(String[] args) {
        String s11 = "ADOBECODEBANC";
        String s12 = "ABC";
        String s21 = "a";
        String s22 = "a";
        String s31 = "a";
        String s32 = "aa";
        System.out.println(minWindow(s11, s12));
        System.out.println(minWindow(s21, s22));
        System.out.println(minWindow(s31, s32));
    }

    public static String minWindow(String s, String t) {
        // Sliding Window
        if (s.length() < t.length()) {
            return "";
        }
        Map<Character, Integer> charCount = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            charCount.merge(t.charAt(i), 1, Integer::sum);
        }
        int need = charCount.size();
        int have = 0;
        int left = 0;
        int start = 0;
        int end = Integer.MAX_VALUE;
        for (int right = 0; right < s.length(); right++) {
            char cur = s.charAt(right);
            if (!charCount.containsKey(cur)) {
                continue;
            }
            charCount.merge(cur, 1, (v1, v2) -> v1 - v2);
            if (charCount.get(cur) == 0) {
                have++;
            }
            while (have == need) {
                if (end - start > right - left) {
                    end = right;
                    start = left;
                }
                char remove = s.charAt(left);
                if (charCount.containsKey(remove)) {
                    charCount.merge(remove, 1, Integer::sum);
                    if (charCount.get(remove) == 1) {
                        have--;
                    }
                }
                left++;
            }
        }
        return end == Integer.MAX_VALUE ? "" : s.substring(start, end + 1);
    }

}

/*
* 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。

 

注意：

对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
如果 s 中存在这样的子串，我们保证它是唯一的答案。
 

示例 1：

输入：s = "ADOBECODEBANC", t = "ABC"
输出："BANC"
示例 2：

输入：s = "a", t = "a"
输出："a"
示例 3:

输入: s = "a", t = "aa"
输出: ""
解释: t 中两个字符 'a' 均应包含在 s 的子串中，
因此没有符合条件的子字符串，返回空字符串。
 

提示：

1 <= s.length, t.length <= 105
s 和 t 由英文字母组成
 

进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimum-window-substring
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
