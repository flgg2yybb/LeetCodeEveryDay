package year2021.month4.no17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsOfPhoneNumber {

    public static void main(String[] args) {
        String digits1 = "23";
        String digits2 = "";
        String digits3 = "2";
        System.out.println(letterCombinations(digits1));
        System.out.println(letterCombinations(digits2));
        System.out.println(letterCombinations(digits3));
    }

    public static List<String> letterCombinations(String digits) {
        Map<Character, List<Character>> alphabet = getAlphabet();
        List<String> result = new ArrayList<>();
        char[] chars = digits.toCharArray();
        backtrack(result, alphabet, chars, 0, new StringBuilder());
        return result;
    }

    private static void backtrack(List<String> result, Map<Character, List<Character>> alphabet,
            char[] chars, int pos, StringBuilder sb) {
        if (pos == chars.length) {
            if (sb.length() > 0) {
                result.add(sb.toString());
            }
            return;
        }
        char currentChar = chars[pos];
        List<Character> characters = alphabet.get(currentChar);
        characters.forEach(c -> {
            sb.append(c);
            backtrack(result, alphabet, chars, pos + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        });
    }

    private static Map<Character, List<Character>> getAlphabet() {
        Map<Character, List<Character>> alphabet = new HashMap<>();
        alphabet.put('2', Arrays.asList('a', 'b', 'c'));
        alphabet.put('3', Arrays.asList('d', 'e', 'f'));
        alphabet.put('4', Arrays.asList('g', 'h', 'i'));
        alphabet.put('5', Arrays.asList('j', 'k', 'l'));
        alphabet.put('6', Arrays.asList('m', 'n', 'o'));
        alphabet.put('7', Arrays.asList('p', 'q', 'r', 's'));
        alphabet.put('8', Arrays.asList('t', 'u', 'v'));
        alphabet.put('9', Arrays.asList('w', 'x', 'y', 'z'));
        return alphabet;
    }

}

/*
* 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。



 

示例 1：

输入：digits = "23"
输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
示例 2：

输入：digits = ""
输出：[]
示例 3：

输入：digits = "2"
输出：["a","b","c"]
 

提示：

0 <= digits.length <= 4
digits[i] 是范围 ['2', '9'] 的一个数字。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
