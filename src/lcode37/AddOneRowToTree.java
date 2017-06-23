package lcode37;

import ccode.Graph.Tree;
import java.util.*;

/**
 * Created by predave on 6/17/17.
 */
public class AddOneRowToTree {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        //root.right = new TreeNode(6);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(1);
        //root.right.left= new TreeNode(5);
        addOneRow(root, 1, 3);

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

        if( root == null )
        {
            return new TreeNode(v);
        } else if(d == 0) {
            return root;
        } else if(d == 1){
            root.val = v;
            return root;
        }
        else if(d == 2) {
            TreeNode tl =  new TreeNode(v);
            TreeNode tr =  new TreeNode(v);

            tl.left = root.left;
            tr.right = root.right;
            root.left = tl;
            root.right = tr;
            return root;
        }
        d = d -1;
        if(root.left != null) root = root.left;
        else if(root.right != null) root = root.right;
        addOneRow(root,v,d);
        return root;
    }
}
