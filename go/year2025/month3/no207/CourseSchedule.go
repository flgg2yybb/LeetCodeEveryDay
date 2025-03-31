package main

func main() {
	numCourses1 := 2
	prerequisites1 := [][]int{{1, 0}}
	println(canFinish(numCourses1, prerequisites1)) //true
	numCourses2 := 2
	prerequisites2 := [][]int{{1, 0}, {0, 1}}
	println(canFinish(numCourses2, prerequisites2)) //false
}

// BFS，times: O(n+e), space: O(n+e)
// 思路：邻接表遍历，每次找到入度为 0 的节点，将其加入队列，然后将其指向的节点的入度减 1，直到队列为空，如果遍历完所有节点，则无环
func canFinish(numCourses int, prerequisites [][]int) bool {
	// 构建邻接表 from -> to list
	adjacentTables := make([][]int, numCourses)
	for i := 0; i < numCourses; i++ { // O(n)
		adjacentTables[i] = make([]int, 0)
	}
	// 构建入度表	to -> inDegree
	inDegrees := make([]int, numCourses)
	for _, prerequisite := range prerequisites { // O(e)
		from, to := prerequisite[1], prerequisite[0]
		adjacentTables[from] = append(adjacentTables[from], to)
		inDegrees[to]++
	}
	// 入度为 0 的节点加入队列
	queue := make([]int, 0)
	for i, degree := range inDegrees { // O(n)
		if degree == 0 {
			queue = append(queue, i)
		}
	}
	// visited 记录访问过的节点数
	visited := 0
	// BFS, 每个点每条边都只会访问一次，总复杂度 O(n+e)
	for len(queue) > 0 {
		nextQueue := make([]int, 0)
		for _, from := range queue {
			visited++ // 访问 from，节点数加 1，需要保证 queue 中的节点都是未访问的
			for _, to := range adjacentTables[from] {
				inDegrees[to]--         // 当前 from 已访问，因此 to 的入度都可以减 1
				if inDegrees[to] == 0 { // 入度为 0 的节点加入队列，这个条件可以保证 to 是未访问过的
					nextQueue = append(nextQueue, to)
				}
			}
		}
		queue = nextQueue // 下一轮访问的节点
	}
	return visited == numCourses
}

// DFS，times: O(n+e), space: O(n^2), n 为节点数，e 为边数
// 思路：如果一个有向图能生成拓扑排序，则等价于有向图无环，对每个节点进行 DFS，若所有节点都不在环内，则可以生成拓扑排序
func canFinish1(numCourses int, prerequisites [][]int) bool {
	// 构建邻接矩阵
	adjacentMatrix := make([][]bool, numCourses) // o(n)
	for i := 0; i < numCourses; i++ {
		adjacentMatrix[i] = make([]bool, numCourses)
	}
	for _, prerequisite := range prerequisites { // o(e)
		from, to := prerequisite[1], prerequisite[0]
		adjacentMatrix[from][to] = true
	}
	// 记录节点状态：0 未访问，1 正在访问，2 已访问
	visited := make([]int, numCourses)
	// 总复杂度 o(n+e)
	for i := 0; i < numCourses; i++ { // o(n)，每个节点最多访问一次
		if hasLoop(adjacentMatrix, visited, i) { // o(e)，每条边最多访问一次
			return false
		}
	}
	return true
}

// DFS，判断是否有环
func hasLoop(adjacentMatrix [][]bool, visited []int, i int) bool {
	if visited[i] == 2 { // 已访问，就不在访问了，避免重复访问造成死循环
		return false
	}
	if visited[i] == 1 { // 正在访问，说明有环
		return true
	}
	visited[i] = 1
	for next, canAccess := range adjacentMatrix[i] {
		if canAccess && hasLoop(adjacentMatrix, visited, next) {
			return true
		}
	}
	visited[i] = 2
	return false
}

/*
 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。

示例 1：
输入：numCourses = 2, prerequisites = [[1,0]]
输出：true
解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。

示例 2：
输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
输出：false
解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。


提示：
1 <= numCourses <= 2000
0 <= prerequisites.length <= 5000
prerequisites[i].length == 2
0 <= ai, bi < numCourses
prerequisites[i] 中的所有课程对 互不相同
*/
