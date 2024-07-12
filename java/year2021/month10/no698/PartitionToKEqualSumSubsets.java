package year2021.month10.no698;

import java.util.Arrays;

public class PartitionToKEqualSumSubsets {
    public static void main(String[] args) {
        int[] nums1 = {4, 3, 2, 3, 5, 2, 1};
        int k1 = 4;
        int[] nums2 = {4, 3, 2, 3, 5, 3, 0};
        int k2 = 4;
        System.out.println(canPartitionKSubsets(nums1, k1));
        System.out.println(canPartitionKSubsets(nums2, k2));
    }

    public static boolean canPartitionKSubsets(int[] nums, int k) {
        int maxElement = Integer.MIN_VALUE;
        int sum = 0;
        for (int num : nums) {
            maxElement = Math.max(maxElement, num);
            sum += num;
        }
        // 无法整除 k，必无法切分
        if (sum % k != 0) {
            return false;
        }
        int target = sum / k;
        // 存在一个元素大于划分的和，无法切分
        if (maxElement > target) {
            return false;
        }
        // 逆序排序，大的元素先放入桶内，剪枝
        Arrays.sort(nums);
        for (int i = 0; i < nums.length / 2; i++) {
            int j = nums.length - i - 1;
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        boolean[] visited = new boolean[nums.length];
        return backtrack(nums, visited, k, 0, target);
    }

    private static boolean backtrack(int[] nums, boolean[] visited, int k, int cur, int target) {
        // 填完一个桶，再填下一个桶，直到填完 k 个桶
        if (k == 0) {
            return true;
        }
        if (cur == target) {
            return backtrack(nums, visited, k - 1, 0, target);
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] || (cur + nums[i] > target)) {
                continue;
            }
            visited[i] = true;
            if (backtrack(nums, visited, k, cur + nums[i], target)) {
                return true;
            }
            visited[i] = false;
        }
        return false;
    }

}

/*
 * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。

示例 1：

输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
输出： True
说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
 

提示：

1 <= k <= len(nums) <= 16
0 < nums[i] < 10000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/partition-to-k-equal-sum-subsets
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
