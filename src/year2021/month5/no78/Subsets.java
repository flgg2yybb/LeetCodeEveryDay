package year2021.month5.no78;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3};
        int[] nums2 = {0};
        System.out.println(subsets1(nums1));
        System.out.println(subsets1(nums2));
    }

    private static List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        int len = nums.length;
        int total = (int) Math.pow(2, len);
        for (int i = 0; i < total; i++) {
//            i 为 0 -> 2^n - 1, 用二进制表示，且令 1 所在的位置为选中的元素
            List<Integer> collect = new ArrayList<>();
            for (int j = 0; j < len; j++) {
//                遍历 nums 下标, 判断哪个元素需要选中
                int mask = 1 << j;
                if ((mask & i) != 0) {
                    collect.add(nums[j]);
                }
            }
            ans.add(collect);
        }
        return ans;
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(ans, new ArrayList<>(), nums, 0);
        return ans;
    }

    private static void backtrack(List<List<Integer>> ans, List<Integer> collect, int[] nums, int pos) {
        if (pos == nums.length) {
            ans.add(new ArrayList<>(collect));
            return;
        }
        backtrack(ans, collect, nums, pos + 1);
        collect.add(nums[pos]);
        backtrack(ans, collect, nums, pos + 1);
        collect.remove(collect.size() - 1);
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
