package year2021.month6.no547;

import java.util.stream.IntStream;

public class NumberOfProvinces {

    public static void main(String[] args) {
        int[][] isConnected1 = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int[][] isConnected2 = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        int[][] isConnected3 = {{1, 0, 0, 1}, {0, 1, 1, 0}, {0, 1, 1, 1}, {1, 0, 1, 1}};
        System.out.println(findCircleNum1(isConnected1));
        System.out.println(findCircleNum1(isConnected2));
        System.out.println(findCircleNum1(isConnected3));
    }

    private static int findCircleNum1(int[][] isConnected) {
        //DFS
        int length = isConnected.length;
        boolean[] visited = new boolean[length];
        int count = 0;
        for (int node = 0; node < length; node++) {
            if (!visited[node]) {
                dfs(isConnected, visited, node);
                count++;
            }
        }
        return count;
    }

    private static void dfs(int[][] isConnected, boolean[] visited, int node) {
        visited[node] = true;
        for (int nextNode = 0; nextNode < isConnected[node].length; nextNode++) {
            if (isConnected[node][nextNode] == 1 && !visited[nextNode]) {
                dfs(isConnected, visited, nextNode);
            }
        }
    }

    public static int findCircleNum(int[][] isConnected) {
        int length = isConnected.length;
        UnionFind unionFind = new UnionFind(length);
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (isConnected[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.getCount();
    }

}

class UnionFind {

    private final int[] parent;
    private final int[] rank;
    private int count;

    public UnionFind(int length) {
        this.parent = new int[length];
        IntStream.range(0, length).boxed().forEach(index -> parent[index] = index);
        this.rank = new int[length];
        this.count = length;
    }

    private int find(int x) {
        return parent[x] == x ? x : find(parent[x]);
    }

    public void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        if (xRoot == yRoot) {
            return;
        }
        if (rank[xRoot] > rank[yRoot]) {
            parent[yRoot] = xRoot;
        } else if (rank[xRoot] < rank[yRoot]) {
            parent[xRoot] = yRoot;
        } else {
            parent[yRoot] = xRoot;
            rank[xRoot]++;
        }
        count--;
    }

    public int getCount() {
        return count;
    }
}

/*
* 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。

省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。

给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。

返回矩阵中 省份 的数量。

 

示例 1：


输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
输出：2
示例 2：


输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
输出：3
 

提示：

1 <= n <= 200
n == isConnected.length
n == isConnected[i].length
isConnected[i][j] 为 1 或 0
isConnected[i][i] == 1
isConnected[i][j] == isConnected[j][i]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/number-of-provinces
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
