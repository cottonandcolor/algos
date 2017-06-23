package hrdatastructures.Trees;

/**
 * Created by predave on 6/21/17.
 *
 *       6
 *    5     8
 * 4      3  9*/
//
public class IsThisABinarySearchTree {

    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int d){
            data = d;
        }
    }

    static boolean checkBST(Node root) {
        Integer max = Integer.MAX_VALUE;
        Integer min = Integer.MIN_VALUE;
        checkIfBst(root, max, min);
        return true;
    }

    private static boolean checkIfBst(Node root, Integer min, Integer max) {
        if(root == null ) return true;
        if(root.data > max || root.data < min) return false;
        if(root.data == max || root.data == min ) return false;

        return checkIfBst(root.left, min, root.data) && checkIfBst(root.right, root.data, max);

    }


    public static void main(String[] args){
        Node n = new Node(4);


        n.left = new Node(2);
        n.right = new Node(6);
        n.left.left = new Node(1);
        n.left.right = new Node(3);
        n.right.left = new Node(5);
        n.right.right = new Node(7);
        System.out.println(checkBST(n));

    }

}
