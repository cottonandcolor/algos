package hralgos;

import java.util.Scanner;

/**
 * Created by predave on 6/6/17.
 */
public class Bomberman {

    public static void main(String[] args ) {
        Scanner scanner = new Scanner(System.in);
        Integer R = scanner.nextInt();
        Integer C = scanner.nextInt();
        Integer N = scanner.nextInt();

        char a[][] = new char[R][C];

        for(int i = 0 ; i < R ; i++){
            a[i] = scanner.next().toCharArray();
        }

        printArray(a, R, C, N);



    }


    private static void printArray(char[][] a, Integer R, Integer C, Integer n) {
       if(n == 1 ) {
           for(int i = 0 ; i < R ; i++) {
               for(int j = 0; j < C ; j++) {
                   System.out.print(a[i][j]);
               }
               System.out.println();
           }
           return;
       } else if (n % 2 == 0) {
           for(int i = 0 ; i < R ; i++) {
               for(int j = 0; j < C ; j++) {
                   System.out.print('0');
               }
               System.out.println();
           }
           return;
       }
       else {
           for (int t = 1 ; t < n/2 ; t++){
               //fill2secs
           }

       }

    }
}
