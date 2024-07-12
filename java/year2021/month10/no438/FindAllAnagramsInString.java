package year2021.month10.no438;

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
        int[] elements = new int[26];
        int need = 0;
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            elements[c - 'a']++;
            if (elements[c - 'a'] == 1) {
                need++;
            }
        }
        List<Integer> ans = new ArrayList<>();
        int left = 0;
        int right = 0;
        int have = 0;
        int[] window = new int[26];
        // window: [left, right]
        while (right < s.length()) {
            char c = s.charAt(right);
            int index = c - 'a';
            if (elements[index] != 0) {
                window[index]++;
                if (window[index] == elements[index]) {
                    have++;
                }
            }
            if (right - left + 1 == p.length()) {
                if (have == need) {
                    ans.add(left);
                }
                char remove = s.charAt(left);
                int removeIndex = remove - 'a';
                if (elements[removeIndex] != 0) {
                    if (window[removeIndex] == elements[removeIndex]) {
                        have--;
                    }
                    window[removeIndex]--;
                }
                left++;
            }
            right++;
        }
        return ans;
    }

}

/*
* 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。

异位词 指字母相同，但排列不同的字符串。

 

示例 1:

输入: s = "cbaebabacd", p = "abc"
输出: [0,6]
解释:
起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 示例 2:

输入: s = "abab", p = "ab"
输出: [0,1,2]
解释:
起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 

提示:

1 <= s.length, p.length <= 3 * 104
s 和 p 仅包含小写字母

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
