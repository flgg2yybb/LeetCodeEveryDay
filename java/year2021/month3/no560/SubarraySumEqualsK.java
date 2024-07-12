package year2021.month3.no560;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {
    public static void main(String[] args) {
        int[] nums1 = {1, 1, 1};
        int k1 = 2;
        int k2 = 1;
        int k3 = 3;
        int[] nums2 = {1, 2, 1, 2, 1};
        System.out.println(subarraySum(nums1, k1));
        System.out.println(subarraySum(nums1, k2));
        System.out.println(subarraySum(nums1, k3));
        System.out.println(subarraySum(nums2, k3));
    }

    public static int subarraySum(int[] nums, int k) {
        /*前缀和, time is O(n), space is O(n)*/
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);  //加入前缀和为0的前缀，处理当前前缀和恰好等于k的情况
        int count = 0;
        int sum = 0;    //当前元素前缀和
        for (int num : nums) {
            sum += num;
            count += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}

/*
* 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。

示例 1 :

输入:nums = [1,1,1], k = 2
输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
说明 :

数组的长度为 [1, 20,000]。
数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/subarray-sum-equals-k
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
