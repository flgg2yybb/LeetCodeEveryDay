package main

import (
	"fmt"
	"sort"
)

func main() {
	candidates1 := []int{2, 3, 6, 7}
	target1 := 7
	candidates2 := []int{2, 3, 5}
	target2 := 8
	candidates3 := []int{2}
	target3 := 1
	println(fmt.Sprintf("%+v", combinationSum(candidates1, target1)))
	println(fmt.Sprintf("%+v", combinationSum(candidates2, target2)))
	println(fmt.Sprintf("%+v", combinationSum(candidates3, target3)))
}

func combinationSum(candidates []int, target int) [][]int {
	ans := make([][]int, 0)
	arr := make([]int, 0)
	backtrack(&ans, candidates, target, &arr, 0)
	return ans
}

// backtrack 通过 pos 控制 candidates 中每个元素的选取次数，避免重复答案
func backtrack(ans *[][]int, candidates []int, target int, arr *[]int, pos int) {
	if target == 0 {
		if len(*arr) > 0 {
			p := make([]int, 0, len(*arr))
			for _, val := range *arr {
				p = append(p, val)
			}
			*ans = append(*ans, p)
		}
		return
	}
	if pos >= len(candidates) {
		return
	}
	loop := target / candidates[pos]
	for i := 0; i <= loop; i++ {
		for j := 0; j < i; j++ { // 选 i 个 candidates[pos]
			*arr = append(*arr, candidates[pos])
		}
		sum := i * candidates[pos]
		backtrack(ans, candidates, target-sum, arr, pos+1)
		*arr = (*arr)[:len(*arr)-i] // 回溯
	}
}

func combinationSum1(candidates []int, target int) [][]int {
	ans := make([][]int, 0)
	arr := make([]int, 0)
	// backtrack
	backtrack1(&ans, candidates, target, &arr, 0)
	// deduplicate
	return deduplicate(ans)
}

func deduplicate(arr [][]int) (res [][]int) {
	dic := make(map[string][]int)
	for _, s := range arr {
		sort.Ints(s)
		key := fmt.Sprintf("%+v", s)
		dic[key] = s
	}
	for _, val := range dic {
		res = append(res, val)
	}
	return
}

func backtrack1(ans *[][]int, candidates []int, target int, arr *[]int, sum int) {
	if sum > target {
		// candidates 都是正数，sum 超过时，后续不可能满足条件
		return
	}
	if sum == target {
		if len(*arr) > 0 {
			p := make([]int, 0, len(*arr))
			for _, val := range *arr {
				p = append(p, val)
			}
			*ans = append(*ans, p)
		}
	}
	for _, candidate := range candidates {
		if sum+candidate > target {
			continue
		}
		*arr = append(*arr, candidate)
		backtrack1(ans, candidates, target, arr, sum+candidate)
		*arr = (*arr)[:len(*arr)-1]
	}
}

/*
 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，
找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，
并以列表形式返回。你可以按 任意顺序 返回这些组合。

candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
对于给定的输入，保证和为 target 的不同组合数少于 150 个。

示例 1：
输入：candidates = [2,3,6,7], target = 7
输出：[[2,2,3],[7]]
解释：
2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
7 也是一个候选， 7 = 7 。
仅有这两种组合。

示例 2：
输入: candidates = [2,3,5], target = 8
输出: [[2,2,2,2],[2,3,3],[3,5]]

示例 3：
输入: candidates = [2], target = 1
输出: []


提示：
1 <= candidates.length <= 30
2 <= candidates[i] <= 40
candidates 的所有元素 互不相同
1 <= target <= 40
*/
