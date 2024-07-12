package year2021.month9.no394;

import java.util.Deque;
import java.util.LinkedList;

public class DecodeString {
    public static void main(String[] args) {
        String s1 = "3[a]2[bc]";
        String s2 = "3[a2[c]]";
        String s3 = "2[abc]3[cd]ef";
        String s4 = "abc3[cd]xyz";
        String s6 = "10[leetcode]";
        System.out.println(decodeString(s1));
        System.out.println(decodeString(s2));
        System.out.println(decodeString(s3));
        System.out.println(decodeString(s4));
        System.out.println(decodeString(s6));
    }

    private static int index;

    private static String decodeString(String s) {
        index = 0;
        return dfs(s);
    }

    private static String dfs(String s) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (index < s.length()) {
            char c = s.charAt(index);
            if (Character.isDigit(c)) {
                count = count * 10 + (c - '0');
            } else if (c == '[') {
                index++;
                String temp = dfs(s);
                for (int i = 0; i < count; i++) {
                    sb.append(temp);
                }
                count = 0;
            } else if (c == ']') {
                break;
            } else {
                sb.append(c);
            }
            index++;
        }
        return sb.toString();
    }

    public static String decodeString1(String s) {
        StringBuilder res = new StringBuilder();
        Deque<StringBuilder> stack = new LinkedList<>();
        Deque<Integer> numStack = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                count = 10 * count + (c - '0');
            } else if (c == '[') {
                numStack.push(count);
                stack.push(res);
                count = 0;
                res = new StringBuilder();
            } else if (c == ']') {
                Integer repeat = numStack.pop();
                StringBuilder temp = new StringBuilder();
                for (int j = 0; j < repeat; j++) {
                    temp.append(res);
                }
                res = new StringBuilder(stack.pop()).append(temp);
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }
}

/*
* 给定一个经过编码的字符串，返回它解码后的字符串。

编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。

你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。

此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。

 

示例 1：

输入：s = "3[a]2[bc]"
输出："aaabcbc"
示例 2：

输入：s = "3[a2[c]]"
输出："accaccacc"
示例 3：

输入：s = "2[abc]3[cd]ef"
输出："abcabccdcdcdef"
示例 4：

输入：s = "abc3[cd]xyz"
输出："abccdcdcdxyz"

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/decode-string
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
