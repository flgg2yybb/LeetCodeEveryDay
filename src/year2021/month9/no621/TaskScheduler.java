package year2021.month9.no621;

import java.util.HashMap;
import java.util.Map;

public class TaskScheduler {
    public static void main(String[] args) {
        char[] tasks1 = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n1 = 2;
        char[] tasks2 = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n2 = 0;
        char[] tasks3 = {'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int n3 = 2;
        System.out.println(leastInterval(tasks1, n1));
        System.out.println(leastInterval(tasks2, n2));
        System.out.println(leastInterval(tasks3, n3));
    }

    public static int leastInterval(char[] tasks, int n) {
        // 桶思想
        // 由于 cpu 在 n 个单位时间内需要执行不同的任务或者待命，则假设有 n + 1 个桶
        // 轮流执行每个桶内的元素的任务， 桶1 -> 桶2 -> ... -> 桶n+1 -> 桶1
        // 则将重复任务放在同一个桶内，则代表 cpu 在每隔 n 个单位时间就执行一次重复任务
        // 由此，只需计算出 cpu 执行 tasks 时所需的总共的待命时间
        // 即可计算出最短时间
        // 存在待命时间的条件为 ==> 当且仅当 tasks 任务数小于 (n+1) * (m-1) + k
        // 其中，m 为最大的重复任务数， k 为重复次数为 m 的任务数量
        // 故最短时间为 ==> max{tasks.length, (n+1) * (m-1) + k}
        // 如下图所示，（* 表示待命）
        // 1: A  A  A  A  A
        // 2: B  B  B  B  B
        // 3: C  C  C  C  *
        // 4: D  D  D  D  *
        // 5: E  E  E  *  *
        Map<Character, Integer> taskCount = new HashMap<>();
        int m = 0;
        for (char task : tasks) {
            taskCount.merge(task, 1, Integer::sum);
            m = Math.max(m, taskCount.get(task));
        }
        final int MAX = m;
        int k = (int) taskCount.values().stream().filter(v -> v == MAX).count();
        return Math.max(tasks.length, (n + 1) * (m - 1) + k);
    }
}

/*
* 给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。

然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。

你需要计算完成所有任务所需要的 最短时间 。

 

示例 1：

输入：tasks = ['A','A','A','B','B','B'], n = 2
输出：8
解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B
     在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。 
示例 2：

输入：tasks = ['A','A','A','B','B','B'], n = 0
输出：6
解释：在这种情况下，任何大小为 6 的排列都可以满足要求，因为 n = 0
['A','A','A','B','B','B']
['A','B','A','B','A','B']
['B','B','B','A','A','A']
...
诸如此类
示例 3：

输入：tasks = ['A','A','A','A','A','A','B','C','D','E','F','G'], n = 2
输出：16
解释：一种可能的解决方案是：
     A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> (待命) -> (待命) -> A -> (待命) -> (待命) -> A
 

提示：

1 <= task.length <= 104
tasks[i] 是大写英文字母
n 的取值范围为 [0, 100]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/task-scheduler
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
