package twenty.november.no402;

import java.util.Deque;
import java.util.LinkedList;

public class RemoveKDigits {
    public static void main(String[] args) {
        String s1 = "1432219";
        int k1 = 3;
        String s2 = "10200";
        int k2 = 1;
        String s3 = "10";
        int k3 = 2;
        String s4 = "143234567885412321321219";
        int k4 = 20;
        String s5 = "1111219";
        int k5 = 3;
        System.out.println(removeKdigits(s1, k1));
        System.out.println(removeKdigits(s2, k2));
        System.out.println(removeKdigits(s3, k3));
        System.out.println(removeKdigits(s4, k4));
        System.out.println(removeKdigits(s5, k5));
    }

    private static String removeKdigits(String num, int k) {
//        单调栈，栈底至栈顶的元素单调不降
        Deque<Character> deque = new LinkedList<>();
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            while (!deque.isEmpty() && deque.peekLast() > c && k > 0) {
                deque.pollLast();
                k--;
            }
            deque.offerLast(c);
        }
//        栈已单调不降，若k>0，需出栈k次将多余元素排除
        for (int i = 0; i < k; i++) {
            deque.pollLast();
        }
//        去掉先导0
        boolean startingWithZero = true;
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            if (startingWithZero && deque.peekFirst() == '0') {
                deque.pollFirst();
                continue;
            }
            startingWithZero = false;
            sb.append(deque.pollFirst());
        }
        return sb.length() == 0 ? "0" : new String(sb);
    }
}

/*
* 给定一个以字符串表示的非负整数num，移除这个数中的 k 位数字，使得剩下的数字最小。

注意:

num 的长度小于 10002 且 > k。
num 不会包含任何前导零。
示例 1 :

输入: num = "1432219", k = 3
输出: "1219"
解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
示例 2 :

输入: num = "10200", k = 1
输出: "200"
解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
示例 3 :

输入: num = "10", k = 2
输出: "0"
解释: 从原数字移除所有的数字，剩余为空就是0。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/remove-k-digits
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

