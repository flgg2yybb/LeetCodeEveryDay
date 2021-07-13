package year2021.month7.jz20;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IsNumber {

    public static void main(String[] args) {
        String s1 = "0";
        String s2 = "e";
        String s3 = ".e";
        String s4 = "      .1   ";
        String s5 = "3.";
        String s6 = ".";
        String s7 = ".3";
        String s8 = ".e1";
        String s9 = "3. ";
        System.out.println(isNumber(s1));
        System.out.println(isNumber(s2));
        System.out.println(isNumber(s3));
        System.out.println(isNumber(s4));
        System.out.println(isNumber(s5));
        System.out.println(isNumber(s6));
        System.out.println(isNumber(s7));
        System.out.println(isNumber(s8));
        System.out.println(isNumber(s9));
    }

    public static boolean isNumber(String s) {
        if (s == null) {
            return false;
        }
        DFA dfa = new DFA();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            dfa.add(c);
        }
        return dfa.getResult();
    }

}

class DFA {

    /*                          ' '         +/-                 number              dot      e/E     others
     *   PRE_SPACE           PRE_SPACE       SIGN            NUMBER_BEFORE_DOT   START_DOT   FALSE    FALSE
     *   SIGN                    FALSE       FLASE           NUMBER_BEFORE_DOT   START_DOT   FALSE    FALSE
     *   NUMBER_BEFORE_DOT   POST_SPACE      FLASE           NUMBER_BEFORE_DOT       DOT       E      FALSE
     *   START_DOT               FALSE       FLASE           NUMBER_AFTER_DOT       FALSE    FALSE    FALSE
     *   DOT                 POST_SPACE       FLASE           NUMBER_AFTER_DOT       FALSE      E      FALSE
     *   NUMBER_AFTER_DOT    POST_SPACE      FLASE           NUMBER_AFTER_DOT       FALSE      E      FALSE
     *   E                       FALSE  SIGN_OF_NUMBER_OF_E     NUMBER_OF_E         FALSE    FALSE    FALSE
     *   SIGN_OF_NUMBER_OF_E     FALSE       FALSE              NUMBER_OF_E         FALSE    FALSE    FALSE
     *   NUMBER_OF_E         POST_SPACE      FALSE              NUMBER_OF_E         FALSE    FALSE    FALSE
     *   POST_SPACE          POST_SPACE      FALSE               FALSE              FALSE    FALSE    FALSE
     *
     *   SUCCESS_STATUS = [ NUMBER_BEFORE_DOT, DOT, NUMBER_AFTER_DOT, NUMBER_OF_E, POST_SPACE ]
     * */

    private static final String PRE_SPACE = "PRE_SPACE";
    private static final String SIGN = "SIGN";
    private static final String NUMBER_BEFORE_DOT = "NUMBER_BEFORE_DOT";
    private static final String START_DOT = "START_DOT";
    private static final String DOT = "DOT";
    private static final String NUMBER_AFTER_DOT = "NUMBER_AFTER_DOT";
    private static final String E = "E";
    private static final String SIGN_OF_NUMBER_OF_E = "SIGN_OF_NUMBER_OF_E";
    private static final String NUMBER_OF_E = "NUMBER_OF_E";
    private static final String POST_SPACE = "POST_SPACE";
    private static final String FALSE = "FALSE";
    private final Set<String> successStatus = new HashSet<>(Arrays.asList(NUMBER_BEFORE_DOT, DOT, NUMBER_AFTER_DOT, NUMBER_OF_E, POST_SPACE));
    private final Map<String, List<String>> statusMap = new HashMap() {{
        put(PRE_SPACE, Arrays.asList(PRE_SPACE, SIGN, NUMBER_BEFORE_DOT, START_DOT, FALSE, FALSE));
        put(SIGN, Arrays.asList(FALSE, FALSE, NUMBER_BEFORE_DOT, START_DOT, FALSE, FALSE));
        put(NUMBER_BEFORE_DOT, Arrays.asList(POST_SPACE, FALSE, NUMBER_BEFORE_DOT, DOT, E, FALSE));
        put(START_DOT, Arrays.asList(FALSE, FALSE, NUMBER_AFTER_DOT, FALSE, FALSE, FALSE));
        put(DOT, Arrays.asList(POST_SPACE, FALSE, NUMBER_AFTER_DOT, FALSE, E, FALSE));
        put(NUMBER_AFTER_DOT, Arrays.asList(POST_SPACE, FALSE, NUMBER_AFTER_DOT, FALSE, E, FALSE));
        put(E, Arrays.asList(FALSE, SIGN_OF_NUMBER_OF_E, NUMBER_OF_E, FALSE, FALSE, FALSE));
        put(SIGN_OF_NUMBER_OF_E, Arrays.asList(FALSE, FALSE, NUMBER_OF_E, FALSE, FALSE, FALSE));
        put(NUMBER_OF_E, Arrays.asList(POST_SPACE, FALSE, NUMBER_OF_E, FALSE, FALSE, FALSE));
        put(POST_SPACE, Arrays.asList(POST_SPACE, FALSE, FALSE, FALSE, FALSE, FALSE));
    }};
    private String status = PRE_SPACE;

    private int getCol(char c) {
        if (c == ' ') {
            return 0;
        }
        if (c == '+' || c == '-') {
            return 1;
        }
        if (Character.isDigit(c)) {
            return 2;
        }
        if (c == '.') {
            return 3;
        }
        if (c == 'e' || c == 'E') {
            return 4;
        }
        return 5;
    }

    public void add(char c) {
        if (!statusMap.containsKey(status)) {
            return;
        }
        status = statusMap.get(status).get(getCol(c));

    }

    public boolean getResult() {
        return successStatus.contains(status);
    }

}

/*
* 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。

数值（按顺序）可以分成以下几个部分：
1. 若干空格
2. 一个 小数 或者 整数
3. （可选）一个 'e' 或 'E' ，后面跟着一个 整数
4. 若干空格

小数（按顺序）可以分成以下几个部分：
1. （可选）一个符号字符（'+' 或 '-'）
2. 下述格式之一：
3. 至少一位数字，后面跟着一个点 '.'
4. 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
5. 一个点 '.' ，后面跟着至少一位数字


整数（按顺序）可以分成以下几个部分：
1. （可选）一个符号字符（'+' 或 '-'）
2. 至少一位数字
部分数值列举如下：

["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]
部分非数值列举如下：

["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]
 

示例 1：

输入：s = "0"
输出：true
示例 2：

输入：s = "e"
输出：false
示例 3：

输入：s = "."
输出：false
示例 4：

输入：s = "    .1  "
输出：true
 

提示：

1 <= s.length <= 20
s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，空格 ' ' 或者点 '.' 。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
