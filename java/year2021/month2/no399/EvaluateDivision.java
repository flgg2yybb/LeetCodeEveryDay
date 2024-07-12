package year2021.month2.no399;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EvaluateDivision {
    public static void main(String[] args) {
        List<List<String>> equations1 = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"));
        double[] values1 = new double[]{2.0, 3.0};
        List<List<String>> queries1 = Arrays.asList(Arrays.asList("a", "c"), Arrays.asList("b", "a"),
                Arrays.asList("a", "e"), Arrays.asList("a", "a"), Arrays.asList("x", "x"));
        List<List<String>> equations2 = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"), Arrays.asList("bc", "cd"));
        double[] values2 = new double[]{1.5, 2.5, 5.0};
        List<List<String>> queries2 = Arrays.asList(Arrays.asList("a", "c"), Arrays.asList("c", "b"),
                Arrays.asList("bc", "cd"), Arrays.asList("cd", "bc"));
        List<List<String>> equations3 = Collections.singletonList(Arrays.asList("a", "b"));
        double[] values3 = new double[]{0.5};
        List<List<String>> queries3 = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "a"),
                Arrays.asList("a", "c"), Arrays.asList("x", "y"));
        disp(calcEquation(equations1, values1, queries1));
        disp(calcEquation(equations2, values2, queries2));
        disp(calcEquation(equations3, values3, queries3));
    }

    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
//        map{
//          "a": {
//              "b": 2.0,
//              "c": 1.0
//          }
//        }
//        ==> a/b = 2.0, a/c = 1.0
        Map<String, Map<String, Double>> map = new HashMap<>();
        IntStream.range(0, equations.size()).forEach(index -> {
            List<String> equation = equations.get(index);
            map.putIfAbsent(equation.get(0), new HashMap<>());
            map.get(equation.get(0)).put(equation.get(1), values[index]);
            map.get(equation.get(0)).putIfAbsent(equation.get(0), 1.0);
//            倒数
            map.putIfAbsent(equation.get(1), new HashMap<>());
            map.get(equation.get(1)).put(equation.get(0), 1.0 / values[index]);
            map.get(equation.get(1)).put(equation.get(1), 1.0);
        });
        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
//            x / y = ?
            List<String> query = queries.get(i);
            String x = query.get(0);
            String y = query.get(1);
            Set<String> visited = new HashSet<>();
            result[i] = dfs(map, x, y, visited);
        }
        return result;
    }

    private static double dfs(Map<String, Map<String, Double>> map, String start, String end, Set<String> visited) {
        if (!map.containsKey(start)) {
            return -1.0;
        }
        if (visited.contains(start)) {
            return -1.0;
        }
        visited.add(start);
        Map<String, Double> subMap = map.get(start);
        if (subMap.containsKey(end)) {
            return subMap.get(end);
        }
        for (String next : subMap.keySet()) {
            double temp = dfs(map, next, end, visited);
            if (temp != -1.0) {
                return subMap.get(next) * temp;
            }
        }
        return -1.0;
    }

    private static void disp(double[] nums) {
        System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()));
    }
}

/*
* 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，
* 其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。
* 每个 Ai 或 Bi 是一个表示单个变量的字符串。

另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，
* 请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。

返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。
* 如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替代这个答案。

注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。

 

示例 1：

输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0],
* queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
解释：
条件：a / b = 2.0, b / c = 3.0
问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
示例 2：

输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0],
* queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
输出：[3.75000,0.40000,5.00000,0.20000]
示例 3：

输入：equations = [["a","b"]], values = [0.5],
* queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
输出：[0.50000,2.00000,-1.00000,-1.00000]
 

提示：

1 <= equations.length <= 20
equations[i].length == 2
1 <= Ai.length, Bi.length <= 5
values.length == equations.length
0.0 < values[i] <= 20.0
1 <= queries.length <= 20
queries[i].length == 2
1 <= Cj.length, Dj.length <= 5
Ai, Bi, Cj, Dj 由小写英文字母与数字组成

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/evaluate-division
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
