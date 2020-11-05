package november;

import java.util.ArrayList;
import java.util.List;

public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        int[] nums1 = new int[]{0, 0};
        int[] nums2 = new int[]{0, 0};
        System.out.println(solution1(nums1, nums2));
    }

    private static double solution1(int[] nums1, int[] nums2) {
        double median = 0;
        List<Integer> list = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] <= nums2[j]) {
                list.add(nums1[i]);
                i++;
            } else {
                list.add(nums2[j]);
                j++;
            }
        }
        for (int x = i; x < nums1.length ; x++) {
            list.add(nums1[x]);
        }
        for (int x = j; x < nums2.length ; x++) {
            list.add(nums2[x]);
        }
        median = list.size() % 2 == 0 ? (list.get(list.size() / 2) + list.get(list.size() / 2 - 1)) / 2.0 : list.get(list.size() / 2);
        return median;
    }
}

/*
* 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。

进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？

 

示例 1：

输入：nums1 = [1,3], nums2 = [2]
输出：2.00000
解释：合并数组 = [1,2,3] ，中位数 2
示例 2：

输入：nums1 = [1,2], nums2 = [3,4]
输出：2.50000
解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
示例 3：

输入：nums1 = [0,0], nums2 = [0,0]
输出：0.00000
示例 4：

输入：nums1 = [], nums2 = [1]
输出：1.00000
示例 5：

输入：nums1 = [2], nums2 = []
输出：2.00000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
