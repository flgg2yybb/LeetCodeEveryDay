package year2021.month1.no78;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Subsets {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3};
        int[] nums2 = new int[]{0};
        System.out.println(subsets1(nums1));
        System.out.println(subsets1(nums2));
    }

    private static List<List<Integer>> subsets1(int[] nums) {
//        位运算，因nums.length <= 10，则int不会溢出
//        生成二进制位，0代表当前位置不选，1代表选
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> subSet = new ArrayList<>();
//        子集的数量等于： 2 ^ nums.length
        int max = 1 << nums.length;
        for (int i = 0; i < max; i++) {
            subSet.clear();
            for (int j = 0; j < nums.length; j++) {
                if ((i & (1 << j)) != 0) {
                    subSet.add(nums[j]);
                }
            }
            result.add(new ArrayList<>(subSet));
        }
        return result;
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, result, new LinkedList<>(), 0);
        return result;
    }

    private static void backtrack(int[] nums, List<List<Integer>> result, List<Integer> subSet, int pos) {
        if (pos == nums.length) {
            result.add(new ArrayList<>(subSet));
            return;
        }
        subSet.add(nums[pos]);
//        选中当前元素递归
        backtrack(nums, result, subSet, pos + 1);
        subSet.remove(subSet.size() - 1);
//        不选中当前元素递归
        backtrack(nums, result, subSet, pos + 1);
    }
}

/*
* 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。

解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。

 

示例 1：

输入：nums = [1,2,3]
输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
示例 2：

输入：nums = [0]
输出：[[],[0]]
 

提示：

1 <= nums.length <= 10
-10 <= nums[i] <= 10
nums 中的所有元素 互不相同

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/subsets
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
