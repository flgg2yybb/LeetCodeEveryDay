package year2021.month2.no287;

import java.util.HashSet;
import java.util.Set;

public class FindTheDuplicateNumber {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 3, 4, 2, 2};
        int[] nums2 = new int[]{3, 1, 3, 4, 2};
        int[] nums3 = new int[]{1, 1};
        int[] nums4 = new int[]{1, 1, 2};
        System.out.println(findDuplicate1(nums1));
        System.out.println(findDuplicate1(nums2));
        System.out.println(findDuplicate1(nums3));
        System.out.println(findDuplicate1(nums4));
    }

    private static int findDuplicate1(int[] nums) {
        /*快慢指针，Floyd判圈算法
         * 由于nums数组长度为 n+1，元素均在[1,n]范围内，其中只有一个元素出现两次及以上
         * 则可将nums的值视为指针，指向下一个节点，因其中有元素重复出现，则必然在形成的
         * 图中，至少有两个指针指向同一个入口，该入口即为重复元素的值
         * */
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

    public static int findDuplicate(int[] nums) {
//        time is O(n), space is O(n)
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return num;
            }
            set.add(num);
        }
        return -1;
    }
}

/*
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。

假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。

 

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

2 <= n <= 3 * 104
nums.length == n + 1
1 <= nums[i] <= n
nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
 

进阶：

如何证明 nums 中至少存在一个重复的数字?
你可以在不修改数组 nums 的情况下解决这个问题吗？
你可以只用常量级 O(1) 的额外空间解决这个问题吗？
你可以设计一个时间复杂度小于 O(n2) 的解决方案吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/find-the-duplicate-number
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
