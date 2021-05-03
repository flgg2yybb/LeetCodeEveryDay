package year2021.month5.no297;

import java.util.LinkedList;
import java.util.Queue;

public class SerializeAndDeserializeBinaryTree {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1,
                new TreeNode(2),
                new TreeNode(3,
                        new TreeNode(4),
                        new TreeNode(5)));
        TreeNode root2 = null;
        TreeNode root3 = new TreeNode(1);
        TreeNode root4 = new TreeNode(1,
                new TreeNode(2),
                null);

        Codec ser = new Codec();
        Codec deser = new Codec();
        disp(deser.deserialize(ser.serialize(root1)));
        disp(deser.deserialize(ser.serialize(root2)));
        disp(deser.deserialize(ser.serialize(root3)));
        disp(deser.deserialize(ser.serialize(root4)));
    }

    private static void disp(TreeNode root) {
        dfs(root);
        System.out.println();
    }

    private static void dfs(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            dfs(root.left);
            dfs(root.right);
        }
    }
}

class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder ans = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll == null) {
                    ans.append("null,");
                    continue;
                }
                ans.append(poll.val).append(",");
                queue.offer(poll.left);
                queue.offer(poll.right);
            }
        }
        ans.deleteCharAt(ans.length() - 1);
        return ans.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodes = data.split(",");
        String rootStr = nodes[0];
        TreeNode root = parse(rootStr);
        if (root == null) {
            return root;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        for (int i = 1; i < nodes.length; i = i + 2) {
            TreeNode node = queue.poll();
            TreeNode left = parse(nodes[i]);
            TreeNode right = parse(nodes[i + 1]);
            node.left = left;
            node.right = right;
            if (left != null) {
                queue.offer(left);
            }
            if (right != null) {
                queue.offer(right);
            }
        }
        return root;
    }

    private TreeNode parse(String nodeStr) {
        if ("null".equals(nodeStr)) {
            return null;
        }
        return new TreeNode(Integer.parseInt(nodeStr));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/*
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。

请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。

提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。

 

示例 1：


输入：root = [1,2,3,null,null,4,5]
输出：[1,2,3,null,null,4,5]
示例 2：

输入：root = []
输出：[]
示例 3：

输入：root = [1]
输出：[1]
示例 4：

输入：root = [1,2]
输出：[1,2]
 

提示：

树中结点数在范围 [0, 104] 内
-1000 <= Node.val <= 1000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
