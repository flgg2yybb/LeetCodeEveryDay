package year2021.month7.no210;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CourseScheduleII {

    private static int[] path = new int[0];
    private static boolean valid = true;
    private static int index = 0;

    public static void main(String[] args) {
        int numCourses1 = 2;
        int[][] prerequisites1 = {{1, 0}};
        int numCourses2 = 4;
        int[][] prerequisites2 = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        int numCourses3 = 3;
        int[][] prerequisites3 = {{1, 0}, {0, 1}, {0, 2}};
        System.out.println(Arrays.toString(findOrder1(numCourses1, prerequisites1)));
        System.out.println(Arrays.toString(findOrder1(numCourses2, prerequisites2)));
        System.out.println(Arrays.toString(findOrder1(numCourses3, prerequisites3)));
    }

    private static int[] findOrder1(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adjacentMap = new HashMap<>();  // 邻接表
        for (int i = 0; i < numCourses; i++) {
            adjacentMap.put(i, new ArrayList<>(numCourses));
        }
        for (int[] prerequisite : prerequisites) {
            int from = prerequisite[1];
            int to = prerequisite[0];
            adjacentMap.get(from).add(to);
        }
        //dfs, -1 未访问；0 正在访问；1 已访问
        int[] visited = new int[numCourses];
        Arrays.fill(visited, -1);
        path = new int[numCourses];
        index = numCourses - 1;
        valid = true;
        for (int node = 0; node < numCourses && valid; node++) {
            dfs(adjacentMap, visited, node);
        }
        return valid ? path : new int[0];
    }

    private static void dfs(Map<Integer, List<Integer>> adjacentMap, int[] visited, int node) {
        if (visited[node] == 1) {   // 已访问
            return;
        }
        if (visited[node] == 0) {   // 正在访问，有环
            valid = false;
            return;
        }
        visited[node] = 0;
        List<Integer> nextNodes = adjacentMap.get(node);
        for (int nextNode : nextNodes) {
            if (!valid) {
                return;
            }
            dfs(adjacentMap, visited, nextNode);
        }
        path[index] = node;
        index--;
        visited[node] = 1;
    }

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses];  // 入度表
        Map<Integer, List<Integer>> adjacentMap = new HashMap<>();  // 邻接表
        for (int i = 0; i < numCourses; i++) {
            adjacentMap.put(i, new ArrayList<>(numCourses));
        }
        for (int[] prerequisite : prerequisites) {
            int from = prerequisite[1];
            int to = prerequisite[0];
            indegrees[to]++;
            adjacentMap.get(from).add(to);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int node = 0; node < indegrees.length; node++) {
            if (indegrees[node] == 0) {
                queue.offer(node);
            }
        }
        List<Integer> ans = new LinkedList<>();
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            ans.add(node);
            List<Integer> nextNodes = adjacentMap.get(node);
            nextNodes.forEach(nextNode -> {
                indegrees[nextNode]--;
                if (indegrees[nextNode] == 0) {
                    queue.offer(nextNode);
                }
            });
        }
        return ans.size() == numCourses ? ans.stream().mapToInt(Integer::intValue).toArray() : new int[0];
    }

}

/*
* 现在你总共有 n 门课需要选，记为 0 到 n-1。

在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]

给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。

可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。

示例 1:

输入: 2, [[1,0]] 
输出: [0,1]
解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
示例 2:

输入: 4, [[1,0],[2,0],[3,1],[3,2]]
输出: [0,1,2,3] or [0,2,1,3]
解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
     因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
说明:

输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
你可以假定输入的先决条件中没有重复的边。
提示:

这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
拓扑排序也可以通过 BFS 完成。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/course-schedule-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
