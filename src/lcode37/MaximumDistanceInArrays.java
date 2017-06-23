package lcode37;
import java.util.*;
import java.util.Scanner;

/**
 * Created by predave on 6/17/17.
 */
public class MaximumDistanceInArrays {
    public static int maxDistance(int[][] arrays) {

      int max = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int minindex1 = 0;
        int maxindex1 = 0;
        for(int i = 0 ; i < arrays.length ; i++){
            min = Math.min(arrays[i][0], min);
            if(min == arrays[i][0])
                minindex1 = i;
            max = Math.max(arrays[i][arrays[i].length -1], max);
            if(max == arrays[i][arrays[i].length -1])
                maxindex1 =i;
        }
        if(maxindex1 == minindex1){ //find second max second min
            for(int i = 0 ; i < arrays.length ; i++){
                if(i == maxindex1) continue;
                min2 = Math.min(arrays[i][0], min2);
                max2 = Math.max(arrays[i][arrays[i].length -1], max2);
            }
            int amax1 = Math.abs(max-min2);
            int amax2 = Math.abs(max2-min);
            return Math.max(amax1, amax2);
        }
        return Math.abs(max - min);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][]a = {{1,2,3} , {0,5} , {1,2,3}};
        System.out.println(maxDistance(a));
    }

}
