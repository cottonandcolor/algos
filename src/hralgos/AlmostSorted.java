package hralgos;

import java.util.Scanner;

/**
 * Created by predave on 6/7/17.
 */
public class AlmostSorted {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for(int i = 0 ; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        System.out.println(sort(a));
    }
   //2 1 3  6 2 3 4 5 1
    private static String sort(int[] a) {
        int i = 0;
        int dec = 0;
        int inc = 0;
        int startdec;
        int enddec;
        int previnc;
        while(i < a.length-2){
            if(a[i] < a[i+1]) {
                if(dec > 0) {
                    enddec = i;
                }
                inc++ ;
            }
            else {
                startdec = i;
                dec++;
            }
        }
        return "No";
    }
}
