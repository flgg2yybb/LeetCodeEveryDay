package main

import "fmt"

func main() {
	n1 := 4
	n2 := 1
	println(fmt.Sprintf("%v", solveNQueens(n1))) // [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
	println(fmt.Sprintf("%v", solveNQueens(n2))) // [["Q"]]
}

func solveNQueens(n int) [][]string {
	ans := make([][]string, 0)
	matrix := make([][]bool, n)
	for i := 0; i < n; i++ {
		matrix[i] = make([]bool, n)
	}
	backtrack(&ans, matrix, n, 0)
	return ans
}

// backtrack, times: O(n * n!), space: O(n^2)
// matrix: 记录当前棋盘上皇后的位置，true 为皇后，false 为空位
// n: 总共需要放置的皇后数量，k：当前已放置的皇后数量
func backtrack(ans *[][]string, matrix [][]bool, n int, row int) {
	if row == n {
		p := make([]string, 0, n) // times: O(n^2)，结果不多可以忽略
		for i := 0; i < len(matrix); i++ {
			s := ""
			for j := 0; j < len(matrix[0]); j++ {
				if matrix[i][j] {
					s += "Q"
				} else {
					s += "."
				}
			}
			p = append(p, s)
		}
		*ans = append(*ans, p)
		return
	}
	// 从 matrix 的第一行开始放置皇后，当前放第 row 个，则需要放到第 row 行
	for col := 0; col < len(matrix[0]); col++ { // times: O(n!), 第一行有 n 种选择，第二行有 n-1 种选择，...，最后一行只有 1 种选择
		if isLegal(matrix, row, col) { // times: O(n)
			matrix[row][col] = true
			backtrack(ans, matrix, n, row+1)
			matrix[row][col] = false
		}
	}
}

// 因为从上往下放，所以只需要判断当前行的上面行和左上、右上是否有皇后
func isLegal(matrix [][]bool, row int, col int) bool {
	// 检查当前列上面行是否有皇后
	for x := 0; x < row; x++ {
		if matrix[x][col] {
			return false
		}
	}
	// 检查左上对角线是否有皇后
	for x, y := row-1, col-1; x >= 0 && y >= 0; x, y = x-1, y-1 {
		if matrix[x][y] {
			return false
		}
	}
	// 检查右上对角线是否有皇后
	for x, y := row-1, col+1; x >= 0 && y < len(matrix[0]); x, y = x-1, y+1 {
		if matrix[x][y] {
			return false
		}
	}
	return true
}

/*
按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。

示例 1：
输入：n = 4
输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
解释：如上图所示，4 皇后问题存在两个不同的解法。

示例 2：
输入：n = 1
输出：[["Q"]]

提示：
1 <= n <= 9
*/
