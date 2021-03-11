package year2021.month3.no621;

public class TaskScheduler {
    public static void main(String[] args) {
        char[] tasks1 = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n1 = 2;
        char[] tasks2 = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n2 = 0;
        char[] tasks3 = {'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int n3 = 2;
        System.out.println(leastInterval(tasks1, n1));  //8
        System.out.println(leastInterval(tasks2, n2));  //6
        System.out.println(leastInterval(tasks3, n3));  //16
    }

    public static int leastInterval(char[] tasks, int n) {
        /*桶思想
         * 建立大小为 n + 1 的桶子，个数等于同类型任务中数量最多的那个，如
         * 等待时间 n = 2，A的任务最多，数量为 6，则我们建立 6个桶子，
         * 每个容量为 3，把每一个桶子看成是每一轮任务，如下
         *
         *   *桶 1： A -> 冷却 -> 冷却
         *   *桶 2： A -> 冷却 -> 冷却
         *   *桶 3： A -> 冷却 -> 冷却
         *   *桶 4： A -> 冷却 -> 冷却
         *   *桶 5： A -> 冷却 -> 冷却
         *   *桶 6： A -> 冷却 -> 冷却
         *
         * 由于A的任务数量最多，则剩余任务的数量最多与A相同，
         * 则剩余任务必可以分配在每一个桶子上，即不会存在
         * 一个桶子装了两个相同的任务
         *
         * （1），若当前总任务数量超过所有桶的容量和，则不需要冷却时间
         * 因为每个桶子都不会存在两个相同的任务，此时时间为任务个数，如下
         *
         *   *桶 1： A -> B -> C -> F
         *   *桶 2： A -> B -> C -> F
         *   *桶 3： A -> B -> C -> F
         *   *桶 4： A -> B -> C
         *   *桶 5： A -> B -> D
         *   *桶 6： A -> D -> F
         *
         * （2），若存在冷却时间，此时最短任务时间计算 = （桶个数 - 1） * （n + 1） + 任务数最多的任务种类数量
         *  如下图所示                           =  （6 - 1） * 3 + 2  = 17
         *
         *   *桶 1： A -> B -> C
         *   *桶 2： A -> B -> C
         *   *桶 3： A -> B -> C
         *   *桶 4： A -> B -> 冷却
         *   *桶 5： A -> B -> 冷却
         *   *桶 6： A -> B -> 无
         * */
        int[] count = new int[26];
        int maxTaskCount = 0;
        for (char task : tasks) {
            int index = task - 'A';
            count[index]++;
            maxTaskCount = Math.max(maxTaskCount, count[index]);
        }
        int maxTask = 0;
        for (int num : count) {
            if (num == maxTaskCount) {
                maxTask++;
            }
        }
        return Math.max(tasks.length, (maxTaskCount - 1) * (n + 1) + maxTask);
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

1 <= task.length <= 10^4
tasks[i] 是大写英文字母
n 的取值范围为 [0, 100]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/task-scheduler
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
