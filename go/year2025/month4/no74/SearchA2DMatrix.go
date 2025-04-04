package main

func main() {
	matrix1 := [][]int{
		{1, 3, 5, 7},
		{10, 11, 16, 20},
		{23, 30, 34, 60},
	}
	target1 := 3
	println(searchMatrix(matrix1, target1)) // true
	matrix2 := [][]int{
		{1, 3, 5, 7},
		{10, 11, 16, 20},
		{23, 30, 34, 60},
	}
	target2 := 13
	println(searchMatrix(matrix2, target2)) // false
}

// 二分，times: O(m+n), space: O(1)
// 思路：从左下角往右上搜索
// 如果当前元素小于目标值，说明目标值在右边；如果当前元素大于目标值，说明目标值在上面；
func searchMatrix(matrix [][]int, target int) bool {
	x, y := len(matrix)-1, 0
	for x >= 0 && y < len(matrix[0]) {
		if matrix[x][y] == target {
			return true
		}
		if matrix[x][y] < target {
			y++
		} else { // matrix[x][y] > target
			x--
		}
	}
	return false
}

/*
给你一个满足下述两条属性的 m x n 整数矩阵：
每行中的整数从左到右按非严格递增顺序排列。
每行的第一个整数大于前一行的最后一个整数。
给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。

示例 1：
输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
输出：true

示例 2：
输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
输出：false

提示：
m == matrix.length
n == matrix[i].length
1 <= m, n <= 100
-104 <= matrix[i][j], target <= 104
*/
