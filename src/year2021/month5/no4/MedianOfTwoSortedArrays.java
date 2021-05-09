package year2021.month5.no4;

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

        System.out.println(findMedianSortedArrays(nums11, nums12));
        System.out.println(findMedianSortedArrays(nums21, nums22));
        System.out.println(findMedianSortedArrays(nums31, nums32));
        System.out.println(findMedianSortedArrays(nums41, nums42));
        System.out.println(findMedianSortedArrays(nums51, nums52));
        System.out.println(findMedianSortedArrays(nums61, nums62));
        System.out.println(findMedianSortedArrays(nums71, nums72));
        System.out.println(findMedianSortedArrays(nums81, nums82));
        System.out.println(findMedianSortedArrays(nums91, nums92));
        System.out.println(findMedianSortedArrays(nums101, nums102));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        /*思路：二分查找
         * 首先根据二分查找找出可以分界线，如下
         *            left1  right1
         * arr1 :  A    B  |  C    D
         *                 -----------
         * arr2 :  a    b    c    d  |  e    f    g
         *                     left2  right2
         * 此分割线将 arr1 、 arr2 分成左右两部分，并
         *   若为奇数时 leftNum - 1 = rightNum
         *   若为偶数时 leftNum = rightNum
         * 且
         *   left1 <= right2 && left2 <= right1
         * 令分界线 s 为 right1 的下标索引，m = arr1.length, n = arr2.length，则
         * 左半部分的元素数量为： (m + n + 1) / 2，已处理奇数
         * left1 = arr1[s-1]
         * right1 = arr1[s]
         * left2 = arr2[((m + n + 1) / 2 ) - s - 1]
         * right2 = arr2[((m + n + 1) / 2 ) - s]
         *
         * **注意**：二分查找条件只需判断 left1 <= right2 即可
         * 即找到最大的 left1 使得 left1 <= right2
         * 因为当前 left1 为最大的能满足 left1 <= right2 的元素时
         * right1 为 left1 下一个元素，left2 为 right2的上一个元素
         * 若存在 left2 > right1，则表示 left1 的下一个元素小于 right2 的上一个元素
         * 与【当前 left1 为最大的能满足 left1 <= right2 的元素】矛盾
         * */
        int[] arr1 = nums1.length < nums2.length ? nums1 : nums2;
        int[] arr2 = nums1.length < nums2.length ? nums2 : nums1;
        int m = arr1.length;
        int n = arr2.length;
        int start = 0;
        int end = arr1.length;
        // 二分查找出分界线下标
        int left1, right1, left2, right2;
        int leftSize = (m + n + 1) / 2;
        while (start < end) {
            // arr1 分界线下标为 i，arr2 为 j
            int i = (start + end + 1) >> 1;
            int j = leftSize - i;
            // 由于 i 为向上取整，而循环体内 start != end，故一定有 i > 0，则 i - 1不越界
            // 由于 m <= n，则用划分分割线时，由 i 不越界可得 j 必不越界
            left1 = arr1[i - 1];
            right2 = arr2[j];
            if (left1 <= right2) {
                start = i;
            } else {
                end = i - 1;
            }
        }
        int i = start;
        int j = leftSize - i;
        left1 = i - 1 >= 0 ? arr1[i - 1] : Integer.MIN_VALUE;
        left2 = j - 1 >= 0 ? arr2[j - 1] : Integer.MIN_VALUE;
        if (((m + n) & 1) == 1) {
            //奇数
            return Math.max(left1, left2);
        }
        right1 = i < m ? arr1[i] : Integer.MAX_VALUE;
        right2 = j < n ? arr2[j] : Integer.MAX_VALUE;
        return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
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
