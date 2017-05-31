package ccode.Graph;

/**
 * Created by predave on 5/3/17.
 */
public class TreeTest {

    public static void main(String[] args) {
        Integer input[] = { 1, 2 , 3 , 4 , 5, 6, 7};

        Node root = new Node(input[0]);
        Tree tree = new Tree(root);
        Node tmp = root;
        for(int i = 1 ; i < input.length; i++){
            tree.addNode(tree.getFirstNode(), input[i]);

        }
        System.out.println("preorder---");
        tree.preOrder(root);
        System.out.println("postorder---");
        tree.postOrder(root);
        System.out.println("inorder---");
        tree.inOrder(root);

        System.out.println(tree.findNode(root, 8));

        System.out.println(tree.delNode(root, root.data));
        System.out.println("preorder---");
        tree.preOrder(root);
    }
}
