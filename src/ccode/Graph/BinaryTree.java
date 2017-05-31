package ccode.Graph;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by predave on 5/4/17.
 */
public class BinaryTree {

    Deque<Node> nodeList = new LinkedList<Node>();
    Node root;

    public void BinaryTree(Node node){
        this.root = node;
        nodeList.add(root);
    }

    public void insert(Node node) {
        Node newroot = nodeList.peek();
        if(node.data <= newroot.data){

        }
        else {

        }
    }

    public Boolean findNode(Node node) {
        return false;
    }

    public Node deleteNode(Node node) {
        return null;
    }

}
