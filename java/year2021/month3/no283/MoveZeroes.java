package year2021.month3.no283;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MoveZeroes {
    public static void main(String[] args) {
        int[] nums1 = {0, 1, 0, 3, 12};
        int[] nums2 = {0, 1, 0, 3, 0};
        int[] nums3 = {1};
        moveZeroes1(nums1);
        moveZeroes1(nums2);
        moveZeroes1(nums3);
        disp(nums1);
        disp(nums2);
        disp(nums3);
    }

    private static void moveZeroes1(int[] nums) {
//        Partition in Quick Sort
//        [0, slow)非零，[slow, right)必为零，[right, n)未知
        int slow = 0;
        int fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                swap(nums, slow, fast);
                slow++;
            }
            fast++;
        }
    }

    private static void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    public static void moveZeroes(int[] nums) {
        int slow = -1;
        int fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        while (slow < nums.length - 1) {
            nums[++slow] = 0;
        }
    }

    private static void disp(int[] nums) {
        System.out.println(IntStream.of(nums).boxed().collect(Collectors.toList()));
    }
}

/*
* 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

示例:

输入: [0,1,0,3,12]
输出: [1,3,12,0,0]
说明:

必须在原数组上操作，不能拷贝额外的数组。
尽量减少操作次数。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/move-zeroes
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
