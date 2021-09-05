package year2021.month9.no297;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

public class SerializeAndDeserializeBinaryTree {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1,
                new TreeNode(2),
                new TreeNode(3, new TreeNode(4), new TreeNode(5)));
        TreeNode root2 = null;
        TreeNode root3 = new TreeNode(1);
        TreeNode root4 = new TreeNode(1, new TreeNode(2), null);
        Codec codec = new Codec();
        disp(codec.deserialize(codec.serialize(root1)));
        disp(codec.deserialize(codec.serialize(root2)));
        disp(codec.deserialize(codec.serialize(root3)));
        disp(codec.deserialize(codec.serialize(root4)));
    }

    private static void disp(TreeNode root) {
        dfsDisp(root);
        System.out.println();
    }

    private static void dfsDisp(TreeNode root) {
        if (root == null) {
            System.out.print("NULL ");
            return;
        }
        System.out.print(root.val + " ");
        dfsDisp(root.left);
        dfsDisp(root.right);
    }
}

class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringJoiner sj = new StringJoiner(",");
        dfsSerialize(root, sj);
        return sj.toString();
    }

    private void dfsSerialize(TreeNode root, StringJoiner sj) {
        if (root == null) {
            sj.add("NULL");
            return;
        }
        sj.add(Integer.toString(root.val));
        dfsSerialize(root.left, sj);
        dfsSerialize(root.right, sj);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        List<String> nodes = new LinkedList<>(Arrays.asList(data.split(",")));
        return dfsDeserialize(nodes);
    }

    private TreeNode dfsDeserialize(List<String> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }
        String node = nodes.get(0);
        nodes.remove(0);
        if ("NULL".equals(node)) {
            return null;
        }
        TreeNode treeNode = new TreeNode(Integer.parseInt(node));
        treeNode.left = dfsDeserialize(nodes);
        treeNode.right = dfsDeserialize(nodes);
        return treeNode;
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

提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。

 

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
