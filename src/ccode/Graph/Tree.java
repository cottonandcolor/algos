package ccode.Graph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by predave on 5/3/17.
 */
public class Tree {
    Node root;
    int size = 0;
    Deque<Node> nodeList = new LinkedList<Node>();

    public Node getFirstNode(){
        return nodeList.peek();
    }
    public Tree(Node root){
        this.root = root;
        nodeList.add(root);
        size++;
    }

    public Node addNode(Node node , Integer data ){

        if(node == null ){
             node = new Node(data);
            nodeList.add(node);
        }
        else {
             if(node.left == null)
             {
                 node.left = addNode(node.left, data);
             }
             else if(node.right == null)
             {
                node.right = addNode(node.right, data);
             }
             if(node.left != null && node.right != null){
                nodeList.remove(node);
             }
        }

        return node;

    }

    public Boolean findNode(Node node, Integer data){
           Boolean status = false;
           if(node == null ) {
               return false;
           }
           if(node.data.equals(data)) {
               return true;
           } else {
               status = findNode(node.left, data) ;
               if(!status) {
                   status = findNode(node.right, data);
               }
           }
        return status;
    }

    public Node delNode(Node node, Integer data){
      //if left deleted replace deleted node with last node in right subtree

        if(findNode(node, data) ){
            //node is leaf node
            Node lastNode = nodeList.removeLast();
            if(node.equals(lastNode)){
                root = null;
            } else {
                node.data = lastNode.data;
                //TODO set parent left or right to null
                lastNode.data = null;
                lastNode = null;
            }
            return root;
        }

        return root;
    }

    public void printTree(Node node, StringBuilder sb) {
        if(node != null){
            sb.append(node.data).append(",");
        }
        else {
            return ;
        }
        printTree(node.left, sb);
        printTree(node.right, sb);
    }



    //centre left right
    public void preOrder(Node node ) {
        if(node == null)
            return;
        System.out.print(node.data + ",");
        preOrder(node.left);
        preOrder(node.right);
    }

    //left right center
    public void postOrder(Node node ) {
        if(node == null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data + ",");
    }

    //left center right
    public void inOrder(Node node ) {
        if(node == null)
            return;
        inOrder(node.left);
        System.out.print(node.data + ",");
        inOrder(node.right);
    }

    public String toString() {
        Node tmp = root;
        StringBuilder sb = new StringBuilder("");
        //printTree(tmp, sb);
        return sb.toString();
    }
}
