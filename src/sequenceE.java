import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by predave on 3/21/17.
 */
public class sequenceE {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int[] s = new int[m];
        Set<Integer> intset = new HashSet<Integer>();
        for(int s_i=0; s_i < m; s_i++){
            s[s_i] = in.nextInt();
            if(intset.contains(s[s_i])){
                System.out.println("NO");
                return;
            }
            else {
                intset.add(s[s_i]);
            }
        }

        System.out.println("YES");
        // Write Your Code Here
    }

}
