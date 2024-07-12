package year2021.month2.no207;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class CourseSchedule {
    public static void main(String[] args) {
        int numCourses1 = 2;
        int[][] prerequisites1 = new int[][]{{1, 0}};
        int numCourses2 = 2;
        int[][] prerequisites2 = new int[][]{{1, 0}, {0, 1}};
        System.out.println(canFinish1(numCourses1, prerequisites1));
        System.out.println(canFinish1(numCourses2, prerequisites2));
    }

    private static boolean canFinish1(int numCourses, int[][] prerequisites) {
        /* Topological Sort (拓扑排序) + DFS,
         * time is O(E + V), space is O(E + V), E为有向图边数，V为节点数
         * 数据结构：
         * adjacencyList为邻接表，索引为源节点，值为源节点指向的目标节点的集合
         * 算法：
         * 对于每一个节点，赋予其三种状态
         *      * 未访问：-1
         *      * 正在访问：0
         *      * 已访问： 1
         * dfs遍历所有未访问的节点，对于每个访问的节点，在访问之初将其标记为正在访问，
         * 并dfs遍历当前节点可以访问的所有候选节点，若候选节点状态为：
         *      *未访问，则对其进行dfs遍历
         *      *正在访问，则表示存在环，则return false
         *      *已访问，则跳过
         * 若dfs可以正常访问所有节点，则代表无环，return true
         * */
        List<List<Integer>> adjacencyList = new ArrayList<>();  //邻接表
        int[] flags = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {                  //初始化邻接表以及flags数组
            adjacencyList.add(new ArrayList<>());
            flags[i] = -1;
        }
        for (int[] prerequisite : prerequisites) {        //更新邻接表
            int from = prerequisite[1];
            int to = prerequisite[0];
            adjacencyList.get(from).add(to);
        }
        for (int i = 0; i < numCourses; i++) {                  //dfs访问每个节点
            if (dfs(adjacencyList, flags, i)) {                //若dfs返回false，则代表有环
                return false;
            }
        }
        return true;
    }

    private static boolean dfs(List<List<Integer>> adjacencyList, int[] flags, int node) {
        if (flags[node] == 1) {     //已访问
            return false;
        }
        if (flags[node] == 0) {     //正在访问
            return true;
        }
//        将当前节点标记为正在访问
        flags[node] = 0;
        List<Integer> nextNodes = adjacencyList.get(node);
        for (int nextNode : nextNodes) {
            if (dfs(adjacencyList, flags, nextNode)) {
                return true;
            }
        }
//        将当前节点标记为已访问
        flags[node] = 1;
        return false;
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        /* Topological Sort (拓扑排序) + BFS,
         * time is O(E + V), space is O(E + V), E为有向图边数，V为节点数
         * 思路：
         * 所有课程是否能学完，即判断有向图是否无环，若无环，则该有向图可以生成拓扑排序，则可以学完，
         * 若有环，则无法生成拓扑排序，则不可以学完
         * 数据结构：
         * adjacencyList为邻接表，索引为源节点，值为源节点指向的目标节点的集合
         * inDegree为入度表，索引为节点，值为该节点的入度（有多少个前置节点指向当前节点）
         * 算法：
         * 1.初始化邻接表以及入度表，使用队列来表示拓扑排序的访问顺序，首先将入度表中入度为0的节点加入队列
         * 2.讲队列中的元素依次出队，并统计出队次数，直到队列为空，并在每次出队时执行以下操作
         *      *将当前出队节点所有可以访问的节点的入度减一，若其中某个节点的入度减一后为0，
         *      *将其加入队列
         * 3.若出队次数等于节点数，代表可以生成所有节点的拓扑排序，则课程可以学完
         * */
        List<List<Integer>> adjacencyList = new ArrayList<>();  //邻接表
        for (int i = 0; i < numCourses; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        int[] inDegree = new int[numCourses];                   //入度表
        for (int[] prerequisite : prerequisites) {        //初始化邻接表和入度表
            int from = prerequisite[1];
            int to = prerequisite[0];
            inDegree[to]++;
            adjacencyList.get(from).add(to);
        }
        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offerLast(i);
            }
        }
//        BFS
        int count = 0;
        while (!queue.isEmpty()) {
            Integer poll = queue.pollFirst();
            count++;
            List<Integer> nextNodes = adjacencyList.get(poll);
//            更新当前出队节点可以访问的所有节点的入度，若入度更新后为0，则将其入队
            for (int nextNode : nextNodes) {
                inDegree[nextNode]--;
                if (inDegree[nextNode] == 0) {
                    queue.offerLast(nextNode);
                }
            }
        }
        return count == numCourses;
    }
}

/*
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。

在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。

例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
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
