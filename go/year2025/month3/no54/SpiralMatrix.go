package main

import "fmt"

func main() {
	matrix1 := [][]int{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}
	matrix2 := [][]int{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}}
	println(fmt.Sprintf("%+v", spiralOrder(matrix1)))
	println(fmt.Sprintf("%+v", spiralOrder(matrix2)))
}

func spiralOrder(matrix [][]int) []int {
	top := 0
	right := len(matrix[0]) - 1
	bottom := len(matrix) - 1
	left := 0
	res := make([]int, 0)
	for top <= bottom && left <= right {
		// top
		for k := left; k <= right && top <= bottom; k++ {
			res = append(res, matrix[top][k])
		}
		top++
		// right
		for k := top; k <= bottom && left <= right; k++ {
			res = append(res, matrix[k][right])
		}
		right--
		// bottom
		for k := right; k >= left && top <= bottom; k-- {
			res = append(res, matrix[bottom][k])
		}
		bottom--
		// left
		for k := bottom; k >= top && left <= right; k-- {
			res = append(res, matrix[k][left])
		}
		left++
	}
	return res
}

/*
给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。



示例 1：


输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[1,2,3,6,9,8,7,4,5]
示例 2：


输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
输出：[1,2,3,4,8,12,11,10,9,5,6,7]


提示：

m == matrix.length
n == matrix[i].length
1 <= m, n <= 10
-100 <= matrix[i][j] <= 100
*/
