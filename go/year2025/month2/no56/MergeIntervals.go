package main

import (
	"fmt"
	"sort"
)

func main() {
	intervals1 := [][]int{{1, 3}, {2, 6}, {8, 10}, {15, 18}}
	intervals2 := [][]int{{1, 4}, {4, 5}}
	println(fmt.Sprintf("res: %+v\n", merge(intervals1)))
	println(fmt.Sprintf("res: %+v\n", merge(intervals2)))
}

/*
	Sort & Merge, time: O(nlogn), space: O(n)

前提：需要保证给定区间 [i, j]， 有 i <= j
 1. 对数组排序，按照左区间升序，如果左区间相同，则按照右区间升序
 2. 顺序遍历 intervals，初始化时取第一个区间作为临时区间
 3. 尝试把临时区间，与后续区间进行合并。假设当前临时区间为 [a, b], 下一区间为 [c, d]，则默认有 a <= c
    3a. 如果当前满足 b >= c，则可以合并，合并后的区间为 [a, max(b, d)]
    3b. 如果当前满足 b < c，则无法合并
 4. 无法合并时，取下一区间作为新的临时区间，继续步骤 3，直到所有区间都遍历完成
*/
func merge(intervals [][]int) (res [][]int) {
	sort.Slice(intervals, func(i, j int) bool {
		if intervals[i][0] != intervals[j][0] {
			return intervals[i][0] < intervals[j][0]
		}
		return intervals[i][1] < intervals[j][1]
	})
	res = make([][]int, 0) // 把 res 中的最后一个元素作为临时区间
	for i := 0; i < len(intervals); i++ {
		if len(res) == 0 || res[len(res)-1][1] < intervals[i][0] {
			// 无法合并，取下一区间作为新的临时区间
			res = append(res, intervals[i])
			continue
		}
		// 可以合并，合并后的区间为 [a, max(b, d)]
		res[len(res)-1][1] = max(res[len(res)-1][1], intervals[i][1])
	}
	return res
}

/*
以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。

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
*/
