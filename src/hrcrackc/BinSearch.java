package hrcrackc;

/**
 * Created by predave on 5/14/17.
 */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class BinSearch {

    private static class icecream  implements Comparable<icecream>{
        int id;
        int cost;

        public icecream(int id, int cost){
            this.id = id;
            this.cost = cost;
        }

        @Override
        public int compareTo(icecream o) {
            if(this.cost < o.cost) return -1;
            else return 1;
        }
    }

   public static void main(String[] args) {
       Scanner in = new Scanner(System.in);
       int t = in.nextInt();
       for (int a0 = 0; a0 < t; a0++) {
           int m = in.nextInt();
           int n = in.nextInt();
           icecream a[] = new icecream[n];
           for (int a_i = 0; a_i < n; a_i++) {
               a[a_i] = new icecream(a_i + 1 , in.nextInt());
           }

           Arrays.sort(a);
           findTwoIceCreams(a, m, a.length-1);

       }
   }

    private static void findTwoIceCreams(icecream[] a, int m, int rightindex) {
        for(int i = 0 ; i < rightindex ; i++ ){
            first = i ;
            int cost  = m - a[i].cost ;
            rightindex  = binSearch(a, cost , i+1 , rightindex);
            if (a[rightindex].cost == cost) {
                    second = a[rightindex].id;
                    break;
            }

        }
        if(a[first].id < second) {
            System.out.println(a[first].id + " " + second);
        } else {
            System.out.println(second + " " + a[first].id);
        }
    }

    private static int first = 0;
    private static int second = -1;

    private static int binSearch(icecream[] a, int m, int leftindex, int rightlimit) {
        int index = leftindex;


        while(leftindex < rightlimit  ) {
            int mid = leftindex + (rightlimit - leftindex) / 2;
            if(leftindex == mid){
                break;
            }
            if (a[mid].cost > m) {
                rightlimit = mid - 1;
            }
            else if(a[mid].cost == m ){
                return mid;
            }
            else {
                leftindex = mid ;
            }
        }

        return rightlimit;
    }
}
