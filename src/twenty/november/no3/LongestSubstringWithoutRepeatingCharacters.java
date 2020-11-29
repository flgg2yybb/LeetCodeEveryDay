package twenty.november.no3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        String str = "asdfg";
//        System.out.println(solution1(str));
//        System.out.println(solution2(str));
        System.out.println(solution3(str));
    }

    private static int solution3(String s) {
        int max = 0;
        Map<Character, Integer> map = new HashMap<>();
        int left = 0, curr = 0;
        while (curr < s.length()) {
            Character c = s.charAt(curr);
            if (map.containsKey(c) && left <= map.get(c)) {
                if (max < curr - left) {
                    max = curr - left;
                }
                left = map.get(c) + 1;
            }
            map.put(c, curr);
            curr++;
        }
        if (max < curr - left) {
            max = curr - left;
        }
        return max;
    }

//    private static int solution2(String s) {
//        int max = 0;
//        List<Character> list = new ArrayList<>();
//        Map<Character, Integer> map = new HashMap<>();
//        for (int i = 0; i < s.length(); i++) {
//            Character currChar = s.charAt(i);
//            if (map.containsKey(currChar)) {
//                if (max < map.size()) {
//                    max = map.size();
//                }
//                while (map.containsKey(currChar)) {
//                    map.remove(list.remove(0));
//                }
//            }
//            map.put(currChar, i);
//            list.add(currChar);
//        }
//        if (max < map.size()) {
//            max = map.size();
//        }
//        return max;
//    }

    private static int solution1(String s) {
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            int currNum = 1;
            Set<Character> set = new HashSet<>();
            set.add(s.charAt(i));
            for (int j = i + 1; j < s.length(); j++) {
                if (set.contains(s.charAt(j))) {
                    break;
                }
                currNum++;
                set.add(s.charAt(j));
            }
            max = Math.max(max, currNum);
        }
        return max;
    }
}

/*
* 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

示例 1:

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
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
