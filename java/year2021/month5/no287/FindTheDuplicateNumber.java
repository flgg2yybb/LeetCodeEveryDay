package year2021.month5.no287;

public class FindTheDuplicateNumber {
    public static void main(String[] args) {
        int[] nums1 = {1, 3, 4, 2, 2};
        int[] nums2 = {3, 1, 3, 4, 2};
        int[] nums3 = {1, 1};
        int[] nums4 = {1, 1, 2};
        System.out.println(findDuplicate1(nums1));
        System.out.println(findDuplicate1(nums2));
        System.out.println(findDuplicate1(nums3));
        System.out.println(findDuplicate1(nums4));
    }

    private static int findDuplicate1(int[] nums) {
        /*快慢指针 - Floyd 判圈法
         * 前提：nums 中数字范围是 [1,n]
         * 将数组下标 i 和数 nums[i] 建立一个关系
         * nums[i] 表示下一个要访问元素的下标，则有如下三种情况
         * 1. 对于无重复元素数组[1, 3, 4, 2]，从起点 0 出发，有
         *      0 -> 1
         *      1 -> 3
         *      3 -> 2
         *      2 -> 4
         *      4 -> null
         * 2. 对于有重复元素数组[1, 3, 4, 2, 2]，从起点 0 出发，有
         *      0 -> 1
         *      1 -> 3
         *      3 -> 2
         *      2 -> 4
         *      4 -> 2
         *      2 -> 4
         *       ...
         *      无限循环
         * 3. 对于下一个访问的元素位置恰好是当前位置，例如 [4, 1, 2, 3, 2]
         *     若访问到下标 1 时，会进入死循环，但若 1 不为重复元素时
         *     从下标 0 出发根本就进入不到 1 这个死循环上，故不影响
         * */
        int fast = 0;
        int slow = 0;
        do {
            fast = nums[nums[fast]];
            slow = nums[slow];
        } while (fast != slow);
        slow = 0;
        while (fast != slow) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }

    public static int findDuplicate(int[] nums) {
        // 修改数组法，将 1 到 n 的元素分别放置在 nums 为 1 到 n 的索引上
        // 则最终 nums[0] 即为重复元素
        for (int i = 0; i < nums.length; i++) {
            while (nums[nums[i]] != nums[i]) {
                int temp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = temp;
            }
        }
        return nums[0];
    }
}

/*
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。

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

2 <= n <= 105
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
