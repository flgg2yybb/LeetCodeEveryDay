package year2025.month1.no560;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {

    // Prefix Map, time: O(n), space: O(n)
    public static int subarraySum(int[] nums, int k) {
        int ans = 0;
        Map<Integer, Integer> prefixSumMap = new HashMap<>(); // key: prefix sum, value: count
        prefixSumMap.put(0, 1); // init
        int sum = 0;
        for (int num : nums) {
            // [1, 2, 3, .....,   n] => sum
            // {targetPrefix} + {k} => sum
            //    prefix      suffix
            sum += num;
            int targetPrefix = sum - k;
            ans += prefixSumMap.getOrDefault(targetPrefix, 0);
            prefixSumMap.merge(sum, 1, Integer::sum);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 1, 1};
        int k1 = 2;
        int[] nums2 = {1, 2, 3};
        int k2 = 3;
        int[] nums3 = {1, 2, 1, 2, 1};
        int k3 = 3;
        System.out.println(subarraySum(nums1, k1));
        System.out.println(subarraySum(nums2, k2));
        System.out.println(subarraySum(nums3, k3));
    }
}

/*
给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。

子数组是数组中元素的连续非空序列。



示例 1：

输入：nums = [1,1,1], k = 2
输出：2
示例 2：

输入：nums = [1,2,3], k = 3
输出：2


提示：

1 <= nums.length <= 2 * 104
-1000 <= nums[i] <= 1000
-107 <= k <= 107
*/

