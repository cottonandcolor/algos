package lcode39;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by predave on 7/2/17.
 * Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 Output: [20,24]
 Explanation:
 List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
 List 2: [0, 9, 12, 20], 20 is in range [20,24].
 List 3: [5, 18, 22, 30], 22 is in range [20,24].
 */
public class SmallestRange {
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<int[]> PQ = new PriorityQueue<int[]>((i1, i2) -> i1[0] - i2[0]);
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int range = Integer.MAX_VALUE;

        int index = 0;
        for(List<Integer> list : nums){
            int[] e = new int[3];
            e[0] = list.get(0);
            e[1] = index;
            e[2] = 0;
            max = Math.max(e[0],max);

            index++;
            PQ.add(e);
        }
        //range = max - min;
        int start = -1;
        int end  = -1;
        while(PQ.size() == nums.size()){
            int[] v = PQ.poll();
            if(max - v[0] < range){
                start = v[0];
                end = max;
                range = max - v[0];
            }
            int aindex = v[2] ;
            aindex++;
            if(aindex < nums.get(v[1]).size()) {
                int val1 = nums.get(v[1]).get(aindex);
                int[] n1 = new int[3];
                n1[0] = val1;
                n1[1] = v[1];
                n1[2] = aindex;
                PQ.offer(n1);
                if(val1  > max){
                    max = val1;
                }
            }
        }


        int res[] = new int[2];
        res[0] = start;
        res[1] = end;

      return res;
    }

    public static void main(String[] args){
        List<Integer> l1 = Arrays.asList(4,10,15,24,26);
        List<Integer> l2 = Arrays.asList(0,9,12,20);
        List<Integer> l3 = Arrays.asList(5,18,22,30);
        SmallestRange sr = new SmallestRange();
        sr.smallestRange(Arrays.asList(l1,l2,l3));
        //[0,9,12,20], [5,18,22,30]]
    }
}
