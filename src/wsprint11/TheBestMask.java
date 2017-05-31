package wsprint11;

import java.util.Arrays;
import java.util.Scanner;


public class TheBestMask {
    static long solve(int[] a){
        // Complete this function
        boolean[] visited = new boolean[a.length];
        int[] smallest = new int[a.length];

        Arrays.fill(smallest, -1);
        checkSmallest(a, visited, smallest);
        long val = 0;
        for(int i = 0 ; i < smallest.length; i++){
            if(smallest[i] == -1) break;
            val+= Math.pow(2,smallest[i]);
        }
        return val;
    }
    static int index = 0;
    static int visitedcount = 0;
    static void checkSmallest(int[] a, boolean[] visited, int[] smallest){
        if(visitedcount >= smallest.length) return;
        int largestPos = 0;
        for(int i = 0 ; i < a.length; i++){
            if(visited[i]) continue;
            int count = 0;
            while((a[i] & 1<<count) == 0) count++;
            largestPos = Math.max(largestPos,count);
        }
        smallest[index++] = largestPos;
        int checkBit = (int)Math.pow(2,largestPos);
        for(int i = 0 ; i < a.length; i++){
            if(!visited[i]) {
                if((a[i]&checkBit) != 0){
                    visited[i] = true;
                    visitedcount++;
                }
            }
        }
        checkSmallest(a,visited,smallest);

    }

    public static void main(String[] args) {
        //& 4& 8& 16& 32& 64& 256 &512& 128
        // find smallest bit set in all nums by right shift (find max val)
        //maintain index in array , remove all numbers with this bit set
        //remaining numbers repeat above


        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        long result = solve(a);
        System.out.println(result);
    }
}
