package year2021.month1.no17;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsOfPhoneNumber {

    public static void main(String[] args) {
        String digits1 = "23";
        List<String> strings = letterCombinations1(digits1);
        System.out.println(strings);
    }

    private static List<String> letterCombinations1(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        char[][] letters = getLetters();
        dfs(digits, result, letters, new StringBuilder(), 0);
        return result;
    }

    private static void dfs(String digits, List<String> result, char[][] letters, StringBuilder sb, int pos) {
        if (pos == digits.length()) {
            result.add(new String(sb));
            return;
        }
        int index = digits.charAt(pos) - '0';
        for (int i = 0; i < letters[index].length; i++) {
            dfs(digits, result, letters, new StringBuilder(sb).append(letters[index][i]), pos + 1);
        }
    }

    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        char[][] letters = getLetters();
        backtrack(digits, result, letters, new StringBuilder(), 0);
        return result;
    }

    private static void backtrack(String digits, List<String> result, char[][] letters, StringBuilder sb, int pos) {
        if (pos == digits.length()) {
            result.add(new String(sb));
            return;
        }
        int index = digits.charAt(pos) - '0';
        for (int i = 0; i < letters[index].length; i++) {
            sb.append(letters[index][i]);
            backtrack(digits, result, letters, sb, pos + 1);
            sb.deleteCharAt(pos);
        }
    }

    private static char[][] getLetters() {
        return new char[][]{
                {'0'},
                {'0'},
                {'a', 'b', 'c'},
                {'d', 'e', 'f'},
                {'g', 'h', 'i'},
                {'j', 'k', 'l'},
                {'m', 'n', 'o'},
                {'p', 'q', 'r', 's'},
                {'t', 'u', 'v'},
                {'w', 'x', 'y', 'z'}
        };
    }
}

/*
* 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。



示例:

输入："23"
输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
说明:
尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
