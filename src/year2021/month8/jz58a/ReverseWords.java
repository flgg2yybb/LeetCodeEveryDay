package year2021.month8.jz58a;

public class ReverseWords {

    public static void main(String[] args) {
        String s1 = "the sky is blue";
        String s2 = "  hello world!  ";
        String s3 = "a good   example";
        System.out.printf("[%s]%n", reverseWords(s1));
        System.out.printf("[%s]%n", reverseWords(s2));
        System.out.printf("[%s]%n", reverseWords(s3));
    }

    public static String reverseWords(String s) {
        s = s.trim();
        StringBuilder ans = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                if (sb.length() > 0) {
                    pushToAns(ans, sb);
                    sb = new StringBuilder();
                }
                continue;
            }
            sb.append(c);
        }
        pushToAns(ans, sb);
        return ans.toString();
    }

    private static void pushToAns(StringBuilder ans, StringBuilder sb) {
        if (ans.length() != 0) {
            ans.insert(0, " ");
        }
        ans.insert(0, sb.toString());
    }

}

class Auto {

}

/*
* 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。

 

示例 1：

输入: "the sky is blue"
输出: "blue is sky the"
示例 2：

输入: "  hello world!  "
输出: "world! hello"
解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
示例 3：

输入: "a good   example"
输出: "example good a"
解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 

说明：

无空格字符构成一个单词。
输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
注意：本题与主站 151 题相同：https://leetcode-cn.com/problems/reverse-words-in-a-string/

注意：此题对比原题有改动

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/fan-zhuan-dan-ci-shun-xu-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
