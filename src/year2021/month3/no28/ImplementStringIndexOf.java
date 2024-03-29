package year2021.month3.no28;

public class ImplementStringIndexOf {
    public static void main(String[] args) {
        String hayStack1 = "hello";
        String needle1 = "ll";
        String hayStack2 = "aaaaa";
        String needle2 = "bba";
        String hayStack3 = "aaaaa";
        String needle3 = "";
        String hayStack4 = "";
        String needle4 = "";
        String hayStack5 = "aaa";
        String needle5 = "aaaa";
        System.out.println(strStr(hayStack1, needle1));
        System.out.println(strStr(hayStack2, needle2));
        System.out.println(strStr(hayStack3, needle3));
        System.out.println(strStr(hayStack4, needle4));
        System.out.println(strStr(hayStack5, needle5));
    }

    public static int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            int j;
            for (j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
            }
            if (j == needle.length()) {
                return i;
            }
        }
        return -1;
    }
}

/*
* 实现  strStr()  函数。

给定一个  haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回   -1。

示例 1:

输入: haystack = "hello", needle = "ll"
输出: 2
示例 2:

输入: haystack = "aaaaa", needle = "bba"
输出: -1
说明:

当  needle  是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。

对于本题而言，当  needle  是空字符串时我们应当返回 0 。这与C语言的  strstr()  以及 Java的  indexOf()  定义相符。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/implement-strstr
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
