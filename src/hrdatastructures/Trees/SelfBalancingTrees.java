package hrdatastructures.Trees;

/**
 * Created by predave on 6/24/17.
 */
public class SelfBalancingTrees {

    static class Node {
        int val;   //Value
        int ht;      //Height
        Node left;   //Left child
        Node right;   //Right child
        public Node(int v){
            this.val = v;
        }
        public Node() {

        }
    }



    private static int height(Node N) {
        if (N == null)
            return 0;

        return N.ht;
    }

    static int getBalance(Node N) {
        if (N == null)
            return 0;

        return height(N.left) - height(N.right);
    }

    static Node rightRotate(Node y) {
        Node x = y.left;
        Node t = x.right;

        x.right = y;
        y.left = t;

        y.ht = Math.max(height(y.left), height(y.right)) + 1;
        x.ht = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    static Node leftRotate(Node x) {
        Node y = x.right;
        Node t = y.left;

        y.left = x;
        x.right = t;

        x.ht = Math.max(height(x.left), height(x.right)) + 1;
        y.ht = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    static Node insert(Node root,int val)
    {
        if( root == null){
            Node n = new Node();
            n.val = val;
            return n;
        }
        if(val < root.val){
            root.left = insert(root.left, val);
        }
        else if(val > root.val){
            root.right = insert(root.right, val);
        } else {
            return root; // duplicate keys not allowed
        }

        root.ht = 1 + Math.max(height(root.left), height(root.right));
        int balance = getBalance(root);
        if (balance > 1 && val < root.left.val)
            return rightRotate(root);
        if (balance < -1 && val > root.right.val)
            return leftRotate(root);
        if (balance > 1 && val > root.left.val) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        if (balance < -1 && val < root.right.val) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }

    public static void main(String[] args){
        Node n = new Node(3);
        n.ht = 2;
        n.left = new Node(2);
        n.right = new Node(4);
        n.right.ht = 1;
        n.right.right = new Node(5);
        insert(n,6);
        System.out.print(n.val);
    }

}
