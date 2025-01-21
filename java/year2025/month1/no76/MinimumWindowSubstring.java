package year2025.month1.no76;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {

    /*
     * Sliding Windows，times: O(n+m), space: O(m)
     * targetMap 维护需要覆盖的 t 串的字符数量
     * curMap 维护需要覆盖的【left,right】窗口的字符数量
     * 【仅关注 t 串出现的字符】，当 curMap 所有字符的数量都 >= targetMap 的所有字符的数量，即当前窗口覆盖 t 串
     * 优化点：
     *   因判断 curMap 是否覆盖 targetMap 需要遍历 targetMap，每次移动完窗口后都判断的话，会额外增加 O(m) 复杂度
     *   因此，新增 include 来表示 t 中有多少个字符已经被当前窗口覆盖
     *   每次对 targetMap 更新时，判断当前窗口内更新的字符数量是否切好处于覆盖的临界状态，来更新 include
     *   从而把遍历 targetMap 是否被覆盖的操作，优化为常数级
     * */
    public static String minWindow(String s, String t) {
        String res = "";
        if (s.length() < t.length()) return res;
        Map<Character, Integer> targetMap = new HashMap<>();
        Map<Character, Integer> curMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            targetMap.put(t.charAt(i), targetMap.getOrDefault(t.charAt(i), 0) + 1);
        }
        int include = 0;    // 表示 t 中有多少个字符已经被当前窗口覆盖，用来优化操作：每次都需要遍历 map 来判断是否覆盖
        int left = 0, right = 0;
        for (; right < s.length(); right++) {
            char c = s.charAt(right);
            if (!targetMap.containsKey(c)) {
                continue;
            }
            curMap.merge(c, 1, Integer::sum);
            if (curMap.get(c).equals(targetMap.get(c))) {   // 只有临界状态需要更新
                include++;
            }
            while (include == targetMap.size()) {   // 判断 t 中的字符是否都被覆盖
                if (res.isEmpty() || res.length() > right + 1 - left) {
                    res = s.substring(left, right + 1);
                }
                char remove = s.charAt(left);
                left++;
                if (targetMap.containsKey(remove)) {
                    if (curMap.get(remove).equals(targetMap.get(remove))) { // 只有临界状态需要更新
                        include--;
                    }
                    curMap.put(remove, curMap.get(remove) - 1);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s1 = "ADOBECODEBANC", t1 = "ABC";
        String s2 = "a", t2 = "a";
        String s3 = "a", t3 = "aa";
        String s4 = "aa", t4 = "aa";
//        System.out.println(minWindow(s1, t1));
//        System.out.println(minWindow(s2, t2));
//        System.out.println(minWindow(s3, t3));
        System.out.println(minWindow(s4, t4));
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
解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
示例 2：

输入：s = "a", t = "a"
输出："a"
解释：整个字符串 s 是最小覆盖子串。
示例 3:

输入: s = "a", t = "aa"
输出: ""
解释: t 中两个字符 'a' 均应包含在 s 的子串中，
因此没有符合条件的子字符串，返回空字符串。


提示：

m == s.length
n == t.length
1 <= m, n <= 105
s 和 t 由英文字母组成


进阶：你能设计一个在 o(m+n) 时间内解决此问题的算法吗？
* */
