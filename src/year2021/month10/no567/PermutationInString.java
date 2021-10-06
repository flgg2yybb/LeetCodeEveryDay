package year2021.month10.no567;

public class PermutationInString {

    public static void main(String[] args) {
        String s11 = "ab";
        String s12 = "eidbaooo";
        String s21 = "ab";
        String s22 = "eidboaoo";
        System.out.println(checkInclusion(s11, s12));
        System.out.println(checkInclusion(s21, s22));
    }

    public static boolean checkInclusion(String s1, String s2) {
        int[] elements = new int[26];
        int need = 0;
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            elements[c - 'a']++;
            if (elements[c - 'a'] == 1) {
                need++;
            }
        }
        int right = 0;
        int left = 0;
        int have = 0;
        int[] window = new int[26];
        // window: [left, right]
        while (right < s2.length()) {
            char c = s2.charAt(right);
            window[c - 'a']++;
            if (window[c - 'a'] == elements[c - 'a']) {
                have++;
            }
            while (right - left + 1 >= s1.length()) {
                if (have == need) {
                    return true;
                }
                char remove = s2.charAt(left);
                if (window[remove - 'a'] == elements[remove - 'a']) {
                    have--;
                }
                window[remove - 'a']--;
                left++;
            }
            right++;
        }
        return false;
    }

}

/*
* 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。

换句话说，s1 的排列之一是 s2 的 子串 。

 

示例 1：

输入：s1 = "ab" s2 = "eidbaooo"
输出：true
解释：s2 包含 s1 的排列之一 ("ba").
示例 2：

输入：s1= "ab" s2 = "eidboaoo"
输出：false
 

提示：

1 <= s1.length, s2.length <= 104
s1 和 s2 仅包含小写字母

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/permutation-in-string
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
