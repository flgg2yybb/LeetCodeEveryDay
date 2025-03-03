package main

import "fmt"

func main() {
	matrix1 := [][]int{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}}
	matrix2 := [][]int{{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}}
	setZeroes(matrix1)
	setZeroes(matrix2)
	println(fmt.Sprintf("%+v", matrix1))
	println(fmt.Sprintf("%+v", matrix2))
}

/*
	O(1) 额外空间, times: O(mn)

思路：使用第一行和第一列来记录对应行和列是否需要置 0
第一行和第一列是否需要置 0 可用两个变量来记录
*/
func setZeroes(matrix [][]int) {
	setFirstRowZero := false
	setFirstColZero := false
	// 如果第一行有 0，就把 setFirstRowZero 置为 true
	for j := 0; j < len(matrix[0]); j++ {
		if matrix[0][j] == 0 {
			setFirstRowZero = true
			break
		}
	}
	// 如果第一列有 0，就把 setFirstColZero 置为 true
	for i := 0; i < len(matrix); i++ {
		if matrix[i][0] == 0 {
			setFirstColZero = true
			break
		}
	}
	// 遍历 [1, m) * [1, n) 的矩阵，如果遇到 0，就把对应的第一行和第一列置为 0
	for i := 1; i < len(matrix); i++ {
		for j := 1; j < len(matrix[0]); j++ {
			if matrix[i][j] == 0 {
				matrix[i][0] = 0
				matrix[0][j] = 0
			}
		}
	}
	// 遍历矩阵第一行，如果有 0，把对应列也都置为 0
	for j := 1; j < len(matrix[0]); j++ {
		if matrix[0][j] == 0 {
			for i := 1; i < len(matrix); i++ {
				matrix[i][j] = 0
			}
		}
	}
	// 遍历矩阵第一列，如果有 0，把对应行也都置为 0
	for i := 1; i < len(matrix); i++ {
		if matrix[i][0] == 0 {
			for j := 1; j < len(matrix[0]); j++ {
				matrix[i][j] = 0
			}
		}
	}
	// 如果 setFirstRowZero 为 true，把第一行置为 0
	if setFirstRowZero {
		for j := 0; j < len(matrix[0]); j++ {
			matrix[0][j] = 0
		}
	}
	// 如果 setFirstColZero 为 true，把第一列置为 0
	if setFirstColZero {
		for i := 0; i < len(matrix); i++ {
			matrix[i][0] = 0
		}
	}
}

/*
	O(m+n) 额外空间, times: O(mn)

思路：新建一个长度分别为 m, n的数组，代表第 m 行，第 n 列是否需要置 0
*/
func setZeroes2(matrix [][]int) {
	zerosRow := make([]bool, len(matrix))
	zerosCol := make([]bool, len(matrix[0]))
	for i := 0; i < len(matrix); i++ {
		for j := 0; j < len(matrix[0]); j++ {
			if matrix[i][j] == 0 {
				zerosRow[i] = true
				zerosCol[j] = true
			}
		}
	}
	for i := 0; i < len(zerosRow); i++ {
		if zerosRow[i] {
			for j := 0; j < len(matrix[0]); j++ {
				matrix[i][j] = 0
			}
		}
	}
	for j := 0; j < len(zerosCol); j++ {
		if zerosCol[j] {
			for i := 0; i < len(matrix); i++ {
				matrix[i][j] = 0
			}
		}
	}
}

/*
	O(mn) 额外空间，times: O(mn)

思路：新建一个m*n的矩阵，全部赋值为 -1
遍历原数组
  - 如果遇到0，就把新数组对应的行和列都赋值为0
  - 如果遇到非 0，且新数组对应位置为 -1，就把新数组对应位置赋值为原数组的值
*/
func setZeroes1(matrix [][]int) {
	res := make([][]int, len(matrix))
	for i := 0; i < len(matrix); i++ {
		res[i] = make([]int, len(matrix[0]))
		for j := 0; j < len(matrix[0]); j++ {
			res[i][j] = -1
		}
	}
	for i := 0; i < len(matrix); i++ {
		for j := 0; j < len(matrix[0]); j++ {
			if matrix[i][j] == 0 {
				for k := 0; k < len(matrix); k++ {
					res[k][j] = 0
				}
				for k := 0; k < len(matrix[0]); k++ {
					res[i][k] = 0
				}
			} else if res[i][j] == -1 {
				res[i][j] = matrix[i][j]
			}
		}
	}
	for i := 0; i < len(matrix); i++ {
		for j := 0; j < len(matrix[0]); j++ {
			matrix[i][j] = res[i][j]
		}
	}
}

/*
给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。


示例 1：


输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
输出：[[1,0,1],[0,0,0],[1,0,1]]
示例 2：


输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]


提示：

m == matrix.length
n == matrix[0].length
1 <= m, n <= 200
-231 <= matrix[i][j] <= 231 - 1


进阶：

一个直观的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
你能想出一个仅使用常量空间的解决方案吗？
*/
