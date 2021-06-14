package year2021.month6.no149;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class MaxPointsOnLine {

    public static void main(String[] args) {
        int[][] points1 = {{1, 1}, {2, 2}, {3, 3}};
        int[][] points2 = {{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}};
        int[][] points3 = {{0, 0}};
        int[][] points4 = {{-6, -1}, {3, 1}, {12, 3}};  // double 计算斜率时存在精度问题
        System.out.println(maxPoints(points1));
        System.out.println(maxPoints(points2));
        System.out.println(maxPoints(points3));
        System.out.println(maxPoints(points4));
    }

    public static int maxPoints(int[][] points) {
        Map<Line, Integer> lineDuplicateCount = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            int[] startPoint = points[i];
            for (int j = i + 1; j < points.length; j++) {
                int[] endPoint = points[j];
                Line line = calculateLine(startPoint, endPoint);
                lineDuplicateCount.put(line, lineDuplicateCount.getOrDefault(line, 0) + 2);
            }
        }
        Line line = lineDuplicateCount.keySet().stream().max(Comparator.comparingInt(lineDuplicateCount::get))
                .orElse(null);
        if (line == null) {
            return 1;
        }
        return (int) Stream.of(points).filter(point -> isInLine(line, point)).count();
    }

    private static boolean isInLine(Line line, int[] point) {
        if (line.a == Double.MAX_VALUE) {
            return line.b == point[0];
        }
        return point[1] == point[0] * line.a + line.b;
    }

    private static Line calculateLine(int[] startPoint, int[] endPoint) {
        int x1 = startPoint[0];
        int y1 = startPoint[1];
        int x2 = endPoint[0];
        int y2 = endPoint[1];
        if (x1 == x2) {
            return new Line(Double.MAX_VALUE, x1);
        }
        double a = (double) (y1 - y2) / (x1 - x2);
        double b = (double) y1 - a * x1;
        return new Line(a, b);
    }
}

class Line {

    double a;
    double b;

    public Line(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Line line = (Line) o;
        return Double.compare(line.a, a) == 0 && Double.compare(line.b, b) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

}

/*
* 给定一个二维平面，平面上有 n 个点，求最多有多少个点在同一条直线上。

示例 1:

输入: [[1,1],[2,2],[3,3]]
输出: 3
解释:
^
|
|        o
|     o
|  o  
+------------->
0  1  2  3  4
示例 2:

输入: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
输出: 4
解释:
^
|
|  o
|     o        o
|        o
|  o        o
+------------------->
0  1  2  3  4  5  6
通过次数23,972提交次数93,369

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/max-points-on-a-line
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
