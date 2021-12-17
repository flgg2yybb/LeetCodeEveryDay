package year2021.month12.no287;

public class FindTheDuplicateNumber {

    public static void main(String[] args) {
        int[] nums1 = {1, 3, 4, 2, 2};
        int[] nums2 = {3, 1, 3, 4, 2};
        int[] nums3 = {1, 1};
        int[] nums4 = {1, 1, 2};
        System.out.println(findDuplicate(nums1));
        System.out.println(findDuplicate(nums2));
        System.out.println(findDuplicate(nums3));
        System.out.println(findDuplicate(nums4));
    }

    public static int findDuplicate(int[] nums) {
        // 由于数组长度为 n+1，则数组索引范围为：[0,n]
        // 数组中的元素范围均在 [1,n] 之间，
        // 建立元素与索引的映射关系：value -> index
        // 用指针从第一个索引位置开始访问，根据当前访问的元素值，
        // 确定下一个访问的索引位置，则即可将元素访问看似成链表访问
        // 则每一个元素所对应的索引位置，即使指针可以访问到的位置
        // 对于无法访问的索引位置不影响，由于有数字重复
        // 则某个索引一定存在多个元素与其对应，即可看成链表有环
        // 故本题即是找到入环口的索引位置即为答案
        int slow = 0;
        int fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}

/*
* 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。

假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。

你设计的解决方案必须不修改数组 nums 且只用常量级 O(1) 的额外空间。

 

示例 1：

输入：nums = [1,3,4,2,2]
输出：2
示例 2：

输入：nums = [3,1,3,4,2]
输出：3
示例 3：

输入：nums = [1,1]
输出：1
示例 4：

输入：nums = [1,1,2]
输出：1
 

提示：

1 <= n <= 105
nums.length == n + 1
1 <= nums[i] <= n
nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
 

进阶：

如何证明 nums 中至少存在一个重复的数字?
你可以设计一个线性级时间复杂度 O(n) 的解决方案吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/find-the-duplicate-number
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
