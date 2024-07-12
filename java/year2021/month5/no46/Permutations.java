package year2021.month5.no46;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Permutations {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3};
        int[] nums2 = {0, 1};
        int[] nums3 = {1};
        System.out.println(permute(nums1));
        System.out.println(permute(nums2));
        System.out.println(permute(nums3));
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> collect = Arrays.stream(nums).boxed().collect(Collectors.toList());
        backtrack(ans, collect, 0);
        return ans;
    }

    private static void backtrack(List<List<Integer>> ans, List<Integer> collect, int pos) {
        if (pos == collect.size()) {
            ans.add(new ArrayList<>(collect));
            return;
        }
        for (int i = pos; i < collect.size(); i++) {
            Collections.swap(collect, pos, i);
            backtrack(ans, collect, pos + 1);
            Collections.swap(collect, pos, i);
        }
    }

}

/*
* 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。

 

示例 1：

输入：nums = [1,2,3]
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
示例 2：

输入：nums = [0,1]
输出：[[0,1],[1,0]]
示例 3：

输入：nums = [1]
输出：[[1]]
 

提示：

1 <= nums.length <= 6
-10 <= nums[i] <= 10
nums 中的所有整数 互不相同

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/permutations
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
