package year2021.month5.no41;

public class FirstMissingPositive {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 0};
        int[] nums2 = {3, 4, -1, 1};
        int[] nums3 = {7, 8, 9, 11, 12};
        int[] nums4 = {1, 1};
        System.out.println(firstMissingPositive(nums1));
        System.out.println(firstMissingPositive(nums2));
        System.out.println(firstMissingPositive(nums3));
        System.out.println(firstMissingPositive(nums4));
    }

    public static int firstMissingPositive(int[] nums) {
        /*
         * 根据题意，第一个缺失的正整数一定出现在区间【1,n+1】中（n为数组nums长度）
         * 则遍历nums，将属于【1,n】区间的元素放置在相应的位置上（交换）
         * 例如 1 放置在 0 位置上，i 放置在 i-1 位置上
         * 再次遍历，若当前元素不满足 nums[i] == i+1，则 i+1 为第一个缺失的正整数
         * */
        final int N = nums.length;
        for (int i = 0; i < N; i++) {
            // nums[nums[i] - 1] != nums[i] 为防止例如 [1,1] 数组在第二个元素的时候死循环
            while (nums[i] > 0 && nums[i] <= N && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < N; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

}

/*
* 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。

请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 

示例 1：

输入：nums = [1,2,0]
输出：3
示例 2：

输入：nums = [3,4,-1,1]
输出：2
示例 3：

输入：nums = [7,8,9,11,12]
输出：1
 

提示：

1 <= nums.length <= 5 * 105
-231 <= nums[i] <= 231 - 1

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/first-missing-positive
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
