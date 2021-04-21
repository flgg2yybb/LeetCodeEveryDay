package year2021.month4.no22;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

    public static void main(String[] args) {
        int n1 = 3;
        int n2 = 1;
        System.out.println(generateParenthesis(n1));
        System.out.println(generateParenthesis(n2));
    }

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, n, n, new StringBuilder());
        return result;
    }

    private static void backtrack(List<String> result, int remainingLeftBracket, int remainingRightBracket, StringBuilder sb) {
        if (remainingLeftBracket == 0 && remainingRightBracket == 0) {
            result.add(sb.toString());
            return;
        }
        if (remainingLeftBracket > 0) {
            sb.append("(");
            backtrack(result, remainingLeftBracket - 1, remainingRightBracket, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (remainingRightBracket > 0 && remainingRightBracket > remainingLeftBracket) {
            sb.append(")");
            backtrack(result, remainingLeftBracket, remainingRightBracket - 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

}

/*
* 数字 n  代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。

  

示例 1：

输入：n = 3
输出：["((()))","(()())","(())()","()(())","()()()"]
示例 2：

输入：n = 1
输出：["()"]
  

提示：

1 <= n <= 8

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/generate-parentheses
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
