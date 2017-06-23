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
public class TreeTopView {


    static class Node {
        int data;
        Node left;
        Node right;
        public Node(int data){
            this.data = data;
        }
    }

    void goLeft(Node node) {
        if(node.left != null) {
            goLeft(node.left);
        }
        System.out.print(node.data + " ");
    }

    void goRight(Node node) {
        System.out.print(node.data + " ");

        if(node.right != null) {
            goRight(node.right);
        }
    }

    void topView(Node root) {
        if(root.left != null) {
            goLeft(root.left);
        }

        System.out.print(root.data + " ");

        if(root.right != null) {
            goRight(root.right);
        }
    }
    public static void main(String[] args){

    }

}
