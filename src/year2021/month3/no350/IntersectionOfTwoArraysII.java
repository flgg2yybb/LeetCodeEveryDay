package year2021.month3.no350;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntersectionOfTwoArraysII {
    public static void main(String[] args) {
        int[] nums11 = {1, 2, 2, 1};
        int[] nums12 = {2, 2};
        int[] nums21 = {4, 9, 5};
        int[] nums22 = {9, 4, 9, 8, 4};
        disp(intersect(nums11, nums12));
        disp(intersect(nums21, nums22));
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums2) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer> res = new LinkedList<>();
        for (int num : nums1) {
            if (map.getOrDefault(num, 0) > 0) {
                res.add(num);
                map.put(num, map.get(num) - 1);
            }
        }
        return res.stream().mapToInt(Integer::valueOf).toArray();
    }

    private static void disp(int[] nums) {
        System.out.println(IntStream.of(nums).boxed().collect(Collectors.toList()));
    }
}

/*
* 给定两个数组，编写一个函数来计算它们的交集。

 

示例 1：

输入：nums1 = [1,2,2,1], nums2 = [2,2]
输出：[2,2]
示例 2:

输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出：[4,9]
 

说明：

输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
我们可以不考虑输出结果的顺序。
进阶：

1. 如果给定的数组已经排好序呢？你将如何优化你的算法？
2. 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
3. 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/intersection-of-two-arrays-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
