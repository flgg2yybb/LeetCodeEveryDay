package year2021.month10.no560;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {

    public static void main(String[] args) {
        int[] nums1 = {1, 1, 1};
        int k1 = 2;
        int[] nums2 = {1, 2, 3};
        int k2 = 3;
        System.out.println(subarraySum(nums1, k1));
        System.out.println(subarraySum(nums2, k2));
    }

    public static int subarraySum(int[] nums, int k) {
        int count = 0;
        int currSum = 0;
        Map<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0, 1);
        for (int num : nums) {
            currSum += num;
            count += prefixSum.getOrDefault(currSum - k, 0);
            prefixSum.merge(currSum, 1, Integer::sum);
        }
        return count;
    }

}

/*
* 给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。

 

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

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/subarray-sum-equals-k
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
