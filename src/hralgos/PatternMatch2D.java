package hralgos;

import java.util.Scanner;

/**
 * Created by predave on 6/4/17.
 */
public class PatternMatch2D {



        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            int t = in.nextInt();
            for(int a0 = 0; a0 < t; a0++){
                int R = in.nextInt();
                int C = in.nextInt();
                String[] G = new String[R];

                    for (int G_i = 0; G_i < R; G_i++) {
                        G[G_i] = in.next();
                    }


                int r = in.nextInt();
                int c = in.nextInt();
                String[] P = new String[r];
                    for (int P_i = 0; P_i < r; P_i++) {
                        P[P_i] = in.next();
                    }


                System.out.println(findMatch(G,R,C,P,r,c));
                found = false;
            }

        }

    private static String findMatch(String[] G, int R, int C, String[] P, int r1 , int c1) {
        int rRange = R - r1;
        if(R == r1) rRange = R;
        boolean status = findExactMatch(G, P, 0, rRange, 0, (C-c1), 0, R, C, r1, c1);


        if(status) {
            return "YES";
        } else {
            return "NO";
        }
    }
    public static boolean found = false;
    private static boolean findExactMatch(String[] G, String[] P, int rowL, int rowH, int colL, int colH, int rIndex, int R , int C , int r1, int c1){
        if(found) return true;
        if(rowH > R ||  colH > C ){
            return false;
        }
        for(int r = rowL; r < rowH ; r++){
            for(int c = colL; c <= colH; c++){
                String str = G[r].substring(c,c+c1) ;
                if(!str.equals(P[rIndex])) {
                    rIndex = 0;
                }
                else  {
                    if(rIndex == r1 - 1) {
                        found = true;
                    }
                    rIndex++;
                    findExactMatch(G,P, r+1, r+2, c, c, rIndex,R,C,r1,c1);
                }
            }
        }

        return found;
    }


}
