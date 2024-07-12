package year2021.month6.no315;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CountOfSmallerNumbersAfterSelf {

    public static void main(String[] args) {
        int[] nums1 = {5, 2, 6, 1};
        System.out.println(countSmaller(nums1));
    }

    public static List<Integer> countSmaller(int[] nums) {
        // 离散化 + 树状数组，time is O(nlogn), space is O(n)
        // 使用集合去重，并从小到大排序元素计算其排名
        // 从末尾遍历数组，并将其排名更新至树状数组中（单点更新）
        // 同时查询树状数组中比当前元素排名小的元素的总和（区间查询）
        // 树状数组中的叶子节点存储的是某个排名的元素个数
        Set<Integer> set = new TreeSet<>();     // 二叉搜索树，从小到大排序元素
        for (int num : nums) {
            set.add(num);
        }
        Map<Integer, Integer> rankMap = new HashMap<>();    // 元素排名表（从小到大）
        set.forEach(num -> rankMap.put(num, rankMap.size() + 1));
        FenwickTree fenwickTree = new FenwickTree(set.size());
        int[] ans = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            Integer rank = rankMap.get(nums[i]);
            fenwickTree.update(rank, 1);
            ans[i] = fenwickTree.query(rank - 1);       // 查询严格小于当前排名的元素个数
        }
        return Arrays.stream(ans).boxed().collect(Collectors.toList());
    }

}

/*
 * 树状数组
 * ref link: https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/solution/shu-zhuang-shu-zu-by-liweiwei1419/
 * */
class FenwickTree {

    final int[] tree;

    FenwickTree(int size) {
        this.tree = new int[size + 1];
    }

    // 单点更新，对 index 节点及其父节点分别加上 value，时间复杂度为 O(logn)
    public void update(int index, int value) {
        for (int i = index; i < tree.length; i += lowbit(i)) {
            tree[i] += value;
        }
    }

    // 区间查询，时间复杂度为 O(logn)
    public int query(int index) {
        int sum = 0;
        for (int i = index; i > 0; i -= lowbit(i)) {
            sum += tree[i];
        }
        return sum;
    }

    private int lowbit(int num) {
        return num & -num;
    }
}

/*
* 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。

 

示例：

输入：nums = [5,2,6,1]
输出：[2,1,1,0] 
解释：
5 的右侧有 2 个更小的元素 (2 和 1)
2 的右侧仅有 1 个更小的元素 (1)
6 的右侧有 1 个更小的元素 (1)
1 的右侧有 0 个更小的元素
 

提示：

0 <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
