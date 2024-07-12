package year2021.month7.jz38;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Permutation {

    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "aab";
        System.out.println(Arrays.toString(permutation(s1)));
        System.out.println(Arrays.toString(permutation(s2)));
    }

    public static String[] permutation(String s) {
        Set<String> ans = new HashSet<>();
        char[] chars = s.toCharArray();
        backtrack(chars, ans, 0);
        return ans.toArray(new String[0]);
    }

    private static void backtrack(char[] chars, Set<String> ans, int pos) {
        if (chars.length == pos) {
            ans.add(new String(chars));
            return;
        }
        for (int i = pos; i < chars.length; i++) {
            swap(chars, i, pos);
            backtrack(chars, ans, pos + 1);
            swap(chars, i, pos);
        }
    }

    private static void swap(char[] chars, int x, int y) {
        char temp = chars[x];
        chars[x] = chars[y];
        chars[y] = temp;
    }

}

/*
* 输入一个字符串，打印出该字符串中字符的所有排列。

 

你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。

 

示例:

输入：s = "abc"
输出：["abc","acb","bac","bca","cab","cba"]
 

限制：

1 <= s 的长度 <= 8

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
