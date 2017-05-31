package hrcrackc;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * Created by predave on 5/12/17.
 */
public class Trie3 {

       public static class TrieNode {
           boolean isLeaf;
           TrieNode[] children;
           int val;

           public TrieNode (){
               children = new TrieNode[26];
               isLeaf  = true;
               val = 0;
           }

           public void addString(String input){
               TrieNode root = this;
               for(int i = 0 ; i < input.length(); i++) {
                   Character c = input.charAt(i);
                   TrieNode node = root.children[c - 'a'];
                   if (node == null) {
                       node = new TrieNode();
                       root.children[c - 'a'] = node;
                   } else {
                       root.isLeaf = false;
                   }
                   root = node;
                   root.val++;

               }

           }

           public  int startsWith(String val){
               TrieNode root = this;
               for(int i = 0 ; i < val.length() ; i++){
                   Character c = val.charAt(i);
                   root = root.children[c - 'a'];
                   if(root == null) return 0;

               }
               return root.val;
           }



       }



       public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            int n = in.nextInt();
           TrieNode trieNode = new TrieNode();
            for(int a0 = 0; a0 < n; a0++){
                String op = in.next();
                String contact = in.next();
                if(op.equals("add")) {
                    trieNode.addString(contact);
                } else if(op.equals("find")){
                    System.out.println(trieNode.startsWith(contact));
                }
            }
        }


}
