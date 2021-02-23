package year2021.month2.no394;

import java.util.Deque;
import java.util.LinkedList;

public class DecodeString {

    private static int index = 0;

    public static void main(String[] args) {
        String s1 = "3[a]2[bc]";
        String s2 = "3[a2[c]]";
        String s3 = "2[abc]3[cd]ef";
        String s4 = "abc3[cd]xyz";
        String s5 = "abccdxyz";
        String s6 = "10[leetcode]";
        System.out.println(decodeString2(s1));
        System.out.println(decodeString2(s2));
        System.out.println(decodeString2(s3));
        System.out.println(decodeString2(s4));
        System.out.println(decodeString2(s5));
        System.out.println(decodeString2(s6));
    }

    private static String decodeString1(String s) {
        char[] chars = s.toCharArray();
        Deque<StringBuilder> stack = new LinkedList<>();
        Deque<Integer> countStack = new LinkedList<>();
        StringBuilder res = new StringBuilder();
        int count = 0;
        for (char c : chars) {
            if (Character.isDigit(c)) {
                count = count * 10 + c - '0';
            } else if (c == '[') {
                countStack.offerLast(count);
                stack.offerLast(res);
                res = new StringBuilder();
                count = 0;
            } else if (c == ']') {
                int times = countStack.pollLast();
                StringBuilder temp = new StringBuilder();
                for (int i = 0; i < times; i++) {
                    temp.append(res);
                }
                res = new StringBuilder(stack.pollLast()).append(temp);
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

    private static String decodeString2(String s) {
        index = 0;
        return dfs(s);
    }

    private static String dfs(String s) {
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        int count = 0;
        while (index < chars.length) {
            char c = chars[index];
            if (Character.isDigit(c)) {
                count = count * 10 + c - '0';
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

    public static String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        StringBuilder digit = new StringBuilder();
        int i = 0;
        while (i < chars.length) {
            if (!Character.isDigit(chars[i])) {
                sb.append(chars[i]);
                i++;
                continue;
            }
            while (Character.isDigit(chars[i])) {
                digit.append(chars[i]);
                i++;
            }
            int times = Integer.valueOf(digit.toString());
            digit = new StringBuilder();
            i++;                //指向[的下一个字符
            int balance = 1;    //括号匹配，代表还需要多少个右括号才能匹配
            StringBuilder temp = new StringBuilder();
            while (i < chars.length && balance != 0) {
                char c = chars[i];
                if (c == '[') {
                    balance++;
                }
                if (c == ']') {
                    balance--;
                }
                if (balance != 0) {
                    temp.append(chars[i]);
                }
                i++;
            }
            String subString = decodeString(new String(temp));
            for (int j = 0; j < times; j++) {
                sb.append(subString);
            }
        }
        return sb.toString();
    }

}

/*
* 给定一个经过编码的字符串，返回它解码后的字符串。

编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。

你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。

此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。

 

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
