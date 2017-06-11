package lcode35;

/**
 * Created by predave on 6/10/17.
 */
public class MergeTrees {
    public static class TreeNode {
             int val;
             TreeNode left;
             TreeNode right;
             TreeNode(int x) { val = x; }
         }

    public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1 == null ) return t2;
        if(t2 == null) return t1;

        TreeNode mergedTreeNode = new TreeNode(0);
        mergeTree(mergedTreeNode, t1, t2);
        return mergedTreeNode;

    }

    private static TreeNode mergeTree(TreeNode mergedTreeNode, TreeNode t1, TreeNode t2) {
        if(t1 != null  )
            mergedTreeNode.val += t1.val ;
        if(t2 != null)
            mergedTreeNode.val += t2.val;
        if((t1 != null && t1.left != null) || (t2 != null && t2.left != null))
        {
            mergedTreeNode.left = new TreeNode(0);
            TreeNode left1 = t1 == null ? null : t1.left;
            TreeNode left2 = t2 == null ? null : t2.left;

            mergeTree(mergedTreeNode.left, left1 , left2);
        }
        if((t1 != null && t1.right != null) || (t2 != null && t2.right != null))
        {
            TreeNode right1 = t1 == null ? null : t1.right;
            TreeNode right2 = t2 == null ? null : t2.right;

            mergedTreeNode.right = new TreeNode(0);
            mergeTree(mergedTreeNode.right, right1 , right2);
        }
        return mergedTreeNode;
    }

    public static void main(String[] args){
        TreeNode tree1 = new TreeNode(1);
        tree1.left = new TreeNode(3);
        tree1.left.left = new TreeNode(5);
        tree1.right = new TreeNode(2);
        TreeNode tree2 = new TreeNode(2);
        tree2.left = new TreeNode(1);
        tree2.right = new TreeNode(3);
        tree2.left.right = new TreeNode(4);
        tree2.right.right = new TreeNode(7);
        mergeTrees(tree1,tree2);

    }
}
