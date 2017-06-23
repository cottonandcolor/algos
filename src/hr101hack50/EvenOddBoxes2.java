package hr101hack50;

import java.util.Scanner;

/**
 * Created by predave on 6/20/17.
 */
public class EvenOddBoxes2 {
    // 3 2 3 2 3 1
    static long minimumChocolateMoves(int n, int[] X) {
        // Complete this function
        long count = 0;
        int sum = 0;
        long o = 0;
        long e = 0;
        //int no = 0;
        long ne = 0;
        long te = 0;
        long to = 0;

        for(int i = 0 ; i < n; i++){
            if(i%2 == 0) {
                if ((X[i] % 2) != 0){
                    if(X[i] == 1) ne++;
                    else {
                        e++;
                    }
                }
                if(X[i] > 2) {
                    te = te + (X[i] - 2);
                }

            }
            else if(i%2 == 1) {
                if ((X[i] % 2) != 1){
                    o++;
                }
                if(X[i] != 1) {
                    to = to + (X[i] - 1);
                }
            }
        }

        if(o == 0 && e == 0 && ne == 0) return 0;
        long totalr = to + te;
        if(totalr < ne) return -1;
        count += ne;
        if(e >= ne) {e = e - ne;  ne = 0;}
        else if( o >= ne) {o = o - ne; ne = 0;}
        else if(ne != 0){
            ne = ne -o;
            o = 0;
            if(ne <= e){
                e = e - ne; ne = 0;
            } else {
                ne = ne -e;
                e = 0;
            }

        }
        if(ne %2 != 0) return -1;

        totalr = totalr -ne;
        //even num only odds or even num only evens
        long alle = e + Math.abs(ne);
        if(o != 0 && alle == 0 &&  o%2 == 0) return count + (o/2);
        if(o != 0 && alle == 0 &&  o%2 == 1) return -1;
        if(alle != 0 && o == 0 &&  alle%2 == 0) return count + (e/2) ;
        if(alle != 0 && o == 0 &&  alle%2 == 1) return -1;

        if(alle!= 0 && o != 0){
            if(alle%2 == 0 && o%2 ==0) return count + ( (e + o)/2);
            if(alle%2 == 1 && o%2 ==1) return count + ((e + o)/2);
            else return -1;
        }
        if(alle == 0 && o == 0)
            return count;
        return -1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //  Return the minimum number of chocolates that need to be moved, or -1 if it's impossible.
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            int n = in.nextInt();
            int[] X = new int[n];
            for(int X_i = 0; X_i < n; X_i++){
                X[X_i] = in.nextInt();
            }
            long result = minimumChocolateMoves(n, X);
            System.out.println(result);
        }
    }
}
