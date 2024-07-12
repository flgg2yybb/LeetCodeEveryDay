package year2021.month1.no75;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SortColors {
    public static void main(String[] args) {
        int[] nums1 = new int[]{2, 0, 2, 1, 1, 0};
        int[] nums2 = new int[]{2, 0, 1};
        int[] nums3 = new int[]{0};
        int[] nums4 = new int[]{1};
        sortColors2(nums1);
        sortColors2(nums2);
        sortColors2(nums3);
        sortColors2(nums4);
        disp(nums1);
        disp(nums2);
        disp(nums3);
        disp(nums4);
    }

    private static void sortColors2(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return;
        }
//        循环不变量定义
//        [0, p1) = 0
//        [p1, i) = 1
//        [p2, len) = 2
        int p1 = 0;
        int i = 0;
        int p2 = len;
        while (i < p2) {
            if (nums[i] == 0) {
                swap(nums, i, p1);
                p1++;
                i++;
            } else if (nums[i] == 1) {
                i++;
            } else {
                //  nums[i] == 2
                p2--;
                swap(nums, i, p2);
            }
        }
    }

    private static void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    private static void sortColors1(int[] nums) {
//        counting sort - 计数排序
        int max = 2;
        int min = 0;
        int[] count = new int[max - min + 1];
        for (int i = 0; i < nums.length; i++) {
            count[nums[i] - min]++;
        }
        int index = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                nums[index++] = i;
                count[i]--;
            }
        }
    }

    public static void sortColors(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private static void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = partition(nums, left, right);
        quickSort(nums, left, pivot - 1);
        quickSort(nums, pivot + 1, right);
    }

    private static int partition(int[] nums, int left, int right) {
        int key = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= key) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] <= key) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = key;
        return left;
    }

    private static void disp(int[] nums) {
        System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()));
    }
}

/*
* 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。

 

示例 1：

输入：nums = [2,0,2,1,1,0]
输出：[0,0,1,1,2,2]
示例 2：

输入：nums = [2,0,1]
输出：[0,1,2]
示例 3：

输入：nums = [0]
输出：[0]
示例 4：

输入：nums = [1]
输出：[1]
 

提示：

n == nums.length
1 <= n <= 300
nums[i] 为 0、1 或 2
 

进阶：

你可以不使用代码库中的排序函数来解决这道题吗？
你能想出一个仅使用常数空间的一趟扫描算法吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sort-colors
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
