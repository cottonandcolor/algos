package hrcrackc;

import java.util.Scanner;

/**
 * Created by predave on 5/15/17.
 */
public class Prime {


        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            int p = in.nextInt();
            for(int a0 = 0; a0 < p; a0++){
                int n = in.nextInt();
                System.out.println(isPrime(n));
            }
        }

    private static String isPrime(int n) {
        if(n == 1 ) {
            return "Not prime";
        }
        for(int i = 2 ; i*i <= n ; i++){
            if(n%i == 0){
                return "Not prime";
            }
        }
        return "Prime";
    }


}
