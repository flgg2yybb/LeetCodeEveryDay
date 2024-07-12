package year2021.month8.no41;

public class FirstMissingPositive {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 0};
        int[] nums2 = {3, 4, -1, 1};
        int[] nums3 = {7, 8, 9, 11, 12};
        int[] nums4 = {1, 2, 3};
        System.out.println(firstMissingPositive(nums1));
        System.out.println(firstMissingPositive(nums2));
        System.out.println(firstMissingPositive(nums3));
        System.out.println(firstMissingPositive(nums4));
    }

    public static int firstMissingPositive(int[] nums) {
        /*
        * 原地 Hash，由于需要找出最小的正整数（从 1 开始）
        * 故可先遍历数组，将符合 1 <= num <= nums.length 的元素
        * 移动至相应的索引位置 num-1
        * 再遍历数组，即可找出未出现的最小正整数
        * */
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        int min = 1;
        while (min <= nums.length && min == nums[min - 1]) {
            min++;
        }
        return min;
    }

    private static void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
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
