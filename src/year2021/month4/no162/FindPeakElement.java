package year2021.month4.no162;

import java.util.stream.IntStream;

public class FindPeakElement {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 1};
        int[] nums2 = {1, 2, 1, 3, 5, 6, 4};
        int[] nums3 = {1};
        int[] nums4 = {1, 2};
        int[] nums5 = {2, 1};
        System.out.println(findPeakElement1(nums1));
        System.out.println(findPeakElement1(nums2));
        System.out.println(findPeakElement1(nums3));
        System.out.println(findPeakElement1(nums4));
        System.out.println(findPeakElement1(nums5));
    }

    private static int findPeakElement1(int[] nums) {
        // 由于题目保证了 nums[i] != nums[i + 1]，且 nums[-1] == nums[nums.length] = 负无穷
        // 则该题一定有解，特殊情况即为 单调递减 以及 单调递增，解分别是头尾元素
        // 故线性遍历时，只需比较 nums[i] 和 nums[i + 1]
        // 若 nums[i] > nums[i + 1]，则 i 为答案
        return IntStream.range(0, nums.length - 1).boxed()
                .filter(index -> nums[index] > nums[index + 1])
                .findAny()
                .orElse(nums.length - 1);
    }

    public static int findPeakElement(int[] nums) {
        int len = nums.length;
        if (len == 1 || nums[0] > nums[1]) {
            return 0;
        }
        if (nums[len - 1] > nums[len - 2]) {
            return len - 1;
        }
        return IntStream.range(1, len - 1).boxed()
                .filter(index -> nums[index] > Math.max(nums[index - 1], nums[index + 1]))
                .findAny()
                .orElse(-1);
    }

}

/*
* 峰值元素是指其值大于左右相邻值的元素。

给你一个输入数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。

你可以假设 nums[-1] = nums[n] = -∞ 。

 

示例 1：

输入：nums = [1,2,3,1]
输出：2
解释：3 是峰值元素，你的函数应该返回其索引 2。
示例 2：

输入：nums = [1,2,1,3,5,6,4]
输出：1 或 5 
解释：你的函数可以返回索引 1，其峰值元素为 2；
     或者返回索引 5， 其峰值元素为 6。
 

提示：

1 <= nums.length <= 1000
-231 <= nums[i] <= 231 - 1
对于所有有效的 i 都有 nums[i] != nums[i + 1]
 

进阶：你可以实现时间复杂度为 O(logN) 的解决方案吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/find-peak-element
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
