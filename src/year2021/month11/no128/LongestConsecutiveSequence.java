package year2021.month11.no128;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LongestConsecutiveSequence {

    public static void main(String[] args) {
        int[] nums1 = {100, 4, 200, 1, 3, 2};
        int[] nums2 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        System.out.println(longestConsecutive(nums1));
        System.out.println(longestConsecutive(nums2));
    }

    public static int longestConsecutive(int[] nums) {
        /*
         * 将nums元素放入集合中去重（重复元素无意义）
         * 由于需要找出最长连续序列，则从序列起点开始计算时序列最长
         * 故非起点不需要寻找
         * 遍历集合的元素，假设当前元素为 num
         * 若 num - 1 在集合中，则 num 非起点，跳过
         * 否则，num 为起点元素，
         *      以 num 元素为起点递增，分别判断集合中是否存在下一个元素
         *      并计数
         * 遍历完所有集合元素即可得知结果
         * */
        Set<Integer> numSet = IntStream.of(nums).boxed().collect(Collectors.toSet());
        int longest = 0;
        for (int num : nums) {
            if (numSet.contains(num - 1)) { // 非起点
                continue;
            }
            int curLong = 1;
            int nextNum = num + 1;
            while (numSet.contains(nextNum)) {
                curLong++;
                nextNum++;
            }
            longest = Math.max(longest, curLong);
        }
        return longest;
    }

}

/*
* 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。

请你设计并实现时间复杂度为 O(n) 的算法解决此问题。

 

示例 1：

输入：nums = [100,4,200,1,3,2]
输出：4
解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
示例 2：

输入：nums = [0,3,7,2,5,8,4,6,0,1]
输出：9
 

提示：

0 <= nums.length <= 105
-109 <= nums[i] <= 109

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-consecutive-sequence
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
