package main

import "fmt"

func main() {
	grid1 := [][]byte{
		{'1', '1', '1', '1', '0'},
		{'1', '1', '0', '1', '0'},
		{'1', '1', '0', '0', '0'},
		{'0', '0', '0', '0', '0'},
	}
	fmt.Println(numIslands(grid1)) // 1
	grid2 := [][]byte{
		{'1', '1', '0', '0', '0'},
		{'1', '1', '0', '0', '0'},
		{'0', '0', '1', '0', '0'},
		{'0', '0', '0', '1', '1'},
	}
	fmt.Println(numIslands(grid2)) // 3
}

// bfs, times: O(m*n), space: O(m*n)
// 思路：遍历网格，每次碰到岛屿且没有访问过，就进行深度优先搜索，将与当前岛屿相连的所有岛屿标记为已访问
// 优化，不使用额外的访问数组，直接在原数组上修改：0 表示水，1 表示陆地（未访问），2 表示陆地（已访问）
func numIslands(grid [][]byte) int {
	bfs := func(i, j int) {
		queue := make([][]int, 0)
		grid[i][j] = '2' // 入队时就标记为访问，避免重复入队
		queue = append(queue, []int{i, j})
		for len(queue) > 0 {
			nextQueue := make([][]int, 0)
			for _, coord := range queue { // coord: 坐标
				x, y := coord[0], coord[1]
				nexts := [][]int{{x - 1, y}, {x + 1, y}, {x, y - 1}, {x, y + 1}}
				for _, next := range nexts {
					nextX, nextY := next[0], next[1]
					if nextX < 0 || nextX >= len(grid) ||
						nextY < 0 || nextY >= len(grid[0]) ||
						grid[nextX][nextY] != '1' {
						continue
					}
					// 入队时就标记为访问，避免重复入队
					grid[nextX][nextY] = '2'
					nextQueue = append(nextQueue, []int{nextX, nextY})
				}
			}
			queue = nextQueue
		}
	}
	count := 0
	for i := 0; i < len(grid); i++ {
		for j := 0; j < len(grid[0]); j++ {
			if grid[i][j] == '1' {
				count++
				bfs(i, j)
			}
		}
	}
	return count
}

// dfs, times: O(m*n), space: O(1)
// 优化，不使用额外的访问数组，直接在原数组上修改：0 表示水，1 表示陆地（未访问），2 表示陆地（已访问）
func numIslands2(grid [][]byte) int {
	count := 0
	for i := 0; i < len(grid); i++ {
		for j := 0; j < len(grid[0]); j++ {
			if grid[i][j] == '1' {
				count++
				dfs(grid, i, j)
			}
		}
	}
	return count
}

func dfs(grid [][]byte, i, j int) {
	if i < 0 || i >= len(grid) || j < 0 || j >= len(grid[0]) || grid[i][j] != '1' {
		return
	}
	grid[i][j] = '2'
	dfs(grid, i-1, j)
	dfs(grid, i+1, j)
	dfs(grid, i, j-1)
	dfs(grid, i, j+1)
}

// dfs, times: O(m*n), space: O(m*n)
// 思路：遍历网格，每次碰到岛屿且没有访问过，就进行深度优先搜索，将与当前岛屿相连的所有岛屿标记为已访问
func numIslands1(grid [][]byte) int {
	access := make([][]bool, len(grid))
	for i := range access {
		access[i] = make([]bool, len(grid[0]))
	}
	count := 0
	for i := 0; i < len(grid); i++ {
		for j := 0; j < len(grid[0]); j++ {
			if grid[i][j] == '1' && !access[i][j] {
				count++
				dfs1(grid, access, i, j)
			}
		}
	}
	return count
}

func dfs1(grid [][]byte, access [][]bool, i, j int) {
	if i < 0 || i >= len(grid) || j < 0 || j >= len(grid[0]) || grid[i][j] != '1' || access[i][j] {
		return
	}
	access[i][j] = true
	dfs1(grid, access, i-1, j)
	dfs1(grid, access, i+1, j)
	dfs1(grid, access, i, j-1)
	dfs1(grid, access, i, j+1)
}

/*
   给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
   岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
   此外，你可以假设该网格的四条边均被水包围。

   示例 1：
   输入：grid = [
     ["1","1","1","1","0"],
     ["1","1","0","1","0"],
     ["1","1","0","0","0"],
     ["0","0","0","0","0"]
   ]
   输出：1

   示例 2：
   输入：grid = [
     ["1","1","0","0","0"],
     ["1","1","0","0","0"],
     ["0","0","1","0","0"],
     ["0","0","0","1","1"]
   ]
   输出：3

   提示：
   m == grid.length
   n == grid[i].length
   1 <= m, n <= 300
   grid[i][j] 的值为 '0' 或 '1'
*/
