package year2021.month11.no57;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertInterval {

    public static void main(String[] args) {
        int[][] intervals1 = {{1, 3}, {6, 9}};
        int[] newInterval1 = {2, 5};
        int[][] intervals2 = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval2 = {4, 8};
        int[][] intervals3 = {};
        int[] newInterval3 = {5, 7};
        int[][] intervals4 = {{1, 5}};
        int[] newInterval4 = {2, 3};
        int[][] intervals5 = {{1, 5}};
        int[] newInterval5 = {2, 7};
        System.out.println(Arrays.deepToString(insert(intervals1, newInterval1)));      // {{1,5}, {6,9}}
        System.out.println(Arrays.deepToString(insert(intervals2, newInterval2)));      // {{1,2}, {3,10}, {12,16}}
        System.out.println(Arrays.deepToString(insert(intervals3, newInterval3)));      // {{5,7}}
        System.out.println(Arrays.deepToString(insert(intervals4, newInterval4)));      // {{1,5}}
        System.out.println(Arrays.deepToString(insert(intervals5, newInterval5)));      // {{1,7}}
    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>();
        int index = 0;
        while (index < intervals.length && intervals[index][1] < newInterval[0]) {  // 将小于待插入区间的元素插入新区间
            list.add(intervals[index]);
            index++;
        }
        while (index < intervals.length && intervals[index][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[index][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[index][1]);
            index++;
        }
        list.add(newInterval);
        while (index < intervals.length) {      // 将大于待插入区间的元素插入新区间
            list.add(intervals[index]);
            index++;
        }
        int[][] ans = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

}

/*
* 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。

在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。

 

示例 1：

输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
输出：[[1,5],[6,9]]
示例 2：

输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
输出：[[1,2],[3,10],[12,16]]
解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
示例 3：

输入：intervals = [], newInterval = [5,7]
输出：[[5,7]]
示例 4：

输入：intervals = [[1,5]], newInterval = [2,3]
输出：[[1,5]]
示例 5：

输入：intervals = [[1,5]], newInterval = [2,7]
输出：[[1,7]]
 

提示：

0 <= intervals.length <= 104
intervals[i].length == 2
0 <= intervals[i][0] <= intervals[i][1] <= 105
intervals 根据 intervals[i][0] 按 升序 排列
newInterval.length == 2
0 <= newInterval[0] <= newInterval[1] <= 105

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/insert-interval
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */