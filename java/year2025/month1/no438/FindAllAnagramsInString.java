package year2025.month1.no438;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class AnagramsMap {

    /* charCounter：key 为 char， value 为 char 需要的个数
    初始化时，对需要匹配的字符串 p 字符依次放入 map 中，同时以 “负数” 标记 p 中字符的个数
    后续遍历字符串 s 时，每次取长度等同于 p 的子串 k，将字符依次放入 map 中，同时以 “正数” 标记 k 中字符的个数
    当某个 key（字符）的 value == 0 时，将该 key 从 map 中移除
    如果 map 中无任何 key（len（dic） == 0）则代表当前子串 k 是 p 的异位词
    */
    private final Map<Character, Integer> charCounter;

    public AnagramsMap(String p) {
        this.charCounter = new HashMap<>();
        for (char c : p.toCharArray()) {
            this.pop(c);
        }
    }

    private void operate(char c, int value) {
        int newValue = this.charCounter.getOrDefault(c, 0) + value;
        if (newValue == 0) {
            this.charCounter.remove(c);
        } else {
            this.charCounter.put(c, newValue);
        }
    }

    void pop(char c) {
        this.operate(c, -1);
    }

    void push(char c) {
        this.operate(c, 1);
    }

    boolean isAnagrams() {
        return this.charCounter.isEmpty();
    }
}

public class FindAllAnagramsInString {

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) {
            return result;
        }
        AnagramsMap anagramsMap = new AnagramsMap(p);
        char[] chars = s.toCharArray();
        for (int end = 0; end < chars.length; end++) {
            anagramsMap.push(chars[end]);
            if (end < p.length() - 1) {
                // 当前 [start, end] 长度小于子串 p 的长度，必然不存在异位词
                continue;
            }
            int start = end - p.length() + 1;
            if (start > 0) {
                // 需要保证 anagramMap 中始终仅包含 [start, end] 的字符
                anagramsMap.pop(chars[start - 1]);
            }
            if (anagramsMap.isAnagrams()) {
                result.add(start);
            }

        }
        return result;
    }

    public static void main(String[] args) {
        String s1 = "cbaebabacd", p1 = "abc";
        String s2 = "abab", p2 = "ab";
        System.out.println(findAnagrams(s1, p1));
        System.out.println(findAnagrams(s2, p2));
    }
}

/*
给定两个字符串 s 和 p，找到 s 中所有 p 的
异位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序。



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
*/
