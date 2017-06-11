package lcode35;

import java.util.Arrays;

/**
 * Created by predave on 6/10/17.
 */
public class ValidTriangleNumber {

    public int triangleNumber(int[] nums) {
        if(nums.length < 3) return 0;

        Arrays.sort(nums);

        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n-2; ++i)
        {
            if(nums[i] <= 0) continue;
            int k = i+2;
            for (int j = i+1; j < n; ++j)
            {

                while (k < n && nums[i] + nums[j] > nums[k]) {
                    ++k;
                }

                count += k - j - 1;
            }
        }
        return count;
    }

    public static void main(String[] args){
        int[] a = {0,0,0,0};
        ValidTriangleNumber vt = new ValidTriangleNumber();
        System.out.println(vt.triangleNumber(a));

    }
}
