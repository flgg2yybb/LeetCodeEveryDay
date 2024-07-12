package year2021.month9.no207;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CourseSchedule {

    public static void main(String[] args) {
        int numCourses1 = 2;
        int[][] prerequisites1 = {{1, 0}};
        int numCourses2 = 2;
        int[][] prerequisites2 = {{1, 0}, {0, 1}};
        System.out.println(canFinish(numCourses1, prerequisites1));
        System.out.println(canFinish(numCourses2, prerequisites2));
    }

    private static boolean canFinish(int numCourses, int[][] prerequisites) {
        // DFS，如果一个有向图能生成拓扑排序，则等价于有向图无环
        // 对每个节点进行 DFS，若所有节点都不在环内，则可以生成拓扑排序
        boolean[][] adjacentMatrix = new boolean[numCourses][numCourses]; // 邻接矩阵
        for (int[] prerequisite : prerequisites) {
            int from = prerequisite[1];
            int to = prerequisite[0];
            adjacentMatrix[from][to] = true;
        }
        int[] visited = new int[numCourses]; // -1: 已访问，0:未访问，1:正在访问
        for (int i = 0; i < numCourses; i++) {
            if (dfs(adjacentMatrix, visited, i)) {
                return false;
            }
        }
        return true;
    }

    private static boolean dfs(boolean[][] adjacentMatrix, int[] visited, int pos) {
        if (visited[pos] == -1) {
            return false;
        }
        if (visited[pos] == 1) {
            return true;
        }
        visited[pos] = 1;
        for (int j = 0; j < adjacentMatrix[pos].length; j++) {
            if (adjacentMatrix[pos][j]) {
                if (dfs(adjacentMatrix, visited, j)) {
                    return true;
                }
            }
        }
        visited[pos] = -1;
        return false;
    }


    public static boolean canFinish1(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adjacencyTable = new HashMap<>(numCourses); // 邻接表
        for (int i = 0; i < numCourses; i++) {
            adjacencyTable.put(i, new LinkedList<>());
        }
        int[] inDegreeTable = new int[numCourses];  // 入度表
        for (int[] prerequisite : prerequisites) {
            int from = prerequisite[1];
            int to = prerequisite[0];
            inDegreeTable[to]++;
            adjacencyTable.get(from).add(to);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegreeTable[i] == 0) {
                queue.offer(i);
            }
        }
        int accessedNodeCount = 0;
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            accessedNodeCount++;
            List<Integer> nextNodes = adjacencyTable.get(node);
            nextNodes.forEach(next -> {
                inDegreeTable[next]--;
                if (inDegreeTable[next] == 0) {
                    queue.offer(next);
                }
            });
        }
        return accessedNodeCount == numCourses;
    }

}

/*
* 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。

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

1 <= numCourses <= 105
0 <= prerequisites.length <= 5000
prerequisites[i].length == 2
0 <= ai, bi < numCourses
prerequisites[i] 中的所有课程对 互不相同

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/course-schedule
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
