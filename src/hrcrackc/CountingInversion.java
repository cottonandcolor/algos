package hrcrackc;

import java.util.Scanner;

/**
 * Created by predave on 5/13/17.
 */
public class CountingInversion {
    private static long invCount = 0;
    private static int[] temp;

    public static void countInversions(int[] a) {
        mergeSort(a, 0,  a.length -1);
    }

    private static void mergeSort(int[] a , int leftIndex, int rightIndex){
        if(leftIndex >= rightIndex){
            return;
        }
        int mid = (leftIndex + rightIndex) / 2;
        mergeSort(a, leftIndex, mid);
        mergeSort(a, mid + 1 , rightIndex);
        merge(a, leftIndex, rightIndex);
    }

    // 9 11 2 5  1234  4 3 2 1  3 4   1 2  2 4  1 3  2 3 1 4
    private static void merge(int[] a, int leftIndex, int rightIndex) {
        int lstart = leftIndex;
        int mid = (leftIndex + rightIndex) / 2;
        int rstart = mid + 1;
        int lend = mid;
        int rend = rightIndex;

        int index = leftIndex;
        int left = lstart;
        int right = rstart;

        while(left <= lend && right <= rend){
            if(a[left] <= a[right]){
                temp[index] = a[left];
                left++;
            }
            else {
                temp[index] = a[right];
                right++;
                invCount += (mid - left) + 1;
            }
            index++;
        }

        System.arraycopy(a, left, temp, index, (lend - left) + 1);
        System.arraycopy(a, right, temp, index, (rend - right) + 1);
        System.arraycopy(temp, leftIndex, a, leftIndex, (rightIndex - leftIndex) + 1);

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int n = in.nextInt();
            int arr[] = new int[n];
            temp = new int[n];
            for(int arr_i=0; arr_i < n; arr_i++){
                arr[arr_i] = in.nextInt();
            }
            countInversions(arr);
            System.out.println(invCount);
            invCount = 0;
        }
    }
}
