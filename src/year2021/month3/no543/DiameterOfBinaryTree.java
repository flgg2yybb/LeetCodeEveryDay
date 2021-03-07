package year2021.month3.no543;

public class DiameterOfBinaryTree {
    private static int nodeNum = 1;
    private static int pathLen = Integer.MIN_VALUE;

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1,
                new TreeNode(2, new TreeNode(4), new TreeNode(5)),
                new TreeNode(3));
        TreeNode root2 = new TreeNode(1);
        TreeNode root3 = new TreeNode(1,
                new TreeNode(2),
                null);
        TreeNode root4 = new TreeNode(1,
                new TreeNode(2),
                new TreeNode(3));
        TreeNode root5 = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4,
                                new TreeNode(6,
                                        new TreeNode(7), null),
                                null),
                        new TreeNode(5,
                                new TreeNode(8,
                                        new TreeNode(9), null),
                                null)),
                new TreeNode(3));
        System.out.println(diameterOfBinaryTree1(root1));    //3
        System.out.println(diameterOfBinaryTree1(root2));    //0
        System.out.println(diameterOfBinaryTree1(root3));    //1
        System.out.println(diameterOfBinaryTree1(root4));    //2
        System.out.println(diameterOfBinaryTree1(root5));    //6
    }

    private static int diameterOfBinaryTree1(TreeNode root) {
        nodeNum = 1;
        depth(root);
        return nodeNum - 1;     //直接为路径上的节点数减一
    }

    private static int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = depth(root.left);
        int right = depth(root.right);
        nodeNum = Math.max(nodeNum, left + right + 1);  //以当前root节点为连接点的路径的节点数
        return Math.max(left, right) + 1;
    }

    public static int diameterOfBinaryTree(TreeNode root) {
        pathLen = Integer.MIN_VALUE;
        dfsGetPathLen(root);
        return pathLen;
    }

    private static void dfsGetPathLen(TreeNode root) {
        if (root == null) {
            return;
        }
        int len = getRootPathLength(root);
        pathLen = Math.max(pathLen, len);
        dfsGetPathLen(root.left);
        dfsGetPathLen(root.right);
    }

    public static int getRootPathLength(TreeNode root) {
        int len = 0;
        len += getPathLength(root.left);
        len += getPathLength(root.right);
        return len;
    }

    private static int getPathLength(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getPathLength(root.left);
        int right = getPathLength(root.right);
        return 1 + Math.max(left, right);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/*
注意：两结点之间的路径长度是以它们之间边的数目表示。

给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。

示例 :
给定二叉树

          1
         / \
        2   3
       / \
      4   5
返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。

注意：两结点之间的路径长度是以它们之间边的数目表示。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/diameter-of-binary-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * */
