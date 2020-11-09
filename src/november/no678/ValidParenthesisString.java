package november.no678;

public class ValidParenthesisString {

    public static void main(String[] args) {
        String s1 = "()";
        String s2 = "(*)";
        String s3 = "(*))";
        String s4 = "((*)";
        String s5 = "()***)";
        String s6 = "()***))))";
        System.out.println(checkValidString(s1));
        System.out.println(checkValidString(s2));
        System.out.println(checkValidString(s3));
        System.out.println(checkValidString(s4));
        System.out.println(checkValidString(s5));
        System.out.println(checkValidString(s6));
    }

    private static boolean checkValidString(String s) {
        int min = 0;
        int max = 0;
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            switch (c) {
                case '(':
                    min++;
                    max++;
                    break;
                case ')':
                    if (min > 0) {
                        min--;
                    }
                    max--;
                    break;
                case '*':
                    if (min > 0) {
                        min--;
                    }
                    max++;
                    break;
                default:
                    return false;
            }
            if (max < 0) {
                return false;
            }
        }
        return min == 0;
    }
}

/*
* 给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：

任何左括号 ( 必须有相应的右括号 )。
任何右括号 ) 必须有相应的左括号 ( 。
左括号 ( 必须在对应的右括号之前 )。
* 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
一个空字符串也被视为有效字符串。
示例 1:

输入: "()"
输出: True
示例 2:

输入: "(*)"
输出: True
示例 3:

输入: "(*))"
输出: True
注意:

字符串大小将在 [1，100] 范围内。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/valid-parenthesis-string
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
