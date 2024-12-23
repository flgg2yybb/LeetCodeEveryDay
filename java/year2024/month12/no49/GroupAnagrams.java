package year2024.month12.no49;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GroupAnagrams {

    // time: O(nm), space: O(nm)
    public static List<List<String>> groupAnagrams(String[] strs) {
        return Arrays.stream(strs).collect(Collectors.groupingBy((s -> {
            int[] hashSlots = new int[26];
            for (char c : s.toCharArray()) {
                hashSlots[c - 'a']++;
            }
            StringBuilder keyBuilder = new StringBuilder();
            for (int i = 0; i < hashSlots.length; i++) {
                if (hashSlots[i] > 0) {
                    keyBuilder.append(hashSlots[i]).append((char) (i + 'a'));
                }
            }
            return keyBuilder.toString();
        }))).values().stream().toList();
    }

    // time: O(n * mlogm), space: O(nm)
    public static List<List<String>> groupAnagrams1(String[] strs) {
        return Arrays.stream(strs).collect(Collectors.groupingBy((s -> {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            return new String(chars);
        }))).values().stream().toList();
    }

    public static void main(String[] args) {
        String[] strs1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        String[] strs2 = {""};
        String[] strs3 = {"a"};
        System.out.println(groupAnagrams(strs1));
        System.out.println(groupAnagrams(strs2));
        System.out.println(groupAnagrams(strs3));
    }
}

/*
给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。

字母异位词 是由重新排列源单词的所有字母得到的一个新单词。



示例 1:

输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
示例 2:

输入: strs = [""]
输出: [[""]]
示例 3:

输入: strs = ["a"]
输出: [["a"]]


提示：

1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] 仅包含小写字母
*/

