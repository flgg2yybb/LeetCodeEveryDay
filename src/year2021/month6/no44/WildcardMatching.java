package year2021.month6.no44;

import java.util.stream.IntStream;

public class WildcardMatching {

    public static void main(String[] args) {
        String s1 = "aa";
        String p1 = "a";
        String s2 = "aa";
        String p2 = "*";
        String s3 = "cb";
        String p3 = "?a";
        String s4 = "adceb";
        String p4 = "*a*b";
        String s5 = "acdcb";
        String p5 = "a*c?b";
        String s6 = "";
        String p6 = "*";
        System.out.println(isMatch(s1, p1));
        System.out.println(isMatch(s2, p2));
        System.out.println(isMatch(s3, p3));
        System.out.println(isMatch(s4, p4));
        System.out.println(isMatch(s5, p5));
        System.out.println(isMatch(s6, p6));
    }

    public static boolean isMatch(String s, String p) {
        return backtrack(s, 0, p, 0);
    }

    private static boolean backtrack(String s, int i, String p, int j) {
        if (i == s.length() && j == p.length()) {
            // 如果字符串 s 和 p 均遍历完成，则匹配
            return true;
        }
        if (i == s.length()) {
            // 如果字符串 s 遍历完成，且 p 串剩下的字符均为 * 则匹配
            return IntStream.range(j, p.length()).boxed().allMatch(index -> p.charAt(index) == '*');
        }
        if (j == p.length()) {
            // 如果只有 p 串遍历完成，而 s 串未遍历完成，则不匹配
            return false;
        }
        char sChar = s.charAt(i);
        char pChar = p.charAt(j);
        if (pChar == '?') {
            return backtrack(s, i + 1, p, j + 1);
        }
        if (pChar == '*') {
            //  * 匹配空字符 || * 匹配多个字符 || * 匹配一个字符
            return backtrack(s, i, p, j + 1) || backtrack(s, i + 1, p, j) || backtrack(s, i + 1, p, j + 1);
        }
        if (sChar != pChar) {
            return false;
        }
        return backtrack(s, i + 1, p, j + 1);
    }

}

/*
* 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。

'?' 可以匹配任何单个字符。
'*' 可以匹配任意字符串（包括空字符串）。
两个字符串完全匹配才算匹配成功。

说明:

s 可能为空，且只包含从 a-z 的小写字母。
p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
示例 1:

输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。
示例 2:

输入:
s = "aa"
p = "*"
输出: true
解释: '*' 可以匹配任意字符串。
示例 3:

输入:
s = "cb"
p = "?a"
输出: false
解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
示例 4:

输入:
s = "adceb"
p = "*a*b"
输出: true
解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
示例 5:

输入:
s = "acdcb"
p = "a*c?b"
输出: false

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/wildcard-matching
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
