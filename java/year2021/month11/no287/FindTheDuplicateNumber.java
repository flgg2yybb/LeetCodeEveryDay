package year2021.month11.no287;

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
        /*
         * Fast Slow Pointers
         * 数字都在[1,n]中，且仅有一个元素重复
         * 建立索引 <->元素的映射，i <-> i+1
         * 若无重复元素，则该路径无环，因为每个索引 i 只会被唯一的入口 i+1 所访问
         * 若存在重复元素，则入环口即为重复元素，说明该索引 i 存在两个以上的入口 i+1
         * */
        int slow = 0;
        int fast = 0;
        do {
            fast = nums[nums[fast]];
            slow = nums[slow];
        } while (fast != slow);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public static int findDuplicate(int[] nums) {
        // Hash in-place (原地哈希) 【需改动数组】
        for (int i = 0; i < nums.length; i++) {
            while (nums[nums[i] - 1] != nums[i]) {
                int a = nums[i];
                int b = nums[nums[i] - 1];
                nums[nums[i] - 1] = a;
                nums[i] = b;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 != nums[i]) {
                return nums[i];
            }
        }
        return -1;
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
