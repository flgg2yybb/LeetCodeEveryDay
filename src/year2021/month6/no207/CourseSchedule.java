package year2021.month6.no207;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule {

    public static void main(String[] args) {
        int numCourses1 = 2;
        int[][] prerequisites1 = {{1, 0}};
        int numCourses2 = 2;
        int[][] prerequisites2 = {{1, 0}, {0, 1}};
        System.out.println(canFinish1(numCourses1, prerequisites1));
        System.out.println(canFinish1(numCourses2, prerequisites2));
    }

    private static boolean canFinish1(int numCourses, int[][] prerequisites) {
        // 入度表 + 邻接表 + 拓扑排序 + BFS, time is O(N + E), space is O(N + E)
        int[] inDegrees = new int[numCourses];
        List<Integer>[] adjacence = new LinkedList[numCourses];
        for (int[] prerequisite : prerequisites) {
            int from = prerequisite[1];
            int to = prerequisite[0];
            inDegrees[to]++;
            if (adjacence[from] == null) {
                adjacence[from] = new LinkedList<>();
            }
            adjacence[from].add(to);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }
        int count = 0;
        // BFS
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            count++;
            List<Integer> next = adjacence[node];
            if (next != null) {
                next.forEach(item -> {
                    inDegrees[item]--;
                    if (inDegrees[item] == 0) {
                        queue.offer(item);
                    }
                });
            }
        }
        return count == numCourses;
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        // 入度表 + 邻接表 + 拓扑排序 + 暴力扫描入度表
        int[] inDegrees = new int[numCourses];
        List<Integer>[] adjacence = new LinkedList[numCourses];
        for (int[] prerequisite : prerequisites) {
            int from = prerequisite[1];
            int to = prerequisite[0];
            inDegrees[to]++;
            if (adjacence[from] == null) {
                adjacence[from] = new LinkedList<>();
            }
            adjacence[from].add(to);
        }
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < inDegrees.length; i++) {
                if (inDegrees[i] != 0) {
                    continue;
                }
                flag = true;
                List<Integer> next = adjacence[i];
                if (next != null) {
                    next.forEach(index -> inDegrees[index]--);
                }
                inDegrees[i] = -1; //标记为已访问
            }
        }
        return Arrays.stream(inDegrees).boxed().allMatch(num -> num < 0);
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
解释：总共有 2 门课程。学习课程 1 之前，你需要先完成 课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 

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
