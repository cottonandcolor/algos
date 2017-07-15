package lcodemock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public class maxnumber2 {

    public static void main(String[] args){
        int[] a = {8,9};
        int[] b = {3,9};
        System.out.print(new maxnumber2().getMaxNumber3(a,b,3));
    }
    private class Position{
        int t;
        int i;
        boolean used;
        public Position(int t, int i){
            this.t = t;
            this.i = i;
            used = false;
        }
    }
    public int[] getMaxNumber3(int[] A , int[] B , int K){
        List<Position>[] N = new ArrayList[10];
        for(int i =0; i < 10; i++) N[i] = new ArrayList<>();
        int Ai = -1;
        int Bi=-1;
        int[] res = new int[K];
        Arrays.fill(res,-1);
        int n = A.length;
        int m = B.length;

        if(n == m){
            for (int i = 0; i < n; i++) {
                if(A[i] == B[i]) {
                    if(i> 0 && A[i-1] > B[i-1]){
                        N[B[i]].add(new Position(2, i));
                        N[A[i]].add(new Position(1, i));
                    } else {
                        N[A[i]].add(new Position(1, i));
                        N[B[i]].add(new Position(2, i));
                    }
                } else {
                    N[A[i]].add(new Position(1, i));
                    N[B[i]].add(new Position(2, i));
                }
            }
        } else {
            for (int i = 0; i < n; i++) {
                N[A[i]].add(new Position(1, i));
            }
            for (int i = 0; i < m; i++) {
                N[B[i]].add(new Position(2, i));
            }
        }

        int index = 0;
        while(index <= K - 1 && res[K-1] == -1) {
            boolean found = false;
            for (int i = 9; i >= 0; i--) {
                if (!N[i].isEmpty() && index <= K - 1) {

                    for (Position p : N[i]) {
                        if ((p.t == 1 && p.i < Ai) || p.used) continue;
                        if ((p.t == 2 && p.i < Bi) || p.used) continue;
                        if (p.t == 1) {
                            int mdelta = Bi >=0 ? m - (Bi + 1) : m;
                            int rem = n - (p.i + 1)  +mdelta;
                            if(rem >= (K - (index + 1))) {
                                Ai = p.i;
                                if (index >= K) break;
                                res[index] = i;
                                index++;
                                p.used = true;
                                found = true;
                                break;
                            }
                        }
                        if (p.t == 2) {
                            int ndelta = Ai >=0 ? n - (Ai + 1) : n;
                            int rem = m - (p.i + 1)  + ndelta;
                            if(rem >= (K - (index + 1))) {
                                Bi = p.i;
                                if (index >= K) break;
                                res[index] = i;
                                index++;
                                p.used = true;
                                found = true;
                                break;
                            }
                        }

                    }
                }
                if(found) break;
            }
        }

        return res;
    }



    public  int[] getMaxNumber2(int[] A, int[] B, int K) {
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
