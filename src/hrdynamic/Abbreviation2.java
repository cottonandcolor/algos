package hrdynamic;

import java.util.Scanner;

/**
 * Created by predave on 6/30/17.
 */
public class Abbreviation2 {


    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        String A = scanner.next();
        String B = scanner.next();

        for(int i =0; i < T; i++){
            System.out.println(findMatch(A,B));
        }
    }

    private static String findMatch(String A, String B) {
        int n = A.length();
        int m = B.length();
        boolean[][] dp = new boolean[n+1][m+1];
        dp[0][0] = true;
        for(int i = 0;i <= n;i++){
            for(int j = 0;j <= m;j++){
                if(i < n && Character.isLowerCase(A.charAt(i))){
                    dp[i+1][j] |= dp[i][j];
                }
                if(i < n && j < m && Character.toUpperCase(A.charAt(i)) == B.charAt(j)){
                    dp[i+1][j+1] |= dp[i][j];
                }

            }
        }

        if(dp[n][m]) return "YES";
        else return "NO";
    }

    private static boolean toLower(String a, int i) {
        return Character.isLowerCase(a.charAt(i));
    }

    private static char toUpper(String a, int i) {
        return Character.toUpperCase(a.charAt(i));
    }
}
