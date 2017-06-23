package lcode37;

import java.util.Scanner;

/**
 * Created by predave on 6/17/17.
 */
public class AddOneRowToTree2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        //root.left.right = new TreeNode(1);
        //root.right.left= new TreeNode(5);
        addOneRow(root, 1, 4);

        root.preOrder(root);
    }

    public static class TreeNode {
             int val;
             TreeNode left;
             TreeNode right;
             TreeNode(int x) { val = x; }

        public void preOrder(TreeNode root) {
            if(root == null) return;
            System.out.print(root.val + " ,");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public static TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode t = new TreeNode(v);
            t.left = root;
            return t;
        }
        addRow(root, v, d, 1);
        return root;
    }

    private static void addRow(TreeNode node, int v, int d, int level) {
        if (node == null) {
            return;
        }

        if (level == d - 1) {
            TreeNode tmp = node.left;
            node.left = new TreeNode(v);
            node.left.left = tmp;
            tmp = node.right;
            node.right = new TreeNode(v);
            node.right.right = tmp;
            return;
        }

        addRow(node.left, v, d, level + 1);
        addRow(node.right, v, d, level + 1);
    }
}
