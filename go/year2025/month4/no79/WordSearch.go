package main

func main() {
	board := [][]byte{
		{'A', 'B', 'C', 'E'},
		{'S', 'F', 'C', 'S'},
		{'A', 'D', 'E', 'E'},
	}
	word1 := "ABCCED"
	word2 := "SEE"
	word3 := "ABCB"
	println(exist(board, word1)) // true
	println(exist(board, word2)) // true
	println(exist(board, word3)) // false

}

// dfs
func exist(board [][]byte, word string) bool {
	visited := make([][]bool, len(board)) // 0: 为访问，1: 已访问
	for i := 0; i < len(visited); i++ {
		visited[i] = make([]bool, len(board[0]))
	}
	for i := 0; i < len(board); i++ {
		for j := 0; j < len(board[0]); j++ {
			if board[i][j] == word[0] && dfs(board, visited, i, j, word, 0) {
				return true
			}
		}
	}
	return false
}

// dfs - backtrack, 保证 i，j 有效
func dfs(board [][]byte, visited [][]bool, i int, j int, word string, p int) bool {
	if p >= len(word)-1 {
		return true
	}
	visited[i][j] = true
	nexts := [][]int{{i + 1, j}, {i - 1, j}, {i, j + 1}, {i, j - 1}}
	for _, next := range nexts {
		x, y := next[0], next[1]
		if x >= 0 && x < len(board) && y >= 0 && y < len(board[0]) && !visited[x][y] && board[x][y] == word[p+1] {
			if dfs(board, visited, x, y, word, p+1) {
				return true
			}
		}
	}
	visited[i][j] = false
	return false
}

/*
给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。
如果 word 存在于网格中，返回 true ；否则，返回 false 。
单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
同一个单元格内的字母不允许被重复使用。

示例 1：
输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
输出：true

示例 2：
输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
输出：true

示例 3：
输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
输出：false


提示：
m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board 和 word 仅由大小写英文字母组成

进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？
*/
