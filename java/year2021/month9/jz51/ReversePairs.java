package year2021.month9.jz51;

public class ReversePairs {
    public static void main(String[] args) {
        int[] nums1 = {7, 5, 6, 4};
        System.out.println(reversePairs(nums1));
    }

    public static int reversePairs(int[] nums) {
        return mergeSortCountReversePairs(nums, 0, nums.length - 1);
    }

    private static int mergeSortCountReversePairs(int[] nums, int start, int end) {
        if (start >= end) {
            return 0;
        }
        int mid = start + (end - start) / 2;
        int left = mergeSortCountReversePairs(nums, start, mid);
        int right = mergeSortCountReversePairs(nums, mid + 1, end);
        int merge = mergeAndCount(nums, start, mid, mid + 1, end);
        return left + right + merge;
    }

    private static int mergeAndCount(int[] nums, int leftStart, int leftEnd, int rightStart, int rightEnd) {
        int count = 0;
        int start = leftStart;
        int[] temp = new int[leftEnd - leftStart + rightEnd - rightStart + 2];
        int pos = 0;
        while (leftStart <= leftEnd && rightStart <= rightEnd) {
            if (nums[leftStart] <= nums[rightStart]) {
                temp[pos] = nums[leftStart];
                leftStart++;
            } else {
                count += leftEnd - leftStart + 1;
                temp[pos] = nums[rightStart];
                rightStart++;
            }
            pos++;
        }
        if (leftStart <= leftEnd) {
            System.arraycopy(nums, leftStart, temp, pos, leftEnd - leftStart + 1);
        }
        // rightStart <= rightEnd 的情况不用单独 copy，位置是正确的
        System.arraycopy(temp, 0, nums, start, rightStart - start);
        return count;
    }
}

/*
* 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。

 

示例 1:

输入: [7,5,6,4]
输出: 5
 

限制：

0 <= 数组长度 <= 50000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
