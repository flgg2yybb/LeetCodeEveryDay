package year2020.month12.no22;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    public static void main(String[] args) {
        int n1 = 3;
        int n2 = 0;
        int n3 = 5;
        List<String> resultList = bruteForceEnhancedEdition(n1);
        resultList.forEach(System.out::println);
    }

    private static List<String> bruteForceEnhancedEdition(int n) {
//        brute force enhanced edition, use dfs and pruning
        List<String> validParentheses = new ArrayList<>();
        generateValid(new char[2 * n], 0, 0, validParentheses);
        return validParentheses;
    }

    private static void generateValid(char[] chars, int index, int balance, List<String> validParentheses) {
        if (index == chars.length) {
            validParentheses.add(new String(chars));
        } else {
            if (balance < chars.length - index) {
                chars[index] = '(';
                generateValid(chars, index + 1, balance + 1, validParentheses);
            }
            if (balance > 0) {
                chars[index] = ')';
                generateValid(chars, index + 1, balance - 1, validParentheses);
            }
        }
    }

    public static List<String> bruteForce(int n) {
//        brute force, use dfs, time is O(n^2n), space is O(n)
        List<String> validParentheses = new ArrayList<>();
        generate(new char[2 * n], 0, validParentheses);
        return validParentheses;
    }

    private static void generate(char[] parentheses, int index, List<String> validParentheses) {
        if (index == parentheses.length) {
            if (valid(parentheses)) {
                validParentheses.add(new String(parentheses));
            }
        } else {
            parentheses[index] = '(';
            generate(parentheses, index + 1, validParentheses);
            parentheses[index] = ')';
            generate(parentheses, index + 1, validParentheses);
        }
    }

    private static boolean valid(char[] parentheses) {
        int balance = 0;
        for (int i = 0; i < parentheses.length; i++) {
            if (parentheses[i] == '(') {
                balance++;
            } else if (parentheses[i] == ')') {
                balance--;
            }
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }
}

/*
* 数字 n代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。



示例：

输入：n = 3
输出：[
       "((()))",
       "(()())",
       "(())()",
       "()(())",
       "()()()"
     ]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/generate-parentheses
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
