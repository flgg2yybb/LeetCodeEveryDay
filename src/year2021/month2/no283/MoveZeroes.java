package year2021.month2.no283;

import java.util.Arrays;
import java.util.stream.Collectors;

public class MoveZeroes {
    public static void main(String[] args) {
        int[] nums1 = new int[]{0, 1, 0, 3, 12};
        moveZeroes(nums1);
        disp(nums1);
    }

    public static void moveZeroes(int[] nums) {
//        用right指针遍历数组，用left指针指向所有已移除0的子数组的末尾元素的下一位
//        当right指向的元素不为0时，将其赋值给left指针指向的位置，并将left指针移动
//        到下一位，当right指针遍历完时，说明right指针已将所有非0元素赋值给left
//        之前的位置了，再将left指针将剩下的位置赋值为0即可
//        time is O(n), space is O(1)
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            if (nums[right] != 0) {
                nums[left] = nums[right];
                left++;
            }
            right++;
        }
        while (left < nums.length) {
            nums[left] = 0;
            left++;
        }
    }

    private static void disp(int[] nums) {
        System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()));
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
