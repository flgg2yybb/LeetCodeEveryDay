package year2020.month11.no8;

import java.util.HashMap;
import java.util.Map;

public class StringToInteger {
    public static void main(String[] args) {
        String s1 = "42";   //42
        String s2 = "    -42";  //-42
        String s3 = "4193 with words";  //4193
        String s4 = "words with 987";   //0
        String s5 = "-91283472332"; //-2147483648
        String s6 = "+-12";     //0
        System.out.println(myAtoi2(s1));
        System.out.println(myAtoi2(s2));
        System.out.println(myAtoi2(s3));
        System.out.println(myAtoi2(s4));
        System.out.println(myAtoi2(s5));
        System.out.println(myAtoi2(s6));
    }

    private static int myAtoi2(String s) {
//        DFA(Deterministic Finite Automation)
        Automation automation = new Automation();
        for (int i = 0; i < s.length(); i++) {
            automation.add(s.charAt(i));
            if (Automation.END.equals(automation.getStatus())) {
                break;
            }
        }
        return automation.getResult();
    }

    private static int myAtoi(String s) {
        int start = -1;
        int end = -1;
        boolean findEnd = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
//            to handle the string starting with alphabet
            if (start == -1 && !isLegal(c) && c != ' ') {
                return 0;
            }
//            to find the first legal char index
            if (start == -1 && isLegal(c)) {
                start = i;
                if (isNumber(c)) {
                    end = i;
                }
                findEnd = true;
                continue;
            }
            if (findEnd) {
                if (isNumber(c)) {
                    end = i;
                } else {
                    break;
                }
            }
        }
        if (start == -1 || end == -1) {
            return 0;
        }
        String numString = s.substring(start, end + 1);
        try {
            return Integer.parseInt(numString);
        } catch (NumberFormatException e) {
            return numString.charAt(0) == '-' ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
    }

    private static boolean isLegal(char c) {
        return isNumber(c) || isSign(c);
    }

    private static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    private static boolean isSign(char c) {
        return c == '-' || c == '+';
    }
}

class Automation {
    /*
     *   Status Transition Array:
     *               ' '         sign('+' / '-')       number      other
     *   start       start           signed          in_number      end
     *   signed      end             end             in_number      end
     *   in_number   end             end             in_number      end
     *   end         end             end                end         end
     * */
    final static String START = "start";
    final static String SIGNED = "signed";
    final static String IN_NUMBER = "in_number";
    final static String END = "end";
    private final Map<String, String[]> statusMap = new HashMap() {{
        put(START, new String[]{START, SIGNED, IN_NUMBER, END});
        put(SIGNED, new String[]{END, END, IN_NUMBER, END});
        put(IN_NUMBER, new String[]{END, END, IN_NUMBER, END});
        put(END, new String[]{END, END, END, END});
    }};
    //    ans is absolute value, not negative
    private long ans = 0;
    private int sign = 1;
    private String status = START;

    public void add(char currChar) {
        status = statusMap.get(status)[getCol(currChar)];
        switch (status) {
            case SIGNED:
                sign = currChar == '-' ? -1 : 1;
                break;
            case IN_NUMBER:
                ans = ans * 10 + currChar - '0';
                if (sign == 1 && ans >= Integer.MAX_VALUE) {
                    ans = Integer.MAX_VALUE;
                } else if (sign == -1 && ans >= -1L * Integer.MIN_VALUE) {
                    ans = -1L * Integer.MIN_VALUE;
                }
                break;
            default:
                break;
        }
    }

    private int getCol(char currChar) {
        if (currChar == ' ') {
            return 0;
        }
        if (currChar == '-' || currChar == '+') {
            return 1;
        }
        if (Character.isDigit(currChar)) {
            return 2;
        }
        return 3;
    }

    public int getResult() {
        /*
         *       Integer range : [-2^32, 2^32 - 1]
         * */
        return ans == -1L * Integer.MIN_VALUE ? Integer.MIN_VALUE : (int) (ans * sign);
    }

    public String getStatus() {
        return status;
    }
}

/*
* 请你来实现一个 atoi 函数，使其能将字符串转换成整数。

首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：

如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。

在任何情况下，若函数不能进行有效的转换时，请返回 0 。

提示：

本题中的空白字符只包括空格字符 ' ' 。
假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 

示例 1:

输入: "42"
输出: 42
示例 2:

输入: "   -42"
输出: -42
解释: 第一个非空白字符为 '-', 它是一个负号。
     我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
示例 3:

输入: "4193 with words"
输出: 4193
解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
示例 4:

输入: "words and 987"
输出: 0
解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
     因此无法执行有效的转换。
示例 5:

输入: "-91283472332"
输出: -2147483648
解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
     因此返回 INT_MIN (−231) 。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/string-to-integer-atoi
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */