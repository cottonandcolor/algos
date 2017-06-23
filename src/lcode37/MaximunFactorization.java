package lcode37;

import java.util.*;

/**
 * Created by predave on 6/17/17.
 */
public class MaximunFactorization {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = 6000000 ;
        //555555688
       System.out.println(doSmallest(a));
    }

    private static int doSmallest(int a) {
        int origa = a;
        int result = 0;
        List<Integer> nums = new ArrayList<>();
        List<Integer> newnums = new ArrayList<>();
        if(a <= 9) return a;
        int x = 1;
        while( x < a ){
            if(a%x == 0){
                int p = a/x;
                if(p < 10 && x < 10) {
                    nums.add(x);
                    nums.add(p);
                    x++;
                    break;
                } else {
                    if(x != 1 && x < 9) {
                        nums.add(x);
                    }
                    a = p;
                    x = 2;
                }
            } else {
                x++;
            }
        }
        Collections.sort(nums);
        int prod = 1;
        for(Integer d:nums){
            prod = prod * d;
        }
        if(prod != origa) return 0;
        int res = 1;
        int i ;
        int ctwos = 0;
        int cthrees = 0;


        for(i = 0; i < nums.size(); i++){

            if(nums.get(i) == 2 ){
               ctwos++;
            } else if(nums.get(i) == 3 ){
                cthrees++;
            } else {
                break;
            }
        }
        if(cthrees == 1 && ctwos ==1 ){
            nums.add(6);
        } else {
            //6 c%3 num 8s rem%2 num 4s rem is 2
            int num8s = ctwos / 3;
            for (int h = 0; h < num8s; h++) {
                newnums.add(8);
            }
            int rem = ctwos % 3;
            int num4s = rem / 2;
            for (int h = 0; h < num4s; h++) {
                newnums.add(4);
            }
            if (num4s == 0 && rem != 0) {
                newnums.add(2);
            }

            int num9s = cthrees / 2;
            for (int h = 0; h < num9s; h++) {
                newnums.add(9);
            }
            if (cthrees % 2 != 0) newnums.add(3);
        }
        for(int j = i; j < nums.size(); j++){
            newnums.add(nums.get(j));
        }

        Collections.sort(newnums);
        int f = newnums.size() - 1;
        int mul = 1;
        while(f >= 0){
            result = result + (newnums.get(f) * mul);
            mul = mul *10;
            if(result < 0) return 0;
            f = f -1;
        }
        return result;
    }
}
