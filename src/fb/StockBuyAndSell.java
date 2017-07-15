package fb;

import java.util.*;

/**
 * Created by predave on 7/4/17.
 * 2
 7
 100 180 260 310 40 535 695
 10
 23 13 25 29 33 19 34 45 65 67
 */
public class StockBuyAndSell {

    public static void main(String[] args) {
        int[] a = {1, 1};//{ 1, 3, 7, 5, 10, 3 };
        findMax(a, 0);
    }

    private static void findMax(int[] a, int k) {
        int maxsofar = 0;
        int n = a.length;

        List<Integer> res = new ArrayList<>();
        int totalprofit = 0;

        int i = 0;
        while (i < n - 1) {
            int index1 = 0;
            int index2 = 0;


            while (i < n - 1) {
                if (a[i + 1] < a[i]) i++;
                else break;
            }
            if (i == n - 1) {
                for (int m = 0; m < res.size(); m = m + 2) {
                    System.out.println(res.get(m) + "," + res.get(m + 1));
                }
                if (res.size() == 0) System.out.println("No Profit");
                return;
            }

            index1 = i;

            while (i < n - 1) {
                if (a[i + 1] > a[i]) i++;
                else break;
            }
            index2 = i;
            if (index2 < n) {
                int profit = (a[index2] - (a[index1] + k));
                if (profit > 0) {
                    res.add(index1);
                    res.add(index2);
                }
            }
            i++;
        }

        for (int m = 0; m < res.size(); m = m + 2) {
            System.out.print("(" + res.get(m) + " " + res.get(m + 1) + ")" + " ");
        }
        if (res.size() == 0) System.out.println("No Profit");

  }





}
