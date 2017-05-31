package hr2;

import java.util.Scanner;

/**
 * Created by predave on 4/29/17.
 */
/*

The key was that no 2 unhappy people can stand next to each other. So if you have some number of unhappy people in a scenario of n-1 people, and put the new (n) number next of an unhappy, you will get a scenario with exactly 1 more happy (and the number of happy people won't be higher if put it elsewhere). You can now count number of minhappy in n people as an arbitrary n-1-permutation with at least k happy (n put anywhere) and the operation above on n-1 length with k-1 happy. So minhappy(n,k)=n*minhappy(n-1,k)+2*(n-k)*(minhappy(n-1,k-1)-minhappy(n-1,k)) (as number of exactly k-1 happy can be calculated as "atleast k-1 minus atleast k", and n-k stands for (n-1)-(k-1) which is number of unhappy people in this state). You can precalculate minhappy(i,j) for 1<=i<=3000, i>=j>=1 in short time.

To count the n-permutations with at least k happy, we want to create them from the suitable n-1-permutations. If you have an n-1-permutation already with at least k happy, you will get an n-permutation with at least k happy for sure, independently where you put the n itself in (the happyness won't be lost - at most gained - if you change the neighbour of an ID with a larger number caused by insertion). So n-perms with at least k happy contain these perms with n different insertion points. This is the n factor. As we know, that no 2 unhappy can be next to each other, insertion of n can be between two happy (in this case the number of happy won't change: the new n can't be happy, and the others were already happy), or between of a happy and an unhappy. In the latter case the number of happy will increase by one. That's why we consider the n-1-perms with k-1 happy. In this case there are - as mentioned - (n-1)-(k-1) unhappies, which makes (n-k). They all have a place before and after them, it makes the factor 2*(n-k).
 */
public class MaxPermutations {
    static long modulo = (long)(Math.pow(10.0,9.0) + 7);
    static int query(int n, int k){
        // Complete this function
        counter = 0;
        int[] arr = new int[n];
        if(n == 1){
            return 1;
        }
        if(k <= n/2){
            return (int)(fact(n) % modulo);
        }
        else if(n-k == 1){
            return (int)(Math.pow(2,k) % modulo);
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
        for(int i = 0 ; i < a.length ; i++){
            if(i == 0 && a.length >= 2){  //first elem
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
