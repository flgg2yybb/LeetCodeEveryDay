package year2021.month3.no387;

public class FirstUniqueCharacterInString {
    public static void main(String[] args) {
        String s1 = "leetcode";
        String s2 = "loveleetcode";
        String s3 = "aa";
        System.out.println(firstUniqChar1(s1));
        System.out.println(firstUniqChar1(s2));
        System.out.println(firstUniqChar1(s3));
    }

    private static int firstUniqChar1(String s) {
//        map 保存 字符 - 字符首次出现的索引加一，因int数组默认值为0，不好区分首个元素是否出现
        int[] map = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            map[c] = map[c] == 0 ? i + 1 : -1;
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < map.length; i++) {
            if (map[i] > 0) {
                res = Math.min(res, map[i]);
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res - 1;
    }

    public static int firstUniqChar(String s) {
//        map 保存 字符 - 字符出现的次数
        int[] map = new int[26];
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (map[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}

/*
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。

 

示例：

s = "leetcode"
返回 0

s = "loveleetcode"
返回 2
 

提示：你可以假定该字符串只包含小写字母。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/first-unique-character-in-a-string
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
