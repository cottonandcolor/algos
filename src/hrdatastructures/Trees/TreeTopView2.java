package hrdatastructures.Trees;

/**
 * Created by predave on 6/21/17.
 * 1
 \
 2
 \
 5
 /  \
 3    6
 \
 4
 */
import java.util.*;
public class TreeTopView2 {


    static class Node {
        int data;
        Node left;
        Node right;
        public Node(int data){
            this.data = data;
        }
    }

    static class HNode{
        Node n;
        Integer h;
        public HNode(Node n, Integer h){
            this.n = n;
            this.h = h;
        }
    }

    void topView(Node root) {
        if(root == null) return;
        Queue<HNode> Q  = new LinkedList<HNode>() ;
        Q.add(new HNode(root,0));
        System.out.print(root.data + " ");

        int h = 1;
        Set<Integer> heights = new HashSet<>();
        heights.add(0);
        while(!Q.isEmpty()){
            HNode n = Q.remove();
            if(!heights.contains(n.h)){
                System.out.print(n.n.data + " ");
            }
            if(n.n.left != null)
                Q.add(new HNode(n.n.left, n.h -1 ));
            if(n.n.right != null)
                Q.add(new HNode(n.n.right, n.h+ 1));

        }

    }
    public static void main(String[] args){

    }

}
