package year2021.month3.no242;

public class ValidAnagram {
    public static void main(String[] args) {
        String s1 = "anagram";
        String t1 = "nagaram";
        String s2 = "rat";
        String t2 = "car";
        System.out.println(isAnagram1(s1, t1));
        System.out.println(isAnagram1(s2, t2));
    }

    private static boolean isAnagram1(String s, String t) {
//        优化方案
        if (s.length() != t.length()) {
            return false;
        }
        int[] map = new int[26];    // 若为 Unicode 字符，则使用 HashMap 即可
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            map[t.charAt(i) - 'a']--;
//            因此时 s 与 t 长度相同，若不为字母异位词，则 t 串中必然含有某个字符，其数量大于在其在 s 串中的数量
            if (map[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] map = new int[26];    // 若为 Unicode 字符，则使用 HashMap 即可
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            map[t.charAt(i) - 'a']--;
        }
        for (int value : map) {
            if (value != 0) {
                return false;
            }
        }
        return true;
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
