package hr2;

import java.util.Scanner;

/**
 * Created by predave on 4/29/17.
 */
public class MaxPermutations2 {
    static long modulo = (long)(Math.pow(10.0,9.0) + 7);
    static int query(int n, int k){
        // Complete this function
        counter = 0;
        int[] arr = new int[n];
        if(n == 1){
            return 1;
        }
        if(k == 1){
            return (int)(fact(n) % modulo);
        }
        else if(k == n) {
            return 0;
        }
        else {
            for(int i = 0 ; i < n ; i++){
                arr[i] = i+1 ;
            }
            // k = findResult(arr , k);
            heapPermutation(arr , n , n, k);
        }
        return (int)(counter );
    }
    static void printArr(int a[], int n)
    {
        for (int i=0; i<n; i++)
            System.out.print(a[i] + " ");
        System.out.println();
    }


    static long counter = 0;
    //Generating permutation using Heap Algorithm
    static void heapPermutation(int a[], int size, int n, int k)
    {
        // if size becomes 1 then prints the obtained
        // permutation
        if (size == 1) {
            //printArr(a, n);
            //find if k surrounded by 1 incr increment counter
            if(isSurroundedByHigher(a, k)) {
                if( counter >  modulo) {
                    counter = (counter + 1) % (modulo);
                }
                else {
                    counter++;
                }
            }
        }

        for (int i=0; i<size; i++)
        {
            heapPermutation(a, size-1, n, k);

            // if size is odd, swap first and last
            // element
            if (size % 2 == 1)
            {
                int temp = a[0];
                a[0] = a[size-1];
                a[size-1] = temp;
            }

            // If size is even, swap ith and last
            // element
            else
            {
                int temp = a[i];
                a[i] = a[size-1];
                a[size-1] = temp;
            }
        }
    }

    private static boolean isSurroundedByHigher(int[] a, int k) {
        int count = 0;
        boolean increasing = a[0] < a[1];
        for(int i = 0 ; i < a.length ; i++){

            if(increasing && i< a.length -1 && a[i] < a[i+1]){
                count++;
                if(count >= k) return true;
            }
            else if(!increasing && i< a.length -1 && a[i] > a[i+1]) {
                count++;
                if(count >= k) return true;
            }
         /*   if(i == 0 && a.length >= 2){  //first elem
                if(a[i] < a[i+1]) {
                    count++;
                    if(count >= k) return true;
                }
            }
            else if(i == a.length-1 && a.length >= 2){ //last elem
                if(a[i] < a[i-1]) {
                    count++;
                    if(count >= k) return true;
                }
            }
            else if( a[i] < a[i+1] || a[i] < a[i-1]){
                count++;
                if(count >= k) return true;
            }
            */
        }
        return count >= k;
    }

    private static int findResult(int[] arr, int k) {
        return 0;
    }

    private static long fact(long val) {
        if(val == 1 ) return 1;
        return (val * fact(val-1)) % modulo;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            int n = in.nextInt();
            int k = in.nextInt();
            // Find the number of ways to arrange 'n' people such that at least 'k' of them will be happy
            // The return value must be modulo 10^9 + 7
            int result = query(n, k);
            System.out.println(result);
        }
    }
}
