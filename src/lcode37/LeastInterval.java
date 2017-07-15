package lcode37;

import java.util.*;

/**
 * Created by predave on 6/17/17.
 */
public class LeastInterval {
    public static int leastInterval(char[] tasks, int n) {
        if(tasks.length == 0) return 0;
        if(n == 0) return tasks.length;

        int[] c = new int[26];
        for(char t : tasks){
            c[t - 'A']++;
        }
        Arrays.sort(c);
        int i = 25;
        while(i >= 0 && c[i] == c[25]) i--;

        return Math.max(tasks.length, (c[25] - 1) * (n + 1) + 25 - i);

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char [] tasks = {'A','B','B','A','B','B', 'C'};
        System.out.println(leastInterval(tasks, 3));
    }
}
