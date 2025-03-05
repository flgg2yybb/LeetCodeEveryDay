package main

import "fmt"

func main() {
	matrix1 := [][]int{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}
	matrix2 := [][]int{{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}}
	rotate(matrix1)
	rotate(matrix2)
	println(fmt.Sprintf("%+v", matrix1))
	println(fmt.Sprintf("%+v", matrix2))
}

// times: O(n^2), space: O(1)
// 1. 沿着主对角线翻转
// 2. 沿着y轴翻转
// PS: 只有 n*n 的矩阵适用，如果是 n*m 的矩阵，需要另外的方法
func rotate(matrix [][]int) {
	// 沿着主对角线翻转
	for i := 0; i < len(matrix); i++ {
		for j := i + 1; j < len(matrix[0]); j++ {
			temp := matrix[i][j]
			matrix[i][j] = matrix[j][i]
			matrix[j][i] = temp
		}
	}
	// 沿着y轴翻转
	for i := 0; i < len(matrix); i++ {
		for j := 0; j < len(matrix[0])/2; j++ {
			temp := matrix[i][j]
			matrix[i][j] = matrix[i][len(matrix[0])-1-j]
			matrix[i][len(matrix[0])-1-j] = temp
		}
	}
}

/*
* 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。

你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。

示例 1：
输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[[7,4,1],[8,5,2],[9,6,3]]

示例 2：
输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]

提示：
n == matrix.length == matrix[i].length
1 <= n <= 20
-1000 <= matrix[i][j] <= 1000
*/
