package year2021.month7.no4;

public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        int[] nums11 = {1, 3};
        int[] nums12 = {2};
        int[] nums21 = {1, 2};
        int[] nums22 = {3, 4};
        int[] nums31 = {0, 0};
        int[] nums32 = {0, 0};
        int[] nums41 = {};
        int[] nums42 = {1};
        int[] nums51 = {2};
        int[] nums52 = {};

        int[] nums61 = {1, 2};
        int[] nums62 = {3, 4};
        int[] nums71 = {3, 4};
        int[] nums72 = {1, 2};
        int[] nums81 = {1, 2, 3};
        int[] nums82 = {4, 4};
        int[] nums91 = {4, 4};
        int[] nums92 = {1, 2, 3};

        int[] nums101 = {3, 4, 5};
        int[] nums102 = {1, 2};

        System.out.println(findMedianSortedArrays1(nums11, nums12));
        System.out.println(findMedianSortedArrays1(nums21, nums22));
        System.out.println(findMedianSortedArrays1(nums31, nums32));
        System.out.println(findMedianSortedArrays1(nums41, nums42));
        System.out.println(findMedianSortedArrays1(nums51, nums52));
        System.out.println(findMedianSortedArrays1(nums61, nums62));
        System.out.println(findMedianSortedArrays1(nums71, nums72));
        System.out.println(findMedianSortedArrays1(nums81, nums82));
        System.out.println(findMedianSortedArrays1(nums91, nums92));
        System.out.println(findMedianSortedArrays1(nums101, nums102));
    }

    public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) {
            return findMedianSortedArrays1(nums2, nums1);
        }
        int total = m + n;
        int leftSize = (total + 1) >> 1;    // leftSize = rightSize ( + 1)
        int left = 0;
        int right = m;
        while (left < right) {
            int i = left + ((right - left) >> 1); // 向下取整, 故 i 不会取到 m
            int j = leftSize - i;   // 同时因为 m <= n，故 j 也不会取到 0
            if (nums2[j - 1] <= nums1[i]) {
                right = i;
            } else {
                left = i + 1;
            }
        }
        int i = left;
        int j = leftSize - i;
        int num1Left = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
        int num1Right = i == m ? Integer.MAX_VALUE : nums1[i];
        int num2Left = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
        int num2Right = j == n ? Integer.MAX_VALUE : nums2[j];
        int leftMax = Math.max(num1Left, num2Left);
        int rightMin = Math.min(num1Right, num2Right);
        return ((m + n) & 1) == 1 ? leftMax : (leftMax + rightMin) / 2.0;
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int total = m + n;
        int leftSize = (total + 1) >> 1;    // leftSize = rightSize ( + 1)
        int left = 0;
        int right = m;
        while (left < right) {
            int i = left + ((right - left + 1) >> 1); // 向上取整, 故 i 不会取到 0
            int j = leftSize - i;   // 同时因为 m <= n，故 j 也不会取到 n
            if (nums1[i - 1] <= nums2[j]) {
                left = i;
            } else {
                right = i - 1;
            }
        }
        int i = left;
        int j = leftSize - i;
        int num1Left = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
        int num1Right = i == m ? Integer.MAX_VALUE : nums1[i];
        int num2Left = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
        int num2Right = j == n ? Integer.MAX_VALUE : nums2[j];
        int leftMax = Math.max(num1Left, num2Left);
        int rightMin = Math.min(num1Right, num2Right);
        return ((m + n) & 1) == 1 ? leftMax : (leftMax + rightMin) / 2.0;
    }

}

/*
* 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。

 

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
 

提示：

nums1.length == m
nums2.length == n
0 <= m <= 1000
0 <= n <= 1000
1 <= m + n <= 2000
-106 <= nums1[i], nums2[i] <= 106
 

进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
