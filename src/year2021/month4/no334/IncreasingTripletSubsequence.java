package year2021.month4.no334;

public class IncreasingTripletSubsequence {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 4, 5};      //true
        int[] nums2 = {5, 4, 3, 2, 1};      //false
        int[] nums3 = {2, 1, 5, 0, 4, 6};   //true, 0,4,6
        int[] nums4 = {2, 5, 4, 3, 6};      //true, 2,4,6
        int[] nums5 = {5, 1, 6};            //false
//        System.out.println(increasingTriplet(nums1));
//        System.out.println(increasingTriplet(nums2));
//        System.out.println(increasingTriplet(nums3));
//        System.out.println(increasingTriplet(nums4));
        System.out.println(increasingTriplet(nums5));
    }

    public static boolean increasingTriplet(int[] nums) {
        // 用 min 数组保存从 0 到 i 的最小值，用 max 数组保存从 i 到 nums.length - 1 的最大值
        // 若存在一个数 nums[i], 其 min[i] < nums[i] < max[i]，则存在符合题意的三元组
        int length = nums.length;
        if (length < 2) {
            return false;
        }
        int[] min = new int[length];
        min[0] = nums[0];
        int[] max = new int[length];
        max[length - 1] = nums[length - 1];
        for (int i = 1; i < length; i++) {
            min[i] = Math.min(min[i - 1], nums[i]);
        }
        for (int i = length - 2; i >= 0; i--) {
            max[i] = Math.max(max[i + 1], nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            if (min[i] < nums[i] && nums[i] < max[i]) {
                return true;
            }
        }
        return false;
    }

}

/*
* 给你一个整数数组  nums ，判断这个数组中是否存在长度为 3 的递增子序列。

如果存在这样的三元组下标 (i, j, k)  且满足 i < j < k ，使得  nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。

  

示例 1：

输入：nums = [1,2,3,4,5]
输出：true
解释：任何 i < j < k 的三元组都满足题意
示例 2：

输入：nums = [5,4,3,2,1]
输出：false
解释：不存在满足题意的三元组
示例 3：

输入：nums = [2,1,5,0,4,6]
输出：true
解释：三元组 (3, 4, 5) 满足题意，因为 nums[3] == 0 < nums[4] == 4 < nums[5] == 6
  

提示：

1 <= nums.length <= 105
-231 <= nums[i] <= 231 - 1
  

进阶：你能实现时间复杂度为 O(n) ，空间复杂度为 O(1) 的解决方案吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/increasing-triplet-subsequence
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
