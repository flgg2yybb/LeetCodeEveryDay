package year2021.month10.no710;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomPickWithBlacklist {

    public static void main(String[] args) {
        Solution solution1 = new Solution(1, new int[]{});
        System.out.println(solution1.pick());
        System.out.println(solution1.pick());
        System.out.println(solution1.pick());
        Solution solution2 = new Solution(5, new int[]{1, 2, 3});
        System.out.println(solution2.pick());
        System.out.println(solution2.pick());
        System.out.println(solution2.pick());
    }

}

class Solution {

    /*
     * 算法：
     * 白名单个数： size = n - blacklist.length
     * 同时维护黑名单映射 blackListMappings，
     * 表示在 [0, size) 范围中，某一个黑名单数所对应的在 [size, n) 的白名单的数
     * 对于 pick 函数，我们只需要在 [0, size) 中取随机数
     * 然后判断：
     *   若随机数不在 blackListMappings 中，表明为白名单数，直接返回
     *   若随机数在 blackListMappings 中，返回 blackListMappings[rand] (对于的白名单数)
     * */

    private final int size;
    private final Map<Integer, Integer> blackListMappings = new HashMap<>();
    private final Random random = new Random();

    public Solution(int n, int[] blacklist) {
        size = n - blacklist.length;
        Set<Integer> blacklistSet = IntStream.of(blacklist).boxed().collect(Collectors.toSet());
        int last = n - 1;
        for (int blackNum : blacklistSet) {
            // 在 [size, n) 范围的黑名单数可以忽略，pick 取模时不会选中
            if (blackNum >= size) {
                continue;
            }
            // 从后往前，找到白名单数
            while (blacklistSet.contains(last)) {
                last--;
            }
            // 将该处于 [0, size) 的黑名单数，与 [size, n) 的一个白名单数进行映射
            blackListMappings.put(blackNum, last);
            // 更新 last （因需要保证等概率，一个白名单数只能与一个黑名单数进行映射）
            last--;
        }
    }

    public int pick() {
        int rand = random.nextInt(size);
        return blackListMappings.getOrDefault(rand, rand);
    }
}

/*
* 给定一个包含 [0，n) 中不重复整数的黑名单 blacklist ，写一个函数从 [0, n) 中返回一个不在 blacklist 中的随机整数。

对它进行优化使其尽量少调用系统方法 Math.random() 。

提示:

1 <= n <= 1000000000
0 <= blacklist.length < min(100000, N)
[0, n) 不包含 n ，详细参见 interval notation 。
示例 1：

输入：
["Solution","pick","pick","pick"]
[[1,[]],[],[],[]]
输出：[null,0,0,0]
示例 2：

输入：
["Solution","pick","pick","pick"]
[[2,[]],[],[],[]]
输出：[null,1,1,1]
示例 3：

输入：
["Solution","pick","pick","pick"]
[[3,[1]],[],[],[]]
输出：[null,0,0,2]
示例 4：

输入： 
["Solution","pick","pick","pick"]
[[4,[2]],[],[],[]]
输出：[null,1,3,1]
输入语法说明：

输入是两个列表：调用成员函数名和调用的参数。Solution的构造函数有两个参数，n 和黑名单 blacklist。pick 没有参数，输入参数是一个列表，即使参数为空，也会输入一个 [] 空列表。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/random-pick-with-blacklist
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
