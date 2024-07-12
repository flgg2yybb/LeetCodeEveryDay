package year2021.month6.no179;

import java.util.Arrays;
import java.util.stream.Collectors;

public class LargestNumber {

    public static void main(String[] args) {
        int[] nums1 = {10, 2};
        int[] nums2 = {3, 30, 34, 5, 9};
        int[] nums3 = {1};
        int[] nums4 = {10};
        int[] nums5 = {1000, 0, 0};
        int[] nums6 = {0, 0, 0, 0};
        System.out.println(largestNumber(nums1));
        System.out.println(largestNumber(nums2));
        System.out.println(largestNumber(nums3));
        System.out.println(largestNumber(nums4));
        System.out.println(largestNumber(nums5));
        System.out.println(largestNumber(nums6));
    }

    public static String largestNumber(int[] nums) {
        /*
         * 对于没有相同前缀的字符串，比较字典序即可
         * 对于有相同前缀的字符串，如下
         * {4, 42} => 442 > 424，此时 4 在前
         * {4, 45} => 445 < 454，此时 4 在后
         * 因此需要将待比较的字符串拼接起来进行比较
         * time is O(nlogn), space is O(logn)
         * */
        String ans = Arrays.stream(nums).boxed().map(String::valueOf)
                // 大的在前面，因此使用 (s2 + s1).compareTo(s1 + s2)
                .sorted((s1, s2) -> (s2 + s1).compareTo(s1 + s2))
                .collect(Collectors.joining());
        return ans.startsWith("0") ? "0" : ans;
    }

}

/*
* 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。

注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。

 

示例 1：

输入：nums = [10,2]
输出："210"
示例 2：

输入：nums = [3,30,34,5,9]
输出："9534330"
示例 3：

输入：nums = [1]
输出："1"
示例 4：

输入：nums = [10]
输出："10"
 

提示：

1 <= nums.length <= 100
0 <= nums[i] <= 109

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/largest-number
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
