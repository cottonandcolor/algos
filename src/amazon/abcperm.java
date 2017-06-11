package amazon;

import java.util.Scanner;

/**
 * Created by predave on 6/8/17.
 * Given a string, count number of subsequences of the form aibjck, i.e., it consists of i ’a’ characters, followed by j ’b’ characters, followed by k ’c’ characters where i >= 1, j >=1 and k >= 1.

 Note: Two subsequences are considered different if the set of array indexes picked for the 2 subsequences are different.

 Expected Time Complexity : O(n)
 */
public class abcperm {

    public static void main(String[] args){
        // abbc abc abbc abc
        char[] a = {'a', 'b' , 'c', 'a', 'b', 'c'}; // a  abc abc abbc abcc abcc abbcc
        int countA = 0;
        int countB = 0;
        int countC = 0;
        for(int i = 0 ; i < a.length; i++){
            if(a[i] == 'a'){
                countA = 1 + 2 * countA;
            }
            if(a[i] == 'b'){
                countB = countA + 2*countB;
            }
            if(a[i] == 'c'){
                countC = countB + 2*countC;
            }
        }
        System.out.println(countC);
    }
}
