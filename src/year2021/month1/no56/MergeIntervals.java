package year2021.month1.no56;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MergeIntervals {
    public static void main(String[] args) {
        int[][] intervals1 = new int[][]{{1, 4}, {2, 3}};
        int[][] intervals2 = new int[][]{{2, 6}, {1, 3}, {15, 18}, {8, 10}};
        int[][] merge1 = merge1(intervals1);
        int[][] merge2 = merge1(intervals2);
        disp(merge1);
        disp(merge2);
    }

    private static int[][] merge1(int[][] intervals) {
        if (intervals == null || intervals.length < 2) {
            return intervals;
        }
        quickSort(intervals, 0, intervals.length - 1);
        int[][] result = new int[intervals.length][2];
        int index = 0;
        int count = 0;
        while (index < intervals.length) {
            int start = intervals[index][0];
            int end = intervals[index][1];
            int next = index + 1;
            while (next < intervals.length) {
                if (intervals[next][0] > end) {
                    break;
                }
                end = Math.max(end, intervals[next][1]);
                next++;
            }
            result[count][0] = start;
            result[count][1] = end;
            count++;
            index = next;
        }
        return Arrays.copyOf(result, count);
    }

    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length < 2) {
            return intervals;
        }
        quickSort(intervals, 0, intervals.length - 1);
//        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
        List<int[]> resultList = new ArrayList<>();
        int index = 0;
        while (index < intervals.length) {
            int start = intervals[index][0];
            int end = intervals[index][1];
            int next = index + 1;
            while (next < intervals.length) {
                if (intervals[next][0] > end) {
                    break;
                }
                end = Math.max(end, intervals[next][1]);
                next++;
            }
            index = next;
            resultList.add(new int[]{start, end});
        }
        return resultList.toArray(new int[resultList.size()][]);
    }

    private static void quickSort(int[][] intervals, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = findPivot(intervals, left, right);
        quickSort(intervals, left, pivot - 1);
        quickSort(intervals, pivot + 1, right);
    }

    private static int findPivot(int[][] intervals, int left, int right) {
        int[] key = Arrays.copyOf(intervals[left], intervals[left].length);
        while (left < right) {
            while (left < right && intervals[right][0] >= key[0]) {
                right--;
            }
            intervals[left] = intervals[right];
            while (left < right && intervals[left][0] <= key[0]) {
                left++;
            }
            intervals[right] = intervals[left];
        }
        intervals[left] = key;
        return left;
    }

    private static void disp(int[][] intervals) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            result.add(Arrays.stream(intervals[i]).boxed().collect(Collectors.toList()));
        }
        System.out.println(result);
    }

}

/*
 * 给出一个区间的集合，请合并所有重叠的区间。

 

示例 1:

输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
输出: [[1,6],[8,10],[15,18]]
解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
示例 2:

输入: intervals = [[1,4],[4,5]]
输出: [[1,5]]
解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
注意：输入类型已于2019年4月15日更改。 请重置默认代码定义以获取新方法签名。

 

提示：

intervals[i][0] <= intervals[i][1]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/merge-intervals
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
