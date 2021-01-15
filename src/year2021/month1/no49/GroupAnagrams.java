package year2021.month1.no49;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class GroupAnagrams {
    public static void main(String[] args) {
        String[] strs1 = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        disp(groupAnagrams(strs1));
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return result;
        }
        int[][] alphaSet = new int[strs.length][26];
        int[] sizes = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            boolean isNewGroup = true;
            for (int j = 0; j < result.size(); j++) {
                if (str.length() != sizes[j]) {
                    continue;
                }
                if (isAnagrams(alphaSet, str, j)) {
                    result.get(j).add(str);
                    isNewGroup = false;
                    break;
                }
            }
            if (isNewGroup) {
                int index = result.size();
                List<String> list = new LinkedList<>();
                list.add(str);
                result.add(list);
                sizes[index] = str.length();
                for (char c : str.toCharArray()) {
                    alphaSet[index][c - 'a']++;
                }
            }
        }
        return result;
    }

    private static boolean isAnagrams(int[][] alphaSet, String str, int index) {
        int[] set = Arrays.copyOf(alphaSet[index], alphaSet[index].length);
        for (char c : str.toCharArray()) {
            set[c - 'a']--;
        }
        return IntStream.of(set).allMatch(num -> num == 0);
    }

    private static void disp(List<List<String>> result) {
        if (result == null || result.size() == 0) {
            System.out.println("EMPTY");
            return;
        }
        System.out.println("[");
        result.forEach(System.out::println);
        System.out.println("]");
    }
}

/*
* 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

示例:

输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
输出:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
说明：

所有输入均为小写字母。
不考虑答案输出的顺序。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/group-anagrams
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
