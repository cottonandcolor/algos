package hrweekcode33;

import java.util.Scanner;

/**
 * Created by predave on 6/15/17.
 */
public class PalindromicTable2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] table = new int[n][m];
        for(int table_i = 0; table_i < n; table_i++){
            for(int table_j = 0; table_j < m; table_j++){
                table[table_i][table_j] = in.nextInt();
            }
        }
        findPalindrominTable(table,n,m);
    }
    static class area {
        int x1=0;
        int y1=0;
        int x2=0;
        int y2=0;
        long a=0;

        public area(int x1, int x2, int y1, int y2,  long i) {
            this.x1 = x1;
            this.y1 =y1;
            this.x2 = x2;
            this.y2 = y2;
            a = i;
        }
    }

    private static void findPalindrominTable(int[][] table, int n, int m) {
        int x1=0,y1=0,x2=0,y2=0;

        area s[][] = new area[n][m];
        area max = new area(0,0,0,0,1);
        for(int u = 0 ; u < n; u++) {

            for(int v = 0 ; v < m; v++){
                s[u][v] = findMaxArea(table, u , v, s);
                max = s[u][v].a > max.a ? s[u][v] : max;

            }
        }

        System.out.println( max.a);
        System.out.println(max.x1 + " " + max.y1 + " " + max.x2 + " " + max.y2);

    }

    private static area findMaxArea(int[][] table, int r, int c, area[][] s){
        if(r == 0 && c == 0) return new area(0,0,0,0,1);
        area max = new area(0,0,0,0,1);
        for(int i = 0; i <= r ; i++) {
            for(int j = 0; j <= c; j++){
                if(s[i][j] != null && s[i][j].a != 0) continue;
                int sz = ((i+1) *(j+1));
                int ar[] = new int[sz];
                int index = 0;

                int x1 = 0;
                int x2 = r;
                int y1 = 0;
                int y2 = c;

                while(x1 < r -1) {

                    for (int p = x1; p <= r; p++) {
                        for (int q = y1; q <= c; q++) {
                            ar[index++] = table[p][q];
                        }
                    }
                    if (isPalindrome(ar)) {
                        long area = ((r) + 1) * ((j) + 1);
                        if (max.a < area) {
                            max = new area(x1, i, y1, j, area);
                        }
                    }
                }

            }
        }
        return max;
    }

    static boolean  isPalindrome(int[] a){
        int len = a.length;
        int[] count = new int[10];
        for(int i = 0; i < len; i++){
            count[a[i]]++;
        }
        int oneodd =0;
        for(int i = 0 ;i <= 9; i++){
            if((count[i] % 2) == 1)  oneodd++;
            if(oneodd > 1) return false;
        }
        if((count[0] * 2) >= len) return false;
        //nz * 2 < len
        return true;
    }

}
