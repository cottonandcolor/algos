/**
 * Created by predave on 6/14/17.
 */
public class longestpalindromesubsequence {

    public static void main(String[] args){
        char[] a = {'a' ,'b', 'b', 'a'};
        System.out.println(lps2(a, 0, a.length-1));
    }

    private static int lps(char[] a, int i, int j) {
        if(i == j ) return 1;
        if(i +1 == j) {
            if(a[i] == a[j]) return 2; else return 1;
        } else {
            if(a[i] == a[j]) return 2 + lps(a, i+1, j-1);
            else return Math.max(lps(a, i, j-1) , lps(a, i+1, j));
        }
    }

    private static int lps2(char[] a , int x, int y){
        int len = a.length;
        int[][] LPS = new int[len][len];
        for(int i = 0; i < len; i++){
            LPS[i][i] = 1;
        }
        if(x + 1 == y && a[x] == a[y]) return 2;

        for(int sublen = 2; sublen <= len; sublen++){
            for(int i = 0; i <= LPS.length - sublen ; i ++){
                int j = i + sublen - 1;
                if(a[i] == a[j] && sublen == 2){
                    LPS[i][j] = 2;
                } else if(a[i] == a[j]){
                    LPS[i][j] = 2 + LPS[i+1][j-1];
                } else {
                    LPS[i][j] = Math.max(LPS[i+1][j] , LPS[i][j-1]);
                }
            }
        }
        return LPS[0][LPS.length -1];
    }
}
