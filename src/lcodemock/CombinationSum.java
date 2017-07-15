package lcodemock;

import java.util.*;

/**
 * Created by predave on 7/11/17.
 */
public class CombinationSum {

    public static void main(String[] args) {
        CombinationSum c = new CombinationSum();
        c.combinationSum(new int[]{2, 3, 6, 7},7);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);

        solve(res, target, candidates, new ArrayList<Integer>(),0);

        return res;

    }

    private void solve(List<List<Integer>> sets, int T, int[] nums, List<Integer> temp, int start) {
        if(T == 0){
            sets.add(new ArrayList<Integer>(temp));

        }
        for(int i = start; i < nums.length && nums[i] <= T; i++){

                temp.add(nums[i]);
                solve(sets, T- nums[i], nums, temp,i);
                temp.remove(temp.size() -1);

        }


    }

}
