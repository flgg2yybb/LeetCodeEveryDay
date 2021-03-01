package year2021.month2.no438;

import java.util.ArrayList;
import java.util.List;

public class FindAllAnagramsInString {
    public static void main(String[] args) {
        String s1 = "cbaebabacd";
        String p1 = "abc";
        String s2 = "abab";
        String p2 = "ab";
        System.out.println(findAnagrams(s1, p1));
        System.out.println(findAnagrams(s2, p2));
    }

    public static List<Integer> findAnagrams(String s, String p) {
//        brute force, time is O(nm), space is O(n)
        List<Integer> res = new ArrayList<>();
        if (s == null || p == null || s.length() < p.length()) {
            return res;
        }
        for (int i = 0; i <= s.length() - p.length(); i++) {
            if (isAnagrams(s, i, p)) {
                res.add(i);
            }
        }
        return res;
    }

    private static boolean isAnagrams(String s, int sIndex, String p) {
        int[] set = new int[26];
        for (int i = 0; i < p.length(); i++) {
            set[p.charAt(i) - 'a']++;
        }
        for (int i = sIndex; i < sIndex + p.length(); i++) {
            set[s.charAt(i) - 'a']--;
        }
        for (int i = 0; i < set.length; i++) {
            if (set[i] != 0) {
                return false;
            }
        }
        return true;
    }
}

/*
* 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。

字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。

说明：

字母异位词指字母相同，但排列不同的字符串。
不考虑答案输出的顺序。
示例 1:

输入:
s: "cbaebabacd" p: "abc"

输出:
[0, 6]

解释:
起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 示例 2:

输入:
s: "abab" p: "ab"

输出:
[0, 1, 2]

解释:
起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
