package main

func main() {
	grid1 := [][]int{
		{2, 1, 1},
		{1, 1, 0},
		{0, 1, 1},
	}
	println(orangesRotting(grid1)) // 4
	grid2 := [][]int{
		{2, 1, 1},
		{0, 1, 1},
		{1, 0, 1},
	}
	println(orangesRotting(grid2)) // -1
	grid3 := [][]int{
		{0, 2},
	}
	println(orangesRotting(grid3)) // 0
}

// bfs, times: O(m*n), space: O(m*n)
// 思路：一开始将所有腐烂的橘子入队，然后每分钟将队列中的橘子腐烂周围的新鲜橘子，直到队列为空
func orangesRotting(grid [][]int) int {
	rotten := make([][]int, 0)
	freshCount := 0
	for i := 0; i < len(grid); i++ {
		for j := 0; j < len(grid[0]); j++ {
			cell := grid[i][j]
			if cell == 2 {
				rotten = append(rotten, []int{i, j})
			}
			if cell == 1 {
				freshCount++
			}
		}
	}
	minute := 0
	for len(rotten) > 0 {
		if freshCount == 0 {
			break
		}
		nextRotten := make([][]int, 0)
		// 开始腐烂
		for _, coord := range rotten {
			x, y := coord[0], coord[1]
			nexts := [][]int{{x - 1, y}, {x + 1, y}, {x, y - 1}, {x, y + 1}}
			for _, next := range nexts {
				nextX, nextY := next[0], next[1]
				if nextX < 0 || nextX >= len(grid) ||
					nextY < 0 || nextY >= len(grid[0]) ||
					grid[nextX][nextY] != 1 {
					continue
				}
				grid[nextX][nextY] = 2
				nextRotten = append(nextRotten, []int{nextX, nextY})
				freshCount--
			}
		}
		minute++            // 时间加 1 分钟
		rotten = nextRotten // 下一分钟扩散腐烂的橘子

	}
	if freshCount > 0 {
		return -1
	}
	return minute
}

/*
 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
值 0 代表空单元格；
值 1 代表新鲜橘子；
值 2 代表腐烂的橘子。
每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。

示例 1：
输入：grid = [[2,1,1],[1,1,0],[0,1,1]]
输出：4

示例 2：
输入：grid = [[2,1,1],[0,1,1],[1,0,1]]
输出：-1
解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个方向上。

示例 3：
输入：grid = [[0,2]]
输出：0
解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。

提示：
m == grid.length
n == grid[i].length
1 <= m, n <= 10
grid[i][j] 仅为 0、1 或 2
*/
