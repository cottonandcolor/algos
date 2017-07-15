package lcode40;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

/**
 * Created by predave on 7/8/17.
 */
public class prog4A {

    public static class TreeNode {
        int val;
             TreeNode left;
             TreeNode right;
             TreeNode(int x) { val = x; }
         }
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
       if(root == null) return res;
        getAvg(root, res, 0);
        return res;
    }

    private void getAvg(TreeNode root, List<Double> res, int d ) {
        Queue<TreeNode> q1 = new LinkedList<TreeNode>(), q2 = new LinkedList<TreeNode>();

        if (root == null)
            return;

        q1.add(root);
        res.add(new Double(root.val));
        while (!q1.isEmpty() || !q2.isEmpty())
        {
            Double sum = new Double(0.0);
            int cnt = 0;
            while (!q1.isEmpty())
            {

               if (q1.peek().left != null) {
                   int v = q1.peek().left.val;
                   sum = sum + new Double(v);
                   q2.add(q1.peek().left);
                   cnt++;
               }

                if (q1.peek().right != null) {
                    int v = q1.peek().right.val;
                    sum = sum + new Double(v);
                    q2.add(q1.peek().right);
                    cnt++;
                }


                q1.poll();
            }
            if(cnt != 0)
            res.add(sum/cnt);

            Double sum2 = new Double(0.0);
            int cnt2 = 0;

            while (!q2.isEmpty())
            {
                if (q2.peek().left != null) {
                    int v = q2.peek().left.val;
                    sum2 = sum2 + new Double(v);
                    q1.add(q2.peek().left);
                    cnt2++;
                }

                if (q2.peek().right != null) {
                    int v = q2.peek().right.val;
                    sum2 = sum2 + new Double(v);
                    q1.add(q2.peek().right);
                    cnt2++;
                }


                q2.poll();
            }
            if(cnt2 != 0)
            res.add(sum2/cnt2);


        }

    }

        private void getAvg3(TreeNode root, List<Double> res, int d ) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);


        while (!queue.isEmpty())
        {
            d++;
            Double sum = 0.0;
            int cnt = 0;
            TreeNode tempNode = null;
            for(int i = 0 ; i < Math.pow(2,(d-1)); i++) {

                tempNode = queue.poll();
                if(tempNode == null) continue;
                sum = sum + tempNode.val;
                cnt++;

            }
            sum = sum /cnt;
            res.add(sum);

            if (tempNode != null && tempNode.left != null) {
                queue.add(tempNode.left);
            }

            if (tempNode != null && tempNode.right != null) {
                queue.add(tempNode.right);
            }
        }
    }
    private void getAvg1(TreeNode root, List<Double> res, int depth ) {
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
     prog4A p = new prog4A();
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
