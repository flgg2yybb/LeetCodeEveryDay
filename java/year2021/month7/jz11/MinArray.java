package year2021.month7.jz11;

public class MinArray {

    public static void main(String[] args) {
        int[] numbers1 = {3, 4, 5, 1, 2};
        int[] numbers2 = {2, 2, 2, 0, 1};
        int[] numbers3 = {1, 2, 3, 4, 5};
        int[] numbers4 = {2, 2, 2, 0, 1, 2, 2};
        System.out.println(minArray(numbers1));
        System.out.println(minArray(numbers2));
        System.out.println(minArray(numbers3));
        System.out.println(minArray(numbers4));
    }

    public static int minArray(int[] numbers) {
        /*
         * Binary Search, time is O(logn), space is O(1)
         * 这道题本质上就是找旋转数组的旋转点
         * 当 nums[mid] > nums[right] 时，说明 [mid, right] 区间不单调递增，
         * 则，旋转点必在 [mid+1, right] 区间
         * 当 nums[mid] < nums[right] 时，说明 [mid, right] 区间单调递增，
         * 则，旋转点必在 [left, mid] 之间（可能存在 mid 为旋转点的情况）
         * 而对于 nums[mid] == nums[right] 时，无法判断区间单调性
         * 即 mid 可能在旋转点，也可能在旋转点右边，此时
         * 应将 right--。因为 nums[mid] == nums[right]，且 mid != right，
         * 故将 right-- 不会影响结果。
         * 另：为什么不可以比较 nums[mid] 和 nums[left]
         * 当 nums[mid] > nums[left] 时，虽然可以保证 [left, mid] 区间单调，
         * 但无法区分旋转点到底属于哪个区间，见下例
         * 当 left = 0, right = 4, mid = 2 时
         * nums1 = {1, 2, 3, 4, 5} 旋转点 1 在 mid 左侧
         * nums2 = {3, 4, 5, 1, 2} 旋转点 1 在 mid 右侧
         * 上述两个数组均可以保证 [0, 2] 区间单调递增，但无法区分旋转点
         * */
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (numbers[mid] > numbers[right]) {
                left = mid + 1;
            } else if (numbers[mid] < numbers[right]) {
                right = mid;
            } else {
                right--;
            }
        }
        return numbers[left];
    }

}

/*
* 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。

示例 1：

输入：[3,4,5,1,2]
输出：1
示例 2：

输入：[2,2,2,0,1]
输出：0
注意：本题与主站 154 题相同：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
