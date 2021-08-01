package year2021.month8.jz51;

public class ReversePairs {

    public static void main(String[] args) {
        int[] nums1 = {7, 5, 6, 4};
        int[] nums2 = {};
        System.out.println(reversePairs1(nums1));
        System.out.println(reversePairs1(nums2));
    }

    private static int reversePairs1(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    private static int mergeSort(int[] nums, int start, int end) {
        if (start >= end) {
            return 0;
        }
        int mid = start + (end - start) / 2;
        int ans = mergeSort(nums, start, mid) + mergeSort(nums, mid + 1, end);
        int left = start;
        int right = mid + 1;
        int[] temp = new int[end - start + 1];
        int pos = 0;
        while (left <= mid && right <= end) {
            if (nums[left] <= nums[right]) {
                temp[pos] = nums[left];
                left++;
            } else {
                // 存在逆序对
                temp[pos] = nums[right];
                right++;
                // 当前 nums[right] 可与 [left, mid] 的元素组成逆序对
                ans += mid - left + 1;
            }
            pos++;
        }
        while (left <= mid) {
            temp[pos++] = nums[left++];
        }
        while (right <= end) {
            temp[pos++] = nums[right++];
        }
        System.arraycopy(temp, 0, nums, start, temp.length);
        return ans;
    }

    public static int reversePairs(int[] nums) {
        // Brute Force, time limit exceed
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    count++;
                }
            }
        }
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
