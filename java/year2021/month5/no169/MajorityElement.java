package year2021.month5.no169;

import java.util.Arrays;

public class MajorityElement {

    public static void main(String[] args) {
        int[] nums1 = {3, 2, 3};
        int[] nums2 = {2, 2, 1, 1, 1, 2, 2};
        System.out.println(majorityElement1(nums1));
        System.out.println(majorityElement1(nums2));
    }

    private static int majorityElement1(int[] nums) {
        // 分治，将数组对半分，则被拆分的数组中的两个众数必有一个是整个数组的众数
        return divideAndConquer(nums, 0, nums.length - 1);
    }

    private static int divideAndConquer(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        int mid = left + (right - left) / 2;
        int leftMajority = divideAndConquer(nums, left, mid);
        int rightMajority = divideAndConquer(nums, mid + 1, right);
        if (leftMajority == rightMajority) {
            return leftMajority;
        }
        int leftMajorityCount = count(nums, leftMajority, left, mid);
        int rightMajorityCount = count(nums, rightMajority, mid + 1, right);
        return leftMajorityCount > rightMajorityCount ? leftMajority : rightMajority;
    }

    private static int count(int[] nums, int num, int left, int right) {
        return (int) Arrays.stream(nums).filter(element -> element == num).count();
    }

    public static int majorityElement(int[] nums) {
        // 抛弃法：多数元素出现次数大于 ⌊ n/2 ⌋ ，则在数组中每次取出两个元素，
        // 若元素不同，则抛弃，循环进行下去，最后剩下的元素则为众数
        // 摩尔投票法：遍历 nums数组，初始化 candidate = nums[0], count = 1
        // 若当前 nums[i] 与 candidate 相等，则 count ++，否则 count --
        // 当 count = 0 时，更换 candidate = nums[i]，
        // 则遍历完成后 candidate 即为答案
        int candidate = Integer.MIN_VALUE;
        int count = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
                count = 1;
                continue;
            }
            count += candidate == num ? 1 : -1;
        }
        return candidate;
    }

}

/*
* 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。

你可以假设数组是非空的，并且给定的数组总是存在多数元素。

 

示例 1：

输入：[3,2,3]
输出：3
示例 2：

输入：[2,2,1,1,1,2,2]
输出：2
 

进阶：

尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/majority-element
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
