package hrw32;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by predave on 5/16/17.
 */
public class Monsters {
    static long maxMonsters = 0;
    static long getMaxMonsters(int n, int hit, int t, int[] h){
        // Complete this function

            int index = 0;
            while(index < n){
                if( h[index] > 0) {
                    h[index] = h[index] - hit;
                    if(h[index] <= 0) maxMonsters++;
                    t--;
                    if(t == 0) break;
                }
                else {
                    index++;
                }
            }

        return maxMonsters;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int hit = in.nextInt();
        int t = in.nextInt();
        int[] h = new int[n];
        for(int h_i=0; h_i < n; h_i++){
            h[h_i] = in.nextInt();
        }
        Arrays.sort(h);
        long result = getMaxMonsters(n, hit, t, h);
        System.out.println(result);
    }
}
