package hr2;

import java.util.Scanner;

/**
 * Created by predave on 4/28/17.
 */
public class ZigZagDel {

    static int arraylength = 0;
    static int count = 0;

    static int minimumDeletions2(int[] a) {

        if(arraylength == 1 || arraylength == 2) return 0;
        int i = 0;


        while(i <= arraylength - 3 ) {
            int status = 0;
            if(a[i] > a[i+1]){
                //find next a[i+2] < a[i+1]
                status = findNext(a, i+1, true);
            }
            else {
                //find next a[i+2] > a[i+1]
                status = findNext(a, i+1, false);
            }

            if(status == 0){
                rearrange(a , i , 1);
            }
            else if(status != i+1) {
                //rearragne delta between status and i+1
                rearrange(a , i+1 , (status  - (i + 1)));
                i++ ;
            }
            else {
                i++;
            }
        }
        return count;
    }

    static int findNext(int[]a , int index , boolean incerasing) {
        int i = index ;
        boolean found = false;
        while(i < arraylength - 1){
            if(incerasing){
                if(a[i] < a[i+1] ) {
                    found = true;
                    break;
                }
            }
            else {
                if(a[i] > a[i+1] ) {
                    found = true;
                    break;
                }
            }
            i++;
        }
        if(!found){
            return 0;
        }
        return i;
    }



    private static void rearrange(int[] a, int to, int delta) {
       // System.out.println("index:" + to + "val:" + a[to] + "del count:" + delta );

        for(int i = to ; i < arraylength - delta ; i++){
            a[i] = a[i+delta];
        }
        count += delta;
        arraylength = arraylength - delta;
       /* for(int i = 0 ; i < arraylength ; i++){
            System.out.print(a[i] + " ");
        }*/

    }





    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        // Return the minimum number of elements to delete to make the array zigzag
        arraylength = a.length;
        int result = minimumDeletions2(a);
        System.out.println(result);
    }
}
