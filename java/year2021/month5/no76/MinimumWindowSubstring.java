package year2021.month5.no76;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {

    public static void main(String[] args) {
        String s1 = "ADOBECODEBANC";
        String t1 = "ABC";
        String s2 = "a";
        String t2 = "a";
        String s3 = "ADOBECODEBANCA";
        String t3 = "ABCA";
        String s4 = "ADOBECODEB";
        String t4 = "ABCA";
        System.out.println(minWindow(s1, t1));  //BANC
        System.out.println(minWindow(s2, t2));  //a
        System.out.println(minWindow(s3, t3));  //BANCA
        System.out.println(minWindow(s4, t4));  //""
    }

    public static String minWindow(String s, String t) {
        // 观察测试样例，可知 s 中的最小子串必须覆盖 t 中所有字符以及出现的次数
        // 滑动窗口，使用一个 map 来表示当前窗口中的子串相比于 t 串中
        // 还缺少哪些字符以及哪些字符有剩余
        // 同时用一个 need 标志位，表示 t 中还有几个字符没有被覆盖到
        // 使用 [left, right] 表示滑动窗口
        // 先右移 right 直至 t 中所有字符以及次数都被覆盖，记录下当前子串的起始末尾
        // 逐步右移 left 并根据 need 和 map 统计当前是否还能覆盖到子串 t
        // 若可以，更新最短子串起始末尾索引，直至当前窗口不能覆盖 t
        // 再次右移 right，循环往复直至终点
        Map<Character, Integer> countMap = new HashMap<>();     // 表示当前窗口对于某个元素还缺少几个
        for (char c : t.toCharArray()) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }
        int need = countMap.size();     // 表示当前窗口还有几个元素没有覆盖
        // start & end 为覆盖子串的起始、末尾索引
        int start = 0;
        int end = 0;
        // 滑动窗口的指针
        int left = 0;
        int right = 0;
        while (right < s.length()) {
            // 扩充窗口直至覆盖 t 串
            if (need > 0) {
                char newChar = s.charAt(right);
                if (countMap.containsKey(newChar)) {
                    countMap.put(newChar, countMap.get(newChar) - 1);
                    if (countMap.get(newChar) == 0) {
                        need--;
                    }
                }
            }
            // 收缩窗口直至不能覆盖 t 串
            while (left <= right && need == 0) {
                // 更新最小覆盖子串索引
                if (end == 0 || right - left + 1 < end - start) {
                    start = left;
                    end = right + 1;
                }
                char remove = s.charAt(left);
                left++;
                if (countMap.containsKey(remove)) {
                    countMap.put(remove, countMap.get(remove) + 1);
                    if (countMap.get(remove) == 1) {
                        need++;
                    }
                }
            }
            right++;
        }
        return s.substring(start, end);
    }

}

/*
* 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。

注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。

 

示例 1：

输入：s = "ADOBECODEBANC", t = "ABC"
输出："BANC"
示例 2：

输入：s = "a", t = "a"
输出："a"
 

提示：

1 <= s.length, t.length <= 105
s 和 t 由英文字母组成
 

进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimum-window-substring
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
