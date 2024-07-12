package year2021.month7.jz10a;

import java.util.Arrays;

public class Fib {

    public static void main(String[] args) {
        int n1 = 2;
        int n2 = 5;
        int n3 = 100;
        System.out.println(fib3(n1));
        System.out.println(fib3(n2));
        System.out.println(fib3(n3));
    }

    private static int fib3(int n) {
        if (n <= 1) {
            return n;
        }
        int first = 0;
        int second = 1;
        int ans = -1;
        for (int i = 2; i <= n; i++) {
            ans = (first + second) % 1000000007;
            first = second;
            second = ans;
        }
        return ans;
    }

    private static int fib2(int n) {
        if (n <= 1) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }
        return dp[n];
    }

    private static int fib1(int n) {
        int[] cache = new int[n + 1];
        Arrays.fill(cache, -1);
        return memorySearch(n, cache);
    }

    private static int memorySearch(int n, int[] cache) {
        if (n <= 1) {
            return n;
        }
        if (cache[n] != -1) {
            return cache[n];
        }
        int a = memorySearch(n - 1, cache);
        int b = memorySearch(n - 2, cache);
        int ans = (a + b) % 1000000007;
        cache[n] = ans;
        return ans;
    }

    public static int fib(int n) {
        if (n <= 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

}

/*
* 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：

F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

 

示例 1：

输入：n = 2
输出：1
示例 2：

输入：n = 5
输出：5
 

提示：

0 <= n <= 100

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
