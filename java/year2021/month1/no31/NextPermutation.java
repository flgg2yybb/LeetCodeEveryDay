package year2021.month1.no31;

public class NextPermutation {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3};       //132
        int[] nums2 = new int[]{1, 3, 2};       //213
        int[] nums3 = new int[]{2, 1, 3};       //231
        int[] nums4 = new int[]{2, 3, 1};       //312
        int[] nums5 = new int[]{3, 1, 2};       //321
        int[] nums6 = new int[]{3, 2, 1};       //123
        nextPermutation(nums1);
        nextPermutation(nums2);
        nextPermutation(nums3);
        nextPermutation(nums4);
        nextPermutation(nums5);
        nextPermutation(nums6);
        disp(nums1);
        disp(nums2);
        disp(nums3);
        disp(nums4);
        disp(nums5);
        disp(nums6);
    }

    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int i;
        for (i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                for (int j = nums.length - 1; j > 0; j--) {
                    if (nums[j] > nums[i]) {
                        swap(nums, i, j);
                        break;
                    }
                }
                break;
            }
        }
        reverse(nums, i + 1, nums.length - 1);
    }

    private static void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static void disp(int[] nums) {
        if (nums == null || nums.length == 0) {
            System.out.println("EMPTY");
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
            if (i != nums.length - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }
}

/*
* 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。

如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

必须 原地 修改，只允许使用额外常数空间。



示例 1：

输入：nums = [1,2,3]
输出：[1,3,2]
示例 2：

输入：nums = [3,2,1]
输出：[1,2,3]
示例 3：

输入：nums = [1,1,5]
输出：[1,5,1]
示例 4：

输入：nums = [1]
输出：[1]


提示：

1 <= nums.length <= 100
0 <= nums[i] <= 100

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/next-permutation
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
