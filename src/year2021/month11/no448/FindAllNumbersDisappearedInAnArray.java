package year2021.month11.no448;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllNumbersDisappearedInAnArray {

    public static void main(String[] args) {
        int[] nums1 = {4, 3, 2, 7, 8, 2, 3, 1};
        int[] nums2 = {1, 1};
        int[] nums3 = {2, 2};
        System.out.println(findDisappearedNumbers1(nums1));
        System.out.println(Arrays.toString(nums1));
        System.out.println(findDisappearedNumbers1(nums2));
        System.out.println(Arrays.toString(nums2));
        System.out.println(findDisappearedNumbers1(nums3));
        System.out.println(Arrays.toString(nums3));
    }

    private static List<Integer> findDisappearedNumbers1(int[] nums) {
        // 将元素值 v 映射至索引 v-1 上，若出现过某元素 v，则在其索引值上 + nums.length
        // 则最后索引值大于 nums.length 的即为出现过的元素，故可找出未出现的元素
        // 同时最后可将所有大于 nums.length 的元素值 % nums.length，即可恢复数组
        int len = nums.length;
        for (int num : nums) {
            nums[(num - 1) % len] += len;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= len) {
                ans.add(i + 1);
            }
        }
        for (int i = 0; i < nums.length; i++) { // 恢复数组
            nums[i] %= len;
            if (nums[i] == 0) {
                nums[i] = len;
            }
        }
        return ans;
    }

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        // 原地哈希
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            while (nums[nums[i] - 1] != nums[i]) {  // nums[i] 所属的位置 nums[i]-1 上是否已经放置了 nums[i]，若无则交换
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                ans.add(i + 1);
            }
        }
        return ans;
    }

}

/*
* 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。

 

示例 1：

输入：nums = [4,3,2,7,8,2,3,1]
输出：[5,6]
示例 2：

输入：nums = [1,1]
输出：[2]
 

提示：

n == nums.length
1 <= n <= 105
1 <= nums[i] <= n
进阶：你能在不使用额外空间且时间复杂度为 O(n) 的情况下解决这个问题吗? 你可以假定返回的数组不算在额外空间内。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
