package hr10148;

import java.util.Scanner;

/**
 * Created by predave on 5/3/17.
 * 5
 * first l before r no impact
 * d after l and d before r no impact
 r
 lrrl
 rrrll
 rrdlldrr
 rrrdllrllrrl
 Sample Output 0



 0
 3
 5
 4
 11

 r->r

 //remove first ls  , remove last rs , while scanning
 //r's before d  , count all rs
 //d before ls , count all ls
 // count remaining except d

 */
public class robotcollision {
    static int howManyCollisions(String s){
        // Complete this function
        int length = s.length();
        if(length == 1) {
            return 0;
        }
        char[] bots = s.toCharArray();
        int count = 0;
        int leftindex = 0 ;
        while(leftindex < length - 1){
            if(bots[leftindex] == 'l'){
                leftindex++;
            }
            else {
                break;
            }
        }
        int rightindex = length - 1;
        while(rightindex >= 0){
            if(bots[rightindex] == 'r'){
                rightindex--;
            }
            else {
                break;
            }
        }

        int dcount = 0;
        for(int i = leftindex; i <= rightindex ; i++){
            if(bots[i] == 'd'){
                dcount++;
            }
        }

        dcount += leftindex ;
        dcount += length - rightindex - 1;

        return length - dcount;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            String s = in.next();
            // Returns the number of times moving robots collide.
            int result = howManyCollisions(s);
            System.out.println(result);
        }
    }
}
