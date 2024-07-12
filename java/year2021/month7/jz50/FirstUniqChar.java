package year2021.month7.jz50;

import java.util.LinkedHashMap;
import java.util.Map;

public class FirstUniqChar {

    public static void main(String[] args) {
        String s1 = "azbaccdeff";
        String s2 = "";
        System.out.println(firstUniqChar1(s1));
        System.out.println(firstUniqChar1(s2));
    }

    private static char firstUniqChar1(String s) {
        Map<Character, Integer> count = new LinkedHashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        for (Character key : count.keySet()) {
            if (count.get(key) == 1) {
                return key;
            }
        }
        return ' ';
    }

    public static char firstUniqChar(String s) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            count[c - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (count[c - 'a'] == 1) {
                return c;
            }
        }
        return ' ';
    }

}

/*
* 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。

示例:

s = "abaccdeff"
返回 "b"

s = "" 
返回 " "
 

限制：

0 <= s 的长度 <= 50000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
