package lcode38;

import java.util.Arrays;

/**
 * Created by predave on 6/24/17.
 */
public class MaximumProduct3 {
    int maxProduct(int arr[], int n)
    {
        if (n < 3)
            return -1;

        Arrays.sort(arr);

        return Math.max(arr[0] * arr[1] * arr[n - 1],
                arr[n - 1] * arr[n - 2] * arr[n - 3]);
    }
    public static void main(String[] args){

    }
}
