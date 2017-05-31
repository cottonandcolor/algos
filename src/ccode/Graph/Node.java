package ccode.Graph;

/**
 * Created by predave on 5/3/17.
 */
public class Node {
    Integer data;
    Node left ;
    Node right ;

    public Node(Integer data ){
        this.data = data;
    }

    public String toString() {
        return data.toString();
    }
    public void visit() {
        System.out.print(data+ ",");
    }

}
