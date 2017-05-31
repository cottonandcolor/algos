/**
 * Created by predave on 4/23/17.
 */
public class PowersOfTwo {

    public static int findPowerOf2(int n){
        if(n < 1)
            return 0;
        if(n == 1) {
            System.out.println("1");
            return 1;
        }
        else {
            int prev = findPowerOf2(n / 2);
            int curr = prev*2;
            System.out.println(curr);
            return curr;
        }
    }
    public static void main(String[] args){
        //2  , 1 ,
        //4 ,   1,2
        findPowerOf2(4);
    }
}
