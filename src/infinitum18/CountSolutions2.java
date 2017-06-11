package infinitum18;

import java.util.Scanner;

/**
 * Created by predave on 6/9/17.
 */
public class CountSolutions2 {
    static int countSolutions(int a, int b, int c, int d){
        int count = 0;
        // Complete this function
        for(int x = Math.min(a,c) ; x <= c; x++){
            for(int y = Math.min(b,d) ; y <= d; y++ ) {
                if((x*x) +(y*y)  == a*x + b*y){
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();
            int d = in.nextInt();
            int result = countSolutions(a, b, c, d);
            System.out.println(result);
        }
    }
}
