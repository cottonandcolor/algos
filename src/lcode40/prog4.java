package lcode40;

import java.util.*;

/**
 * Created by predave on 7/8/17.
 */
public class prog4 {

    public static class TreeNode {
        int val;
             TreeNode left;
             TreeNode right;
             TreeNode(int x) { val = x; }
         }
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
       if(root == null) return res;
        res.add(new Double(root.val));
        getAvg(root, res, 0);
        return res;
    }

    private void getAvg(TreeNode root, List<Double> res, int depth ) {
        if(root == null) return;
        Double a = 0.0;
        Double b = 0.0;
        int cnt = 0;
        if (root.left != null)  {a = new Double(root.left.val);cnt++;}
        if (root.right != null)  {b = new Double(root.right.val);cnt++;}
        if(a != 0 || b != 0){
            res.add((a+b)/cnt);
        }
        getAvg(root.left, res, depth++);
        getAvg(root.right, res, depth++);
        Double sum = 0.0;
        if( depth >= 2) {
            for(int i = 1 ; i <= depth; i++){
                sum = sum + res.remove(res.size()-i);
            }

            sum = sum * depth;
            res.add(sum);
        }

    }

    public static void main(String[] args){
     prog4 p = new prog4();
        TreeNode r = new TreeNode(3);
        r.left = new TreeNode(1);
        r.left.left = new TreeNode(0);
        r.left.right = new TreeNode(2);
        r.right = new TreeNode(5);
        r.right.left = new TreeNode(4);
        r.right.right = new TreeNode(6);
        List<Double> x = p.averageOfLevels(r);
        System.out.print(x);
    }
}
