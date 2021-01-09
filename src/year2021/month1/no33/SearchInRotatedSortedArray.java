package year2021.month1.no33;

import java.util.stream.IntStream;

public class SearchInRotatedSortedArray {
    public static void main(String[] args) {
        int[] nums1 = new int[]{4, 5, 6, 7, 0, 1, 2};
        int target1 = 0;
        int[] nums2 = new int[]{4, 5, 6, 7, 0, 1, 2};
        int target2 = 3;
        int[] nums3 = new int[]{1};
        int target3 = 0;
        System.out.println(linearSearch(nums1, target1));
        System.out.println(linearSearch(nums2, target2));
        System.out.println(linearSearch(nums3, target3));
    }

    public static int linearSearch(int[] nums, int target) {
        return IntStream.range(0, nums.length).filter(i -> nums[i] == target).findAny().orElse(-1);
    }
}

/*
* 升序排列的整数数组 nums 在预先未知的某个点上进行了旋转（例如， [0,1,2,4,5,6,7] 经旋转后可能变为[4,5,6,7,0,1,2] ）。

请你在数组中搜索target ，如果数组中存在这个目标值，则返回它的索引，否则返回-1。



示例 1：

输入：nums = [4,5,6,7,0,1,2], target = 0
输出：4
示例2：

输入：nums = [4,5,6,7,0,1,2], target = 3
输出：-1
示例 3：

输入：nums = [1], target = 0
输出：-1


提示：

1 <= nums.length <= 5000
-10^4 <= nums[i] <= 10^4
nums 中的每个值都 独一无二
nums 肯定会在某个点上旋转
-10^4 <= target <= 10^4

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
