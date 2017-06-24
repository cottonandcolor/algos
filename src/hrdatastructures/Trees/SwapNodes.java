package hrdatastructures.Trees;

import java.util.*;

/**
 * Created by predave on 6/24/17.
 */
public class SwapNodes {

    private static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Node root = new Node(1);
        Queue<Node> Q = new LinkedList<Node>();
        Q.add(root);
        while(!Q.isEmpty()) {
            Node r = Q.remove();
            getData(r, in);
            if(r.left != null) Q.add(r.left);
            if(r.right!= null) Q.add(r.right);
        }
        int T = in.nextInt();
        for(int i = 0 ; i < T ; i++){
            swapNodes(root, in.nextInt(), 1);
            printInOrder(root);
            System.out.println();
        }
    }

    private static void printInOrder(Node root) {
        if(root == null) return;
        if(root.left != null) printInOrder(root.left);
        System.out.print(root.data + " ");
        if(root.right!=null) printInOrder(root.right);
    }

    private static void swapNodes(Node root, int k, int cd) {
        if(root == null) return;

        if(cd % k == 0) {
            swap(root);
        }
        swapNodes(root.left, k, cd +1);
        swapNodes(root.right, k, cd +1);
    }

    private static void swap(Node root) {
        Node tmp = root.left;
        root.left = root.right;
        root.right = tmp;
    }

    public static void getData(Node root,  Scanner in){
            if(root == null ) return;
            int L = in.nextInt();
            int R = in.nextInt();
            Node left = null;
            Node right = null;
            if( L != -1){
                left = new Node(L);
            }
            if( R != -1){
                right = new Node(R);
            }
            root.left = left;
            root.right = right;

    }
}
