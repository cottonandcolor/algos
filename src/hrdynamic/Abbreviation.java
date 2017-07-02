package hrdynamic;

import java.util.Scanner;

/**
 * Created by predave on 6/30/17.
 */
public class Abbreviation {
    public static class Trie{
        Trie[] N =  new Trie[26];

    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        String A = scanner.next();
        String B = scanner.next();

        Trie ATrie = new Trie();
        for(int i = 0; i < A.length(); i++){
             char val = A.charAt(i);

             ATrie.N[val - 'A'] = new Trie();
        }
    }
}
