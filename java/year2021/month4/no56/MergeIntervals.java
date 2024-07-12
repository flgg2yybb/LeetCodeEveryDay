package year2021.month4.no56;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MergeIntervals {

    public static void main(String[] args) {
        int[][] intervals1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] intervals2 = {{1, 4}, {4, 5}};
        int[][] intervals3 = {{1, 3}, {-3, 0}, {8, 15}, {14, 18}};
        int[][] intervals4 = {{15, 18}, {9, 16}, {3, 12}, {-100, 3}};
        int[][] intervals5 = {{1, 4}, {2, 3}};
        disp(merge(intervals1));
        disp(merge(intervals2));
        disp(merge(intervals3));
        disp(merge(intervals4));
        disp(merge(intervals5));
    }

    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (nums1, nums2) -> {
            if (nums1[0] != nums2[0]) {
                return nums1[0] - nums2[0];
            }
            return nums1[1] - nums2[1];
        });
        List<int[]> list = new LinkedList<>();
        int i = 0;
        while (i < intervals.length) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            int j = i + 1;
            while (j < intervals.length && end >= intervals[j][0]) {
                end = Math.max(end, intervals[j][1]);
                j++;
            }
            list.add(new int[]{start, end});
            i = j;
        }
        return list.toArray(new int[0][0]);
    }

    private static void disp(int[][] matrix) {
        for (int[] nums : matrix) {
            System.out.print(Arrays.toString(nums) + "  ");
        }
        System.out.println();
    }

}

/*
* 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。

 

示例 1：

输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
输出：[[1,6],[8,10],[15,18]]
解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
示例 2：

输入：intervals = [[1,4],[4,5]]
输出：[[1,5]]
解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 

提示：

1 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 104

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/merge-intervals
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
