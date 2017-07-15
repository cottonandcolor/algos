package lcodemock;

/**
 * Created by predave on 7/5/17.
 */
public class MaxProfit2Stocks {
    public static void main(String[] args){
        int a[] = {2, 30, 15, 10, 8, 25, 80};
        int[] profit = new int[a.length];
        int i = a.length - 1;
        int max = a[i];

        while(i > 0) {
            if( a[i-1] > max )
            max =a[i-1];
            i--;
        }


    }
}
