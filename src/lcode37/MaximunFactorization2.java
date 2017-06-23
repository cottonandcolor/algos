package lcode37;

import java.util.ArrayList;
import java.util.Collections;
import java.util.*;
import java.util.Scanner;

/**
 * Created by predave on 6/17/17.
 */
public class MaximunFactorization2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = 9765625 ;
        //555555688
       System.out.println(doSmallest(a));
    }

    private static int doSmallest(int a) {
        int result = 0;
        List<Integer> nums = new ArrayList<>();

        if(a <= 9) return a;

        int[] v = {2,3,5,7};
        Map<Integer,Integer> cmap = new HashMap<>();
        while(a != 1) {
            int preva = a;
            for (int i : v) {
                if (a % i == 0) {
                    cmap.put(i, (cmap.getOrDefault(i, 0) + 1));
                    a = a / i;
                }
            }
            if(a == preva) return 0;
        }

        Map<Integer,Integer> tempMap = new HashMap<>();
        for(Integer key : cmap.keySet()) {
            if (key.equals(2)) {
                int num2s = cmap.get(key);
                int num8s = num2s / 3;
                if (num8s > 0) tempMap.put(8, num8s);
                num2s = num2s - (num8s * 3);
                tempMap.put(2, num2s);
            } else if (key.equals(3)) {
                int num3s = cmap.get(key);
                int num9s = num3s / 2;
                if (num9s > 0) tempMap.put(9, num9s);
                num3s = num3s - (num9s * 2);
                tempMap.put(3,num3s);
            }
        }

        for(Integer key : tempMap.keySet()){
            Integer val = tempMap.get(key);
            if(val == 0) cmap.remove(key);
            else cmap.put(key,val);
        }

        if(tempMap.get(2) != null && tempMap.get(2) != 0 && tempMap.get(3) != null&& tempMap.get(3) != 0){
            if(tempMap.get(2) == 1) {
                cmap.put(6, 1);
                cmap.remove(2);
                cmap.remove(3);
            }else if(tempMap.get(2) == 2) {
                cmap.put(6, 1);
                cmap.put(2,1);
                cmap.remove(3);
            }
        } else if(tempMap.get(2) != null && tempMap.get(2) != 0){
            if(tempMap.get(2) == 2){
                cmap.put(4,1);
                cmap.remove(2);
            }
        }



        int mul = 1;
        int[] k = {9,8,7,6,5,4,3,2};
        for(Integer t : k){
            Integer count = cmap.get(t);
            if(count != null && count != 0) {
                for(int p = 0 ; p < count; p++) {
                    result = result + (t * mul);
                    if(mul >= 1000000000) return 0;
                    mul = mul * 10;
                    if (result < 0 || result > Integer.MAX_VALUE) return 0;
                }
            }
        }
        return result;
    }
}
