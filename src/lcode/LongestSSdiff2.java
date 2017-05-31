package lcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Input: [1,3,2,2,5,2,3,7]
 Output: 5
 Explanation: The longest harmonious subsequence is [3,2,2,2,3].
 */
public class LongestSSdiff2 {


    public static void main(String[] args){

        int[] a =    {1,2,3,4};//{1,3,2,2,5,2,3,7};
                     //1,2,3,4          //         1,1,2,3,1,4,5,1
        System.out.println(findLongestSS(a));
    }

    private static int findLongestSS(int[] a) {
        int max = 0;

        Map<Integer, Integer> map = new HashMap<Integer,Integer>();
        for (int i = 0 ; i < a.length; i++) {
            map.put(a[i], map.getOrDefault(a[i], 0) + 1);
        }
        for (int key : map.keySet()) {
            if (map.containsKey(key + 1)) {
                max = Math.max(max, map.get(key + 1) + map.get(key));
            }
        }
        return max;
    }
}
