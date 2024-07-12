package year2021.month7.jz45;

import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MinNumber {

    public static void main(String[] args) {
        int[] nums1 = {10, 2};
        int[] nums2 = {3, 30, 34, 5, 9};
        System.out.println(minNumber1(nums1));
        System.out.println(minNumber1(nums2));
    }

    private static String minNumber1(int[] nums) {
        return IntStream.of(nums)
                .boxed()
                .map(String::valueOf)
                .sorted((s1, s2) -> (s1 + s2).compareTo(s2 + s1))
                .collect(Collectors.joining());
    }

    public static String minNumber(int[] nums) {
        PriorityQueue<String> numStrMinHeap = new PriorityQueue<>((s1, s2) -> {
            if (s1.length() == s2.length()) {
                return s1.compareTo(s2);
            }
            String str1 = s1 + s2;
            String str2 = s2 + s1;
            return str1.compareTo(str2);
        });
        IntStream.of(nums).boxed().forEach(num -> numStrMinHeap.add(String.valueOf(num)));
        StringBuilder ans = new StringBuilder();
        while (!numStrMinHeap.isEmpty()) {
            ans.append(numStrMinHeap.poll());
        }
        return ans.toString();
    }

}

/*
* 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。

    abced
    abc
    * abc ed abc
    * abc abc ed

示例 1:

输入: [10,2]
输出: "102"
示例 2:

输入: [3,30,34,5,9]
输出: "3033459"
 

提示:

0 < nums.length <= 100
说明:

输出结果可能非常大，所以你需要返回一个字符串而不是整数
拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

