package twenty.november.no242;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class ValidAnagram {
    public static void main(String[] args) {
        String s1 = "anagram";
        String t1 = "ngaaram";
        System.out.println(isAnagram(s1, t1));
    }

    private static boolean isAnagram(String s, String t) {
//        if the s only contain lowercase, can use 26-size array to stand for hashmap
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> tMap = new HashMap<>();
        IntStream.range(0, s.length()).forEach(i -> {
            sMap.put(s.charAt(i), sMap.getOrDefault(s.charAt(i), 0) + 1);
            tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
        });
        if (tMap.size() != sMap.size()) {
            return false;
        }
        return sMap.keySet().stream().allMatch(key -> sMap.get(key).equals(tMap.get(key)));
    }
}

/*
* 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。

示例 1:

输入: s = "anagram", t = "nagaram"
输出: true
示例 2:

输入: s = "rat", t = "car"
输出: false
说明:
你可以假设字符串只包含小写字母。

进阶:
如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/valid-anagram
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
