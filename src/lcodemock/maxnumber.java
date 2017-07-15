package lcodemock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 * Created by predave on 7/4/17.
 * This task can be solved in O(N + M) time.

 First, it is obvious, that a greedy algorithm should be used, i.e. the first digit should be as big as possible and as left as possible in respective array.
 Let us divide both arrays into 10 buckets, where a bucket number i (0 <= i <= 9) should contain a list of all i's positions in the respective array.
 Algorithms performs K steps, each finds a max. possible digit for the current position. We should maintain 2 indices - one for the rightmost digit taken from the first array, and one for the second array. Initially these indices are both zero (or -1 depending on implementation). On each of K steps go through 10 buckets of both arrays in decreasing order of digit and try to find a position such that (number of remaining elements in the respective array after this index) + (number of remaining elements in the other array) is at least K-T, where T is a number of performed steps. When iterating through buckets, if we meet a position that is to the left of current rightmost index for the respective array, we can drop this position and never consider it again. Otherwise, if it is to the right of the rightmost position, we immediately discard it, put to the resulting array, and go to the next step immediately.
 It is easy to see, that each element will be considered only once: it will either be dropped, or put into result. Thus, the complexity has an order of total number of elements = O(N + M).
 [3,4,6,5]
 [9,1,2,5,8,3]
 5

 [2,5,6,4,4,0]
 [7,3,8,0,6,5,7,6,2]
 15

 Output:
 [7,3,8,2,5,6,4,4,0,0,6,5,7,6,2]
 Expected:
 [7,3,8,2,5,6,4,4,0,6,5,7,6,2,0]
 */
public class maxnumber {

    public static void main(String[] args){
        int[] a = {8,9};
        int[] b = {3,9};
        System.out.print(new maxnumber().getMaxNumber(a,b,3));
    }



    public  int[] getMaxNumber(int[] A, int[] B, int K) {
        int n = A.length;
        int m = B.length;
        int[] ans = new int[K];
        for (int i = Math.max(0, K - m); i <= K && i <= n; ++i) {
            int[] candidate = merge(maxArray(A, i), maxArray(B, K - i), K);
            if (greater(candidate, 0, ans, 0)) ans = candidate;
        }
        return ans;
    }

    private int[] merge(int[] nums1, int[] nums2, int k) {
        int[] ans = new int[k];
        for (int i = 0, j = 0, r = 0; r < k; ++r)
            ans[r] = greater(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
        return ans;
    }
    public boolean greater(int[] nums1, int i, int[] nums2, int j) {
        while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
            i++;
            j++;
        }
        return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
    }
    public int[] maxArray(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[k];
        for (int i = 0, j = 0; i < n; ++i) {
            while (n - i + j > k && j > 0 && ans[j - 1] < nums[i]) j--;
            if (j < k) ans[j++] = nums[i];
        }
        return ans;
    }


}
