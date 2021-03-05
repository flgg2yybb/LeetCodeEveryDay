package year2021.month3.no448;

import java.util.ArrayList;
import java.util.List;

public class FindAllNumbersDisappearedInArray {
    public static void main(String[] args) {
        int[] nums1 = {4, 3, 2, 7, 8, 2, 3, 1};
        int[] nums2 = {1, 1, 2, 3, 3, 5, 7, 8};
        System.out.println(findDisappearedNumbers(nums1));
        System.out.println(findDisappearedNumbers(nums2));
    }

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[(nums[i] - 1) % nums.length] += nums.length;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
//            原来就等于nums.length的也要包括进去，若出现过的数组，其位置上的数必定严格大于nums.length
            if (nums[i] <= nums.length) {
                res.add(i + 1);
            }
        }
        return res;
    }
}

/*
* 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。

找到所有在 [1, n] 范围之间没有出现在数组中的数字。

您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。

示例:

输入:
[4,3,2,7,8,2,3,1]

输出:
[5,6]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
